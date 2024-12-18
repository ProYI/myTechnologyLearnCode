package online.proyi.normal.test.problem.lambda.function;

import online.proyi.normal.test.problem.entity.Employee;

import java.util.function.Function;

public class FunctionDemo2 {
    public static void main(String[] args) {
        Employee employee = new Employee("John", 30, 5000D);

        Function<Employee, Double> salaryExtractor = Employee::getSalary;
        Function<Double, Double> increaseSalary = salary -> salary * 1.1;
        Function<Double, String> formatSalary = salary -> "$" + String.format("%.2f", salary);

        // 使用 andThen 链式调用
        String finalSalary = salaryExtractor
                .andThen(increaseSalary)
                .andThen(formatSalary)
                .apply(employee);

        System.out.println("Employee new salary: " + finalSalary);
    }
}
