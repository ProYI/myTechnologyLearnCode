/**
深入理解特征

一些不常用到但是该了解的特性
 */

/**
 关联类型

在特征定义的语句块中，申明一个自定义类型，这样就可以在特征的方法签名中使用该类型
 */

// 标准库中的迭代器特征 Iterator，它有一个 Item 关联类型，用于替代遍历的值的类型
pub trait Iterator {
    type Item;

    fn next(&mut self) -> Option<Self::Item>;
}

/**
默认泛型类型参数

当使用泛型类型参数时，可以为其指定一个默认的具体类型


默认类型参数主要用于两个方面：
1. 减少实现的样板代码
2. 扩展类型但是无需大幅修改现有的代码
 */
// 标准库的 std::ops::add
trait Add<RHS=Self> {
    type Output;
    fn add(self, rhs:RHS) -> Self::Output;
}

/**
 调用同名方法

1.优先调用类型上的方法


 */
trait Pilot {
    fn fly(&self);
}

trait Wizard {
    fn fly(&self);
}

struct Human;

impl Pilot for Human {
    fn fly(&self) {
        println!("This is your captain speaking.");
    }
}

impl Wizard for Human {
    fn fly(&self) {
        println!("Up!");
    }
}

impl Human {
    fn fly(&self) {
        println!("*waving arms furiously*");
    }
}

pub fn trait_same_named_demo() {
    let person = Human;
    // 默认 优先调用类型上的方法
    person.fly();

    // 调用特征上的方法
    Pilot::fly(&person);
    Wizard::fly(&person);

}
