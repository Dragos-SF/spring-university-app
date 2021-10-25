package university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.repository.Semester;
import university.repository.SemesterRepository;
import university.service.DTO.SemesterDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SemesterService {

    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(
            SemesterRepository semesterRepository ) {
        this.semesterRepository = semesterRepository;
    }

    public List<SemesterDTO> getAllSemesters() {
        final List<Semester> all = this.semesterRepository.findAll();
        return all.stream()
                .map(this::mapToSemesterDto)
                .collect(Collectors.toList());
    }

    public SemesterDTO mapToSemesterDto(Semester semesterEntity) {
        SemesterDTO createdSemester  = new SemesterDTO();
        createdSemester.setId(semesterEntity.getId());
        return createdSemester;
    }

    public SemesterDTO getSemesterById(Long semesterId) {
        Optional<Semester> semesterEntity = this.semesterRepository.findById(semesterId);
        Semester semester = semesterEntity.orElseThrow(() -> new RuntimeException("Semester not found"));
        return this.mapToSemesterDto(semester);
    }

    public Semester findByUniversityDeptAndUniversityYearAndSemesterNo(String universityDept, Integer universityYear, Integer semesterNo) {
        Optional<Semester> semesterOptional = this.semesterRepository.findByUniversityDeptAndUniversityYearAndSemesterNo(universityDept, universityYear, semesterNo);
        return semesterOptional.orElseThrow(() -> new RuntimeException("Could not find semester"));
    }

    public void createOrUpdateSemester(SemesterDTO toCreate) {
        Semester createOrUpdateMe = new Semester();
        createOrUpdateMe.setId(toCreate.getId());
        this.semesterRepository.save(createOrUpdateMe);
    }

    public void deleteSemesterById(Long semesterIdToDelete) {
        this.semesterRepository.deleteById(semesterIdToDelete);
    }


}
