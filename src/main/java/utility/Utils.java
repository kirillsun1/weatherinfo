package utility;

import java.util.Arrays;
import java.util.Locale;

public class Utils {
    public static boolean isCountryCodeCorrect(String countryCode) {
        return Arrays.asList(Locale.getISOCountries()).contains(countryCode);
    }
}
