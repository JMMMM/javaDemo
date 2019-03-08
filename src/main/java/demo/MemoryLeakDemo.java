package demo;

import java.util.HashMap;
import java.util.Map;

/**
 * OOM 这里没有重写equals方法，
 * 会导致永远都不是同一个对象
 */
public class MemoryLeakDemo {
    static class Key {
        Integer id;

        public Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        while (true) {
            for (int i = 0; i <= 10000; i++) {
                if (!map.containsKey(new Key(i)))
                    map.put(new Key(i), "Number:" + i);
            }
        }
    }
}
