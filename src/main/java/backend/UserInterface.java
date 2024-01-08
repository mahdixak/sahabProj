package backend;

import java.sql.*;

public class UserInterface {

    public static void main(String[] args) {
        new UserInterface().run();
    }

    private void run() {
        String sql = "";
        String url = "jdbc:mysql://localhost:3306/logdb";
        String username = "mahdi";
        String password = "mahdiak0447@1#";
        try {
            Class.forName("com.mysql:jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(sql);
            sql = "INSERT INTO Log (LogDate,LogTime,LogNumber,thread,info,package,argument)" + "Values (23423,15324,234,thread,info,package,argument) ";


            statement.execute(sql);


            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
