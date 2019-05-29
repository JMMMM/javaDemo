package byteBuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

/**
 * byteBuddy
 *
 * @author jimmy
 * @date 2019-05-28
 */
public class MakeObject {
    public static void main(String[] args) {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy().subclass(Object.class).name("example.Type").make();

    }
}
