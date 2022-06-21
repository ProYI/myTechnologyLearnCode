package online.proyi.normal.test.jdk11To17;

/**
 * instanceof 模式匹配
 * 模式匹配通过对instanceof运算符进行模式匹配来增强
 * 主要目的是为了让创建对象更简单、简洁和高效，并且可读性更强、提高安全性
 */
public class NewInstanceOf {
    public static void main(String[] args) {
        Animal animal1 = new Dog(10);

        // 旧的instanceof
        if (animal1 instanceof Dog) {
            Dog dogNow = (Dog) animal1;
            System.out.println(dogNow.say());
        }

        // 新的instanceof 避免了显示的强制转换
        if (animal1 instanceof Dog testDog) {
            System.out.println(testDog.say());
        }

        // 匹配的testDog的作用返回 只在if内，且变量作用延长在&&情况下才会生效，"||"情况下不会生效
        // 未匹配成功会短路，提高了代码的安全性，健壮性
        if (animal1 instanceof Dog testDog && testDog.getAge()>5) {
            System.out.println(testDog.say());
        }

//        if (animal1 instanceof Dog testDog || tesDog) {}
    }
}

abstract class Animal {
    public String say() {
        return "这是一个动物的叫声";
    }
}

class Dog extends Animal {
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Dog(Integer age) {
        this.age = age;
    }

    @Override
    public String say() {
        return "汪汪汪";
    }
}