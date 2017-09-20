package links;

import java.util.Arrays;
import java.util.Locale;

public class URLLinkGenerator {
    public static boolean isCountryCodeValid(String countryCode) {
        return Arrays.asList(Locale.getISOCountries()).contains(countryCode.toUpperCase());
    }

    public static String generateURLLinkByCityName(String cityName) {
        return null;
    }

    public static String generateURLLinkByCityNameAndCountryCode(String cityName, String countryCode) {
        // this doesn't work properly in api.

        return null;
    }

    public static String generateURLLinkByCityID(int cityID) {
        return null;
    }

    public static String generateURLLinkByLatitudeAndLongtitude(int latitude, int longitude) {
        return null;
    }

    public static String generateURLLinkByZipCodeAndCountryCode(int zipCode, String countryCode) {
        return null;
    }
}
