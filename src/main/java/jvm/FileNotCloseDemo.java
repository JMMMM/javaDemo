package jvm;

import kafka.utils.timer.TimingWheel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileNotCloseDemo {
    public static void main(String[] args) throws IOException {

        File tempFile = File.createTempFile("test", "ttt");
        FileInputStream fi = new FileInputStream(tempFile);
        System.in.read();
    }
}
