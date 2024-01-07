import fileIngester.FileIngester;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        checkDirectoryExistence();
        FileIngester ingester = new FileIngester();
        ingester.start();

    }

    private static void checkDirectoryExistence() {
        String path = "/home/mahdixak/sahab/sahabProj/logs/";
        File file = new File(path);
        if (!file.exists()) new File(path).mkdir();
    }
}
