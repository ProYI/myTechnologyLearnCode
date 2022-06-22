use num::complex::Complex;

/**
基本类型

数值类型:
    有符号整数 (i8, i16, i32, i64, isize)
    无符号整数 (u8, u16, u32, u64, usize)
    浮点数 (f32, f64)
    有理数
    复数
字符串：
    字符串字面量
    字符串切片 &str
布尔类型：
    true
    false
字符类型:
    表示单个 Unicode 字符，存储为 4 个字节
单元类型:
    () ，其唯一的值也是 ()
 */

/**
类型推导
 */

pub fn baseType() {
    //type annotations needed
    // let guess = "42".parse().expect("Not a number!");

    // 显示标注
    let guess1: u32 = "42".parse().expect("Not a number!");
    let guess2 = "42".parse::<u32>().expect("Not a number!");
    println!("the value is : {}", guess1);
    println!("the value is : {}", guess2);
}

/**
 浮点类型

浮点数陷阱
1. 浮点数类型基于二进制实现，但是想要计算的数字基于十进制，导致精度差异
2. rust的f32 f64没有实现eq特征，不能直接进行比较
 */
pub fn float_demo() {
    let abc: (f32, f32, f32) = (0.1, 0.2, 0.3);
    let xyz: (f64, f64, f64) = (0.1, 0.2, 0.3);

    println!("abc (f32)");
    println!("   0.1 + 0.2: {:x}", (abc.0 + abc.1).to_bits());
    println!("         0.3: {:x}", (abc.2).to_bits());
    println!();

    println!("xyz (f64)");
    println!("   0.1 + 0.2: {:x}", (xyz.0 + xyz.1).to_bits());
    println!("         0.3: {:x}", (xyz.2).to_bits());
    println!();

    assert!(abc.0 + abc.1 == abc.2);
    assert!(xyz.0 + xyz.1 == xyz.2);
}

/**
NaN
   not a number
   处理数学上未定义的结果
 */
pub fn nan_demo() {
    let x = (-42.0_f32).sqrt();
    assert_eq!(x, x);
}

pub fn nan_demo2() {
    let x = (-42.0_f32).sqrt();
    if x.is_nan() {
        println!("未定义的数学行为")
    }
}

/**
数字运算
 */
pub fn number_demo() {
    // 加法
    let sum = 5 + 10;

    // 减法
    let difference = 95.5 - 4.3;

    // 乘法
    let product = 4 * 30;

    // 除法
    let quotient = 56.7 / 32.2;

    // 求余
    let remainder = 43 % 5;
}

/**
 序列
一个非常简洁的方式，用来生成连续的数值
1..5 左闭右开
1..=5 左右均为闭区间
 */
pub fn range_demo() {
    for i in 1..5 {
        println!("1..5:  {}", i);
    }
    for i in 1..=5 {
        println!("1..=5:  {}", i);
    }

    // demo
    for i in '你'..='我' {
        println!("你..=我:  {}", i);
    }
}

/**
有理数和复数
有理数和复数并未包含在标准库中

cargo引入社区Rust数值库 num
num = "0.4.0"
 */

pub fn complex_num() {
    let a = Complex { re: 2.1, im: -1.2 };
    let b = Complex::new(11.1, 22.2);
    let result = a + b;

    println!("{} + {}i", result.re, result.im)
}

/**
 字符类型 char

Rust 的字符不仅仅是 ASCII，
所有的 Unicode 值都可以作为 Rust 字符，包括单个的中文、日文、韩文、emoji 表情符号等等，都是合法的字符类型
字符类型占用 4 个字节
 */
pub fn char_demo() {
    let x = '中';
    println!("字符'中'占用了{}字节的内存大小", std::mem::size_of_val(&x));
}

/**
 布尔 （bool）

true 和 false
布尔值占用内存的大小为 1 个字节
 */
pub fn bool_demo() {
    let t = false;

    let f: bool = true; // 使用类型标注,显式指定f的类型

    if f {
        println!("这是段毫无意义的代码");
    }
}

/**
 单元类型 （）
 唯一值也是 （）

main 函数就返回这个单元类型 ()
不能说 main 函数无返回值，
因为没有返回值的函数在 Rust 中是有单独的定义的：发散函数( diverge function )
顾名思义，无法收敛的函数

常见的 println!() 的返回值也是单元类型 ()

用 () 作为 map 的值，表示我们不关注具体的值，只关注 key
可以作为一个值用来占位，但是完全不占用任何内存
 */
pub fn unit_demo() {}

/**
 语句和表达式
Rust 的函数体是由一系列语句组成，最后由一个表达式来返回值
 */

pub fn add_with_extra(x: i32, y: i32) -> i32 {
    let x = x + 1; // 语句
    let y = y + 5; // 语句
    println!("the value : {}", x + y);
    x + y             // 表达式,后面不能跟";"

    // let a = 8;
    // let b: Vec<f64> = Vec::new();
    // let (a, c) = ("hi", false);
    //以上都是语句，它们完成了一个具体的操作，但是并没有返回值
}

/**
表达式
表达式会进行求值，然后返回一个值

表达式可以成为语句的一部分
例如 let y = 6 中，6 就是一个表达式，它在求值后返回一个值 6

调用一个函数是表达式，因为会返回一个值，
调用宏也是表达式
用花括号包裹最终返回一个值的语句块也是表达式

总之，能返回值，就是表达式
 */
pub fn expression_demo() {
    let y = {
        let x = 3;
        //返回了 x + 1 的值
        // 注意 x + 1 不能以分号结尾，否则就会从表达式变成语句， 表达式不能包含分号

        x + 1
    };

    println!("The value of y is: {}", y);
}
