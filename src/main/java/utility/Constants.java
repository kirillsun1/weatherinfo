package utility;

public class Constants {
    public enum TemperatureUnits {
        STANDART, METRIC, IMPERIAL;

        public static TemperatureUnits getUnitByDefault() {
            return STANDART;
        }
    }
}
