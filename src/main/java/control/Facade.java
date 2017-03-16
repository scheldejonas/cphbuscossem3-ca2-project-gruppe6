package control;


import config.DataConfig;
import dao.CityInfoDao;
import dao.PersonDao;
import entity.Address;
import entity.CityInfo;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Facade {

    private EntityManager manager;
    private PersonDao personDao;
    private CityInfoDao cityDao;

    public Facade() {
        EntityManagerFactory factory = DataConfig.getSingleton().getEntityManagerFactory();
        manager = factory.createEntityManager();
        personDao = new PersonDao(manager);
        cityDao = new CityInfoDao(manager);

    }

    public CityInfo findSingleCity(String zipCode) {
        CityInfo info = personDao.findSingleCity(zipCode);
        return info;
    }

    public ArrayList<Person> findPeopleFromZipcode(String zipcode) {
        return personDao.findPeopleFromZipcode(zipcode);
    }

    public ArrayList<Person> findPeopleFromAddress(String address) {
        return personDao.findPeopleFromAddress(address);
    }

    public ArrayList<Person> findPeopleFromPhone(String phone) {
        return personDao.findPeopleFromPhone(phone);
    }

    public ArrayList<Person> findPeopleFromHobby(String hobby) {
        return personDao.findPeopleFromHobby(hobby);
    }

    public Person getPersonByID(String id) {
        return personDao.getPersonByID(id);
    }

    public ArrayList<CityInfo> findAllCities(){return cityDao.findAllCities();}

    public ArrayList<String> testArraylist() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("This is #" + i);
        }
        return list;
    }

    public void addNewPerson() {
        Person p = new Person();
        //Address address = new Address();
        //address.setCityInfo(test("0800"));
        //address.setStreet("Test Street");
        p.setEmail("Test@Email.email");
        p.setFirstName("Test1");
        p.setLastName("Test1");
       // p.setAddress(address);
        manager.getTransaction().begin();
        //manager.persist(address);
        manager.persist(p);
        manager.getTransaction().commit();
    }


    public void deleteAddress(int personID) {
        Query personQ = manager.createQuery("SELECT p FROM Person p WHERE p.id = :id");
        personQ.setParameter("id", personID);
        Person p = (Person) personQ.getSingleResult();
        try {
            manager.getTransaction().begin();
            manager.remove(p);
            manager.remove(p.getAddress());
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            //throw new ServerException("Error deleting person or address");
        }
    }

}