package demo;

import java.util.Random;

/**
 * 看守打算和 A 、 B 两名囚犯做一个游戏。首先，看守从一副牌中取出大小王，将剩余的 52 张牌洗好，
 * 并在桌子上从左至右地把它们摆成一排，每张牌都是正面朝上。然后，看守让囚犯 A 来到桌前，
 * 允许囚犯 A 观察牌面，并交换其中两张牌的位置。
 * 接着，看守将囚犯 A 关回牢房，把所有牌全都翻到背面朝上（但位置不变），
 * 让囚犯 B 来到桌前。看守随便报出一张牌的花色和点数（比如“梅花 3”），
 * 要求囚犯 B 找出这张牌。囚犯 B 每次可以翻开任意一张尚未翻开的牌，
 * 但一共只有 26 次机会。如果囚犯 B 在这 26 次机会之内找到了看守想要的牌，
 * 则两名囚犯赢得游戏，无罪释放；如果囚犯 B 翻开了 26 张牌之后，还没找到看守想要的牌，
 * 则两名囚犯输掉游戏，立即死刑。在整个游戏开始之前，两名囚犯可以商量一个策略；游戏开始后，
 * 两人就不能有任何其他形式的交流。果不其然，这又是一个关满了数学天才的监狱。两名囚犯碰头后，
 * 很快就商量出了一种必胜的策略。这种必胜的策略是什么？
 */
public class TestDemo {
    public static void main(String[] args) {
        int[] rs = random(init());
        for (int i = 0; i < 52; i++) {
            System.out.print((i+1)+":"+rs[i] + ",");
        }
        int temp = 0;
        temp = rs[4];
        rs[4] = rs[48];
        rs[48]=temp;

        int[][] cir = new int[52][52];
        int index = 0;
        System.out.println("\n");

        for (int i = 0; i < 52; i++) {
            int now_index = i;
            int sec_index = 0;
            if (rs[i] == 0) continue;
            int[] sec_cir = cir[index++];
            while (true) {
                int now = rs[now_index];
                sec_cir[sec_index++] = now;
                rs[i]=0;
                now_index = now-1;
                if(now_index>=52||rs[now_index]==0) break;
            }
        }

        for (int i = 0; i < cir.length; i++) {
            int lengt = 0;

            if (cir[i] == null) break;
            else {
                for (int j = 0; j < cir[i].length; j++) {
                    if (cir[i][j] == 0) break;
                    System.out.print(cir[i][j]+",");
                    lengt++;
                }
                System.out.println("长度:"+lengt);
            }
        }
    }

    public static int[] init() {
        int[] rs = new int[52];
        for (int i = 1; i <= 52; i++) {
            rs[i - 1] = i;
        }
        return rs;
    }


    public static int[] random(int[] org) {
        int[] rs = new int[52];
        Random r = new Random(52);
        int index = 0;
        while (true) {
            int now = r.nextInt(52);
            if (rs[now] == 0) {
                rs[now] = org[index++];
            } else {
                if (index == org.length) break;
            }

        }
        return rs;
    }
}
