package weatherdata;

import java.time.LocalDate;

public class ForecastOneDayWeather {
    double minimumTemperature = Integer.MAX_VALUE;
    double maximumTemperature = Integer.MIN_VALUE;
    // double currentTemperature = Integer.MIN_VALUE;
    LocalDate currentDateTime;

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    public LocalDate getCurrentDateTime() {
        return currentDateTime;
    }

    @Override
    public String toString() {
        return String.format("[Day: %s] [Min temp: %f] [Max temp: %f]", currentDateTime,
                minimumTemperature, maximumTemperature);
    }
}
