package demo;

import java.time.LocalDateTime;

public class Demo4 {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().isAfter(LocalDateTime.of(2019, 2, 14, 23, 59, 59)));

    }
}
