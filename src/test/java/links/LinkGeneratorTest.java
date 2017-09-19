package links;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkGeneratorTest {
    // Is it okay to use such long methods names?

    @Test
    public void testGetLinkByNullCityName() {
        assertNull(LinkGenerator.generateLinkByCityName(null));
    }

    @Test
    public void testGetLinkByNullCityNameAndNormalCountryCode() {
        assertNull(LinkGenerator.generateLinkByCityNameAndCountryCode(null, "EE"));
    }

    @Test
    public void testGetLinkByNormalCityNameAndNullCountryCode() {
        assertNull(LinkGenerator.generateLinkByCityNameAndCountryCode("Estonia", null));
    }

    @Test
    public void testGetLinkByNullCityNameAndCountryCode() {
        assertNull(LinkGenerator.generateLinkByCityNameAndCountryCode(null, null));
    }
}