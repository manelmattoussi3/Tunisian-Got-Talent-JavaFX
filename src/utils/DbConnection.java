/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DevelopAndroid
 */
public class DbConnection {
     public  Connection Connect(){
       try {
             String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3308/frame";
            String usr = "root";
            String pwd = "";
            Class.forName("com.mysql.jdbc.Driver");
             System.setProperty(driver,"");
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
        }
    
    return null;
    
    }
    
}
