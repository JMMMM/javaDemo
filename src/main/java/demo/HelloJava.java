package demo;

import java.io.*;

public class HelloJava {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        write();
        read();
    }

    private static void write() throws IOException {
        FileOutputStream out = new FileOutputStream("bin.bin");
        ObjectOutputStream obj_out = new ObjectOutputStream(out);
        Person p = new Person("Tom", 23);
        obj_out.writeObject(p);
        obj_out.close();
    }

    private static void read() throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream("bin.bin");
        ObjectInputStream ins = new ObjectInputStream(in);
        Person2 p = (Person2) ins.readObject();
        System.out.println(p.getName());
        ins.close();
    }
}


class Person implements Serializable {

    private int age;
    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        Runtime.getRuntime().exec("/Applications/Calculator.app/Contents/MacOS/Calculator");
    }
}

class Person2 implements Serializable {
    private int age;
    private String name;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

