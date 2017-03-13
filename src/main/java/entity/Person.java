package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Person extends Info
{
    private String firstName, lastName;

    @ManyToMany(mappedBy="people")
    List<Hobby> hobbies;

    public Person()
    {

    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(ArrayList<Hobby> hobbies)
    {
        this.hobbies = hobbies;
    }
}
