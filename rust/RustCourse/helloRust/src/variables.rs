/**
 * 变量可变性
 */
fn variables_1() {
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

fn main() {
    variables_1();
}