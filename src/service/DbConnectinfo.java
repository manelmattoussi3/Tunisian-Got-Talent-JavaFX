/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import models.Formation;
import models.Participant;
import models.Rate;

/**
 *
 * @author DevelopAndroid
 */
public class DbConnectinfo {

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

    public void save(Formation fom) {
        int st = 0;
        try {
            String sql = "INSERT INTO `formation`( `coach_id`,`description`,  `date_debut`,`date_fin`, `nbr_place`,`categorie`) VALUES (?,?,?,?,?,?)";
            Connection conn = DbConnectinfo.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            stat.setInt(1, fom.getCoach_id());
            stat.setString(2, fom.getDescription());
            stat.setString(3, fom.getDate_Debut());
            stat.setString(4, fom.getDate_Fin());
            stat.setString(5, fom.getNbr_Place());
            stat.setString(6, fom.getCategorie());
            st = stat.executeUpdate();
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return st;
    }

    public void update(Formation fom) {
        int st = 0;
        try {
            String sql = "UPDATE `formation` SET coach_id= ?, `description`=?,`date_debut`=?,`date_fin`=?,`nbr_place`=? ,`categorie`=? where id=?";
            Connection conn = DbConnectinfo.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            stat.setInt(1, fom.getCoach_id());
            stat.setString(2, fom.getDescription());
            stat.setString(3, fom.getDate_Debut());
            stat.setString(4, fom.getDate_Fin());
            stat.setString(5, fom.getNbr_Place());
            stat.setString(6, fom.getCategorie());
            stat.setInt(7, fom.getId());
            st = stat.executeUpdate();
            // conn.close();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("erreur lors de la mise à jour " + e.getMessage());
        }

        //return st;
    }

    public void delete(int Id) {
        int st = 0;
        String sql = "DELETE FROM `formation` WHERE id='" + Id + "'";
        try {

            Connection conn = DbConnectinfo.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            //stat.setInt(1, id);
            st = stat.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return st;

    }

    /* public void removefor(int id) {
        java.sql.Connection conn = DbConnectinfo.Connect();
        String requete = "delete from formation where id='" + id + "'";
        try {

            java.sql.PreparedStatement ps = conn.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("publicité supprimé");
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }*/
    //une methode pour remplir les champs 
    public Formation getFormation(int Id) {
        String sql = "SELECT * FROM `formation` WHERE id=?";
        Formation fom = new Formation();
        try {

            Connection conn = DbConnectinfo.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            stat.setInt(1, Id);
            ResultSet rst = stat.executeQuery();
            while (rst.next()) {
                fom.setId(Id);
                fom.setCoach_id(rst.getInt(2));
                fom.setDescription(rst.getString(3));
                fom.setDate_Debut(rst.getString(4));
                fom.setDate_Fin(rst.getString(5));
                fom.setNbr_Place(rst.getString(6));
                fom.setCategorie(rst.getString(7));

            }
            //conn.close();
            return fom;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /* public static Formation getFormationdescription(String Description) {
       Formation fom = new Formation();
        try {
            String sql = "SELECT * FROM `formation` WHERE description like?";
            Connection conn = DbConnectinfo.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            stat.setString(1, Description);
            ResultSet rst = stat.executeQuery();
            if (rst.next()) {
                //fom.setId(rst.getInt(1));
                 fom.setId(rst.getInt(1));
                 fom.setCoach_id(rst.getInt(2));
                fom.setDescription(rst.getString(2));
                fom.setDate_Debut(rst.getString(3));
                fom.setDate_Fin(rst.getString(4));
                fom.setNbr_Place(rst.getString(5));
                 fom.setCategorie(rst.getString(6));

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fom;
      
    }*/
    public ObservableList<Formation> chercherFormation(String categorie) {
        String sql = "SELECT * FROM `formation` WHERE categorie like?";
        PreparedStatement ps = null;
        ObservableList<Formation> lst = FXCollections.observableArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, categorie);
            //ps.setInt(3,1);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                lst.add(new Formation(resultat.getInt("Id"), resultat.getInt("Coach_id"), resultat.getString("Description"), resultat.getString("Date_Debut"), resultat.getString("Date_Fin"), resultat.getString("Nbr_Place"), resultat.getString("Categorie")));

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

    /*
    public ObservableList<Formation> chercherFormation(String description) {
        String requete = "select * from formation where description like? ";
        PreparedStatement ps = null;
        ObservableList<Formation> lst = FXCollections.observableArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            ps = (PreparedStatement) conn.prepareStatement(requete);
            ps.setString(1, description);
            //ps.setInt(3,1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(new Formation(rs.getString("Description"), rs.getString("Date_Debut"), rs.getString("Date_Fin"), rs.getString("Nbr_Place"),rs.getString("Categorie"),rs.getInt("Coach_id"))
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
                Logger.getLogger(DbConnectinfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/
    public void rate(Rate r) {
        int st = 0;
        try {
            String sql = "INSERT INTO `rating`( `candidat_id`,`formation_id`,`avis`) VALUES (?,?,?)";
            Connection conn = DbConnectinfo.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
            stat.setInt(1, r.getCandidat_id());
            stat.setInt(2, r.getFormation_id());
            stat.setString(3, r.getAvis());
            st = stat.executeUpdate();
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return st;
    }

    public ArrayList<Formation> listerNomFormation() {
        ArrayList<Formation> listNomFormation = new ArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select * from formation");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Formation nomc = new Formation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                listNomFormation.add(nomc);

            }

          //  for (Formation nomc : listNomFormation) {
               // System.out.println(nomc.toString());
           // }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return listNomFormation;
    }
 public void saveproposition(Participant p ) {
        int st = 0;
        try {
            String sql = "INSERT INTO `proposition`( `nom`, `prenom`, `mail`, `contenu`) VALUES (?,?,?,?)";
            Connection conn = DbConnectinfo.Connect();
            PreparedStatement stat;
            stat = (PreparedStatement) conn.prepareStatement(sql);
           
            stat.setString(1, p.getNom());
            stat.setString(2, p.getPrenom());
            stat.setString(3, p.getMail());
            stat.setString(4, p.getProposition());
          
            st = stat.executeUpdate();
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return st;
    }

}
