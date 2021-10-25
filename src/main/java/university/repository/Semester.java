package university.repository;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity(name = "semesters")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String universityDept;

    @Column
    Integer universityYear;

    @Column
    Integer semesterNo;

    @Column
    Date startDate;

    @Column
    Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversityDept() {
        return universityDept;
    }

    public void setUniversityDept(String universityDept) {
        this.universityDept = universityDept;
    }

    public Integer getUniversityYear() {
        return universityYear;
    }

    public void setUniversityYear(Integer universityYear) {
        this.universityYear = universityYear;
    }

    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(id, semester.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
