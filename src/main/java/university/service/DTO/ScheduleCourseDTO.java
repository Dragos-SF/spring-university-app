package university.service.DTO;

import university.repository.Course;

import java.util.Objects;

public class ScheduleCourseDTO {

    Long id;
    CourseDTO course;
    ProfessorDTO professor;
    SemesterDTO semester;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public ProfessorDTO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDTO professor) {
        this.professor = professor;
    }

    public SemesterDTO getSemester() {
        return semester;
    }

    public void setSemester(SemesterDTO semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleCourseDTO that = (ScheduleCourseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(course, that.course) && Objects.equals(professor, that.professor) && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, professor, semester);
    }
}
