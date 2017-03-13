package entity;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Hobby
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name, description;

    @ManyToMany
    ArrayList<Person> people;

    public Hobby()
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ArrayList<Person> getPeople()
    {
        return people;
    }

    public void setPeople(ArrayList<Person> people)
    {
        this.people = people;
    }
}
