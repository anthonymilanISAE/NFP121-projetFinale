package models;

import java.io.*;

public abstract class FileReaderFactory {
    protected BufferedReader bufferedReader;
    protected File readFile;
    protected FileReader fileReader;

    public BufferedReader createFileReader(String directory) throws FileNotFoundException {
        readFile = new File(directory);
        fileReader = new FileReader(readFile);
        bufferedReader = new BufferedReader(fileReader);

        return bufferedReader;
    }

    public abstract void readCredentials(String directory, boolean isAgent);
}
