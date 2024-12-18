package online.proyi.normal.test.problem.lambda.predicate;

import online.proyi.normal.test.problem.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo1 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 30, 5000D),
                new Employee("Alice", 25, 7000D),
                new Employee("Bob", 35, 6000D),
                new Employee("Charlie", 28, 4000D)
        );

        // 使用 Predicate 筛选出工资大于 5000 的员工
        Predicate<Employee> highSalary = e -> e.getSalary() > 5000;

        List<Employee> highSalaryEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (highSalary.test(employee)) {
                highSalaryEmployees.add(employee);
            }
        }

        System.out.println("Employees with high salary: " + highSalaryEmployees);
    }
}
