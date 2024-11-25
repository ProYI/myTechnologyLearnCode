/**
generic 泛型

在 Rust 中，泛型参数的名称你可以任意起，但是出于惯例，都用 T ( T 是 type 的首字母)来作为首选
T 就是泛型参数
 */

fn largest<T: std::cmp::PartialOrd>(list: &[T]) -> &T {
    let mut largest = &list[0];

    for item in list.iter() {
        // 因为 T 可以是任何类型，但不是所有的类型都能进行比较
        // 编译器建议给 T 添加类型限制：使用 std::cmp::PartialOrd 特征（Trait）对 T 进行限制
        if item > largest {
            largest = item;
        }
    }
    largest
}

pub fn generic_demo() {
    let a = [1, 2, 3, 4, 5];
    let largest = largest(&a);
    println!("largest value is : {}", largest)
}

/**
结构体中使用泛型


提前声明，跟泛型函数定义类似，首先我们在使用泛型参数之前必需要进行声明 Point<T>
接着就可以在结构体的字段类型中使用 T 来替代具体的类型

x 和 y 是相同的类型

 */
struct Point<T> {
    x: T,
    y: T,
}

pub fn generic_demo2() {
    let integer = Point { x: 5, y: 10 };
    let float = Point { x: 1.0, y: 4.0 };
    println!("integer x value is :{}", integer.x);
}

/**
枚举中使用泛型

提到枚举类型，Option

Option<T> 是一个拥有泛型 T 的枚举类型，它第一个成员是 Some(T)，存放了一个类型为 T 的值

Result 关注的主要是值的正确性
 */

/**
方法中使用泛型



 */

struct PointGeneric<T> {
    x: T,
    y: T,
}

impl<T> PointGeneric<T> {
    fn x(&self) -> &T {
        &self.x
    }
}

pub fn generic_demo3() {
    let p = PointGeneric { x: 5, y: 10 };
    println!("p.x = {}", p.x());
}

struct PointGeneric2<T, U> {
    x: T,
    y: U,
}

// T,U 是定义在结构体 Point 上的泛型参数，V,W 是单独定义在方法 mixup 上的泛型参数
// 可以理解为，一个是结构体泛型，一个是函数泛型
impl<T, U> PointGeneric2<T, U> {
    fn mixup<V, W>(self, other: PointGeneric2<V, W>) -> PointGeneric2<T, W> {
        PointGeneric2 {
            x: self.x,
            y: other.y,
        }
    }
}

pub fn generic_demo4() {
    let p1 = PointGeneric2 { x: 5, y: 10.4 };
    let p2 = PointGeneric2 { x: "Hello", y: 'c' };

    let p3 = p1.mixup(p2);

    println!("p3.x = {}, p3.y = {}", p3.x, p3.y);
}

/**
为具体的泛型类型实现方法

对于 Point<T> 类型，你不仅能定义基于 T 的方法，还能针对特定的具体类型，进行方法定义

意味着 Point<f32> 类型会有一个方法 distance_from_origin，而其他 T 不是 f32 类型的 Point<T> 实例则没有定义此方法
 */
impl PointGeneric<f32> {
    fn distance_from_origin(&self) -> f32 {
        (self.x.powi(2) + self.y.powi(2)).sqrt()
    }
}

pub fn generic_demo5() {
    let a:PointGeneric<i32> = PointGeneric { x: 12, y: 20 };
    let b:PointGeneric<f32> = PointGeneric { x: 12.0, y: 20.0 };

    // a无法调用distance_from_origin
    let result = b.distance_from_origin();
    println!("PointGeneric f32 distance_from_origin result : {}", result);
}

/**
const泛型

定义了一个类型为 [T; N] 的数组，其中 T 是一个基于类型的泛型参数
重点在于 N 这个泛型参数，它是一个基于值的泛型参数！因为它用来替代的是数组的长度

N 就是 const 泛型，定义的语法是 const N: usize，表示 const 泛型 N ，它基于的值类型是 usize

在 Rust 中泛型是零成本的抽象，意味着在使用泛型时，完全不用担心性能上的问题
但是任何选择都是权衡得失的，既然我们获得了性能上的巨大优势，那么又失去了什么呢？
Rust 是在编译期为泛型对应的多个类型，生成各自的代码，因此损失了编译速度和增大了最终生成文件的大小

Rust 通过在编译时进行泛型代码的 单态化(monomorphization)来保证效率
单态化是一个通过填充编译时使用的具体类型，将通用代码转换为特定代码的过程

个人理解：编译器根据泛型将没有明确写出的泛型代码，自动补全生成了具体的类型代码
所以代码生成文件变大，编译速度变慢，并且穷举了需要使用的代码，性能上才没有任何影响
 */

fn display_array<T: std::fmt::Debug, const N: usize>(arr: [T; N]) {
    println!("{:?}", arr);
}
pub fn generic_demo6() {
    let arr: [i32; 3] = [1, 2, 3];
    display_array(arr);

    let arr: [i32; 2] = [1, 2];
    display_array(arr);
}