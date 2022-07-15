package online.proyi.codeSegment.jvm.bytecodeEnhancements;

// 引用外部包，不要引用JDK内的包: jdk.internal.org.objectweb.asm
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * 利用ASM实现AOP，需要定义两个类：
 * 一个是MyClassVisitor类，用于对字节码的visit以及修改；
 * 另一个是Generator类，在这个类中定义ClassReader和ClassWriter
 *
 * 其中的逻辑是，classReader读取字节码，然后交给MyClassVisitor类处理，处理完成后由ClassWriter写字节码并将旧的字节码替换掉
 *
 *
 * 利用ASM的CoreAPI来增强类
 * 增强后，期望的是，process()方法执行前输出“start”，之后输出”end”
 */
public class Generator {
    public static void main(String[] args) throws Exception {

        //读取
        ClassReader classReader = new ClassReader("online/proyi/codeSegment/jvm/bytecodeEnhancements/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        //输出
        File f = new File("Java/codeSegment/target/classes/online/proyi/codeSegment/jvm/bytecodeEnhancements/Base.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");

        Base base = new Base();
        base.process();
    }
}
