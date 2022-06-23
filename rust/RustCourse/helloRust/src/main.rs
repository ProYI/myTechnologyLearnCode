mod variables;
mod base_type;
mod compound_type;

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

    // println!("{}", "------------variables------------ ");
    // variables::variables_1();
    // variables::variables_2();
    // variables::variables_3();
    // variables::variables_4();
    // variables::variables_5();

    // println!("{}", "------------baseType------------ ");
    // base_type::baseType();
    // base_type::float_demo();
    // base_type::nan_demo();
    // base_type::nan_demo2();
    // base_type::number_demo();
    // base_type::range_demo();
    // base_type::complex_num();
    // base_type::char_demo();
    // base_type::bool_demo();
    // let i = base_type::add_with_extra(1,1);
    // base_type::expression_demo();
    // base_type::fn_demo();
    // base_type::fn_in_or_return_demo();
    // base_type::fn_return_owner_demo();
    // base_type::borrowing_demo();
    // base_type::quote_demo();
    // base_type::quote_demo3();
    println!("{}", "------------compoundType------------ ");
    // compound_type::slice_demo();
    // compound_type::slice_demo2();
    // compound_type::other_slice_demo();
    // compound_type::string_slice();
    // compound_type::string_parse_demo();
    // compound_type::string_push_demo();
    // compound_type::string_insert_demo();
    // compound_type::string_replace_demo();
    // compound_type::string_replacen_demo();
    // compound_type::string_replace_range_demo();
    // compound_type::string_pop_demo();
    // compound_type::string_remove_demo();
    // compound_type::string_truncate_demo();
    // compound_type::string_clear_demo();
    // compound_type::string_catenate_demo();
    // compound_type::string_escape_demo();
    // compound_type::string_utf8_string_demo();
    // compound_type::tuple_demo();
    // compound_type::tuple_use_demo();
    // compound_type::struct_demo();
    // compound_type::tuple_struct_demo();
    // compound_type::struct_fmt_demo();
    // compound_type::enum_demo();
    // compound_type::enum_value_demo();
    // compound_type::enum_type_demo();
    // compound_type::enum_option_demo();
    compound_type::enum_option_match_demo();



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
