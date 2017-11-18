package weatherdata;

import java.time.LocalDate;

public class ForecastOneDayWeather {
    LocalDate currentDateTime;
    double minimumTemperature = Integer.MAX_VALUE;
    double maximumTemperature = Integer.MIN_VALUE;

    public LocalDate getCurrentDateTime() {
        return currentDateTime;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public double getMaximumTemperature() {
        return maximumTemperature;
    }
}
