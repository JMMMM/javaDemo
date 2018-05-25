package demo;

import com.github.dapeng.core.SoaException;
import com.isuwang.soa.company.ComponentOnBinlogServiceClient;
import com.isuwang.soa.crm.company.enums.CrmCompanyType;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.StringUtils;
import sun.misc.Unsafe;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Demo {
    public static void main(String[] args) throws IllegalAccessException, IOException, NoSuchFieldException {
        //Unsafe unsafe = Unsafe.getUnsafe();
        Field f = Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        File file = new File("shm.data");

        RandomAccessFile access = new RandomAccessFile(file, "rw");

        MappedByteBuffer buffer = access.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        Field address = Buffer.class.getDeclaredField("address");
        address.setAccessible(true);

        long addr = (Long) address.get(buffer);

        buffer.putInt(0x31_32_33_34);

        System.out.println("buffer = " + buffer + " addr = " + addr);

        int it = unsafe.getInt(addr);
        System.out.println("it = " + it); // 0x34333231

        boolean set = unsafe.compareAndSwapInt(null, addr, 0x34333231, 0x35363738);

        System.out.println("set = " + set);
        System.out.println("it = " + unsafe.getInt(addr)); // 0x35363738
    }
}
