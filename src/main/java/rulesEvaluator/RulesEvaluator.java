package rulesEvaluator;

import fileIngester.Log;
import mySql.MySql;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RulesEvaluator {
    private final MySql mySql = new MySql();

    public void run() {
        String sql = "";
        String url = "jdbc:mysql://localhost:3306/logdb";
        String username = "mahdi";
        String password = "mahdiak0447@1#";
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void stringToLog(String value) {
        String deserializeRegex = "date:(?<date>[^,]+),time:(?<time>[^,]+),number:(?<number>[^,]+),thread:(?<thread>[^,]+),info:(?<info>[^,]+),package:(?<package>[^,]+),argument:(?<argument>[^,]+)";
        Pattern pattern = Pattern.compile(deserializeRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        findingArguments(matcher);
    }

    private void findingArguments(Matcher matcher) {
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

    private void createLog(String date, String time, String number, String thread, String info, String packageName, String argument) {
        Log log = new Log(date,time,number,thread,info,packageName,argument);
        sendDataToMysql(log);
    }

    private void sendDataToMysql(Log log) {
        mySql.sendLogToDatabase(log);
    }
}
