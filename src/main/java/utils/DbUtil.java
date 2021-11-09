package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static Connection getConnection() throws SQLException {

        //postgreSQL connection
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
    }

////mySQL connection
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/movies?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
//                "root",
//                "coderslab");

}