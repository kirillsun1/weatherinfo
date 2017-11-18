package io;

import org.junit.Test;
import utility.FileWriter;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OutputFileWriterTest {
    @Test
    public void testFileWriterUsed() {
        try {
            FileWriter writerMock = mock(FileWriter.class);
            OutputFileWriter outputFileWriter = new OutputFileWriter(writerMock);

            ReportFile reportFile = new ReportFile("name", null, null);

            outputFileWriter.writeToFile(reportFile, "mocked");

            verify(writerMock, times(1)).writeToFile(anyString(), eq("mocked"));
        } catch (IOException e) {
            fail("Error occurred: " + e.getMessage());
        }
    }
}