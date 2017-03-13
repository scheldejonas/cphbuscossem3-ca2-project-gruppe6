package entity;

import javax.persistence.*;

@Entity
public class Info
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;

    @ManyToOne
    private Address adress;

    @OneToMany
    Phone phone;

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

    public Address getAdress()
    {
        return adress;
    }

    public void setAdress(Address adress)
    {
        this.adress = adress;
    }

    public Phone getPhone()
    {
        return phone;
    }

    public void setPhone(Phone phone)
    {
        this.phone = phone;
    }
}
