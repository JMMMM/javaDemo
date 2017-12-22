package nio_study;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

public class ChannelTest {
    public static void main(String[] args) throws IOException {
        FileInputStream aFile = null;
        try {
            aFile = new FileInputStream("C:\\Users\\Jimmy\\IdeaProject\\javaDemo\\src\\main\\java\\activeMQ\\Comsumer.java");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buff = ByteBuffer.allocate(9);
            int bytesRead = fileChannel.read(buff);
            byte[] bytes = new byte[buff.limit()];
            while (bytesRead != -1) {
                buff.flip();
                while (buff.hasRemaining()) {
                    buff.get(bytes);
                    System.out.println(new String(bytes));
                }
                buff.compact();
                bytesRead = fileChannel.read(buff);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            aFile.close();
        }

    }
}
