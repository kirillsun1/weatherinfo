package utility;

public class Constants {
    public enum TemperatureUnits {
        KELVIN(0), CELSIUS(-273.15f), FAHRENHEIT(-255.93f);

        private float difference;

        TemperatureUnits(float difference) {
            this.difference = difference;
        }

        public float getDifference() {
            return difference;
        }
    }
}
