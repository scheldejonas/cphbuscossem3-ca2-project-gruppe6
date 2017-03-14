package dao;

import entity.CityInfo;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CompanyDao {

    EntityManager manager;

    public CompanyDao(EntityManager manager) {
        this.manager = manager;
    }

    public CityInfo findSingleCity(String zipCode) {
        Query q = manager.createQuery("SELECT i FROM CityInfo i WHERE i.zipCode = :zipCode");
        q.setParameter("zipCode", zipCode);
        return (CityInfo) q.getSingleResult();
    }

}