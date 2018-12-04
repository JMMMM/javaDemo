package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 强引用的转换，这里是吧map从a引用，转移到list引用
 */
public class Demo1 {

    public static void add(int temp) {
        temp++;
    }

    public static void main(String[] args) {
        Map<String, String> a = new HashMap<>();
        a.put("1", "a");
        List<Map> b = new ArrayList<Map>();
        b.add(a);
        a = null;
        Map aa = b.get(0);
        System.out.println(aa.get("1"));
        //结论传递的是引用的副本
        //测试值传递
        int xx = 1;
        add(xx);
        System.out.print(xx);
    }
}

