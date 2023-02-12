package models;

import java.io.*;

public class FileReaderFactory {
    protected BufferedReader bufferedReader;
    protected File readFile;
    protected FileReader fileReader;

    private static FileReaderFactory instance = null;

    private FileReaderFactory() {
    }

    public static FileReaderFactory getInstance() {
        if (instance == null) {
            instance = new FileReaderFactory();
        }
        return instance;
    }

    public BufferedReader createFileReader(String directory) throws FileNotFoundException {
        readFile = new File(directory);
        fileReader = new FileReader(readFile);
        bufferedReader = new BufferedReader(fileReader);

        return bufferedReader;
    }

}
