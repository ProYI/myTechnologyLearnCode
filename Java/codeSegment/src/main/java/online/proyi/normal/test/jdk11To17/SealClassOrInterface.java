package online.proyi.normal.test.jdk11To17;

/**
 * 密封类或接口
 *
 * 可以限制类的层次结构
 */
public class SealClassOrInterface {
    public static void main(String[] args) {

    }
}

// 限制只能被下属三个类继承，其他类不能继承
sealed class PersonDemo permits Teacher, Worker, Student {

}

// 子类可以被修饰为 final
final class Teacher extends PersonDemo {

}

// 子类可以被修饰为 non-sealed，此时 Worker类就成了普通类，可以继承它
non-sealed class Worker extends PersonDemo {

}

class ITWorker extends Worker {
}

//子类可以被修饰为 sealed
sealed class Student extends PersonDemo permits MiddleSchoolStudent {
}

final class MiddleSchoolStudent extends Student {
}

sealed interface BaseInterface permits Interface1, Interface2 {

}

// 子接口不能修饰为final，因为还需要其他类或接口实现或继承
non-sealed interface Interface1 extends BaseInterface {
}

sealed interface Interface2 extends BaseInterface permits Interface3 {

}

non-sealed interface Interface3 extends Interface2 {

}

// 抽象类实现 non-sealed修饰接口
abstract class AbstractClass implements Interface1 {

}

// 普通类实现 non-sealed修饰接口
class NormalClass implements Interface1 {

}

// 抽象类不可以实现 sealed修饰接口
//abstract class AbstractClass2 implements Interface2 {}