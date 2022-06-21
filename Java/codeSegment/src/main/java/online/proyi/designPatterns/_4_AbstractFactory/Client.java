package online.proyi.designPatterns._4_AbstractFactory;

public class Client {
    public static void main(String[] args) {
        //创建抽象工厂对象
        AbstractFactory abstractFactory = new ConcreteFactory1();
        //通过抽象工厂来获取一系列的对象，如产品A和产品B
        AbstractProductA abstractProductA = abstractFactory.createProductA();
        AbstractProductB abstractProductB = abstractFactory.createProductB();
    }
}
