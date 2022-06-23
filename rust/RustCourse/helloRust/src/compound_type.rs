/**
切片 slice

允许引用集合中部分连续的元素序列，而不是引用整个集合

创建切片的语法: 使用 方括号 包括的一个序列
[开始索引..终止索引]
其中开始索引是切片中第一个元素的索引位置，而终止索引是最后一个元素后面的索引位置，
也就是这是一个 右半开区间

切片数据结构内部会保存 开始的位置 和 切片的长度
长度是通过   终止索引-开始索引   的方式计算得来的

字符串切片的类型标识是 &str
&str 是一个不可变引用
 */
pub fn slice_demo() {
    let s = String::from("hello world");

    let hello = &s[0..5];
    let world = &s[6..11];
    println!("the value of hello : {}", hello);
    println!("the value of hello : {}", world);

    // range语法等效 0..5
    let hello1 = &s[..5];
    println!("the value of hello1 : {}", hello1);

    // 包含 String 的最后一个字节
    let s = String::from("hello");

    let len = s.len();

    let slice1 = &s[4..len];
    let slice2 = &s[4..];
    println!("the value of slice1 : {}", slice1);
    println!("the value of slice2 : {}", slice2);

    // 截取完整的 String 切片
    let slice3 = &s[0..len];
    let slice4 = &s[..];
    println!("the value of slice3 : {}", slice3);
    println!("the value of slice4 : {}", slice4);

    // 切片的索引必须落在字符之间的边界位置，也就是 UTF-8 字符的边界
    // 例如中文在 UTF-8 中占用三个字节,否则程序崩溃
    let s = "中国人";
    let a = &s[0..2];
    println!("{}", a);
}

pub fn slice_demo2() {
    let mut s = String::from("hello world");

    // 不可变引用的切片
    let word = first_word(&s);

    // s.clear(); // error!

    println!("the first word is: {}", word);
}

fn first_word(s: &String) -> &str {
    &s[..1]
}

/**
其他切片
 */

pub fn other_slice_demo() {
    let a = [1, 2, 3, 4, 5];

    let slice = &a[1..3];

    assert_eq!(slice, &[2, 3]);
}

/**
字符串字面量是切片
 */
pub fn string_slice() {
    let s = "Hello, world!";
    let s1: &str = "Hello, world!";

    println!("the value of s : {}", s);
    println!("the value of s1 : {}", s1);
}

/**
字符串：由字符组成的连续集合

Rust 中的字符是 Unicode 类型，因此每个字符占据 4 个字节内存空间
         字符串中不一样，字符串是 UTF-8 编码，也就是字符串中的字符所占的字节数是变化的(1 - 4)， 有助于大幅降低字符串所占用的内存空间


在语言级别，只有一种字符串类型： str，它通常是以引用类型出现 &str，也就是字符串切片
虽然语言级别只有上述的 str 类型,但在标准库里，还有多种不同用途的字符串类型，其中使用最广的即是 String 类型

str 类型是硬编码进可执行文件，也无法被修改
String 则是一个可增长、可改变且具有所有权的 UTF-8 编码字符串

当 Rust 用户提到字符串时，往往指的就是 String 类型和 &str 字符串切片类型，这两个类型都是 UTF-8 编码
 */

/**
String 与 &str 的转换
 */

pub fn string_parse_demo() {
    let s = String::from("hello,world!");
    say_hello(&s);
    say_hello(&s[..]);
    say_hello(s.as_str());
}

fn say_hello(s: &str) {
    println!("{}", s);
}

/**
字符串索引

   let s1 = String::from("hello");
   let h = s1[0];
错误`String` cannot be indexed by `{integer}`


字符串的底层的数据存储格式实际上是[ u8 ]
对于 let hello = String::from("Hola");  Hola 的长度是 4 个字节, 每个字母在 UTF-8 编码中仅占用 1 个字节

let hello = String::from("中国人"); 实际上是 9 个字节的长度，因为大部分常用汉字在 UTF-8 中的长度是 3 个字节，
这种情况下对 hello 进行索引，访问 &hello[0] 没有任何意义

索引操作，我们总是期望它的性能表现是 O(1)，然而对于 String 类型来说，无法保证这一点

所以Rust 不允许去索引字符串
 */



