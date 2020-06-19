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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Conseil;
import models.ConseilModel;

/**
 *
 * @author DevelopAndroid
 */
public class Serviceconseil {

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

    public void save(Conseil con) {
        int st = 0;
        try {
            String sql = "INSERT INTO `conseil`( `description`,`categoricons`,`image`, `candidat_id`,`coach_id`) VALUES (?,?,?,?,?)";
            Connection conn = Serviceconseil.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
          // stat.setInt(1, con.getIdcons());
           // stat.setInt(1, con.getCoach_id());
            stat.setInt(5, con.getCoach_id());
             stat.setInt(4, con.getCandidat_id());
            stat.setString(2, con.getDescriptioncons());
            stat.setString(3, con.getCategoricons());
            stat.setString(1, con.getImagecons());

           
            st = stat.executeUpdate();
           // conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return st;
    }

    public void delete(int idcons) {
        int st = 0;
        String sql = "DELETE FROM `conseil` WHERE id='" + idcons + "'";
        try {

            Connection conn = Serviceconseil.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            //stat.setInt(1, idcons);
            st = stat.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return st;

    }

    public  Conseil getConseil(int idcons) {
        String sql = "SELECT * FROM `conseil` WHERE id=?";
        Conseil con = new Conseil();
        try {
            
            Connection conn = Serviceconseil.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            stat.setInt(1, idcons);
            ResultSet rst = stat.executeQuery();
            while (rst.next()) {
                con.setIdcons(idcons);
                con.setDescriptioncons(rst.getString(3));
                con.setImagecons(rst.getString(2));
                con.setCategoricons(rst.getString(4));
               // con.setCandidat_id(rst.getInt(5));
               // con.setCoach_id(rst.getInt(6));

            }
            
            //conn.close();
             return con;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
       
    }

    public ObservableList<ConseilModel> chercherConseil(String description) {
        String requete = "select * from conseil where categoricons like?";
        PreparedStatement ps = null;
        ObservableList<ConseilModel> lst = FXCollections.observableArrayList();
        try {
            java.sql.Connection conn = Serviceconseil.Connect();
            ps = (PreparedStatement) conn.prepareStatement(requete);
            ps.setString(1, description);
            //ps.setInt(3,1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(new ConseilModel(rs.getInt("id"),rs.getString("description"), rs.getString("Categoricons"),rs.getString("Image"))
                );
            }
            return lst;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return null;
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
