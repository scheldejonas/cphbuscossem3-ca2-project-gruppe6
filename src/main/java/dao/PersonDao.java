package dao;

import config.DataConfig;
import entity.CityInfo;
import entity.Person;
import entity.Phone;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    EntityManager manager;

    public PersonDao(EntityManager manager) {
        this.manager = manager;
    }

    public ArrayList<Person> findPeopleFromZipcode(String zipcode) {
        Query q = manager.createQuery("SELECT p FROM Person p WHERE p.address.cityInfo.zipCode = :zipCode");
        q.setParameter("zipCode", zipcode);
        return (ArrayList<Person>) q.getResultList();
    }

    public Person findPeopleFromAddress() {
        return null;
    }

    public Person findPeopleFromPhone() {
        return null;
    }

    public Person findPeopleFromHobby() {
        return null;
    }

    public CityInfo findSingleCity(String zipCode) {
        Query q = manager.createQuery("SELECT i FROM CityInfo i WHERE i.zipCode = :zipCode");
        q.setParameter("zipCode", zipCode);
        return (CityInfo) q.getSingleResult();
    }

}