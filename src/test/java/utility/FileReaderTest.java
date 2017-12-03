package utility;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileReaderTest {
    @Test(expected = IOException.class)
    public void testThrowsExceptionIfNoFile() throws IOException {
        FileReader reader = new FileReader();

        String fileName = "abc.txt";

        File testFile = new File(fileName);
        testFile.delete();

        reader.readFile(fileName);
    }
}