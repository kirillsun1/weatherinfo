package links;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

public class URLLinkGeneratorTest {
    @Test
    public void testGetLinkByNullCityName() {
        assertNull(URLLinkGenerator.generateURLLinkByCityName(null));
    }

    @Test
    public void testGetLinkByEmptyCityName() {
        assertEquals("api.openweathermap.org/data/2.5/weather?q=",
                URLLinkGenerator.generateURLLinkByCityName(""));
    }

    @Test
    public void testGetLinkForTallinn() {
        assertEquals("api.openweathermap.org/data/2.5/weather?q=Tallinn",
                URLLinkGenerator.generateURLLinkByCityName("Tallinn"));
    }

    @Test
    public void testGetLinkByNullCityNameAndNormalCountryCode() {
        assertNull(URLLinkGenerator.generateURLLinkByCityNameAndCountryCode(null, "EE"));
    }

    @Test
    public void testGetLinkByEmptyCityNameAndEmptyCountryCode() {
        assertEquals("api.openweathermap.org/data/2.5/weather?q=,",
                URLLinkGenerator.generateURLLinkByCityNameAndCountryCode("", ""));
    }

    @Test
    public void testGetLinkByCityAndCountryCode() {
        assertEquals("api.openweathermap.org/data/2.5/weather?q=tallinn,ee",
                URLLinkGenerator.generateURLLinkByCityNameAndCountryCode("tallinn", "ee"));
    }

    @Test
    public void testGetLinkByCityAndInvalidCountryCode() {
        assertNull(URLLinkGenerator.generateURLLinkByCityNameAndCountryCode(null, "znl"));
    }

    @Test
    public void testGetLinkByNormalCityNameAndNullCountryCode() {
        assertNull(URLLinkGenerator.generateURLLinkByCityNameAndCountryCode("Estonia", null));
    }

    @Test
    public void testGetLinkByNullCityNameAndCountryCode() {
        assertNull(URLLinkGenerator.generateURLLinkByCityNameAndCountryCode(null, null));
    }

    @Test
    public void testGetLinkByPositiveCityID() {
        assertEquals("api.openweathermap.org/data/2.5/weather?id=5",
                URLLinkGenerator.generateURLLinkByCityID(5));
    }

    @Test
    public void testGetLinkByNegativeCityID() {
        assertEquals("api.openweathermap.org/data/2.5/weather?id=-11",
                URLLinkGenerator.generateURLLinkByCityID(-11));
    }

    @Test
    public void testGetLinkByZeroCityID() {
        assertEquals("api.openweathermap.org/data/2.5/weather?id=0",
                URLLinkGenerator.generateURLLinkByCityID(0));
    }

    @Test
    public void testGetLinkByPositiveLongitudeAndPositiveLatitude() {
        assertEquals("api.openweathermap.org/data/2.5/weather?lat=987&lon=1",
                URLLinkGenerator.generateURLLinkByLatitudeAndLongtitude(987, 1));
        assertEquals("api.openweathermap.org/data/2.5/weather?lat=1&lon=13641",
                URLLinkGenerator.generateURLLinkByLatitudeAndLongtitude(1, 13641));
    }

    @Test
    public void testGetLinkByPositiveLongitudeAndNegativeLatitude() {
        assertEquals("api.openweathermap.org/data/2.5/weather?lat=741&lon=-3",
                URLLinkGenerator.generateURLLinkByLatitudeAndLongtitude(741, -3));
    }

    @Test
    public void testGetLinkByNegativeLongitudeAndPositiveLatitude() {
        assertEquals("api.openweathermap.org/data/2.5/weather?lat=-9999&lon=22222",
                URLLinkGenerator.generateURLLinkByLatitudeAndLongtitude(-9999, 22222));
    }

    @Test
    public void testGetLinkByZeroLongitudeAndLatitude() {
        assertEquals("api.openweathermap.org/data/2.5/weather?lat=0&lon=0",
                URLLinkGenerator.generateURLLinkByLatitudeAndLongtitude(0, 0));
        assertEquals("api.openweathermap.org/data/2.5/weather?lat=-1&lon=0",
                URLLinkGenerator.generateURLLinkByLatitudeAndLongtitude(-1, 0));
        assertEquals("api.openweathermap.org/data/2.5/weather?lat=0&lon=1",
                URLLinkGenerator.generateURLLinkByLatitudeAndLongtitude(0, 1));
    }

    @Test
    public void testGetLinkByZipCodeAndCountryCode() {
        assertEquals("api.openweathermap.org/data/2.5/weather?zip=94040,us",
                URLLinkGenerator.generateURLLinkByZipCodeAndCountryCode(94040, "US"));
    }

    @Test
    public void testGetLinkByZipCodeAndIncorrectCountryCode() {
        assertNull(URLLinkGenerator.generateURLLinkByZipCodeAndCountryCode(94040, "USA"));
    }

    @Test
    public void testNullCountryCodeIsIncorrect() {
        // assertEquals(false, URLLinkGenerator.isCountryCodeValid(null));
        // must throw nullpointer
        fail("exception is not raised!");
    }

    @Test
    public void testEmptyCountryCodeIsIncorrect() {
        assertEquals(false, URLLinkGenerator.isCountryCodeValid(""));
    }

    @Test
    public void testExistingCountryCodesAreCorrect() {
        assertEquals(true, URLLinkGenerator.isCountryCodeValid("EE"));
        assertEquals(true, URLLinkGenerator.isCountryCodeValid("US"));
        assertEquals(true, URLLinkGenerator.isCountryCodeValid("FI"));
        assertEquals(true, URLLinkGenerator.isCountryCodeValid("LA"));
    }

    @Test
    public void testNotExistingCountryCodesAreIncorrect() {
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("AA"));
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("AB"));
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("KL"));
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("ZD"));
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("SO"));
    }

    @Test
    public void testCountryCodesNotInISOFormatAreIncorrect() {
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("1"));
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("AAA"));
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("oe"));
        assertEquals(false, URLLinkGenerator.isCountryCodeValid("ss"));
    }

    @Test
    public void testCountryCodeInSmallLettersButStillCorrect() {
        assertEquals(true, URLLinkGenerator.isCountryCodeValid("ee"));
        assertEquals(true, URLLinkGenerator.isCountryCodeValid("us"));
        assertEquals(true, URLLinkGenerator.isCountryCodeValid("fi"));
        assertEquals(true, URLLinkGenerator.isCountryCodeValid("la"));
    }
}