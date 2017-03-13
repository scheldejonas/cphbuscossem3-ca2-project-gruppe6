package dao;

import config.DataConfig;
import entity.Phone;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class PhoneDao
{
    private static final PhoneDao singleton = new PhoneDao();

    private PhoneDao()
    {

    }

    public static PhoneDao getSingleton() {
        return singleton;
    }

    public List<Phone> findAll() {
        List<Phone> phoneList = new ArrayList<>();
        EntityManager entityManager = DataConfig.getSingleton().getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            phoneList = entityManager.createQuery("select p from dao p",Phone.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return phoneList;
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