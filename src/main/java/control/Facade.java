package control;


import dao.PersonDao;
import entity.Person;

import java.util.List;

public class Facade
{
    private static final Facade singleton = new Facade();
    private PersonDao personDao = PersonDao.getSingleton();

    private Facade() {
    }

    public static Facade getSingleton() {
        return singleton;
    }

    public List<Person> getAllPersons() {
        return personDao.findAll();
    }
}
