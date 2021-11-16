package demo;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/10/25
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        new ExceptionHello().throwException();
    }
}

class ExceptionHello {
    public void throwException() {
        throw new BusinessException();
    }
}

class BusinessException extends RuntimeException {

}