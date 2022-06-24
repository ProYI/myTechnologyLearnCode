package online.proyi.designPatterns._8_Facade;

/**
 * 礼物兑换服务
 */
public class GiftExchangeService {
    private QualifyService qualifyService = new QualifyService();
    private PointPaymentService pointPaymentService = new PointPaymentService();
    private ShippingService shippingService = new ShippingService();

    public void giftExchange(PointGift pointGift) {
        if (qualifyService.isAvailable(pointGift)) {
            // 资格校验通过
            if (pointPaymentService.pay(pointGift)) {
                // 支付积分成功
                String shippingOrderNo = shippingService.shipGift(pointGift);
                System.out.println("物流系统下单成功，订单号是: " + shippingOrderNo);
            }
        }

    }
}
