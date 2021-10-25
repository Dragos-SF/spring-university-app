package university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.service.DTO.SemesterDTO;
import university.service.SemesterService;

import java.util.List;

@RestController
public class SemesterController {

    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }


    @GetMapping("/api/semesters")
    public ResponseEntity<List<SemesterDTO>> getAllSemesters() {
        return ResponseEntity.ok(semesterService.getAllSemesters());
    }

    @GetMapping("/api/semesters/{id}")
    public ResponseEntity<SemesterDTO> getSemestersById(@PathVariable("id") Long semesterId) {
        return ResponseEntity.ok(semesterService.getSemesterById(semesterId));
    }

    @PostMapping("/api/semesters")
    public ResponseEntity createOrUpdateSemester(@RequestBody SemesterDTO semesterRequest) {
        this.semesterService.createOrUpdateSemester(semesterRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/semesters/{id}")
    public void deleteSemesterById(@PathVariable("id") Long semesterId) {
        this.semesterService.deleteSemesterById(semesterId);
    }

}
