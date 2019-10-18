package demo;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        WeakReference wr = new WeakReference(obj);
        System.out.println(wr.get());
        obj = null;
//        Thread.sleep(5 * 1000);
        System.out.println(wr.get());
        HashMap map = new HashMap<String, Integer>();
        System.out.println(map.put("1", 1));
        System.out.println(map.put("1", 2));


        // 内容
        String value = "pay.weixin.xxx";

        // 匹配规则
        String reg = "\\.(.*?)\\.";
        Pattern pattern = Pattern.compile(reg);

        // 内容 与 匹配规则 的测试
        Matcher matcher = pattern.matcher(value);

        if( matcher.find() ){
            // 包含前后的两个字符
            System.out.println(matcher.group());
            // 不包含前后的两个字符
            System.out.println( matcher.group(1) );
        }else{
            System.out.println(" 没有匹配到内容....");
        }

    }
}
