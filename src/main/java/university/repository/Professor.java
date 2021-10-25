package university.repository;

import javax.persistence.*;

@Entity(name="professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "human_id")
    private Human human;

    public Long getId() {
        return id;
    }

    public void setId(Long ID) {
        this.id = ID;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }
}
