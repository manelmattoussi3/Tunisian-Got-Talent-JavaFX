/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Rate;


/**
 *
 * @author DevelopAndroid
 */
public class servicerating {

    public static Connection Connect() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3308/frame";
            String usr = "root";
            String pwd = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
        }

        return null;

    }

    public void ajouterRating(Rate r) throws SQLException {
 String sql = "INSERT INTO `rating`( `id`,`candidat_id`, `formation_id`, `avis`) VALUES (?,?,?,?)"+"VALUES ('"+r.getId()+"','"+r.getCandidat_id()+"','"+r.getFormation_id()+"','"+r.getAvis()+"');";
            Connection conn = servicerating.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            stat.executeUpdate(sql);
            System.out.println("vous avez ajouté un vote");
    }
    public double count(int formation_id ){
    double count=0;
        try {
            String req="select AVG(avis) from rating";
             Connection conn = servicerating.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(req);
            stat.setInt(1, formation_id);
            ResultSet rst = stat.executeQuery();
        } catch (Exception e) {
        }
    return count;
    }
}
