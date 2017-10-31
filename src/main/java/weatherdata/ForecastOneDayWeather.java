package weatherdata;

import java.time.LocalDate;

public class ForecastOneDayWeather {
    double minimumTemperature = Integer.MAX_VALUE;
    double maximumTemperature = Integer.MIN_VALUE;
    // double currentTemperature = Integer.MIN_VALUE;
    LocalDate currentDateTime;

    @Override
    public String toString() {
        return String.format("[Day: %s] [Min temp: %f] [Max temp: %f]", currentDateTime,
                minimumTemperature, maximumTemperature);
    }
}
