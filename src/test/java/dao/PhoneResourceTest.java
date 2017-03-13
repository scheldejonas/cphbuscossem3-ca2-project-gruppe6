package dao;

import entity.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rest.PhoneResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 13/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneResourceTest {

    @Mock
    private PhoneDao phoneDao;

    @InjectMocks
    private PhoneResource phoneResource = new PhoneResource();

    @Test
    public void requestToApiPhones_ShouldReturnTwo() {
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(new Phone("20772194", "private phone"));
        phoneList.add(new Phone("79998801", "work phone"));
    }
}
