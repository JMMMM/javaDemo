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
        temp.listIterator().add(2);
        Iterator iterator = temp.listIterator();
        iterator.next();
        iterator.remove();
        System.out.println(temp.size());
    }
}
