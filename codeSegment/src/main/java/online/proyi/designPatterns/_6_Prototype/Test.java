package online.proyi.designPatterns._6_Prototype;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
         Mail mail = new Mail();
         mail.setContent("初始化模板");

        for (int i = 0; i < 10; i++) {
            Mail mailTemp = (Mail) mail.clone();
            mailTemp.setName("姓名" + i);
            mailTemp.setEmailAddress("姓名" + i + "@test.com");
            mailTemp.setContent("恭喜中奖");
            MailUtil.sendMail(mailTemp);
        }
        MailUtil.saveOriginMailRecord(mail);
    }
}
