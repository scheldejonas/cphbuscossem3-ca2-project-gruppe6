package entity;


import javax.persistence.*;

@Entity
public class Company extends Info
{
    private int numEmployees, marketValue;
    private String name, description, cvr;

    public Company()
    {
    }

    public int getNumEmployees()
    {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees)
    {
        this.numEmployees = numEmployees;
    }

    public int getMarketValue()
    {
        return marketValue;
    }

    public void setMarketValue(int marketValue)
    {
        this.marketValue = marketValue;
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

    public String getCvr()
    {
        return cvr;
    }

    public void setCvr(String cvr)
    {
        this.cvr = cvr;
    }

}
