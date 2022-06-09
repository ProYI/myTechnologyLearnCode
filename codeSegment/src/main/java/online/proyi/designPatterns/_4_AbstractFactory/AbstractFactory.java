package online.proyi.designPatterns._4_AbstractFactory;

/**
 * 抽象工厂的接口，声明创建抽象产品对象的操作
 */
public interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

/**
 * 具体的工厂实现对象，实现创建具体的产品对象的操作
 */
class ConcreteFactory1 implements AbstractFactory {
    // ProductA1 与 ProductB1是有实际的关联意义的
    // 所以 实现ConcreteFactory1时，ProductA1 与 ProductB1是一组对象
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}