package demo;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<SameHash, Integer> map = new HashMap<>();
        map.put(new SameHash(1), 1);
        map.put(new SameHash(2), 2);
        map.put(new SameHash(3), 3);
        map.put(new SameHash(4), 4);
        map.put(new SameHash(5), 5);
        map.put(new SameHash(6), 6);
        map.put(new SameHash(7), 7);
        map.put(new SameHash(8), 8);
        map.put(new SameHash(9), 9);
        map.put(new SameHash(10), 10);
        map.put(new SameHash(11), 11);
        System.out.println(map.get("数学"));
        System.out.println(map.get("化学"));
        System.out.println(map.get("hello"));
    }
}

class SameHash {
    private int id;

    public SameHash(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return 2;
    }
}