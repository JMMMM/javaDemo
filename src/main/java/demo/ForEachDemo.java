package demo;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForEachDemo {
    public static void main(String[] args) {
        // 使用ImmutableList初始化一个List
// 使用双括弧语法（double-brace syntax）建立并初始化一个List
        List<String> userNames = new ArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};
//        System.out.println("使用for循环遍历List");
//        for (int i = 0; i < userNames.size(); i++) {
//            System.out.println(userNames.get(i));
//        }
//
//        System.out.println("使用foreach遍历List");
//        for (String userName : userNames) {
//            System.out.println(userName);
//        }
//
//        for (int i = 0; i < userNames.size(); i++) {
//            if (userNames.get(i).equals("Hollis")) {
//                userNames.remove(i);
//            }
//        }
//        //Exception
//        for (String userName : userNames) {
//            if (userName.equals("hollis")) {
//                userNames.remove(userName);
//            }
//        }
//        System.out.println(userNames);
//

        new Thread(() -> {
            Iterator<String> iterator = userNames.iterator();
            do {
                System.out.println(iterator.next());
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (iterator.hasNext());
        }).start();

        new Thread(() -> {
            userNames.remove("H");
        }).start();
    }
}
