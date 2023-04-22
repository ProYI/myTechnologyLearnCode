package online.proyi.designPatterns._9_Adapter;

public class Client {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);

        duck.quack();
    }
}
