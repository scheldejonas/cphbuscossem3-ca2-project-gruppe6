package dao;

import entity.CityInfo;
import entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class CompanyDao {

    EntityManager manager;

    public CompanyDao(EntityManager manager) {
        this.manager = manager;
    }
    
    public Company findCompanyFromName(String name) {
        Query q = manager.createQuery("SELECT c FROM Company c WHERE c.name = :name");
        q.setParameter("name", name);
        return (Company) q.getSingleResult();
    }
    
    public Company findCompanyFromCVR(String cvr) {
        Query q = manager.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr");
        q.setParameter("cvr", cvr);
        return (Company) q.getSingleResult();
    }
    
    public Company findCompanyFromPhone(String phone) {
        Query q = manager.createQuery("SELECT c FROM Company c, Phone p WHERE p MEMBER OF c.phones AND p.number = :phone");
        q.setParameter("phone", phone);
        return (Company) q.getSingleResult();
    }
    
    public ArrayList<Company> findCompaniesFromAddress(String street) {
        Query q = manager.createQuery("SELECT c FROM Company c WHERE c.address.street = :street");
        q.setParameter("street", street);
        return (ArrayList<Company>) q.getResultList();
    }
    
    public ArrayList<Company> findCompaniesFromZip(String zip) {
        Query q = manager.createQuery("SELECT c FROM Company c WHERE c.address.cityInfo.zipCode = :zip");
        q.setParameter("zip", zip);
        return (ArrayList<Company>) q.getResultList();
    }
    
}