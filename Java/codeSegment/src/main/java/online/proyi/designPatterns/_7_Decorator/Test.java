package online.proyi.designPatterns._7_Decorator;

public class Test {
    public static void main(String[] args) {
        APancake pancake = new Pancake();

        // 加了2个鸡蛋 1个香肠
        pancake = new EggDecorator(pancake);
        pancake = new EggDecorator(pancake);
        pancake = new SausageDecorator(pancake);
        System.out.println(pancake.getDesc() + " 销售价格: " + pancake.getCost());
    }
}
