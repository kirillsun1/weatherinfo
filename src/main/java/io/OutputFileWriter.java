package io;

import com.google.gson.Gson;
import io.ReportFile;
import utility.FileWriter;

import java.io.IOException;

public class OutputFileWriter {
    private final FileWriter writer;

    public OutputFileWriter(FileWriter writer) {
        this.writer = writer;
    }

    public void writeToFile(ReportFile file, String pathToOutputFile) throws IOException {
        Gson gson = new Gson();
        writer.writeToFile(gson.toJson(file), pathToOutputFile);
    }
}
