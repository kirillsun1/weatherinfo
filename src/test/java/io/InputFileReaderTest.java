package io;

import org.junit.Test;
import utility.FileReader;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class InputFileReaderTest {
    @Test
    public void testCitiesAreNotSpecified() {
        try {
            String inputFile = "{tempunit: \"standart\"}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertNull(requestFile.getCitiesNames());
            assertEquals("standart", requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testCitiesListIsEmpty() {
        try {
            String inputFile = "{cities: [], tempunit: \"standart\"}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertArrayEquals(new String[]{}, requestFile.getCitiesNames());
            assertEquals("standart", requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testOnlyOneCity() {
        try {
            String inputFile = "{cities: [\"Tartu\"], tempunit: \"standart\"}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertArrayEquals(new String[]{"Tartu"}, requestFile.getCitiesNames());
            assertEquals("standart", requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testTempUnitIsNotSpecified() {
        try {
            String inputFile = "{cities: [\"Tartu\", \"Pärnu\", \"Maardu\"]}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertArrayEquals(new String[]{"Tartu", "Pärnu", "Maardu"}, requestFile.getCitiesNames());
            assertNull(requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testSeveralCities() {
        try {
            String inputFile = "{cities: [\"Tartu\", \"Pärnu\", \"Maardu\"], tempunit: \"standart\"}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertArrayEquals(new String[]{"Tartu", "Pärnu", "Maardu"}, requestFile.getCitiesNames());
            assertEquals("standart", requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test(expected = IOException.class)
    public void testThrowsExceptionIfNoFile() throws IOException {
        FileReader readerMock = mock(FileReader.class);
        when(readerMock.readFile("aa.txt")).thenThrow(IOException.class);

        InputFileReader inputFileReader = new InputFileReader(readerMock);

        inputFileReader.readFromFile("aa.txt");
    }

    @Test
    public void testFileReaderIsUsed() {
        try {
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile(anyString())).thenReturn(
                    "{cities: [\"Tartu\", \"Pärnu\", \"Maardu\"], tempunit: \"standart\"}");

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            inputFileReader.readFromFile("testing_method_usage.txt");

            verify(readerMock, times(1)).readFile("testing_method_usage.txt");
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }
}