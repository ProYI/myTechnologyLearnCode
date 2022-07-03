/**
Rust 使用 impl 来定义方法


impl Rectangle {} 表示为 Rectangle 实现方法(impl 是实现 implementation 的缩写)
表明 impl 语句块中的一切都是跟 Rectangle 相关联

在 area 的签名中，使用 &self 替代 rectangle: &Rectangle
&self 其实是 self: &Self 的简写（注意大小写）


在一个 impl 块内，Self 指代被实现方法的结构体类型，self 指代此类型的实例
self 指代的是 Rectangle 结构体实例


self 依然有所有权的概念：
* self 表示 Rectangle 的所有权转移到该方法中，这种形式用的较少
* &self 表示该方法对 Rectangle 的不可变借用
* &mut self 表示可变借用


在 Rust 中，允许方法名跟结构体的字段名相同
一般来说，方法跟字段同名，往往适用于实现 getter 访问器

 */

struct Rectangle {
    width: u32,
    height: u32,
}

impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }

    // fn width(&self) -> bool {
    //     self.width > 0
    // }

    // 实现Getter
    fn width(&self) -> u32 {
        return self.width
    }

    // 带有多个参数的方法
    fn can_hold(&self, other: &Rectangle) -> bool {
        self.width > other.width && self.height > other.height
    }

}

pub fn method_demo() {
    let rect1 = Rectangle { width: 30, height: 50 };

    println!(
        "The area of the rectangle is {} square pixels.",
        rect1.area()
    );

    // if rect1.width() {
    //     println!("The rectangle has a nonzero width; it is {}", rect1.width);
    // }

    let rect1 = Rectangle { width: 30, height: 50 };
    let rect2 = Rectangle { width: 10, height: 40 };
    let rect3 = Rectangle { width: 60, height: 45 };

    println!("Can rect1 hold rect2? {}", rect1.can_hold(&rect2));
    println!("Can rect1 hold rect3? {}", rect1.can_hold(&rect3));
}

/**
 关联函数

定义在 impl 中且没有 self 的函数被称之为关联函数
因为它没有 self，不能用 f.read() 的形式调用，因此它是一个函数而不是方法，
它又在impl 中，与结构体紧密关联，因此称为关联函数

Rust 中有一个约定俗称的规则，使用 new 来作为构造器的名称
出于设计上的考虑，Rust 特地没有用 new 作为关键字

**---------因为是函数，所以不能用 . 的方式来调用，我们需要用 :: 来调用---------**


多个 impl 定义
Rust 允许为一个结构体定义多个 impl 块，目的是提供更多的灵活性和代码组织性

 */

impl Rectangle {
    fn new(w: u32, h: u32) -> Rectangle {
        Rectangle { width: w, height: h }
    }
}
pub fn method_demo2() {
    let r1 = Rectangle::new(50, 50);
    println!("r1 width : {}", r1.width)

}


/**
为枚举实现方法

枚举类型之所以强大，不仅仅在于它好用、可以同一化类型
还在于，可以像结构体一样，为枚举实现方法

 */


enum Message {
    Quit,
    Move { x: i32, y: i32 },
    Write(String),
    ChangeColor(i32, i32, i32),
}

impl Message {
    fn call(&self) {
        // 这里定义方法体
    }
}

pub fn method_demo3() {
    let m = Message::Write(String::from("hello"));
    m.call();
}

