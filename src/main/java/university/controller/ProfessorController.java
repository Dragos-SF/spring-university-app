package university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.service.DTO.ProfessorDTO;
import university.service.ProfessorService;

import java.util.List;

@RestController
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


    @GetMapping("/api/professors")
    public ResponseEntity<List<ProfessorDTO>> getAllProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @GetMapping("/api/professors/{id}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable("id") Long professorId) {
        return ResponseEntity.ok(professorService.getProfessorById(professorId));
    }

    @PostMapping("/api/professors")
    public ResponseEntity createOrUpdateProfessor(@RequestBody ProfessorDTO professorRequest) {
        this.professorService.createOrUpdateProfessor(professorRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/professors/{id}")
    public void deleteProfessorById(@PathVariable("id") Long professorId) {
        this.professorService.deleteProfessorById(professorId);
    }
    
}
