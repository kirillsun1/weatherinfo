package utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureUnitsTest {
    @Test
    public void testGetTempUnitFromStringIfAllLettersAreLowered() {
        assertEquals(Constants.TemperatureUnits.STANDART, Constants.TemperatureUnits.of("standart"));
        assertEquals(Constants.TemperatureUnits.METRIC, Constants.TemperatureUnits.of("metric"));
        assertEquals(Constants.TemperatureUnits.IMPERIAL, Constants.TemperatureUnits.of("imperial"));
    }

    @Test
    public void testGetTempUnitFromStringIfAllLettersAreUppered() {
        assertEquals(Constants.TemperatureUnits.STANDART, Constants.TemperatureUnits.of("STANDART"));
        assertEquals(Constants.TemperatureUnits.METRIC, Constants.TemperatureUnits.of("METRIC"));
        assertEquals(Constants.TemperatureUnits.IMPERIAL, Constants.TemperatureUnits.of("Imperial"));
    }

    @Test
    public void testGetTempUnitFromStringIfAllLettersAreInDifferentCases() {
        assertEquals(Constants.TemperatureUnits.STANDART, Constants.TemperatureUnits.of("Standart"));
        assertEquals(Constants.TemperatureUnits.METRIC, Constants.TemperatureUnits.of("MEtric"));
        assertEquals(Constants.TemperatureUnits.IMPERIAL, Constants.TemperatureUnits.of("imPERIal"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionIfNoSuchUnit() {
        Constants.TemperatureUnits.of("abc");
    }
}