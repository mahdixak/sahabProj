package fileIngester;

import java.io.*;

public class FileIngester extends Thread{
    private final String logsRegex = "^((?<date>\\d+\\-\\d+\\-\\d+)\\s*)|" +
                                     "((?<time>\\d+:\\d+:\\d+)\\s*)|" +
                                     "(,(?<number>\\d+)\\s*)|" +
                                     "(\\[(?<thread>\\S*)\\]\\s*)|" +
                                     "((?<info>\\S+)\\s*)|" +
                                     "((?<package>\\S+)\\s*)|" +
                                     "(\\-\\s*(?<argument>\\S+)\\s*)$";
    private static final String logsDirectoryFolder = "/home/mahdixak/sahab/sahabProj/logs/\n";
    public void writing() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logsDirectoryFolder));
            bufferedWriter.write("hello motherfucker!");
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readingLogs() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(logsDirectoryFolder));
            reader.close();
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while (true) {
            // always checking the log directory
        }
    }
}

