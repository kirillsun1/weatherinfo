package utility;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileReaderTest {
    private static final String FILE_NAME = "abc.txt";

    @Before
    public void deleteFileIfPresent() {
        File testFile = new File(FILE_NAME);
        testFile.delete();
    }

    @Test(expected = IOException.class)
    public void testThrowsExceptionIfNoFile() throws IOException {
        FileReader reader = new FileReader();

        reader.readFile(FILE_NAME);
    }
}