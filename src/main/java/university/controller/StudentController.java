package university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import university.service.DTO.StudentDTO;

import university.service.StudentService;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/api/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/api/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long studentId) {
        return ResponseEntity.ok(studentService.getStudentDTOById(studentId));
    }

    @PostMapping("/api/students")
    public ResponseEntity createOrUpdateStudent(@RequestBody StudentDTO studentRequest) {
        this.studentService.createOrUpdateStudent(studentRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/students/{id}")
    public void deleteStudentById(@PathVariable("id") Long studentId) {
        this.studentService.deleteStudentById(studentId);
    }




}
