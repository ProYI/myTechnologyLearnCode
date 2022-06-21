package online.proyi.normal.test.jdk11To17;

/**
 * Switch表达式 使用方式增强
 */
public class NewSwitchDemo {
    public static void main(String[] args) {
        for (int i = 1; i <= 7; i++) {
            int flag = i;
            String dateStr = switch (flag) {
                case 1 -> "星期一";
                case 2 -> "星期二";
                case 3 -> "星期三";
                case 4 -> "星期四";
                case 5 -> "星期五";
                case 6 -> "星期六";
                case 7 -> "星期天";
                default -> "";
            };
            String dataRange = switch (flag) {
                case 1,2,3,4,5 -> "周内";
                case 6,7 -> "周末";
                default -> "";
            };
            System.out.println("今天是" + dateStr + ", 今天是" + dataRange);
        }
    }
}
