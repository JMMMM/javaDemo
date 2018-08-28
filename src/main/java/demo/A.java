package demo;

public class A {
    protected String name;

    public A(String name) {
        this.name = name;
    }

    public String test() {
        return this.name;
    }

    public static void main(String[] args) {
        A a = new A("a");
        System.out.println(a.test());
        A b = new B("b");
        System.out.println(b.test());
    }
}

class B extends A {
    public B(String name) {
        super(name);
        this.name = name;
    }
}

class C extends A {
    public C(String name) {
        super(name);
        this.name = name;
    }
}