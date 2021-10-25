package university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.message.QueueSender;
import university.service.DTO.AssignmentRequest;
import university.service.DTO.AssignmentResponse;
import university.service.DTO.ScheduleCourseDTO;
import university.service.ScheduleCourseService;

import java.util.Date;
import java.util.List;

@RestController
public class ScheduleCoursesController {

    private final ScheduleCourseService scheduleCourseService;
    private final QueueSender queueSender;

    @Autowired
    public ScheduleCoursesController(ScheduleCourseService scheduleCourseService, QueueSender queueSender) {
        this.scheduleCourseService = scheduleCourseService;
        this.queueSender = queueSender;
    }


    @GetMapping("/api/scheduleCourses")
    public ResponseEntity<List<ScheduleCourseDTO>> getAllCourses() {
        return ResponseEntity.ok(scheduleCourseService.getAllScheduleCourses());
    }

    @GetMapping("/api/scheduleCourses/{id}")
    public ResponseEntity<ScheduleCourseDTO> getScheduleCoursesById(@PathVariable("id") Long scheduleCourseId) {
        return ResponseEntity.ok(scheduleCourseService.getScheduleCoursesById(scheduleCourseId));
    }

    @PostMapping("/api/scheduleCourses")
    public ResponseEntity createOrUpdateCourses(@RequestBody ScheduleCourseDTO scheduleCourseRequest) {
        this.scheduleCourseService.createOrUpdateScheduleCourses(scheduleCourseRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/scheduleCourses/{id}")
    public void deleteCourseById(@PathVariable("id") Long courseId) {
        this.scheduleCourseService.deleteScheduleCoursesById(courseId);
    }

    @PostMapping("/api/assign-courses")
    public ResponseEntity<AssignmentResponse> createOrUpdateCourses(@RequestBody AssignmentRequest assignmentRequest) {
        AssignmentResponse assignmentResponse = this.scheduleCourseService.assignStudents(
                assignmentRequest.getStudentIDs(),
                assignmentRequest.getUniversityDept(),
                assignmentRequest.getUniversityYear(),
                assignmentRequest.getSemesterNo()
        );
        Long numberOfAssignedStudents = assignmentResponse.getNoAssignedStudents();
        String message = numberOfAssignedStudents + " students were assigned";
        this.queueSender.send(message);
        return ResponseEntity.ok(assignmentResponse);
    }

}
