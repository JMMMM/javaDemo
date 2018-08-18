package face;

import java.util.ArrayList;
import java.util.List;

public class DemoTest1 {
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        ls.add("Hello");
        ls.add("world");
        //正常
        for (int i = 0; i < 2; i++) {
            ls.add(ls.get(i).toUpperCase());
        }
        System.out.println(ls.size());

        //死循环
        for (int i = 0; i < ls.size(); i++) {
            ls.add(ls.get(i).toUpperCase());
        }

        //iterator 不允许add，throw Exception
        for (String str : ls) {
            ls.add(str.toUpperCase());
        }
        System.out.println(ls.size());
    }
}
