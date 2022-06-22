mod variables;

use ferris_says::say;
use std::{io::{stdout, BufWriter}, f32};

fn main() {
    // greet_world();
    // let stdout = stdout();
    // let message = String::from("Hello fellow Rustaceans!");
    // let width = message.chars().count();
    //
    // let mut writer = BufWriter::new(stdout.lock());
    // say(message.as_bytes(), width, &mut writer).unwrap();
    //
    // println!("{}", "------------");
    //
    // test_funcation();

    println!("{}", "------------variables------------ ");
    // variables::variables_1();
    // variables::variables_2();
    // variables::variables_3();
    // variables::variables_4();
    variables::variables_5();
}

fn greet_world() {
    let southern_germany = "Grüß Gott!";
    let chinese = "世界，你好";
    let english = "World, hello";
    let regions = [southern_germany, chinese, english];
    for region in regions.iter() {
        println!("{}", &region);
    }
}

fn test_funcation() {
    let penguin_data = "
   common name,length (cm)
   Little penguin,33
   Yellow-eyed penguin,65
   Fiordland penguin,60
   Invalid,data
   ";
    let records = penguin_data.lines();
    for (i, record) in records.enumerate() {
        if i == 0 || record.trim().len() == 0 {
            continue;
        }
        let fields: Vec<_> = record.split(",").map(|field| field.trim()).collect();
        if cfg!(debug_assertions) {
            eprintln!("debug:{:?} -> {:?}", record, fields);
        }
        let name = fields[0];

        if let Ok(length) = fields[1].parse::<f32>() {
            println!("{}, {}cm", name, length);
        }
    }
}
