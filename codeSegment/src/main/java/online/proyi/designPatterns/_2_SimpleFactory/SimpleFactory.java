package online.proyi.designPatterns._2_SimpleFactory;

public class SimpleFactory {
    public Product createProduct(int type) {
        if (type == 1) {
            return new ConcreteProduct();
        } else if (type == 2) {
            return new ConcreteProduct2();
        } else if (type == 3) {
            return new ConcreteProduct3();
        }
        return null;
    }
}
