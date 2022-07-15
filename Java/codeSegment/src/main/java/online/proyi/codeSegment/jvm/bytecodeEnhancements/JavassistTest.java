package online.proyi.codeSegment.jvm.bytecodeEnhancements;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Javassist实现字节码增强
 *
 * vm增加 --add-opens java.base/java.lang=ALL-UNNAMED
 */
public class JavassistTest {
    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("online.proyi.codeSegment.jvm.bytecodeEnhancements.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        cc.writeFile("Java/codeSegment/target/classes/online/proyi/codeSegment/jvm/bytecodeEnhancements");

        Class c = cc.toClass();
        Base h = (Base) c.newInstance();
        h.process();
    }
}
