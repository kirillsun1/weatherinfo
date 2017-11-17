package io;

import com.google.gson.Gson;
import io.RequestFile;
import utility.FileReader;

import java.io.IOException;

public class InputFileReader {
    private final FileReader reader;

    public InputFileReader(FileReader reader) {
        this.reader = reader;
    }

    public RequestFile readFromFile(String pathToInputFile) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(reader.readFile(pathToInputFile), RequestFile.class);
    }
}
