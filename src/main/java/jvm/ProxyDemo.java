package jvm;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Person teacher = new Teacher();
        InvocationHandler handler = new DynamicProxy(teacher);
        Person subject = (Person) Proxy.newProxyInstance(handler.getClass().getClassLoader(), teacher.getClass().getInterfaces(), handler);
        System.out.println(subject.getClass().getName());
        System.out.println(subject.speck());
    }
}


interface Person {
    String speck();
}

class Teacher implements Person {

    @Override
    public String speck() {
        return "I'm Teacher";
    }
}

class DynamicProxy implements InvocationHandler {
    Person person;

    public DynamicProxy(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy start....");
        System.out.println("proxy method..." + person.speck());
        System.out.println("proxy method ..." + method);
        System.out.println("args ... " + args);
        return null;
    }
}

