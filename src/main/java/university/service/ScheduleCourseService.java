package university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.repository.*;
import university.service.DTO.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleCourseService {

    private final ScheduleCoursesRepository scheduleCoursesRepository;
    private final CourseService courseService;
    private final ProfessorService professorService;
    private final SemesterService semesterService;
    private final StudentService studentService;

    @Autowired
    public ScheduleCourseService(
            ScheduleCoursesRepository scheduleCoursesRepository,
            CourseService courseService,
            ProfessorService professorService,
            SemesterService semesterService,
            StudentService studentService
            ) {
        this.scheduleCoursesRepository = scheduleCoursesRepository;
        this.courseService = courseService;
        this.professorService = professorService;
        this.semesterService = semesterService;
        this.studentService = studentService;

    }

    public List<ScheduleCourseDTO> getAllScheduleCourses() {
        final List<ScheduleCourses> all = this.scheduleCoursesRepository.findAll();
        return all.stream()
                .map(this::mapToScheduleCoursesDto)
                .collect(Collectors.toList());
    }

    private ScheduleCourseDTO mapToScheduleCoursesDto(ScheduleCourses scheduleCoursesEntity) {
        ScheduleCourseDTO createdScheduleCourse  = new ScheduleCourseDTO();
        createdScheduleCourse.setId(scheduleCoursesEntity.getId());

        Professor professor = scheduleCoursesEntity.getProfessor();
        ProfessorDTO professorDTO = professorService.mapToProfessorDto(professor);
        createdScheduleCourse.setProfessor(professorDTO);

        Course course = scheduleCoursesEntity.getCourse();
        CourseDTO courseDTO = courseService.mapToCourseDto(course);
        createdScheduleCourse.setCourse(courseDTO);

        Semester semester = scheduleCoursesEntity.getSemester();
        SemesterDTO semesterDTO = semesterService.mapToSemesterDto(semester);
        createdScheduleCourse.setSemester(semesterDTO);

        return createdScheduleCourse;
    }

    public ScheduleCourseDTO getScheduleCoursesById(Long scheduleCourseId) {
        Optional<ScheduleCourses> scheduleCoursesEntity = this.scheduleCoursesRepository.findById(scheduleCourseId);
        ScheduleCourses scheduleCourses = scheduleCoursesEntity.orElseThrow(() -> new RuntimeException("Schedule Courses not found"));
        return this.mapToScheduleCoursesDto(scheduleCourses);
    }

    public void createOrUpdateScheduleCourses(ScheduleCourseDTO toCreate) {
        ScheduleCourses createOrUpdateMe = new ScheduleCourses();
        createOrUpdateMe.setId(toCreate.getId());

        this.scheduleCoursesRepository.save(createOrUpdateMe);
    }

//    public void assignStudentsToCourses

    public void deleteScheduleCoursesById(Long scheduleCoursesIdToDelete) {
        this.scheduleCoursesRepository.deleteById(scheduleCoursesIdToDelete);
    }

    @Transactional
    public AssignmentResponse assignStudents(Set<Long> studentIDs, String universityDept, Integer universityYear, Integer semesterNo) {
        Semester semester = semesterService.findByUniversityDeptAndUniversityYearAndSemesterNo(universityDept, universityYear, semesterNo);
        Set<ScheduleCourses> scheduledCourses = scheduleCoursesRepository.findAllBySemesterId(semester.getId());
        Set<Student> assignedStudents = new HashSet<>();
        Set<String> unassignedStudents = new HashSet<>();
        studentIDs
                .stream()
                .map(studentService::getStudentById)
                .forEach(student -> {
                    var studentScheduledCourses = student.getScheduleCourses();
                    var unassignedCourses = scheduledCourses
                            .stream()
                            .filter(scheduleCourse -> !studentScheduledCourses.contains(scheduleCourse))
                            .collect(Collectors.toSet());
                    if (unassignedCourses.size() == 0) {
                        var studentHuman = student.getHuman();
                        unassignedStudents.add(studentHuman.getFirstname() + " " + studentHuman.getLastname());
                    }
                    else {
                        assignedStudents.add(student);
                        studentScheduledCourses.addAll(unassignedCourses);
                        studentService.save(student);
                    }
                });
        return new AssignmentResponse((long) assignedStudents.size(), unassignedStudents, (long) studentIDs.size());
    }

}
