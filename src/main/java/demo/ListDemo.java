package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * stream之后filter和foreach会自动组合；
 */
public class ListDemo {
    private static int i = 0;

    public static void main(String[] args) {
        Person example = new Person(5, "name5");
        List<Person> persons = new ArrayList<>(10);
        for (int i = 0; i < 20; i++) {
            Person p = new Person(i, "name" + i);
            persons.add(p);
        }

        persons.stream().filter((p) -> p.equals(example)).forEach((p) -> System.out.println(p));
        System.out.println(i);
    }

    static class Person {
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            i++;
            if (obj instanceof Person) {
                Person temp = (Person) obj;
                return temp.age == this.age && temp.name.equals(this.name);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.age + this.name.hashCode();
        }

        @Override
        public String toString() {
            i++;
            return "Person age : " + this.age + ",name: " + this.name;
        }
    }
}
