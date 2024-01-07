package fileIngester;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileIngester extends Thread{
    private final ArrayList<File> allFiles = new ArrayList<>();

    @Override
    public void run() {
        checkCurrentFiles();        //this method will check the files that already are in the logs directory
        try {                       // always checking the log directory for new files
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
                    String eventAddress = eventPath.toString();
                    if (( kind.toString().equals("ENTRY_CREATE") ) && (eventAddress.charAt(eventAddress.length()-1)!='~')) {
                        System.out.println(kind + ": " + eventPath);
                        createFile(eventAddress);
                    }
                    if ((kind.toString().equals("ENTRY_MODIFY") ) && (eventAddress.charAt(eventAddress.length()-1)!='~')) {
                        System.out.println(kind + ": " + eventPath);
                        fileHandler(eventAddress);
                    }
                    if (( kind.toString().equals("ENTRY_DELETE") ) && (eventAddress.charAt(eventAddress.length()-1)!='~')) {
                        System.out.println(kind + ": " + eventPath);
                        removeFile(eventAddress);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void checkCurrentFiles() {
        Path path = Paths.get("/home/mahdixak/sahab/sahabProj/logs/");
        File file = new File(path.toString());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
                        allFiles.add(f);
                        fileHandler(f.getAbsolutePath());
                    }
                    else if (f.isDirectory()) {
                        checkCurrentFiles();
                    }
                }
            }
        }

    }

    private void createFile(String eventAddress) {
        File file = new File(eventAddress);
        allFiles.add(file);
        System.out.println("file "+eventAddress+" created successfully!");
    }

    private void removeFile(String eventAddress) {
        File file = new File(eventAddress);
        allFiles.remove(file);
    }

    private void fileHandler(String eventAddress) {
        File file = new File(eventAddress);
        new FileThread(file).start();
        System.out.println("file handling for : "+file.getName());
    }
}

