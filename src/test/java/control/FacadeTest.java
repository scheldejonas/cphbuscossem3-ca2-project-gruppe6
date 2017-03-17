package control;

import com.sun.corba.se.spi.activation.Server;
import com.sun.jersey.api.NotFoundException;
import dao.PersonDao;
import entity.CityInfo;
import entity.Person;
import errorhandling.ServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FacadeTest
{
    @Mock
    private PersonDao personDao;

    @InjectMocks
    private Facade facade = Facade.getSingleton();

    @Test
    public void findSingleCity_shouldReturnCityInfo() throws Exception
    {
        when(personDao.findSingleCity("1")).thenReturn(new CityInfo());
        assertThat( facade.findSingleCity("1") , instanceOf(CityInfo.class) );
        verify(personDao).findSingleCity("1");
    }

    @Test
    public void findPeopleFromZipcode_shouldReturnOne() throws Exception
    {
        Person p = new Person();
        ArrayList<Person> arrPer = new ArrayList<>();
        arrPer.add(p);
        when(personDao.findPeopleFromZipcode("2640")).thenReturn(arrPer);
        ArrayList<Person> newArrPer = facade.findPeopleFromZipcode("2640");
        assertEquals(newArrPer.size(), 1);
        verify(personDao).findPeopleFromZipcode("2640");
    }

    @Test
    public void findPeopleFromAddress() throws Exception
    {
        Person p = new Person();
        ArrayList<Person> arrPer = new ArrayList<>();
        arrPer.add(p);
        when(personDao.findPeopleFromAddress("Bingovejen1")).thenReturn(arrPer);
        ArrayList<Person> newArrPer = facade.findPeopleFromAddress("Bingovejen1");
        assertEquals(newArrPer.size(), 1);
        verify(personDao).findPeopleFromAddress("Bingovejen1");
    }

    @Test(expected=NotFoundException.class)
    public void addNewPerson_shouldReturnNotFoundException()
    {
        when(personDao.findSingleCity("99999")).thenThrow(NotFoundException.class);
        personDao.findSingleCity("99999");
        verify(personDao).findSingleCity("99999");
    }
}