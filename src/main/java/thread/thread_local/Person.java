package thread.thread_local;

public class Person {
    public byte[] name;

    public Person() {
        this.name = new byte[1024 * 1024];
    }

    @Override
    public void finalize() {
        System.out.println("person gc ed  ......");
    }
}
