package demo;

public class ThreadlocalDemo {

    static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            Integer a = 100;
            threadLocal.set(a);
            a = null;
            System.gc();
            System.out.println(threadLocal.get());
        }).start();

        int fee = 200;
        //彩蛋：这个东西是goto语句的那个名字
        saveDefault:save(fee);
    }

    static void saveDefault(){
        System.out.println("saveDefault");
    }
    static void save(int fee){
        System.out.println("save");
    }
}
