package utility;

public class Constants {
    public enum TemperatureUnits {
        STANDART, METRIC, IMPERIAL;

        public static TemperatureUnits getUnitByDefault() {
            return STANDART;
        }

        public static TemperatureUnits of(String tempUnit) {
            switch (tempUnit.toLowerCase()) {
                case "standart":
                    return STANDART;

                case "metric":
                    return METRIC;

                case "imperial":
                    return IMPERIAL;

                default:
                    throw new IllegalArgumentException(String.format("Incorrect temperature unit [%s]", tempUnit));
            }
        }
    }
}
