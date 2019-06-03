package byteBuddy;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * byteBuddy
 *
 * @author jimmy
 * @date 2019-06-03
 */
public class Myagent {
    /**
     * 注意，此处的方法名premain不是随意起的，代理类必须按照下面方法进行定义
     *
     * @param args            这个参数不要小看，-javaagent启动参数的jarpath的值会通过这个参数传递进来
     * @param instrumentation 代表JVM内部组建的实例，用于注册ClassFileTransformer
     */
    public static void premain(String args, Instrumentation instrumentation) {
        ClassFileTransformer transformer = new MyTransformer();
        instrumentation.addTransformer(transformer);
    }
}
