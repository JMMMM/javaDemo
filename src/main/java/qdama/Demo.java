package qdama;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/4/12
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/abc/Work/abc/javaDemo/src/main/resources/test.json");
        FileInputStream isr = new FileInputStream(file);
        byte[] b = new byte[2048000];
        StringBuffer sb = new StringBuffer();
        while (isr.read(b) != -1) {
            sb.append(new String(b));
        }
        JSONObject jsonObject = JSONObject.parseObject(sb.toString());
        JSONObject jsonObject1 = jsonObject.getJSONObject("body").getJSONObject("hits");
        JSONArray hits = jsonObject1.getJSONArray("hits");
        BigDecimal result = BigDecimal.ZERO;
        for (Iterator<Object> it = hits.stream().iterator(); it.hasNext(); ) {
            JSONObject a = (JSONObject) it.next();
            Long orderId = a.getLong("_id");
            Object rateFee = a.getJSONArray("sort").stream().findFirst().orElse(null);
            System.out.println(orderId + ":" + rateFee);

            if (rateFee instanceof Long) {
                result = result.add(new BigDecimal((Long) rateFee));
            } else if (rateFee instanceof Integer) {
                result = result.add(new BigDecimal((Integer) rateFee));
            } else {
                result = result.add((BigDecimal) rateFee);
            }
        }
        System.out.println("结果:" + result.toPlainString());

    }
}
