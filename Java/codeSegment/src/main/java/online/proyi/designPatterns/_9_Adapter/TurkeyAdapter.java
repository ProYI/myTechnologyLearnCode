package online.proyi.designPatterns._9_Adapter;

public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    // 使用火鸡的叫声 冒充了 鸭子的叫声
    @Override
    public void quack() {
        turkey.gobble();
    }
}
