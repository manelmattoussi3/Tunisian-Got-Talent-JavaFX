/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.Candidat;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DbConnection;
import utils.Source;

/**
 *
 * @author Khoubaib
 */
public class CandidatService {

    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    private DbConnection dc;

    /*public CandidatService() {
        // java.sql.Connection conn = dc.Connect();
        connection = (Connection) (Source.getInstance()).getConnection();
    }*/
    public static java.sql.Connection Connect() {
        try {
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

    public ArrayList<Candidat> afficherCandidat() {
        ArrayList<Candidat> listCandidat = new ArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select * from candidat");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Candidat c = new Candidat(rs.getInt(1), rs.getString(2), rs.getString(3));
                listCandidat.add(c);

            }

            for (Candidat c : listCandidat) {
                System.out.println(c.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return listCandidat;
    }

     /* public boolean rechercheCandidat(String nom) {
       boolean res = false;
        try {
            String req = "select * from Candidat where nom =?";
            pst=(PreparedStatement) connection.prepareStatement(req);
            pst.setString(1, nom);
            rs = pst.executeQuery();
            if(rs.next())
            {
                res = true;
            }  
        } catch (SQLException ex) {
            Logger.getLogger(CandidatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }*/
  public ObservableList<Candidat> chercherCandidat(String nom) {
        String requete = "select * from Candidat where nom like? ";
        PreparedStatement ps = null;
        ObservableList<Candidat> lst = FXCollections.observableArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            ps = (PreparedStatement) conn.prepareStatement(requete);
            ps.setString(1, nom);
            //ps.setInt(3,1);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                lst.add(new Candidat(resultat.getInt("id"), resultat.getString("nom"), resultat.getString("prenom"))
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
                Logger.getLogger(CandidatService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
    public ArrayList<Candidat> listerNomCandidat() {
        ArrayList<Candidat> listNomCandidat = new ArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select * from Candidat");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Candidat nomc = new Candidat(rs.getInt(1), rs.getString(2), rs.getString(3));
                listNomCandidat.add(nomc);

            }

            for (Candidat nomc : listNomCandidat) {
                System.out.println(nomc.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return listNomCandidat;
    }

    /* public int getCandidatById(int id) {
java.sql.Connection conn = DbConnectinfo.Connect();
        String req = "select * from Candidat where id= '" + id + "'";
        try {
            pst = (PreparedStatement) connection.prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }*/
}
