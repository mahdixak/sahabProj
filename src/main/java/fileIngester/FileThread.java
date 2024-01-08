package fileIngester;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileThread extends Thread{

    public final String defaultRegex = "^\\s*(?<date>\\d+-\\d+-\\d+)\\s*(?<time>\\d+:\\d+:\\d+)\\s*,(?<number>\\d+)\\s*(\\[(?<thread>\\S*)\\])\\s*(?<info>[A-Z]+)\\s*(?<package>\\S+)\\s*-(?<argument>\\S+)\\s*$";
    public final String logsRegex = "^\\s*(((?<date>\\d+-\\d+-\\d+)\\s+)|((?<time>\\d+:\\d+:\\d+)\\s*)|(,(?<number>\\d+)\\s*)|(\\[(?<thread>\\S*)]\\s*)|((?<info>[A-Z]+)\\s*)|((?<package>\\S+)\\s*)|(-\\s*(?<argument>\\S+)\\s*))+\\s*$";
    private final File file;

    public FileThread(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        String line;
        Matcher matcher;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                System.out.println(line);
                if (line.length()>=5) {
                    Pattern pattern = Pattern.compile(defaultRegex,Pattern.CASE_INSENSITIVE);
                    if ((matcher = isRegexMatched(pattern,line))!=null) {
                        findParameters(matcher);
                    } else {
                        pattern = Pattern.compile(logsRegex, Pattern.CASE_INSENSITIVE);
                        if ((matcher = isRegexMatched(pattern,line))!=null)
                            findParameters(matcher);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Matcher isRegexMatched(Pattern pattern, String text) {
        return pattern.matcher(text);
    }

    private void findParameters(Matcher matcher) {
        String date = matcher.group("date");
        String time = matcher.group("time");
        String number = matcher.group("number");
        String thread = matcher.group("thread");
        String info = matcher.group("info");
        String packageName = matcher.group("package");
        String argument = matcher.group("argument");
        Calendar dateC = null;
        if (date!=null) {
            dateC = splitDate(date);
        }
//        System.out.println("date is: " + date);
//        System.out.println("time is: " + time);
//        System.out.println("number is: " + number);
//        System.out.println("thread is: " + thread);
//        System.out.println("info is: " + info);
//        System.out.println("package is: " + packageName);
//        System.out.println("argument is: " + argument);
        createLog(dateC,time,number,thread,info,packageName,argument);
    }

    private void createLog(Calendar dateC, String time, String number, String thread, String info, String packageName, String argument) {
        Log log = new Log(dateC,time,number,thread,info,packageName,argument);
        logToJson(log);
    }

    private void logToJson(Log log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("date:");
        buffer.append(log.getDate());
        buffer.append("time:");
        buffer.append(log.getTime());
        buffer.append("number:");
        buffer.append(log.getNumber());
        buffer.append("thread:");
        buffer.append(log.getThread());
        buffer.append("info:");
        buffer.append(log.getInfo());
        buffer.append("package:");
        buffer.append(log.getPackageName());
        buffer.append("argument:");
        buffer.append(log.getArgument());

    }

    private Calendar splitDate(String date) {
        String[] args = date.split("-");
        Calendar calendar = new GregorianCalendar();
        ArrayList<Integer> argss = new ArrayList<>();
        for (String s : args) {
            argss.add(Integer.parseInt(s));
        }
        calendar.set(argss.get(0), argss.get(1), argss.get(2));
        System.out.println(calendar.getTime());
        return calendar;
    }
}
