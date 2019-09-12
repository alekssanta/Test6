package ru.dexsys;

import java.net.URI;
import java.sql.*;

public class Main {

    private static final String USERNAME = "dexautomation";
    private static final String PASSWORD = "dexautomation";
    private static final String URL = "jdbc:mysql://db4free.net:3306/dexautomation";
    //"jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=UTC";


    public static void main(String[] args) throws SQLException {

        Driver driver;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
        }
        catch (SQLException ex){
            System.out.println("ошибка при создании драйвера");
            return;
        }
        try {
            DriverManager.registerDriver(driver);
        }
        catch ( SQLException ex){
            System.out.println("не удалось зарегистрировать драйвер");
            return;
        }
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // подключаемся к бд
            Statement statement = connection.createStatement();

            statement.execute("insert into Students (firstName, lastName, age, phone) values (\"Alexandr\", \"Makedonsky\", \"2375\", null )");
// создаем пункт в таблице с данными
            ResultSet resultSet = statement.executeQuery("select *from Students");
            while (resultSet.next()) {
                // смотрим поля в таблице
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                System.out.println("___________");  //разделитель


            }
        }

         catch (SQLException ex){
             System.out.println("не удалось соединение");
             return;
         }
//finally {
//            if (connection !=null)
//                connection.close();
//        }
    }
}
