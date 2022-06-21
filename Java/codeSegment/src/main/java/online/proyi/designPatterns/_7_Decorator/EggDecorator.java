package online.proyi.designPatterns._7_Decorator;

/**
 * 具体装饰实现类: 加鸡蛋
 */
public class EggDecorator extends AbstractDecorator {

    public EggDecorator(APancake aPancake) {
        super(aPancake);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " 加鸡蛋";
    }

    @Override
    public int getCost() {
        return super.getCost() + 1;
    }
}
