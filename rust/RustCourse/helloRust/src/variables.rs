use std::ops::RangeFrom;

/**
 * 变量可变性
 */
pub fn variables_1() {
    // let x = 5;
    // println!("the value of x is : {}", x);
    // 报错 Cannot assign twice to immutable variable
    // x = 6;
    // println!("the value of x is : {}", x);

    let mut x = 5;
    println!("the value of x is : {}", x);
    x = 6;
    println!("the value of x is : {}", x);
}

/**
使用下划线开头忽略未使用的变量
*/
pub fn variables_2() {
    // warning: unused variable: `x`
    // let x = 5;
    // 添加下划线前缀后不再warning
    let _x = 5;
    let y = 10;
}

/**
let 变量解构
从一个相对复杂的变量中，匹配出该变量的一部分内容
 */
pub fn variables_3() {
    let (a, mut b): (bool, bool) = (true, false);
    // a=true 不可变， b=false可变
    println!("a = {:?}, b={:?}", a, b);

    b = true;
    assert_eq!(a, b);
}

/**
 解构式赋值
 */
struct Struct {
    e: i32
}

pub fn variables_4() {
    let (a, b, c, d, e);

    (a, b) = (1, 2);

    // _代表匹配一个值，但是不关心具体值是什么，因此不是一个变量名而是使用"_"
    [c, .., d,_] = [1, 2, 3, 4, 5];
    Struct {e, ..} = Struct {e:5};
}
/**
变量和常量之间的差异

常量不允许使用 mut, 常量不仅仅默认不可变，而且自始至终不可变
常量使用 const 关键字而不是 let 关键字来声明，并且值的类型必须标注


const MAX_POINTS: u32 = 100_000;
 */

/**
变量遮蔽(shadowing)

允许声明相同的变量名，在后面声明的变量会遮蔽掉前面声明的
 */
pub fn variables_5() {
    let x = 5;
    // 对之前的x进行遮蔽
    let x = x + 1;
    {
        // 对括号作用于内 进行遮蔽
        let x = x * 2;
        println!("the value of x in the inner scope is : {}", x);
    }
    println!("the value of x is: {}", x);
}