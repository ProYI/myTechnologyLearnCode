package online.proyi.designPatterns._2_SimpleFactory;

public class Client {
    /**
     * 错误示例
     * 客户端使用Product时需要自行实例化对应子类
     */
//    public static void main(String[] args) {
//        int type = 1;
//        Product product;
//
//        if (type == 1) {
//            product = new ConcreteProduct();
//        } else if (type == 2) {
//            product = new ConcreteProduct2();
//        } else if (type == 3) {
//            product = new ConcreteProduct3();
//        }
//
//        // do something with product
//    }
    public static void main(String[] args) {
        int type = 1;
        Product product = new SimpleFactory().createProduct(type);
        product.doSomething();
        // do something with product
        Product product1 = new SimpleFactory().createProduct(ConcreteProduct.class);
        product1.doSomething();
    }
}
