use std::fmt::Display;

/**
特征 trait

特征很类似接口

如果不同的类型具有相同的行为，那么就可以定义一个特征，然后为这些类型实现该特征
定义特征是把一些方法组合在一起，目的是定义一个实现某些目标所必需的行为的集合

特征定义了一个可以被共享的行为，只要实现了特征，你就能使用该行为
 */

pub trait Summary {
    fn summarize(&self) -> String { String::from("(default context...)") }
}


pub struct Post {
    // 标题
    pub title: String,
    // 作者
    pub author: String,
    // 内容
    pub content: String,
}

impl Summary for Post {
    fn summarize(&self) -> String {
        format!("文章{}, 作者是{}", self.title, self.author)
    }
}

pub struct Post2 {
    // 标题
    pub title: String,
    // 作者
    pub author: String,
    // 内容
    pub content: String,
}

impl Summary for Post2 {}


pub struct Weibo {
    pub username: String,
    pub content: String,
}

impl Summary for Weibo {
    fn summarize(&self) -> String {
        format!("{}发表了微博{}", self.username, self.content)
    }
}

pub fn trait_demo1() {
    let post: Post = Post { title: "Rust语言简介".to_string(), author: "proyi".to_string(), content: "Rust Hello".to_string() };
    let weibo = Weibo { username: "proyi".to_string(), content: "发现生活".to_string() };

    println!("{}", post.summarize());
    println!("{}", weibo.summarize());
}

/**
特征定义与实现的位置(孤儿规则)

关于特征实现与定义的位置，有一条非常重要的原则：
如果你想要为类型 A 实现特征 T，那么 A 或者 T 至少有一个是在当前作用域中定义的！

无法在当前作用域中，为 String 类型实现 Display 特征，因为它们俩都定义在标准库中，其定义所在的位置都不在当前作用域
跟你没关系

该规则被称为孤儿规则，可以确保其它人编写的代码不会破坏你的代码，也确保了你不会莫名其妙就破坏了风马牛不相及的代码



默认实现
你可以在特征中定义具有默认实现的方法，这样其它类型无需再实现该方法，或者也可以选择重载该方法
 */

pub fn trait_demo2() {
    let post: Post2 = Post2 { title: "Rust语言简介".to_string(), author: "proyi".to_string(), content: "Rust Hello".to_string() };
    println!("{}", post.summarize());
}

/**
默认实现允许调用相同特征中的其他方法，哪怕这些方法没有默认实现
 */

pub trait Summary2 {
    fn summarize_author(&self) -> String;

    fn summarize(&self) -> String {
        format!("(Read more from {}...)", self.summarize_author())
    }
}

pub struct Weibo2 {
    pub username: String,
    pub content: String,
}

impl Summary2 for Weibo2 {
    fn summarize_author(&self) -> String {
        format!("@{}", self.username)
    }
}

pub fn trait_demo3() {
    let weibo = Weibo2 { username: "proyi".to_string(), content: "发现生活".to_string() };
    println!("1 new weibo: {}", weibo.summarize());
}

/**
使用特征作为函数参数
 */
pub fn notify(item: &impl Summary) {
    println!("Breaking news! {}", item.summarize());
}

pub fn trait_demo4() {
    let weibo = Weibo { username: "proyi".to_string(), content: "发现生活".to_string() };
    notify(&weibo);
}

/**
特征约束(trait bound)

impl Trait 这种语法好理解，但是实际上它只是一个语法糖

真正的完整书写形式，形如 T: Summary 被称为特征约束
在简单的场景下 impl Trait 这种语法糖就足够使用，但是对于复杂的场景，特征约束可以让我们拥有更大的灵活性和语法表现能力

pub fn notify(item1: &impl Summary, item2: &impl Summary) {}
pub fn notify<T: Summary>(item1: &T, item2: &T) {}

类型约束可以实现 强制函数的两个参数是同一类型，第一种写法只能表名参数为Summary，不能实现两个参数之间的约束


多重约束
指定多个约束条件

例如除了让参数实现 Summary 特征外，还可以让参数实现 Display 特征以控制它的格式化输出
下面两种均可以
pub fn notify(item: &(impl Summary + Display)) {}
pub fn notify<T: Summary + Display>(item: &T) {}


Where 约束

当特征约束变得很多时，函数的签名将变得很复杂
fn some_function<T: Display + Clone, U: Clone + Debug>(t: &T, u: &U) -> i32 {}
可以通过 where 对其做一些形式上的改进，

fn some_function<T, U>(t: &T, u: &U) -> i32
    where T: Display + Clone,
          U: Clone + Debug
{}






使用特征约束有条件地实现方法或特征
 */
struct Pair<T> {
    x: T,
    y: T,
}

impl<T> Pair<T> {
    fn new(x: T, y: T) -> Self {
        Self {
            x,
            y,
        }
    }
}

impl<T: Display + PartialOrd> Pair<T> {
    fn cmp_display(&self) {
        if self.x >= self.y {
            println!("The largest member is x = {}", self.x);
        } else {
            println!("The largest member is y = {}", self.y);
        }
    }
}

pub fn trait_demo5() {
    let pair = Pair { x: 7, y: 9 };
    pair.cmp_display();
}

/**
函数返回中的 impl Trait

可以通过 impl Trait 来说明一个函数返回了一个类型，该类型实现了某个特征
 */
fn returns_summarizable() -> impl Summary {
    Weibo {
        username: String::from("proyi"),
        content: String::from(
            "今天是个好天气",
        ),
    }
}
pub fn trait_demo6() {
    let a = returns_summarizable();
    println!("{}", a.summarize());
}