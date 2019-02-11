package demo;

import java.time.LocalDateTime;

public class Demo4 {
    static Demo4Test test = new Demo4Test();

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().isAfter(LocalDateTime.of(2019, 2, 14, 23, 59, 59)));

    }
}

class Demo4Test {
    public Demo4Test() {
        System.out.println("demo4Test init ");
    }
}