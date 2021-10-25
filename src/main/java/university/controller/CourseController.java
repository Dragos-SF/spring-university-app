package university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.service.CourseService;
import university.service.DTO.CourseDTO;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/api/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/api/courses/{id}")
    public ResponseEntity<CourseDTO> getCoursesById(@PathVariable("id") Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PostMapping("/api/courses")
    public ResponseEntity createOrUpdateCourses(@RequestBody CourseDTO courseRequest) {
        this.courseService.createOrUpdateCourse(courseRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/courses/{id}")
    public void deleteCourseById(@PathVariable("id") Long courseId) {
        this.courseService.deleteCourseById(courseId);
    }


}
