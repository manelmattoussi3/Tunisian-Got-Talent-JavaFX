/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.Sponsoring;
import models.SponsoringModel;

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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import utils.Source;
import controllers.LineChartController;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;

public class SponsoringService {

    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    /*public SponsoringService(){
        connection = (Connection) (Source.getInstance()).getConnection();
    }
     public ArrayList<SponsoringModel> afficherSponsoring(){
            ArrayList<SponsoringModel> listSponsoringModel = new ArrayList();
        try {
          
            PreparedStatement pst = (PreparedStatement) connection.prepareStatement("select S.id, S.description , S.type , C.nom as nomCandidat ,  S.image , S.date_debut , S.date_fin  from Sponsoring as S , Candidat as C where  S.Candidat_id=C.id ");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                SponsoringModel s;
                 s = new SponsoringModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                listSponsoringModel.add(s);
             
            }
            
            for (SponsoringModel d : listSponsoringModel){
                System.out.println(d.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);
             
        }       
        return listSponsoringModel;
    } */
    public void add(Sponsoring a) {
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            String req = "insert into Sponsoring (description,type,candidat_id,sponsor_id,image,date_debut,date_fin) values (?,?,?,?,?,?,?)";
            pst = (PreparedStatement) conn.prepareStatement(req);
            pst.setString(1, a.getDescription());
            pst.setString(2, a.getType());
            pst.setInt(3, a.getCandidat_id());
            pst.setInt(4, a.getSponsor_id());
            pst.setString(5, a.getImage());
            pst.setString(6, a.getDate_debut());
            pst.setString(7, a.getDate_fin());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void removeSponsoring(int id) {
        java.sql.Connection conn = DbConnectinfo.Connect();
        String requete = "delete from Sponsoring where id='" + id + "'";
        try {

            java.sql.PreparedStatement ps = conn.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("publicité supprimé");
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    /* public void update(int id , String description , String type , String image ) { 
        try {
        PreparedStatement pst  = (PreparedStatement) connection .prepareStatement("update Sponsoring set description =? , type =? , image=?  where id=?");
       
           
            pst.setString(1,description);
            pst.setString(2 ,type);
            pst.setString(3 ,image);
             pst.setInt(4, id);
            pst.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }    
    }*/
    public void update(Sponsoring c) {

        try {
            String req = "update sponsoring set description = ?, type = ?, image=?,  candidat_id= ?,date_debut=?,date_fin=? where id = ?";
            java.sql.Connection conn = DbConnectinfo.Connect();
            pst = (PreparedStatement) conn.prepareStatement(req);
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getType());
            pst.setString(1, c.getImage());
            pst.setInt(6, c.getCandidat_id());
            pst.setString(4, c.getDate_debut());
            pst.setString(5, c.getDate_fin());
            pst.setInt(7, c.getId());

            pst.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    /* public void afficherSponsoring(){
        try {
            ArrayList<Sponsoring> ls = new ArrayList();
            PreparedStatement pst = (PreparedStatement) connection.prepareStatement("select * from Sponsoring");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                
                Sponsoring p = new Sponsoring(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                ls.add(p);
            }
            
            for (Sponsoring p : ls){
                System.out.println(p.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    public Sponsoring getSponsoring(int id) {

        String req = "select * from sponsoring where id = ?";
        Sponsoring sponsoring = new Sponsoring();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            pst = (PreparedStatement) conn.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                sponsoring.setId(id);
                sponsoring.setDescription(rs.getString(3));
                sponsoring.setType(rs.getString(4));
                sponsoring.setImage(rs.getString(2));
                // sponsoring.setSponsor_id(rs.getInt(5));
                //sponsoring.setCandidat_id(rs.getInt(6));
                sponsoring.setDate_debut(rs.getString(5));
                sponsoring.setDate_fin(rs.getString(6));

            }
            return sponsoring;
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ObservableList<XYChart.Series<String, Number>> dataChartSecond(int sponsorId) {

        ObservableList<XYChart.Series<String, Number>> answer = FXCollections.observableArrayList();
        try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            String requete = "SELECT  CASE month(S.date_debut) "
                    + "                    when \"01\" then \"Janvier\" "
                    + "                    when \"02\" then \"Fevrier\" "
                    + "                    when \"03\" then \"Mars\" "
                    + "                    when \"04\" then \"Avril\" "
                    + "                    when \"05\" then \"Mai\" "
                    + "                    when \"06\" then \"Juin\" "
                    + "                    when \"07\" then \"Juillet\" "
                    + "                    when \"08\" then \"Aout\" "
                    + "                    when \"09\" then \"Septembre\" "
                    + "                    when \"10\" then \"Octobre\" "
                    + "                    when \"11\" then \"Novembre\" "
                    + "                    when \"12\" then \"Decembre\" "
                    + "                    END ,  "
                    + "                    count(S.id) From Sponsoring S where S.sponsor_id = ? GROUP BY month(S.date_debut)";

            pst = (PreparedStatement) conn.prepareStatement(requete);
            pst.setInt(1, sponsorId);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("nbr: " + rs.getInt(2) + " Month: " + rs.getString(1));
               XYChart.Series<String, Number> serie = new XYChart.Series<String,  Number>();
                serie.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
                answer.add(serie);
            }
            return answer;
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
