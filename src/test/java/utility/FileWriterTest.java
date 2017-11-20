package utility;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileWriterTest {
    @Test
    public void testFileWriterCreatesFile() {
        try {
            String fileName = "filewritertest.txt";

            File testFile = new File(fileName);
            testFile.delete();

            FileWriter writer = new FileWriter();

            writer.writeToFile("abc", fileName);

            if (!testFile.exists()) {
                fail("File did not appear!");
            }
        } catch (IOException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }
}