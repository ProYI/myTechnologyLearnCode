package online.proyi.designPatterns._7_Decorator;

/**
 * AbstractDecorator是不是是抽象类需根据业务场景区分
 *
 * 如果没有定义抽象方法 不是抽象类也可以
 * 如果定义了一些子类必须实现的方法，则需要定义为抽象类
 */
public class AbstractDecorator extends APancake {
    private APancake aPancake;

    public AbstractDecorator(APancake aPancake) {
        this.aPancake = aPancake;
    }

    @Override
    public String getDesc() {
        return this.aPancake.getDesc();
    }

    @Override
    public int getCost() {
        return this.aPancake.getCost();
    }
}
