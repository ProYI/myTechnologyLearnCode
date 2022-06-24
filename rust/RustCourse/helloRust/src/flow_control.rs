/**
 流程控制

if

for
for循环中直接遍历比下标遍历性能好
避免边界检查(Bounds Checking)导致运行时的性能损耗
对 collection 的索引访问是非连续的


关键字：continue，break
 */
pub fn if_demo() {
    let condition = true;
    let number = if condition {
        5
    } else {
        6
    };

    println!("The value of number is: {}", number);

    println!("--------------------------");

    let n = 6;

    if n % 4 == 0 {
        println!("number is divisible by 4");
    } else if n % 3 == 0 {
        println!("number is divisible by 3");
    } else if n % 2 == 0 {
        println!("number is divisible by 2");
    } else {
        println!("number is not divisible by 4, 3, or 2");
    }

    println!("--------------------------");

    for i in 1..=5 {
        println!("{}", i);
    }
    println!("--------------------------");

    /**
    for 循环遍历集合时，由于没有触发copy，会转移所有权，因此最好使用引用来遍历
    比如 container
     */

    /**
    循环获取元素索引
     */
    let a = [4, 3, 2, 1];
    // `.iter()` 方法把 `a` 数组变成一个迭代器
    for (i, v) in a.iter().enumerate() {
        println!("第{}个元素是{}", i + 1, v);
    }
    println!("--------------------------");

    /**
    用 for 循环控制某个过程执行, 但是又不想单独声明一个变量来控制这个流程
    _ 的含义是忽略该值或者类型

    消息体内部操作与遍历对象无关
     */
    let mut b = [4, 3, 2, 1];
    for _ in b {
        // 循环处理
        println!("*****")
    }
}

pub fn while_demo() {
    let mut n = 0;

    while n <= 5  {
        println!("{}!", n);

        n = n + 1;
    }

    println!("我出来了！");
    println!("--------------------------");


}
pub fn loop_demo() {
    let mut n = 0;

    loop {
        if n > 5 {
            break
        }
        println!("{}", n);
        n+=1;
    }

    println!("我出来了！");
}