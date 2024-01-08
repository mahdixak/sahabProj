package mySql;

import fileIngester.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql {
    private final Connection connection;

    private final Statement statement;
    public MySql() {
        String sql;
        String url = "jdbc:mysql://localhost:3306/logdb";
        String username = "mahdi";
        String password = "mahdiak0447@1#";
        try {
            Class.forName("com.mysql:jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(sql);
//            sql = "INSERT INTO Log (LogDate,LogTime,LogNumber,thread,info,package,argument)" + "Values (23423,15324,234,thread,info,package,argument) ";
//            statement.execute(sql);
//            statement.close();
//            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendLogToDatabase(Log log) {
        String date = log.getDate();
        String time = log.getTime();
        String number = log.getNumber();
        String thread = log.getThread();
        String info = log.getInfo();
        String packageName = log.getPackageName();
        String argument = log.getArgument();
        String toExecute = "INSERT TO Log (LogDate,LogTime,LogNumber,thread,info,package,argument)"+
                            "Values ("+date+","+time+","+number+","+thread+","+info+","+packageName+","+argument+")";
        try {
            statement.execute(toExecute);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
