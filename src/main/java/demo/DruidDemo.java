package demo;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public class DruidDemo {



    private DataSource createDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://192.168.4.196:3306/admindb?useUnicode=true&characterEncoding=utf8");
        return dataSource;
    }
}
