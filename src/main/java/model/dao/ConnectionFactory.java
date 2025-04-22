package model.dao;

import model.domain.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection;
    private ConnectionFactory(){}

    public static Connection getConnection() throws SQLException {
        return connection;
    }

    public static void changeRole(Role role){
        try(FileInputStream in = new FileInputStream("src/main/resources/credentials.properties");){
            Properties properties = new Properties();
            properties.load(in);
            String username = properties.getProperty(role.name() + "_USER");
            String password = properties.getProperty(role.name() + "_PASSWORD");
            String url = properties.getProperty("URL");
            connection = DriverManager.getConnection(url, username, password);
        } catch(IOException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
