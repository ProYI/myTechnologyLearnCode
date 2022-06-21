package online.proyi.designPatterns._3_FactoryMethod;

public abstract class Factory {
    public abstract Product factoryMethod();

    public void doSomething() {
        Product product = factoryMethod();

        // do something with product
    }
}

class ConcreteProductFactory extends Factory {
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }

    @Override
    public void doSomething() {
        System.out.println("ConcreteProduct对象 do something");
    }
}

class ConcreteProduct2Factory extends Factory {
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct2();
    }

    @Override
    public void doSomething() {
        System.out.println("ConcreteProduct2对象 do something");
    }
}