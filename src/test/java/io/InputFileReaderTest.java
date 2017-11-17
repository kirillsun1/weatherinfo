package io;

import org.junit.Test;
import utility.FileReader;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputFileReaderTest {
    @Test
    public void testReadInputFileOneCity() {
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
    public void testReadInputFileSeveralCities() {
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
}