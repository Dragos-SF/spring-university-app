package university.service.DTO;

import java.util.Objects;

public class HumanDTO {


    Long id;

    String cnp;

    String lastname;

    String firstname;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanDTO humans = (HumanDTO) o;
        return Objects.equals(id, humans.id) && Objects.equals(cnp, humans.cnp) &&
                Objects.equals(lastname, humans.lastname) && Objects.equals(firstname, humans.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnp, lastname, firstname);
    }
}


