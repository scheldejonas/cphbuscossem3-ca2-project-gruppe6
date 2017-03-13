package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CityInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String zipCode, city;

    @OneToMany(mappedBy = "cityInfo")
    private List<Address> addresses = new ArrayList<>();

    public CityInfo()
    {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
