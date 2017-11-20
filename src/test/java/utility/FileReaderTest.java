package utility;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileReaderTest {
    @Test(expected = IOException.class)
    public void testThrowsExceptionIfNoFile() throws IOException {
        FileReader reader = new FileReader();
        reader.readFile("abc.txt");
    }
}