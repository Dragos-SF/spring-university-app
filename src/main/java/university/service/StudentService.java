package university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.repository.Human;
import university.repository.Student;
import university.repository.StudentRepository;
import university.service.DTO.HumanDTO;
import university.service.DTO.StudentDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final HumanService humanService;

    @Autowired
    public StudentService(
            StudentRepository studentRepository,
            HumanService humanService
                          ) {
        this.studentRepository = studentRepository;
        this.humanService = humanService;
    }

    public List<StudentDTO> getAllStudents() {
        final List<Student> all = this.studentRepository.findAll();
        return all.stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }

    private StudentDTO mapToStudentDto(Student studentEntity) {
        StudentDTO createdStudent  = new StudentDTO();
        createdStudent.setId(studentEntity.getId());
        Human human =studentEntity.getHuman();
        HumanDTO humanDTO = humanService.mapToHumanDto(human);
    createdStudent.setHuman(humanDTO);
        return createdStudent;
    }

    public StudentDTO getStudentDTOById(Long studentId) {
        Optional<Student> studentEntity = this.studentRepository.findById(studentId);
        Student student = studentEntity.orElseThrow(() -> new RuntimeException("Student not found"));
        return this.mapToStudentDto(student);
    }

    public Student getStudentById(Long studentId) {
        Optional<Student> studentEntity = this.studentRepository.findById(studentId);
        return studentEntity.orElseThrow(() -> new RuntimeException("Student not found"));
    }


    public void createOrUpdateStudent(StudentDTO toCreate) {
        Student createOrUpdateMe = new Student();
        createOrUpdateMe.setId(toCreate.getId());

        this.studentRepository.save(createOrUpdateMe);
    }

    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    public void deleteStudentById(Long studentIdToDelete) {
        this.studentRepository.deleteById(studentIdToDelete);
    }



}
