package online.proyi.designPatterns._8_Facade;

public class QualifyService {
    public boolean isAvailable(PointGift pointGift) {
        System.out.println("校验" + pointGift.getName() + "积分资格通过，库存通过");
        return true;
    }
}
