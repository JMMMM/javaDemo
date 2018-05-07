//package netty;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//
//import java.nio.charset.Charset;
//
//public class ByteBufDemo {
//    public static void main(String[] args) {
//        Charset utf8 = Charset.forName("UTF-8");
//        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
//        ByteBuf sliced = buf.slice(0, 14);
//        System.out.println(sliced.toString(utf8));
//        System.out.println(buf.toString(utf8));
//        System.out.println(buf.readSlice(14).toString(utf8));
//        System.out.println(buf.toString(utf8));
//        System.out.println(buf.slice());
//        buf.setByte(0, (byte) 'J');
//        buf = Unpooled.copiedBuffer("Netty in Action rocks!!", utf8);
//        System.out.println(buf.isWritable());
//    }
//}
