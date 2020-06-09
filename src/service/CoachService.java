/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Coach;
import utils.DbConnection;
import controllers.ListeCoachController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author DevelopAndroid
 */
public class CoachService {
   Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    private DbConnection dc;
     public static java.sql.Connection Connect() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3308/frame";
            String usr = "root";
            String pwd = "";
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection(url, usr, pwd);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
        }

        return null;

    }

    public ArrayList<Coach> afficherCoach() {
        ArrayList<Coach> listCoach = new ArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("SELECT * FROM `coach`");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Coach co = new Coach(rs.getInt(1), rs.getString(2), rs.getString(3));
                listCoach.add(co);

            }

            for (Coach co : listCoach) {
                System.out.println(co.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return listCoach;
    }
    public ObservableList<Coach> chercherCoach(String nom) {
        String requete = "select * from Coach where nom like? ";
        PreparedStatement ps = null;
        ObservableList<Coach> lst = FXCollections.observableArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            ps = (PreparedStatement) conn.prepareStatement(requete);
            ps.setString(1, nom);
            //ps.setInt(3,1);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
         lst.add(new Coach(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("prenom")));
                
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
    public ArrayList<Coach> listerNomCoach() {
        ArrayList<Coach> listNomCoach = new ArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select * from Coach");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Coach nomc = new Coach(rs.getInt(1), rs.getString(2), rs.getString(3));
                listNomCoach.add(nomc);

            }

            for (Coach nomc : listNomCoach) {
                System.out.println(nomc.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return listNomCoach;
    }
}
