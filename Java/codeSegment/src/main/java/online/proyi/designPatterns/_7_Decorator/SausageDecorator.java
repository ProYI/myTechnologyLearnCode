package online.proyi.designPatterns._7_Decorator;

/**
 * 具体装饰实现类: 加香肠
 */
public class SausageDecorator extends AbstractDecorator {
    public SausageDecorator(APancake aPancake) {
        super(aPancake);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " 加香肠";
    }

    @Override
    public int getCost() {
        return super.getCost() + 2;
    }
}
