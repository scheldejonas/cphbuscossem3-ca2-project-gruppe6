package dao;

import entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by scheldejonas on 17/03/2017.
 */
public class AddressDao {

    private EntityManager entityManager;

    public AddressDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Address getById(int addressId) {
        Query query = entityManager.createQuery("SELECT a FROM Address a WHERE a.id = :id");
        query.setParameter("id", addressId);
        return (Address) query.getSingleResult();
    }
}
