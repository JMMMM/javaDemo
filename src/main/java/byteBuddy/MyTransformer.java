package byteBuddy;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * byteBuddy
 *
 * classFileTransformer
 * 它是class文件转换器接口，这个接口有且仅有一个方法。
 * 注意：transform方法会有一个返回值，类型是byte[],
 * 表示转换后的字节码，但是如果返回为null，则表示不进行字节码转换处理，千万不要当作是把原先类的字节码清空。
 * @author jimmy
 * @date 2019-06-03
 */
public class MyTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("加载的classLoader+" + loader.getClass());
        System.out.println("模拟aop织入所需的功能，打印当前类名：" + className);
        return null;
    }
}
