package utility;

public class Constants {
    public enum TemperatureUnits {
        STANDARD("°K"), METRIC("°C"), IMPERIAL("°F");

        private final String symbol;

        TemperatureUnits(String symbol) {
            this.symbol = symbol;
        }

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
                    throw new IllegalArgumentException(String.format("Incorrect temperature unit: %s", tempUnit));
            }
        }

        public String getSymbol() {
            return symbol;
        }
    }
}
