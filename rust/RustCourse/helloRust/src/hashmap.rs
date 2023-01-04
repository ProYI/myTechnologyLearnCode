use std::collections::HashMap;

/**
HashMap

HashMap中存储的是一一映射的 KV 键值对，并提供了平均复杂度为 O(1) 的查询方法
 */
pub fn hashmap_demo() {
    /**
     创建HashMap

    使用 new 方法来创建 HashMap，然后通过 insert 方法插入键值对
     */
    let mut map = HashMap::new();
    map.insert("蓝宝石", 1);

    // HashMap 并没有包含在 Rust 的 prelude 中

    // 所有的集合类型都是动态的，意味着没有固定的内存大小
    // 因此它们底层的数据都存储在内存堆上，然后通过一个存储在栈中的引用类型来访问

    // HashMap 也是内聚性的，即所有的 K 必须拥有同样的类型，V 也是如此

    // 创建指定大小的 HashMap可以提高性能


    /**
    使用迭代器和 collect 方法创建 HashMap
     */
    let teams_list = vec![
        ("中国队".to_string(), 100),
        ("美国队".to_string(), 10),
        ("日本队".to_string(), 50),
    ];
    let mut teams_hashmap = HashMap::new();
    for team in &teams_list {
        teams_hashmap.insert(&team.0, team.1);
    }
    println!("{:?}", teams_hashmap);

    // 转换 将列表转为迭代器，接着通过 collect 进行收集 需要通过类型标注HashMap<_,_>
    let mut teams_hashmap2 : HashMap<_ ,_ > = teams_list.into_iter().collect();
    println!("{:?}", teams_hashmap2);


    /**
     所有权转移

    与其它 Rust 类型没有区别：
    若类型实现 Copy 特征，该类型会被复制进 HashMap，因此无所谓所有权
    若没实现 Copy 特征，所有权将被转移给 HashMap 中

    使用引用类型放入 HashMap 中，请确保该引用的生命周期跟 HashMap一样

     */

    let name = String::from("小明");
    let age = 18;

    let mut boys = HashMap::new();
    boys.insert(&name, age);

    println!("男孩名单中有{}", name);
    println!("他的年龄为{}岁", age);

    // drop(name);
    // println!("{:?}已经被删除了", boys); 删除后再使用hashmap访问无法通过编译，因为hashmap保留的引用，所有权已转移

    /**
     查询hashmap

    通过 get 方法获取元素

    get 方法返回一个 Option<&i32> 类型：当查询不到时，会返回一个 None，查询到时返回 Some(&i32)
    &i32 是对 HashMap 中值的借用，如果不使用借用，可能会发生所有权的转移
     */
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Yellow"), 50);
    let team_name = String::from("Blue");
    let score : Option<&i32> = scores.get(&team_name);
    println!("{}队的分数是：{:?}", team_name, score.unwrap());

    /**
    循环遍历
     */

    for (key, value) in &scores {
        println!("{}: {}", key, value);
    }


    let mut scores2 = HashMap::new();

    scores2.insert("Blue", 10);

    // 覆盖已有的值 如果存在，会更新新值，并将老的值返回回来
    let old = scores2.insert("Blue", 20);
    assert_eq!(old, Some(10));

    // 查询新插入的值
    let new = scores2.get("Blue");
    assert_eq!(new, Some(&20));

    // 查询Yellow对应的值，若不存在则插入新值
    let v = scores2.entry("Yellow").or_insert(5);
    assert_eq!(*v, 5); // 不存在，插入5

    // 查询Yellow对应的值，若不存在则插入新值
    let v = scores2.entry("Yellow").or_insert(50);
    assert_eq!(*v, 5); // 已经存在，因此50没有插入

    let text = "hello world wonderful world";

    let mut map = HashMap::new();
    // 根据空格来切分字符串
    for word in text.split_whitespace() {
        let count = map.entry(word).or_insert(0);
        *count += 1;
    }

    println!("{:?}", map);

    /**
     hash函数

    通过hash函数把 Key 计算后映射为哈希值，然后使用该哈希值来进行存储、查询、比较等操作、

    追求安全，尽可能减少冲突，同时防止拒绝服务攻击  ------> 用密码学安全的哈希函数
    若要追求性能 ---------------> 需要使用相对没有那么安全的算法


    目前，HashMap 使用的哈希函数是 SipHash，它的性能不是很高，但是安全性很高

    SipHash 在中等大小的 Key 上，性能不错
    但是对于小型的 Key （例如整数）或者大型 Key （例如字符串），性能不够好
     */


}
