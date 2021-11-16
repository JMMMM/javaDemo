package demo;

import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/10/25
 */
public class ClassMapDemo {
    public static void main(String[] args) {
        Map<Class, String> map = new HashMap<>();
        map.put(HttpRequestMethodNotSupportedException.class, "HttpRequestMethodNotSupportedException");
        map.put(Object.class, "object");
        System.out.println(map.get(Object.class));
    }
}
