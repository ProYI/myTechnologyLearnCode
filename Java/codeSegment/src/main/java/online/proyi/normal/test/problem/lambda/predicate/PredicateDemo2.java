package online.proyi.normal.test.problem.lambda.predicate;

import online.proyi.normal.test.problem.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo2 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 30, 5000D),
                new Employee("Alice", 25, 7000D),
                new Employee("Bob", 35, 6000D),
                new Employee("Charlie", 28, 4000D)
        );

        Predicate<Employee> highSalary = e -> e.getSalary() > 5000;
        Predicate<Employee> youngEmployee = e -> e.getAge() < 30;

        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (highSalary.and(youngEmployee).test(employee)) {
                filteredEmployees.add(employee);
            }
        }

        System.out.println("Young employees with high salary: " + filteredEmployees);
    }
}
