/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Khoubaib
 */
public class Source {
    private String url;
    private String login;
    private String password;
    private Connection connection;
    private Properties properties;
    private static Source instance;

    private Source() {
        try {
            properties = new Properties();
            
            properties.load(new FileInputStream(new File("configuration.properties")));
            url = properties.getProperty("url");
            login = properties.getProperty("login");
            password = properties.getProperty("password");
          connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Source getInstance() {
        if (instance == null) {
            instance = new Source();
        }
        return instance;
    }
    
}

