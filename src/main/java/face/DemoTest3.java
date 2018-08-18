package face;

import java.util.ArrayList;
import java.util.List;

public class DemoTest3 {
    public static void main(String[] args) {
        List<String> ls = new ArrayList<String>();
        ls.add("1") ;
        ls.add("2") ;
        ls.add("3") ;
        ls.add("4") ;
        ls.add("5") ;
        for(int i = 0;i<ls.size();i++){
            System.out.println(ls.get(i));
            ls.remove(i);
        }
    }
}
