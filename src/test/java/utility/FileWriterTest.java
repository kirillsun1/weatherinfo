package utility;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

public class FileWriterTest {

    private static final String FILE_NAME = "filewritertest.txt";
    private File testFile;

    @Before
    public void deleteFile() {
        testFile = new File(FILE_NAME);
        testFile.delete();
    }

    @Test
    public void testFileWriterCreatesFile() {
        try {
            FileWriter writer = new FileWriter();

            writer.writeToFile("abc", FILE_NAME);

            if (!testFile.exists()) {
                fail("File did not appear!");
            }
        } catch (IOException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }
}