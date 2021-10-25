package university.service.DTO;

import java.util.Objects;

public class StudentDTO {

    Long id;
    HumanDTO human;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HumanDTO getHuman() {
        return human;
    }

    public void setHuman(HumanDTO human) {
        this.human = human;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(human, that.human);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, human);
    }
}
