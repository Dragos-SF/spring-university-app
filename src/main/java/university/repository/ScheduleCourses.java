package university.repository;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity(name= "scheduled_courses")
public class ScheduleCourses {

    @Id
    Long id;

    @OneToOne
    @JoinColumn(name = "course_id")
    Course course;

    @OneToOne
    @JoinColumn(name = "professor_id")
    Professor professor;

    @OneToOne
    @JoinColumn(name = "semester_id")
    Semester semester;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleCourses that = (ScheduleCourses) o;
        return Objects.equals(id, that.id) && Objects.equals(course, that.course) && Objects.equals(professor, that.professor) && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, professor, semester);
    }
}
