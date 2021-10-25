package university.repository;

import javax.persistence.*;
import java.util.Set;

@Entity(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "human_id")
    private Human human;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "scheduled_course_id")
    )
    private Set<ScheduleCourses> scheduleCourses;

    public Long getId() {
        return id;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public void setId(Long ID) {
        this.id = ID;
    }

    public Set<ScheduleCourses> getScheduleCourses() {
        return scheduleCourses;
    }

    public void setScheduleCourses(Set<ScheduleCourses> scheduleCourses) {
        this.scheduleCourses = scheduleCourses;
    }
}
