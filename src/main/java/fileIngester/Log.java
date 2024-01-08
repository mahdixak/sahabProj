package fileIngester;

import java.util.ArrayList;

public class Log {
    private final ArrayList<Log> allLogs = new ArrayList<>();
    private final String date;
    private final String  time;
    private final String number;
    private final String thread;
    private final String info;
    private final String packageName;
    private final String argument;

    public Log(String date, String time, String number, String thread, String info, String packageName, String argument) {
        this.date = date;
        this.time = time;
        this.number = number;
        this.thread = thread;
        this.info = info;
        this.packageName = packageName;
        this.argument = argument;
        allLogs.add(this);
        System.out.println("log added successfully");
    }


    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getNumber() {
        return number;
    }

    public String getThread() {
        return thread;
    }

    public String getInfo() {
        return info;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getArgument() {
        return argument;
    }
}
