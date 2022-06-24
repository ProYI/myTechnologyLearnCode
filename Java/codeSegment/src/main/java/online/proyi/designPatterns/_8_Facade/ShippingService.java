package online.proyi.designPatterns._8_Facade;

/**
 * 物流子系统
 */
public class ShippingService {
    public String shipGift(PointGift pointGift) {
        // 物流对接逻辑
        System.out.println(pointGift.getName() + "进入物流系统");
        String shippingOrderNo = "12345678";
        return shippingOrderNo;
    }
}
