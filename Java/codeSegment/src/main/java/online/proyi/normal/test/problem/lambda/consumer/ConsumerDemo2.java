package online.proyi.normal.test.problem.lambda.consumer;

import online.proyi.normal.test.problem.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo2 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 30, 5000D),
                new Employee("Alice", 25, 7000D)
        );

        Consumer<Employee> printName = e -> System.out.println("Name: " + e.getName());
        Consumer<Employee> printSalary = e -> System.out.println("Salary: " + e.getSalary());

        Consumer<Employee> printAll = printName.andThen(printSalary);
        employees.forEach(printAll);
    }
}
