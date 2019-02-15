package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * filter和foreach同时进行时，filter和foreach交替执行，只有当filter为true时，才回去执行foreach
 */
public class ListDemo {
    private static int j = 0;

    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(0, 1, 2, 3, 4);

        ls.stream().filter((t) -> j++ == t).forEach((t) -> j++);
        //6
        System.out.println(j);

        j = 0;
        ls.stream().filter((t) -> {
            j++;
            return true;
        }).forEach((t) -> j++);
        //10
        System.out.println(j);

        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l1.add(5);
//        for (Integer t : l1) {
//            l1.remove(t);
//        }

        Integer[] intArray = new Integer[]{6,6,6,6,6,6,6,6};
        l1.toArray(intArray);
        System.out.println(Arrays.toString(intArray));
    }

}
