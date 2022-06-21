package online.proyi.designPatterns._3_FactoryMethod;

public class Client {
    public static void main(String[] args) {
        ConcreteProductFactory productFactory = new ConcreteProductFactory();
        productFactory.doSomething();

        ConcreteProduct2Factory product2Factory = new ConcreteProduct2Factory();
        product2Factory.doSomething();
    }
}
