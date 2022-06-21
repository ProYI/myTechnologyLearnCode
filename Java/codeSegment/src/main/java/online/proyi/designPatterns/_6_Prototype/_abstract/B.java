package online.proyi.designPatterns._6_Prototype._abstract;

public class B extends A {
    public static void main(String[] args) throws CloneNotSupportedException {
        B b = new B();
        b.clone();
    }
}
