package fileIngester;

import java.util.Date;

public class Log {
    private final String date;
    private final Date time;
    private final String number;
    private final String thread;
    private final String info;
    private final String packageName;
    private final String argument;

    public Log(String date, Date time, String number, String thread, String info, String packageName, String argument) {
        this.date = date;
        this.time = time;
        this.number = number;
        this.thread = thread;
        this.info = info;
        this.packageName = packageName;
        this.argument = argument;
    }

    public String getDate() {
        return date;
    }

    public Date getTime() {
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
