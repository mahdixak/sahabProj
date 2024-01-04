package fileIngester;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

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
    public void run() {   // always checking the log directory
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Map<WatchKey,Path> keyMap = new HashMap<>();
            Path path = Paths.get("/home/mahdixak/sahab/sahabProj/logs/");
            keyMap.put(path.register(watchService,StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_DELETE),path);
            WatchKey watchKey = watchService.take();
            while (watchKey.reset()) {
                watchKey = watchService.take();
                Path eventDir = keyMap.get(watchKey);
                for (WatchEvent<?> event: watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();
                    System.out.println(kind + ": " + eventPath);
                    String eventAddress = eventPath.toString();
                    if (( kind.toString().equals("ENTRY_CREATE") ) && (eventAddress.charAt(eventAddress.length()-1)!='~')) {
                        fileHandler(eventAddress);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void fileHandler(String eventAddress) {
        System.out.println("hello");
    }
}

