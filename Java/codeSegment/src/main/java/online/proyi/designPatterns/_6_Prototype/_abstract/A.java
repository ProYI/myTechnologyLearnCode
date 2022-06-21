package online.proyi.designPatterns._6_Prototype._abstract;

/**
 * 使用抽象类实现原型模式
 */
public abstract class A implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
