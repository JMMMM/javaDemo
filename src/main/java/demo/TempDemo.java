package demo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.StringUtils;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TempDemo {
    public static void main(String[] args) throws IOException, SQLException {
        List<Warehouse> wss = loadExcel("C:\\Users\\Jimmy\\Downloads\\仓库名20180822.xls");
        System.out.println(wss.size());
    }

    private static List<Warehouse> loadExcel(String path) throws IOException, SQLException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
        HSSFSheet sheet = workbook.getSheet("总表");
        MysqlConnectionPoolDataSource pool = getDataSource();
        PreparedStatement ps = pool.getPooledConnection().getConnection().prepareStatement("select code from sys_areas where name = ?");
        List<Warehouse> warehouses = new ArrayList<>(2000);
        for (int i = 1; i < 2000; i++) {
            warehouses.add(loadObj(sheet.getRow(i), ps));
        }
        return warehouses;
    }

    private static Warehouse loadObj(HSSFRow row, PreparedStatement ps) throws SQLException {
        Warehouse warehouse = new Warehouse();
        warehouse.id = Integer.valueOf(row.getCell(0).getStringCellValue());
        warehouse.name = row.getCell(1).getStringCellValue();
        warehouse.telphone = row.getCell(2).getStringCellValue();
        warehouse.attendant = row.getCell(3).getStringCellValue();
        String sheng = row.getCell(4).getStringCellValue();
        String shi = row.getCell(5).getStringCellValue();
        String qu = row.getCell(6).getStringCellValue();
        String jiedao = row.getCell(7).getStringCellValue();
        String[] temp = {jiedao, qu, shi, sheng};
        String rs = Arrays.asList(temp).stream().filter((str)->StringUtils.isNullOrEmpty(str))
        ps.setString(1, StringUtils.isNullOrEmpty(jiedao) ? qu : jiedao);
        ResultSet rs = ps.executeQuery();
        rs.next();
        warehouse.areaCode = rs.getString(1);
        warehouse.address = row.getCell(2).getStringCellValue();
        String disable = row.getCell(2).getStringCellValue();
        warehouse.disable = "启用" == disable ? 0 : 1;
        return warehouse;
    }

    private static MysqlConnectionPoolDataSource getDataSource() {
        MysqlConnectionPoolDataSource pool = new MysqlConnectionPoolDataSource();
        pool.setURL("jdbc:mysql://192.168.99.100:3306/productdb?useUnicode=true&characterEncoding=utf8");
        pool.setUser("root");
        pool.setPassword("root");
        return pool;
    }

}

class Warehouse {
    int id;
    String name;
    String areaCode;
    String address;
    String attendant;
    String telphone;
    int disable;


}
