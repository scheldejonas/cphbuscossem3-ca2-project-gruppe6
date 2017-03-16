package entity;

import org.junit.Test;

import static org.junit.Assert.*;


public class CityInfoTest
{
    @Test
    public void getAndSetZipCode() throws Exception
    {
        CityInfo ci = new CityInfo();
        ci.setZipCode("2640");
        assertEquals(ci.getZipCode(), "2640");
    }

}