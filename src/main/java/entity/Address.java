package entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street, additionalInfo;

    @OneToMany(mappedBy = "address")
    private List<Info> infos;

    @ManyToOne
    private CityInfo cityInfo;

    public Address()
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

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

    public CityInfo getCityInfo()
    {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo)
    {
        this.cityInfo = cityInfo;
    }

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }
}
