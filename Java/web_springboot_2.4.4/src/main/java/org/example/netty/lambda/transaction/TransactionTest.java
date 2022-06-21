package org.example.netty.lambda.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionTest {
    public static void main(String[] args) throws JsonProcessingException {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）
        List<Transaction> list1 = transactions.stream()
                .filter(e -> e.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(objectWriter.writeValueAsString(list1));
        System.out.println("----------------------------------------");
        // (2) 交易员都在哪些不同的城市工作过？
        List<String> citys = transactions.stream().map(Transaction::getTrader).map(Trader::getCity)
                // 不加distinct就用set
                .distinct()
                .collect(Collectors.toList());
        System.out.println(objectWriter.writeValueAsString(citys));
        System.out.println("----------------------------------------");
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> traders = transactions.stream().map(Transaction::getTrader)
                .filter(e -> e.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(objectWriter.writeValueAsString(traders));
        System.out.println("----------------------------------------");
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        String traderNameStr = transactions.stream()
                .map(Transaction::getTrader).map(Trader::getName)
                .sorted().collect(Collectors.joining());
        System.out.println(traderNameStr);
        System.out.println("----------------------------------------");
        // (5) 有没有交易员是在米兰工作的？
//        boolean result1 = transactions.stream().map(Transaction::getTrader).filter(e -> "Milan".equals(e.getCity())).findAny().isPresent();
        boolean result1 = transactions.stream().anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println(result1);
        System.out.println("----------------------------------------");
        // (6) 打印生活在剑桥的交易员的所有交易额。
//        transactions.stream().filter(e -> e.getTrader().getCity().equals("Cambridge")).forEach(e -> System.out.println(e.getValue()));
        transactions.stream().filter(e -> e.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
        System.out.println("----------------------------------------");
        // (7) 所有交易中，最高的交易额是多少？
        // comparator和reduce都可以使用
//        Optional<Integer> max = transactions.stream().max(Comparator.comparingInt(Transaction::getValue)).map(Transaction::getValue);
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println(max.get());
        System.out.println("----------------------------------------");

        // (8) 找到交易额最小的交易。
//        Optional<Transaction> transaction = transactions.stream().sorted(Comparator.comparingInt(Transaction::getValue)).findFirst();
        Optional<Transaction> transaction = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(objectWriter.writeValueAsString(transaction.get()));
        System.out.println("----------------------------------------");
    }
}
