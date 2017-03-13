package dao;

import config.DataConfig;
import entity.Person;
import entity.Phone;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class PersonDao
{
    private static final PersonDao singleton = new PersonDao();

    private PersonDao()
    {

    }

    public static PersonDao getSingleton() {
        return singleton;
    }

    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            people = entityManager.createQuery("select p from Phone p").getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return people;
    }

    public Phone findById(Long phoneId) {
        Phone foundPhone = null;
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            foundPhone = entityManager.find(Phone.class, phoneId);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return foundPhone;
    }
}
