package jvm;
//jvm:-verbose:gc

/**
 * jvm:-verbose:gc
 * 当没有 int a = 0时
 * System.gc执行后，堆内存并没有得到释放，
 * 但当 int a =0被执行时，
 * System.gc执行后，堆内存得到了释放。
 * 这个是由于局部变量表好存储这placeholder的引用，在赋值之后，没有任何堆局部变量比哦的读写操作，
 * plaeholder原本所占用的slot还没有被其他变量所复用。
 */
public class PlaceHolderDemo {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
//        int a = 0 ;
        System.gc();
    }
}
