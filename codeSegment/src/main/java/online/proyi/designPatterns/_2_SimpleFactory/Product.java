package online.proyi.designPatterns._2_SimpleFactory;

public interface Product {
    void doSomething();
}

class ConcreteProduct implements Product {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProduct doSomething");
    }
}

class ConcreteProduct2 implements Product {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProduct2 doSomething");
    }
}

class ConcreteProduct3 implements Product {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProduct3 doSomething");
    }
}