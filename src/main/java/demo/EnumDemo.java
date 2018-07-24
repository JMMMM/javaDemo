package demo;

enum DataSourceType{
    MASTER,SLAVE
}

public class EnumDemo {
    public static void main(String[] args) {
        System.out.println(DataSourceType.MASTER.name());
    }
}
