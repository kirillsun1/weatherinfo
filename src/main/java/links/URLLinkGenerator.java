package links;

import java.util.Arrays;
import java.util.Locale;

public class URLLinkGenerator {
    public static final String API_URL = "api.openweathermap.org/forecast/2.5/forecast?";
    public static final String API_KEY = "";

    public static boolean isCountryCodeValid(String countryCode) {
        return Arrays.asList(Locale.getISOCountries()).contains(countryCode.toUpperCase());
    }

    public static String generateURLLinkByCityName(String cityName) {
        return null;
    }

    public static String generateURLLinkByCityNameAndCountryCode(String cityName, String countryCode) {
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
