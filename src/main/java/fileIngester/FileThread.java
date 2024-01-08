package fileIngester;

import kafka.KafkaApplication;

import java.io.File;
import java.io.IOException;
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
                String temp = line.replaceAll(" ","");
                if (temp.length()>=5) {
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
//        System.out.println("date is: " + date);
//        System.out.println("time is: " + time);
//        System.out.println("number is: " + number);
//        System.out.println("thread is: " + thread);
//        System.out.println("info is: " + info);
//        System.out.println("package is: " + packageName);
//        System.out.println("argument is: " + argument);
        createLog(date,time,number,thread,info,packageName,argument);
    }

    private void createLog(String dateC, String time, String number, String thread, String info, String packageName, String argument) {
        Log log = new Log(dateC,time,number,thread,info,packageName,argument);
        logToString(log);
    }

    private void logToString(Log log) {
        String buffer = "date:" +
                log.getDate() +
                ",time:" +
                log.getTime() +
                ",number:" +
                log.getNumber() +
                ",thread:" +
                log.getThread() +
                ",info:" +
                log.getInfo() +
                ",package:" +
                log.getPackageName() +
                ",  argument:" +
                log.getArgument();
        KafkaApplication.run(buffer);   // from here json string line will send to the kafka producer !
    }

}
