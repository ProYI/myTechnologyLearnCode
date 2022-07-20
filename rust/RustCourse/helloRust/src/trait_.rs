use std::env::var;

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
impl Summary for Post2 {
}


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
