package entity;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName, lastName;

    @ManyToMany
    ArrayList<Hobby> hobbies;

    public Person()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public ArrayList<Hobby> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(ArrayList<Hobby> hobbies)
    {
        this.hobbies = hobbies;
    }
}
