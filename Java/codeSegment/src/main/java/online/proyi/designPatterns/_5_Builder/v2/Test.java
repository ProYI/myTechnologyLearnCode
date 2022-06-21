package online.proyi.designPatterns._5_Builder.v2;

public class Test {
    public static void main(String[] args) {
        Course course = new Course.Builder()
                .courseName("课程名称")
                .courseVideo("课程视频")
                .courseArticle("课程文章")
                .build();
        System.out.println(course);
    }
}
