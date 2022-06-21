package online.proyi.designPatterns._5_Builder;

public class Test {
    public static void main(String[] args) {
        CourseBuilder builder = new CourseActualBuilder();
        Coach coach = new Coach();

        coach.setCourseBuilder(builder);
        Course course = coach.makeCourse("课程名称", "课程视频", "课程文章");
        System.out.println(course);
    }
}
