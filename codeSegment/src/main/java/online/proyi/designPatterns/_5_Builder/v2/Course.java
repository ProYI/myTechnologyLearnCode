package online.proyi.designPatterns._5_Builder.v2;

public class Course {
    private String courseName;
    private String courseVideo;
    private String courseArticle;

    public Course(Builder builder) {
        this.courseName = builder.courseName;
        this.courseVideo = builder.courseVideo;
        this.courseArticle = builder.courseArticle;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseVideo='" + courseVideo + '\'' +
                ", courseArticle='" + courseArticle + '\'' +
                '}';
    }

    public static class Builder {
        private String courseName;
        private String courseVideo;
        private String courseArticle;

        public Builder courseName(String courseName) {
            this.courseName = courseName;
            return this;
        }
        public Builder courseVideo(String courseVideo) {
            this.courseVideo = courseVideo;
            return this;
        }
        public Builder courseArticle(String courseArticle) {
            this.courseArticle = courseArticle;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
