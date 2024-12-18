package online.proyi.normal.test.problem.lambda.function;

import online.proyi.normal.test.problem.entity.Employee;

import java.util.function.Function;

public class FunctionDemo1 {
    public static void main(String[] args) {
        Employee employee = new Employee("John", 30, 5000D);

        // 使用 Function 接口，将 Employee 对象的 salary 转换为 Double 类型
        Function<Employee, Double> salaryExtractor = Employee::getSalary;
        Double salary = salaryExtractor.apply(employee);
        System.out.println("Employee salary: " + salary);
    }
}
