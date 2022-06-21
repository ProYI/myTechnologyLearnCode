package online.proyi.designPatterns._7_Decorator;

/**
 * 煎饼
 */
public class Pancake extends APancake {
    @Override
    public String getDesc() {
        return "杂粮煎饼";
    }

    @Override
    public int getCost() {
        return 8 ;
    }
}
