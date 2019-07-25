package demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * demo
 * 通过反射获得私有构造器，构造目标对象。
 * @author jimmy
 * @date 2019-07-25
 */
public class ReflexDemo {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<FuckDemo> fuckDemoClass = FuckDemo.class;
        Constructor[] constructors = fuckDemoClass.getDeclaredConstructors();
        Constructor constructors1 = constructors[0];
        constructors1.setAccessible(true);
        FuckDemo fuckDemo = (FuckDemo) constructors1.newInstance();
        System.out.println(fuckDemo);
    }
}


class FuckDemo {
    private FuckDemo() {
        System.out.println("私有构造方法");
    }
}
