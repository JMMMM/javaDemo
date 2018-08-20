package face;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            temp.add(i);
        }
        temp.forEach((a)-> System.out.println(a));
        System.out.println(temp.size());
    }
}
