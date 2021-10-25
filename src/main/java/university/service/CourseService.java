package university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.repository.Course;
import university.repository.CourseRepository;
import university.service.DTO.CourseDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(
            CourseRepository courseRepository ) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> getAllCourses() {
        final List<Course> all = this.courseRepository.findAll();
        return all.stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }

    public CourseDTO mapToCourseDto(Course courseEntity) {
        CourseDTO createdCourse  = new CourseDTO();
        createdCourse.setId(courseEntity.getId());
        return createdCourse;
    }

    public CourseDTO getCourseById(Long courseId) {
        Optional<Course> courseEntity = this.courseRepository.findById(courseId);
        Course course = courseEntity.orElseThrow(() -> new RuntimeException("Course not found"));
        return this.mapToCourseDto(course);


    }

    public void createOrUpdateCourse(CourseDTO toCreate) {
        Course createOrUpdateMe = new Course();
        createOrUpdateMe.setId(toCreate.getId());
        this.courseRepository.save(createOrUpdateMe);
    }

    public void deleteCourseById(Long courseIdToDelete) {
        this.courseRepository.deleteById(courseIdToDelete);
    }

}
