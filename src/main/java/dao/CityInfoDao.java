package dao;

import entity.Person;

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

/**
 * Created by William Pfaffe on 16-03-2017.
 */
public class CityInfoDao {

    EntityManager manager;

    public CityInfoDao(EntityManager manager) {
        this.manager = manager;
    }

    public ArrayList<CityInfo> findAllCities() {
        Query q = manager.createQuery("SELECT ci FROM CityInfo ci");
        return (ArrayList<CityInfo>) q.getResultList();
    }
}
