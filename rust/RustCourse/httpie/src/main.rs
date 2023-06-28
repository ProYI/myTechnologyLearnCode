use std::str::FromStr;

use anyhow::{anyhow, Result};
use clap::{Parser, Subcommand};
use reqwest::Url;

#[derive(Parser, Debug)]
#[command(author = "pc", version = "0.0.1", about, long_about = None)]
struct Opts {
    #[command(subcommand)]
    subcmd: SubCommand,
}

#[derive(Subcommand, Debug)]
enum SubCommand {
    Get(Get),
    Post(Post),
}

// GET请求
#[derive(Parser, Debug, Clone)]
struct Get {
    /// HTTP 请求的 URL
    #[clap(value_parser(parse_url))]
    url: String,
}

fn parse_url(s: &str) -> Result<String> {
    // 检查url是否合法
    let _url: Url = s.parse()?;
    Ok(s.into())
}

// POST 请求
#[derive(Parser, Debug, Clone)]
struct Post {
    /// HTTP 请求的 URL
    #[clap(value_parser(parse_url))]
    url: String,
    /// HTTP 请求的 body
    #[clap(value_parser(parse_kv_pair))]
    body: Vec<KvPair>,
}

/// 命令行中的 key=value 可以通过 parse_kv_pair 解析成 KvPair 结构
#[derive(Debug, Clone)]
struct KvPair {
    k: String,
    v: String,
}

/// 当我们实现 FromStr trait 后，可以用 str.parse() 方法将字符串解析成 KvPair
impl FromStr for KvPair {
    type Err = anyhow::Error;
    fn from_str(s: &str) -> Result<Self, Self::Err> {
        // 使用 = 进行 split，这会得到一个迭代器
        let mut split  = s.split("=");
        let err = || anyhow!(format!("Failed to parse {}", s));
        Ok(Self {
            // 从迭代器中取第一个结果作为 key，迭代器返回 Some(T)/None
            // 我们将其转换成 Ok(T)/Err(E)，然后用 ? 处理错误
            k : (split.next().ok_or_else(err)?).to_string(),
            // 从迭代器中取第二个结果作为 value
            v : (split.next().ok_or_else(err)?).to_string(),
        })
    }
}

/// 因为我们为 KvPair 实现了 FromStr，这里可以直接 s.parse() 得到 KvPair
fn parse_kv_pair(s: &str) -> Result<KvPair> {
    Ok(s.parse()?)
}

fn main() {
    let opts: Opts = Opts::parse();
    println!("{:?}", opts);
}
