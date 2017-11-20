package io;

import exceptions.FailedToReadInputFileException;
import org.junit.Test;
import utility.FileReader;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InputFileReaderTest {
    @Test
    public void testNoCitiesInRequestIfNoCitiesInFile() {
        try {
            String inputFile = "{tempunit: \"standard\"}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertNull(requestFile.getCitiesNames());
            assertEquals("standard", requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testEmptyCitiesArrayIfCitiesListInFileIsEmpty() {
        try {
            String inputFile = "{cities: [], tempunit: \"standard\"}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertArrayEquals(new String[]{}, requestFile.getCitiesNames());
            assertEquals("standard", requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testOnlyOneCityInRequestFileIfOnlyOneIsSpecified() {
        try {
            String inputFile = "{cities: [\"Tartu\"], tempunit: \"standard\"}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertArrayEquals(new String[]{"Tartu"}, requestFile.getCitiesNames());
            assertEquals("standard", requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testTempUnitIsNullInRequestFileIfItIsNotSpecifiedInFile() {
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
    public void testAllTheCitiesFromTheFileAreInRequestFile() {
        try {
            String inputFile = "{cities: [\"Tartu\", \"Pärnu\", \"Maardu\"], tempunit: \"standard\"}";
            FileReader readerMock = mock(FileReader.class);
            when(readerMock.readFile("test1.txt")).thenReturn(inputFile);

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            RequestFile requestFile = inputFileReader.readFromFile("test1.txt");

            assertArrayEquals(new String[]{"Tartu", "Pärnu", "Maardu"}, requestFile.getCitiesNames());
            assertEquals("standard", requestFile.getTemperatureUnit());
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test(expected = FailedToReadInputFileException.class)
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
                    "{cities: [\"Tartu\", \"Pärnu\", \"Maardu\"], tempunit: \"standard\"}");

            InputFileReader inputFileReader = new InputFileReader(readerMock);

            inputFileReader.readFromFile("testing_method_usage.txt");

            verify(readerMock, times(1)).readFile("testing_method_usage.txt");
        } catch (IOException ex) {
            fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test(expected = FailedToReadInputFileException.class)
    public void testThrowsExceptionIfInputIsIncorrect() throws IOException {
        FileReader readerMock = mock(FileReader.class);
        when(readerMock.readFile(anyString())).thenReturn("non-json");

        InputFileReader inputFileReader = new InputFileReader(readerMock);

        inputFileReader.readFromFile("testing_method_usage.txt");
    }
}