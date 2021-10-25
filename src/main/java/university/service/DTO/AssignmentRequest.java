package university.service.DTO;

import java.util.Set;

public class AssignmentRequest {
    Set<Long> studentIDs;
    String universityDept;
    Integer universityYear;
    Integer semesterNo;

    public Set<Long> getStudentIDs() {
        return studentIDs;
    }

    public void setStudentIDs(Set<Long> studentIDs) {
        this.studentIDs = studentIDs;
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
}
