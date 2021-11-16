package qdama;


import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/6/16
 */
public class Demo1 {
    public static void main(String[] args) {
//        RSACryptoServiceProvider rsaCryptoServiceProvider
        StringBuffer sb = new StringBuffer();
        List<Integer> ls = Lists.newArrayList(1, 2, 3);
        ls.stream().filter(i -> sb.append("a") != null).forEach(i -> sb.append("b"));
        System.out.println(sb);

        System.out.println((int) 'A');
        for (char c : "ESAB".toCharArray()) {
            System.out.println((int) c);
        }
    }
}
