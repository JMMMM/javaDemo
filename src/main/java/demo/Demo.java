package demo;

import com.github.dapeng.core.SoaException;
import com.isuwang.soa.admin.StaffServiceClient;
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

    private static Object lock = new Object();

    //奇数
    static class OddNum implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i += 2) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    lock.notify();
                }
            }
        }
    }

    //偶数
    static class EvenNum implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 100; i += 2) {
                synchronized (lock) {
                    System.out.println(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new OddNum()).start();
        new Thread(new EvenNum()).start();
    }
}
