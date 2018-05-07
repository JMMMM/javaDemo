//package netty;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " Say: " + msg);
//        ctx.writeAndFlush("Received your message !\n");
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("RemoteAddress:" + ctx.channel().remoteAddress());
//        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostAddress() + " service!\n");
//        super.channelActive(ctx);
//    }
//}
