/**
 模式匹配

模式匹配最常用的就是 match 和 if let

match
match 后的表达式返回值可以是任意类型
 */
enum Direction {
    East,
    West,
    North,
    South,
}

enum Coin {
    Penny,
    Nickel,
    Dime,
    Quarter,
}

fn value_in_cents(coin: Coin) -> u8 {
    match coin {
        Coin::Penny => {
            println!("Lucky penny!");
            1
        }
        Coin::Nickel => 5,
        Coin::Dime => 10,
        Coin::Quarter => 25,
    }
}

pub fn match_demo() {
    let dire = Direction::South;
    match dire {
        Direction::East => println!("East"),
        Direction::North | Direction::South => {
            println!("South or North");
        }
        // 用 _ 来代表未列出的所有可能性
        _ => println!("West"),
    };
    println!("--------------------------");

    let value = value_in_cents(Coin::Dime);
    println!("value in cents : {}", value);

    println!("--------------------------");

    /**
    表达式赋值
     */
    let ip1 = IpAddr::Ipv6;
    let ip_str = match ip1 {
        IpAddr::Ipv4 => "127.0.0.1",
        _ => "::1",
    };

    println!("{}", ip_str);
    println!("--------------------------");

    /**
    模式绑定
    从模式中取出绑定的值
     */
    value_in_cents1(Coin1::Quarter(UsState::Alaska));
}


enum IpAddr {
    Ipv4,
    Ipv6,
}

#[derive(Debug)]
pub enum UsState {
    Alabama,
    Alaska,
    // --snip--
}

pub enum Coin1 {
    Penny,
    Nickel,
    Dime,
    Quarter(UsState), // 25美分硬币
}

pub fn value_in_cents1(coin: Coin1) -> u8 {
    match coin {
        Coin1::Penny => 1,
        Coin1::Nickel => 5,
        Coin1::Dime => 10,
        Coin1::Quarter(state) => {
            println!("State quarter from {:?}!", state);
            25
        }
    }
}

enum Action {
    Say(String),
    MoveTo(i32, i32),
    ChangeColorRGB(u16, u16, u16),
}

pub fn value_in_cents2() {
    let actions = [
        Action::Say("Hello Rust".to_string()),
        Action::MoveTo(1, 2),
        Action::ChangeColorRGB(255, 255, 0),
    ];
    for action in actions {
        match action {
            Action::Say(s) => {
                println!("{}", s);
            }
            Action::MoveTo(x, y) => {
                println!("point from (0, 0) move to ({}, {})", x, y);
            }
            Action::ChangeColorRGB(r, g, _) => {
                println!("change color into '(r:{}, g:{}, b:0)', 'b' has been ignored", r, g);
            }
        }
    }
}

/**
穷尽匹配
 */


pub fn match_all_demo() {
    let dire = Direction::South;
    // Match must be exhaustive 所有选项都必须表明匹配规则
    match dire {
        Direction::East => println!("East"),
        Direction::North | Direction::South => {
            println!("South or North");
        }
        Direction::West => println!("West")
    };
}

/**
if let 匹配
当只要匹配一个条件，且忽略其他条件时就用 if let ，否则都用 match
 */

pub fn if_let_demo() {
    let v = Some(3u8);
    if let Some(3) = v {
        println!("three");
    }
}

/**
模式
模式是 Rust 中的特殊语法，
它用来匹配类型中的结构和数据

往往和 match 表达式联用，以实现强大的模式匹配能力

模式一般由以下内容组合而成：
字面值
解构的数组、枚举、结构体或者元组
变量
通配符
占位符
 */

pub fn while_let_demo() {
// Vec是动态数组
    let mut stack = Vec::new();

// 向数组尾部插入元素
    stack.push(1);
    stack.push(2);
    stack.push(3);

// stack.pop从数组尾部弹出元素
    while let Some(top) = stack.pop() {
        println!("{}", top);
    }

    //如果动态数组是空的，将返回 None，
    // 对于 while 来说，只要 pop 返回 Some 就会一直不停的循环
    // 一旦其返回 None，while 循环停止
    // 可以使用 while let 来弹出栈中的每一个元素
}

pub fn for_demo() {
    let v = vec!['a', 'b', 'c'];

    // 使用 enumerate 方法产生一个迭代器，该迭代器每次迭代会返回一个 (索引，值) 形式的元组，然后用 (index,value) 来匹配

    for (index, value) in v.iter().enumerate() {
        println!("{} is at index {}", value, index);
    }
}
