package online.proyi.normal.test.jdk8To11;

import java.io.IOException;

/**
 * 进程API
 * 增加ProcessHandle 接口，可以对原生进程进行管理，尤其适合于管理长时间运行的进程
 * <p>
 * <p>
 * 在使用 ProcessBuilder 来启动一个进程之后，可以通过 Process.toHandle()方法来得到一个 ProcessHandl e 对象的实例。
 * 通过 ProcessHandle 可以获取到由 ProcessHandle.Info 表示的进程的基本信息，如命令行参数、可执行文件路径和启动时间等。
 * ProcessHandle 的 onExit()方法返回一个 CompletableFuture对象，可以在进程结束时执行自定义的动作
 */
public class ProcessHandleTest {
    public static void main(String[] args) throws IOException {
        final ProcessBuilder processBuilder = new ProcessBuilder("top").inheritIO();
        final ProcessHandle processHandle = processBuilder.start().toHandle();

        processHandle.onExit().whenCompleteAsync((handle, throwable) -> {
            if (throwable == null) {
                System.out.println(handle.pid());
            } else {
                throwable.printStackTrace();
            }
        });
    }
}
