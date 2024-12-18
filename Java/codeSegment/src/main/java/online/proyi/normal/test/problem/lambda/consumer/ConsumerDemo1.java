package online.proyi.normal.test.problem.lambda.consumer;

import online.proyi.normal.test.problem.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo1 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 30, 5000D),
                new Employee("Alice", 25, 7000D)
        );

        // 使用 Consumer 遍历集合并打印姓名
        Consumer<Employee> printName = e -> System.out.println(e.getName());

        employees.forEach(printName);
    }
}
