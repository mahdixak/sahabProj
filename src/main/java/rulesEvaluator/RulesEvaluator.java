package rulesEvaluator;

import java.sql.*;

public class RulesEvaluator {
    public static void main(String[] args) {
        new RulesEvaluator().run();
    }

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
}
