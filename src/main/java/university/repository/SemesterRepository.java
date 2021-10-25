package university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {

    Optional<Semester> findByUniversityDeptAndUniversityYearAndSemesterNo(String universityDept, Integer universityYear, Integer semesterNo);
}
