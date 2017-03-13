package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance
public abstract class Info
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "info")
    private List<Phone> phones;

    public Info()
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address adress)
    {
        this.address = adress;
    }

    public List<Phone> getPhones()
    {
        return phones;
    }

    public void setPhones(List<Phone> phones)
    {
        this.phones = phones;
    }

}