/**
操作String字符串

String 是可变字符串

追加 (Push)
在字符串尾部可以使用 push() 方法追加字符 char
也可以使用 push_str() 方法追加字符串字面量
追加操作要修改原来的字符串
字符串变量必须由 mut 关键字修饰


插入 (Insert)
可以使用 insert() 方法插入单个字符 char，
也可以使用 insert_str() 方法插入字符串字面量
插入操作要修改原来的字符串
字符串变量必须由 mut 关键字修饰

方法需要传入两个参数，第一个参数是字符（串）插入位置的索引，第二个参数是要插入的字符（串），索引从 0 开始计数


替换 (Replace)
与替换有关的方法有三个。

1、replace
该方法可适用于 String 和 &str 类型
replace() 方法接收两个参数，第一个参数是要被替换的字符串，第二个参数是新的字符串
该方法是返回一个新的字符串


2、replacen

该方法可适用于 String 和 &str 类型
replacen() 方法接收三个参数，前两个参数与 replace() 方法一样，第三个参数则表示替换的个数
该方法是返回一个新的字符串

3、replace_range

该方法仅适用于 String 类型
replace_range 接收两个参数，第一个参数是要替换字符串的范围（Range），第二个参数是新的字符串
该方法是直接操作原来的字符串


删除 (Delete)
字符串删除相关的方法4个，pop()，remove()，truncate()，clear()
这四个方法仅适用于 String 类型

1、pop —— 删除 并 返回 字符串的最后一个字符
该方法是直接操作原来的字符串，但是存在返回值，其返回值是一个 Option 类型，如果字符串为空，则返回 None

2、 remove —— 删除并返回字符串中指定位置的字符
该方法是直接操作原来的字符串
存在返回值，其返回值是删除位置的字符串，只接收一个参数，表示该字符起始索引位置
remove() 方法是按照字节来处理字符串的

3、truncate —— 删除字符串中从指定位置开始到结尾的全部字符
该方法是直接操作原来的字符串
无返回值
truncate() 方法是按照字节来处理字符串的

4、clear —— 清空字符串
该方法是直接操作原来的字符串
删除字符串中的所有字符，相当于 truncate() 方法参数为 0


连接 (Catenate)

1、使用 + 或者 += 连接字符串
使用 + 或者 += 连接字符串，要求右边的参数必须为字符串的切片引用（Slice)类型
+ 的操作符时，相当于调用了 std::string 标准库中的 add() 方法，这里 add() 方法的第二个参数是一个引用的类型
因此我们在使用 +， 必须传递切片引用类型

不能直接传递 String 类型
+ 和 += 都是返回一个新的字符串
 */
pub fn string_push_demo() {
    let mut s = String::from("Hello ");
    s.push('r');
    println!("追加字符 push() -> {}", s);

    s.push_str("ust!");
    println!("追加字符串 push_str() -> {}", s);
}

pub fn string_insert_demo() {
    let mut s = String::from("Hello rust!");
    s.insert(5, ',');
    println!("插入字符 insert() -> {}", s);
    s.insert_str(6, " I like");
    println!("插入字符串 insert_str() -> {}", s);
}

pub fn string_replace_demo() {
    let string_replace = String::from("I like rust. Learning rust is my favorite!");
    let new_string_replace = string_replace.replace("rust", "RUST");
    dbg!(new_string_replace);
}

pub fn string_replacen_demo() {
    let string_replace = String::from("I like rust. Learning rust is my favorite!");
    let new_string_replace = string_replace.replacen("rust", "RUST", 1);
    dbg!(new_string_replace);
}

pub fn string_replace_range_demo() {
    let mut string_replace_range = String::from("I like rust!");
    string_replace_range.replace_range(7..8, "R");
    dbg!(string_replace_range);
}

pub fn string_pop_demo() {
    let mut string_pop = String::from("rust pop 中文!");
    let p1 = string_pop.pop();
    let p2 = string_pop.pop();
    dbg!(p1);
    dbg!(p2);
    dbg!(string_pop);
}

pub fn string_remove_demo() {
    let mut string_remove = String::from("测试remove方法");
    println!(
        "string_remove 占 {} 个字节", std::mem::size_of_val(string_remove.as_str())
    );
    // 删除第一个汉字
    string_remove.remove(0);
    // 下面代码会发生错误
    // string_remove.remove(1);
    // 直接删除第二个汉字
    // string_remove.remove(3);
    dbg!(string_remove);
}

pub fn string_truncate_demo() {
    let mut string_truncate = String::from("测试truncate");
    string_truncate.truncate(3);
    dbg!(string_truncate);
}

pub fn string_clear_demo() {
    let mut string_clear = String::from("string clear");
    string_clear.clear();
    dbg!(string_clear);
}

pub fn string_catenate_demo() {
    let string_append = String::from("hello ");
    let string_rust = String::from("rust");
    // 在下句中，string_append的所有权被转移走了，因此后面不能再使用string_append
    // &string_rust会自动解引用为&str
    let result = string_append + &string_rust;
    let mut result = result + "!";
    result += "!!!";

    println!("连接字符串 + -> {}", result);
}
/**
 字符串转义
 可以通过转义的方式 \ 输出 ASCII 和 Unicode 字符
 */
pub fn string_escape_demo() {
    // 通过 \ + 字符的十六进制表示，转义输出一个字符
    let byte_escape = "I'm writing \x52\x75\x73\x74!";
    println!("What are you doing\x3F (\\x3F means ?) {}", byte_escape);

    // \u 可以输出一个 unicode 字符
    let unicode_codepoint = "\u{211D}";
    let character_name = "\"DOUBLE-STRUCK CAPITAL R\"";

    println!(
        "Unicode character {} (U+211D) is called {}",
        unicode_codepoint, character_name
    );

    // 换行了也会保持之前的字符串格式
    let long_string = "String literals
                        can span multiple lines.
                        The linebreak and indentation here ->\
                        <- can be escaped too!";
    println!("{}", long_string);
}

/**
操作 UTF-8 字符串

字符
如果想要以 Unicode 字符的方式遍历字符串，最好的办法是使用 chars 方法

字节
这种方式是返回字符串的底层字节数组表现形式

获取子串
想要准确的从 UTF-8 字符串中获取子串是较为复杂的事情
例如想要从 holla中国人नमस्ते 这种变长的字符串中取出某一个子串，使用标准库你是做不到的
需要在 crates.io 上搜索 utf8 来寻找想要的功能
 */

pub fn string_utf8_string_demo() {
    println!("-----------chars-------");
    for c in "中国人".chars() {
        println!("{}", c);
    }
    println!("------------bytes------");
    for b in "中国人".bytes() {
        println!("{}", b);
    }
}
