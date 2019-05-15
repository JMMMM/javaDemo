package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetCode
 *
 * @author jimmy
 * @date 2019-05-14
 */
public class Demo6 {
    public String convert(String s, int numRows) {
        char[] chars = s.toCharArray();
        List<List<Character>> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            lists.add(new ArrayList<>());
        }

        int line = 0;
        int target = 0;
        boolean flag = true;
        for (int i = 0; i < chars.length; i++) {
            List<Character> characters = lists.get(line);
            if (characters == null) {
                characters = new ArrayList<>();
            }
            if (flag) {
                characters.add(chars[i]);
            } else {
                if (line == target) {
                    characters.add(chars[i]);
                    target--;
                    line = 0;
                    if (target > -1) {
                        continue;
                    } else {
                        target = 0;
                        flag = true;
                    }
                } else {
                    i--;
                }
            }


            if (line >= numRows - 1 && numRows-1>0) {
                flag = false;
                target = line - 1;
                line = 0;
                continue;
            } else {
                if (target == 0 && !flag) {
                    flag = true;
                    continue;
                }
                line++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lists.size(); i++) {
            lists.get(i).stream().forEach((c) -> sb.append(c));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "AB";
        int numRows = 1;
        String rs = new Demo6().convert(s, numRows);
        System.out.println(rs);
    }

}
