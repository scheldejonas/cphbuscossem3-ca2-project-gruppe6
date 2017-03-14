package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person extends Info
{
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "people")
    private List<Hobby> hobbies = new ArrayList<>();

    public Person()
    {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }

}
