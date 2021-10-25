package university.service.DTO;

import java.util.Set;

public class AssignmentResponse {

    private Long noAssignedStudents;
    private Set<String> unassignedStudents;
    private Long noStudents;

    public AssignmentResponse(Long noAssignedStudents, Set<String> unassignedStudents, Long noStudents) {
        this.noAssignedStudents = noAssignedStudents;
        this.unassignedStudents = unassignedStudents;
        this.noStudents = noStudents;
    }

    public Long getNoAssignedStudents() {
        return noAssignedStudents;
    }

    public void setNoAssignedStudents(Long noAssignedStudents) {
        this.noAssignedStudents = noAssignedStudents;
    }

    public Set<String> getUnassignedStudents() {
        return unassignedStudents;
    }

    public void setUnassignedStudents(Set<String> unassignedStudents) {
        this.unassignedStudents = unassignedStudents;
    }

    public Long getNoStudents() {
        return noStudents;
    }

    public void setNoStudents(Long noStudents) {
        this.noStudents = noStudents;
    }
}
