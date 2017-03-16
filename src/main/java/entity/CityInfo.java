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

    public CityInfo()
    {
    }


    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
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

}
