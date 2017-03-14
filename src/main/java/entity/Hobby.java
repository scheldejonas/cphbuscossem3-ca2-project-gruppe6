package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hobby
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name, description;

    @ManyToMany
    List<Person> people = new ArrayList<>();

    public Hobby()
    {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
