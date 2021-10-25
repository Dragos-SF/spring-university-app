package university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.repository.Human;
import university.repository.Professor;
import university.repository.ProfessorRepository;
import university.service.DTO.HumanDTO;
import university.service.DTO.ProfessorDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final HumanService humanService;

    @Autowired
    public ProfessorService(
            ProfessorRepository professorRepository,
            HumanService humanService
    ) {
        this.professorRepository = professorRepository;
        this.humanService = humanService;
    }

    public List<ProfessorDTO> getAllProfessors() {
        final List<Professor> all = this.professorRepository.findAll();
        return all.stream()
                .map(this::mapToProfessorDto)
                .collect(Collectors.toList());
    }

    public ProfessorDTO mapToProfessorDto(Professor professorEntity) {
        ProfessorDTO createdProfessor  = new ProfessorDTO();
        createdProfessor.setId(professorEntity.getId());
        Human human =professorEntity.getHuman();
        HumanDTO humanDTO = humanService.mapToHumanDto(human);
        createdProfessor.setHuman(humanDTO);
        return createdProfessor;
    }

    public ProfessorDTO getProfessorById(Long professorId) {
        Optional<Professor> professorEntity = this.professorRepository.findById(professorId);
        Professor professor = professorEntity.orElseThrow(() -> new RuntimeException("Professor not found"));
        return this.mapToProfessorDto(professor);


    }

    public void createOrUpdateProfessor(ProfessorDTO toCreate) {
        Professor createOrUpdateMe = new Professor();
        createOrUpdateMe.setId(toCreate.getId());

        this.professorRepository.save(createOrUpdateMe);
    }

    public void deleteProfessorById(Long professorIdToDelete) {
        this.professorRepository.deleteById(professorIdToDelete);
    }


}
