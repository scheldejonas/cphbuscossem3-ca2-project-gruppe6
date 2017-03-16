package dao;

import config.DataConfig;
import entity.CityInfo;
import entity.Person;
import entity.Phone;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.lang.reflect.Array;
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

    public ArrayList<Person> findPeopleFromAddress(String street) {
        Query q = manager.createQuery("SELECT p FROM Person p WHERE p.address.street = :street");
        q.setParameter("street", street);
        return (ArrayList<Person>) q.getResultList();
    }

    public ArrayList<Person> findPeopleFromPhone(String phone) {
        Query q = manager.createQuery("SELECT p FROM Person p, Phone ph WHERE ph.info.id = p.id AND ph.number = :phone");
        q.setParameter("phone", phone);
        return (ArrayList<Person>) q.getResultList();
    }

    public ArrayList<Person> findPeopleFromHobby(String hobby) {
        Query q = manager.createQuery("SELECT p FROM Person p, Hobby h WHERE p MEMBER OF h.people AND h.name = :hobby");
        q.setParameter("hobby", hobby);
        return (ArrayList<Person>) q.getResultList();
    }

    public Person getPersonByID(String id) {
        Query q = manager.createQuery("SELECT p FROM Person p WHERE p.id = :id");
        q.setParameter("id", Integer.parseInt(id));
        return (Person) q.getSingleResult();
    }

    public CityInfo findSingleCity(String zipCode) {
        Query q = manager.createQuery("SELECT i FROM CityInfo i WHERE i.zipCode = :zipCode");
        q.setParameter("zipCode", zipCode);
        return (CityInfo) q.getSingleResult();
    }

    public void EditObjectInDatabase(Object o) {
        manager.getTransaction().begin();
        manager.merge(o);
        manager.getTransaction().commit();
    }

}