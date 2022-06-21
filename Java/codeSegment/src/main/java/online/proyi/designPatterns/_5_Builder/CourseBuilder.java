package online.proyi.designPatterns._5_Builder;

public abstract class CourseBuilder {
    public abstract void buildCourseName(String courseName);
    public abstract void buildCourseVideo(String courseVideo);
    public abstract void buildCourseAraticle(String courseArticle);

    public abstract Course makeCourse();
}
