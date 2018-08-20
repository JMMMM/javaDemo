package face;

import java.util.HashMap;

public class MapDemo {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf(30).hashCode());
        HashMap<Person, String> temp = new HashMap<>();
        for (int i = 0; i <= 1000; i++) {
            Person p1 = new Person();
            temp.put(p1, p1.toString());
        }
    }
}

class Person {
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 16;
    }
}