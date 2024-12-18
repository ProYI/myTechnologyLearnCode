package online.proyi.normal.test.problem.lambda.supplier;

import online.proyi.normal.test.problem.entity.Employee;

import java.util.function.Supplier;

public class SupplierDemo1 {
    public static void main(String[] args) {
        // 使用 Supplier 接口生成一个随机员工
        Supplier<Employee> randomEmployee = () -> new Employee("Random", (int) (Math.random() * 50), Math.random() * 10000);

        Employee employee = randomEmployee.get();
        System.out.println("Generated employee: " + employee.getName() + ", Age: " + employee.getAge() + ", Salary: " + employee.getSalary());
    }
}
