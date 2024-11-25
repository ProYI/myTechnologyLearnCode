/**
函数通过返回 impl Summary, 返回值类型并不支持多种不同的类型返回
为了解决此问题，Rust 引入了一个概念 —— 特征对象

特征对象指向实现了 Draw 特征的类型的实例,这种映射关系是存储在一张表中，可以在运行时通过特征对象找到具体调用的类型方法
可以通过 & 引用或者 Box<T> 智能指针的方式来创建特征对象
 */

trait Draw {
    fn draw(&self) -> String;
}

impl Draw for u8 {
    fn draw(&self) -> String {
        format!("u8: {}", *self)
    }
}

impl Draw for f64 {
    fn draw(&self) -> String {
        format!("f64: {}", *self)
    }
}

// 若 T 实现了 Draw 特征， 则调用该函数时传入的 Box<T> 可以被隐式转换成函数参数签名中的 Box<dyn Draw>
fn draw1(x: Box<dyn Draw>) {
    // 由于实现了 Deref 特征，Box 智能指针会自动解引用为它所包裹的值，然后调用该值对应的类型上定义的 `draw` 方法
    x.draw();
}

fn draw2(x: &dyn Draw) {
    x.draw();
}

pub fn trait_object_demo1() {
    let x = 1.1f64;
    let y = 8u8;

    // x 和 y 的类型 T 都实现了 `Draw` 特征，因为 Box<T> 可以在函数调用时隐式地被转换为特征对象 Box<dyn Draw>
    // dyn 关键字只用在特征对象的类型声明上，在创建时无需使用 dyn


    // 基于 x 的值创建一个 Box<f64> 类型的智能指针，指针指向的数据被放置在了堆上
    draw1(Box::new(x));
    // 基于 y 的值创建一个 Box<u8> 类型的智能指针
    draw1(Box::new(y));

    draw2(&x);
    draw2(&y);
}


pub struct Screen {
    pub components: Vec<Box<dyn Draw>>,
}

impl Screen {
    pub fn run(&self) {
        for component in self.components.iter() {
            component.draw();
        }
    }
}

/**
 第二种写法 - 通过泛型实现
如果只需要同质（相同类型）集合，更倾向于这种写法：使用泛型和 特征约束
因为实现更清晰，且性能更好(特征对象，需要在运行时从 vtable 动态查找需要调用的方法)
 */
struct Screen_Generic<T: Draw> {
    pub components: Vec<T>,
}

impl<T> Screen_Generic<T>
    where T: Draw {
    pub fn run(&self) {
        for component in self.components.iter() {
            component.draw();
        }
    }
}

/**
特征对象的动态分发

动态分发(dynamic dispatch)，直到运行时，才能确定需要调用什么方法
之前代码中的关键字 dyn 正是在强调这一“动态”的特点

当使用特征对象时，Rust 必须使用动态分发
编译器无法知晓所有可能用于特征对象代码的类型，所以它也不知道应该调用哪个类型的哪个方法实现
为此，Rust 在运行时使用特征对象中的指针来知晓需要调用哪个方法


Self 与 self

在 Rust 中，有两个self
self 指代当前的实例对象，
Self 指代特征或者方法类型的别名



特征对象的限制

不是所有特征都能拥有特征对象，只有对象安全的特征才行

当一个特征的所有方法都有如下属性时，它的对象才是安全的：
方法的返回类型不能是 Self
方法没有任何泛型参数
 */
// 标准库中的 Clone 特征就不符合对象安全的要求
pub trait Clone {
    fn clone(&self) -> Self;
}

/**
关联类型

在特征定义的语句块中，申明一个自定义类型，这样就可以在特征的方法签名中使用该类型
 */
pub trait Iterator {
    type Item;

    fn next(&mut self) -> Option<Self::Item>;
}
