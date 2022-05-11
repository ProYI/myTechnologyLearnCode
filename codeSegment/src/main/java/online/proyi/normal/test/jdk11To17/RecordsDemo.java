package online.proyi.normal.test.jdk11To17;

/**
 * record 类
 * 作为不可变数据类型的封装持有者
 * 一种受限形式的类型，主要用于存储、保存数据
 *
 * 反编译
 * public final class Person extends java.lang.Record
 */
public class RecordsDemo {
    public static void main(String[] args) {
        Person person = new Person("张三", 15);
        System.out.println(person.name() + " : " + person.age());
        System.out.println(Person.LIVE_ADDRESS);
    }
}

record Person(String name, Integer age) {
    public static String LIVE_ADDRESS = "地球";
}