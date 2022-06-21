package online.proyi.designPatterns._5_Builder;

public class Coach {
    CourseBuilder courseBuilder;

    public void setCourseBuilder(CourseBuilder courseBuilder) {
        this.courseBuilder = courseBuilder;
    }

    public Course makeCourse(String courseName,
                             String courseVideo,
                             String courseArticle) {
        this.courseBuilder.buildCourseName(courseName);
        this.courseBuilder.buildCourseVideo(courseVideo);
        this.courseBuilder.buildCourseAraticle(courseArticle);
        return this.courseBuilder.makeCourse();
    }
}
