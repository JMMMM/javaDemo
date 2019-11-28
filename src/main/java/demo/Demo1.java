package demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import javafx.util.Pair;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 强引用的转换，这里是吧map从a引用，转移到list引用
 */
public class Demo1 {
    private DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("zhuizhi");
        dataSource.setPassword("guGMmWVKkJ");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.111.105.14:3306/product_db");
        return dataSource;
    }

    public static void main(String[] args) throws IOException, SQLException {
        Demo1 demo1 = new Demo1();
        DataSource dataSource = demo1.createDataSource();
        Connection conn = dataSource.getConnection();
        Map<String, Pair<BigDecimal, BigDecimal>> 已知损耗 = demo1.knowJson();
        Map<String, com.zhuizhi.sc.center.dto.receive.ReceiveSkuDto> 验收数量 = demo1.purchaseJson();
        Map<String, com.zhuizhi.order.search.center.dto.OrderItemSettleSkuCodeDto> 销售数量 = demo1.salesJson().stream().collect(Collectors.toMap(com.zhuizhi.order.search.center.dto.OrderItemSettleSkuCodeDto::getSkuCode, Function.identity()));
        Map<String, Pair<BigDecimal, BigDecimal>> 未知损耗 = demo1.unknownJson();
        File file = new File("/Users/jimmy/Desktop/对账.xls");
        FileOutputStream os = new FileOutputStream(file);
        ExcelWriter writer = new ExcelWriter(os, ExcelTypeEnum.XLS);
        List<String> skucodes = new ArrayList<>();
        skucodes.addAll(已知损耗.keySet());
        skucodes.addAll(验收数量.keySet());
        skucodes.addAll(销售数量.keySet());
        skucodes = skucodes.stream().distinct().sorted().collect(Collectors.toList());
        String skuSql = skucodes.toString().replace("[", "(").replace("]", ")");
        PreparedStatement ps = conn.prepareStatement("select * from t_sku where sku_code in " + skuSql);
        ResultSet resultSet = ps.executeQuery();
        Map<String, Map<String, String>> skuResult = Maps.newHashMap();
        List<String> categoryId = Lists.newArrayList();
        while (resultSet.next()) {
            Map<String, String> row = Maps.newHashMap();
            String skuCode = resultSet.getString("sku_code");
            row.put("category_id", resultSet.getString("category_id"));
            row.put("sku_name", resultSet.getString("sku_name"));
            categoryId.add(resultSet.getString("category_id"));
            categoryId.add(resultSet.getString("category_id").substring(0, 2));
            categoryId.add(resultSet.getString("category_id").substring(0, 3));
            skuResult.put(skuCode, row);
        }
        Map<String, String> categoryName = Maps.newHashMap();
        String categorySql = categoryId.stream().distinct().collect(Collectors.toList()).toString().replace("[", "(").replace("]", ")");
        PreparedStatement ps2 = conn.prepareStatement("select * from t_category where category_id in " + categorySql);
        ResultSet resultSet2 = ps2.executeQuery();
        while (resultSet2.next()) {
            String id = resultSet2.getString("category_id");
            String name = resultSet2.getString("category_name");
            categoryName.put(id, name);
        }
        List<List<String>> models = new ArrayList<>();
        models.add(Lists.newArrayList("sku", "sku名", "分类1", "分类2", "分类3", "验收数量", "验收金额", "销售数量", "销售金额", "已知损耗数量", "已知损耗额", "未知损耗"));
        for (String skucode : skucodes) {
            List<String> model = new ArrayList<>();
            model.add(skucode);
            Map<String, String> skuMap = skuResult.get(skucode);
            model.add(skuMap.get("sku_name"));
            String category = skuMap.get("category_id");
            model.add(categoryName.get(category.substring(0, 2)));
            model.add(categoryName.get(category.substring(0, 3)));
            model.add(categoryName.get(category));
            Optional<com.zhuizhi.sc.center.dto.receive.ReceiveSkuDto> receiveInfo = Optional.ofNullable(验收数量.get(skucode));
            model.add(receiveInfo.map(com.zhuizhi.sc.center.dto.receive.ReceiveSkuDto::getReceiveQty).orElse(BigDecimal.ZERO).toPlainString());
            model.add(receiveInfo.map(com.zhuizhi.sc.center.dto.receive.ReceiveSkuDto::getReceivePrice).orElse(BigDecimal.ZERO).toPlainString());
            Optional<com.zhuizhi.order.search.center.dto.OrderItemSettleSkuCodeDto> salesInfo = Optional.ofNullable(销售数量.get(skucode));
            model.add(salesInfo.map(com.zhuizhi.order.search.center.dto.OrderItemSettleSkuCodeDto::getTotalWeight).orElse(BigDecimal.ZERO).toPlainString());
            model.add(salesInfo.map(com.zhuizhi.order.search.center.dto.OrderItemSettleSkuCodeDto::getTotalPspAmt).orElse(BigDecimal.ZERO).toPlainString());
            Optional<Pair<BigDecimal, BigDecimal>> knowInfo = Optional.ofNullable(已知损耗.get(skucode));
            model.add(knowInfo.map(Pair::getKey).orElse(BigDecimal.ZERO).toPlainString());
            model.add(knowInfo.map(Pair::getValue).orElse(BigDecimal.ZERO).toPlainString());
            Optional<Pair<BigDecimal, BigDecimal>> unknowInfo = Optional.ofNullable(未知损耗.get(skucode));
            model.add(unknowInfo.map(Pair::getValue).orElse(BigDecimal.ZERO).toPlainString());
            models.add(model);
        }

        writer.write0(models, new Sheet(0));
        writer.finish();
        conn.close();
    }


    private Map<String, Pair<BigDecimal, BigDecimal>> knowJson() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("knowWastage.json");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String result = br.readLine();
        br.close();
        Map<String, Pair<BigDecimal, BigDecimal>> knowJson = new Gson().fromJson(result, new TypeToken<Map<String, Pair<BigDecimal, BigDecimal>>>() {
        }.getType());
        if(CollectionUtils.isEmpty(knowJson)){
            knowJson = Maps.newHashMap();
        }
        return knowJson;
    }


    private Map<String, com.zhuizhi.sc.center.dto.receive.ReceiveSkuDto> purchaseJson() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("purchase.json");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String result = br.readLine();
        br.close();
        Map<String, com.zhuizhi.sc.center.dto.receive.ReceiveSkuDto> purchase = new Gson().fromJson(result, new TypeToken<Map<String, com.zhuizhi.sc.center.dto.receive.ReceiveSkuDto>>() {
        }.getType());
        br.close();
        return purchase;
    }

    private List<com.zhuizhi.order.search.center.dto.OrderItemSettleSkuCodeDto> salesJson() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("sales.json");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String result = br.readLine();
        br.close();
        List<com.zhuizhi.order.search.center.dto.OrderItemSettleSkuCodeDto> sales = new Gson().fromJson(result, new TypeToken<List<com.zhuizhi.order.search.center.dto.OrderItemSettleSkuCodeDto>>() {
        }.getType());
        br.close();
        return sales;
    }

    private Map<String, Pair<BigDecimal, BigDecimal>> unknownJson() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("unknow.json");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String result = br.readLine();
        br.close();
        Map<String, Pair<BigDecimal, BigDecimal>> unknownJson = new Gson().fromJson(result, new TypeToken<Map<String, Pair<BigDecimal, BigDecimal>>>() {
        }.getType());
        return unknownJson;
    }
}

