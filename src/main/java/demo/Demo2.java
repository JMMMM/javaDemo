package demo;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo2 {
    public static void main(String[] args) {
        int len = 16;
        for (int i = 0; i < 16; i++) {
            System.out.println(fullZero(Integer.toBinaryString(Integer.valueOf(i * 0x61c88647))));
        }
        System.out.println("===================================================");
        System.out.println(fullZero(Integer.toBinaryString(Integer.valueOf(len))));
        System.out.println("===================================================");
        for (int i = 0; i < 16; i++) {
            System.out.println(fullZero(Integer.toBinaryString((i*0x61c88647)&(len-1))));
            System.out.println((i*0x61c88647)&(len-1));
        }
        System.out.println("===================================================");

        int n = len;
        do {
            System.out.println(n);
        }while ( (n >>>= 1) != 0);
    }

    private static String fullZero(String binaryString) {
        int i = 32 - binaryString.length();
        String temp = "";
        for (int j = 0; j < i; j++) {
            temp += 0;
        }
        return temp + binaryString;
    }
}
