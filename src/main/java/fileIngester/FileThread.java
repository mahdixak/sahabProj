package fileIngester;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileThread extends Thread{

    private final File file;

    private final String defaultRegex = "^\\s*(?<date>\\d+-\\d+-\\d+)\\s*(?<time>\\d+:\\d+:\\d+)\\s*,(?<number>\\d+)\\s*(\\[(?<thread>\\S*)\\])\\s*(?<info>[A-Z]+)\\s*(?<package>\\S+)\\s*-(?<argument>\\S+)\\s*$";

    private final String logsRegex = "^\\s*(((?<date>\\d+-\\d+-\\d+)\\s+)|((?<time>\\d+:\\d+:\\d+)\\s*)|(,(?<number>\\d+)\\s*)|(\\[(?<thread>\\S*)]\\s*)|((?<info>[A-Z]+)\\s*)|((?<package>\\S+)\\s*)|(-\\s*(?<argument>\\S+)\\s*))+\\s*$";

    public FileThread(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        String line;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                System.out.println(line);
                if (line.length()>=5) {
                    Pattern pattern1 = Pattern.compile(defaultRegex,Pattern.CASE_INSENSITIVE);
                    Pattern pattern2 = Pattern.compile(logsRegex, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern1.matcher(line);
                    if (matcher.find()) {
                        findParameters(matcher);
                    } else {
                        matcher = pattern2.matcher(line);
                        if (matcher.find())
                            findParameters(matcher);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        System.out.println("date is: " + date);
        System.out.println("time is: " + time);
        System.out.println("number is: " + number);
        System.out.println("thread is: " + thread);
        System.out.println("info is: " + info);
        System.out.println("package is: " + packageName);
        System.out.println("argument is: " + argument);
        new Log(dateC,time,number,thread,info,packageName,argument);
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
