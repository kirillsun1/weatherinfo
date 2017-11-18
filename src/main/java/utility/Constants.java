package utility;

public class Constants {
    public enum TemperatureUnits {
        STANDARD, METRIC, IMPERIAL;

        public static TemperatureUnits getUnitByDefault() {
            return STANDARD;
        }

        public static TemperatureUnits of(String tempUnit) {
            switch (tempUnit.toLowerCase()) {
                case "standard":
                    return STANDARD;

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
