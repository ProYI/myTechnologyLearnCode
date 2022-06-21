package online.proyi.designPatterns._5_Builder;

public class CourseActualBuilder extends CourseBuilder {
    Course course = new Course();

    @Override
    public void buildCourseName(String courseName) {
         course.setCourseName(courseName);
    }

    @Override
    public void buildCourseVideo(String courseVideo) {
        course.setCourseVideo(courseVideo);
    }

    @Override
    public void buildCourseAraticle(String courseArticle) {
        course.setCourseArticle(courseArticle);
    }

    @Override
    public Course makeCourse() {
        return course;
    }
}
