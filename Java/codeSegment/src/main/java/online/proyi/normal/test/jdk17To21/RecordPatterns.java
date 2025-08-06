package online.proyi.normal.test.jdk17To21;

/**
 * record 模式匹配 + switch模式匹配
 */
public class RecordPatterns {
    public static void main(String[] args) {
        Point point = new Point(3, 4);
        handle(point);
    }


    static void handle(Object o) {
        switch (o) {
            case Point(int x, int y) -> System.out.println("Point at " + x + "," + y);
            case null -> System.out.println("null");
            default -> System.out.println("something else");
        }
    }
}

record Point(int x, int y) {}