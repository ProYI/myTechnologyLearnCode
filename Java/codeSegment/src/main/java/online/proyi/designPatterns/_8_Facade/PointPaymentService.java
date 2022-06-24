package online.proyi.designPatterns._8_Facade;

/**
 * 积分支付
 */
public class PointPaymentService {
    public boolean pay(PointGift pointGift) {
        // 扣减支付
        System.out.println("支付" + pointGift.getName() + "积分成功");
        return true;
    }
}
