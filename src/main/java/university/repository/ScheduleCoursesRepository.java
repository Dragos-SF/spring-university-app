package university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ScheduleCoursesRepository extends JpaRepository<ScheduleCourses, Long> {

    Set<ScheduleCourses> findAllBySemesterId(Long semesterId);
}
