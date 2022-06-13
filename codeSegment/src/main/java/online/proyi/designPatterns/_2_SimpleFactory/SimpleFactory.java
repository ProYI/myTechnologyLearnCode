package online.proyi.designPatterns._2_SimpleFactory;

import java.lang.reflect.InvocationTargetException;

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

    // 如果简单工厂中增加新的类型需要修改代码
    // 使用反射来实现不修改代码
    public Product createProduct(Class c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }
}
