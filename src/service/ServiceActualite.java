/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.Actualite;
import models.Categorie;
import models.Commentaire;
import models.Note;
import static models.SmsSender.ACCOUNT_SID;
import static models.SmsSender.AUTH_TOKEN;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Baleina
 */
public class ServiceActualite {
    
  // Connection con;
   
 /*  public ServiceActualite()
   {
       con = Conn.getInstance().getCnx();
   }*/
   
   public void ajouterNote(Note note)
   {
       
   }
   
   public void ajouterActualite(Actualite a)
   {
       try 
            {
                java.sql.Connection conn = DbConnectinfo.Connect();
                 Statement st = conn.createStatement();
                 String req="insert into actualite (titre,contenu,idcategorie) values ('"+a.getTitre()+"', '"+a.getContenu()+"',  '"+a.getIdcategorie()+"')";
                 st.executeUpdate(req);
                 
            }    catch (SQLException ex) 
            {
                Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
            }
       
              // Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       // Message message = Message
               // .creator(new PhoneNumber(""), // to
                        //new PhoneNumber("+15709894862"), // from
                     //   "Tester l'Api")
              //  .create();

       // System.out.println(message.getSid());
   }
   
   
   
   public void modifierActualite(String titre, String contenu, int idCat, int idActualite)
   {
       try {
           java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pt  =conn.prepareStatement("update actualite set titre =?, contenu=?, idCategorie=?  where idActualite=?");
            pt.setString(1, titre);
            pt.setString(2, contenu);
            pt.setInt(3, idCat);
            pt.setInt(4, idActualite);
             
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void supprimerActualite(int idActualite)
   {
       try {
           java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pt = conn.prepareStatement("delete from actualite where idActualite=?");
            pt.setInt(1, idActualite);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void moyenneNote(Note note)
   {
       
   }
   
   public void afficherActualite()
   {
       try {
           
           java.sql.Connection conn = DbConnectinfo.Connect();
            List<Actualite> ls =new ArrayList<Actualite>();
            PreparedStatement pt = conn.prepareStatement("SELECT titre, contenu, dateAjout, nomCategorie from actualite, categorie where actualite.idcategorie = categorie.idcategorie");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                System.out.print(rs.getString(1));
                System.out.print("----");
                System.out.print(rs.getString(2));
                System.out.print("----");
                System.out.print(rs.getString(3));
                System.out.print("----");
                System.out.print(rs.getString(4));
                System.out.println("");
                
               
            }
            
            for (Actualite a : ls){
                System.out.println(a.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void lireActualite(int idactualite)
   {
       try {
           
           java.sql.Connection conn = DbConnectinfo.Connect();
            List<Actualite> ls =new ArrayList<Actualite>();
            PreparedStatement pt = conn.prepareStatement("SELECT contenu FROM actualite WHERE idActualite=?");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
               
                
                System.out.print(rs.getString(2));
                System.out.print("----");
               
               
            }
            
            for (Actualite a : ls){
                System.out.println(a.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void getCategorie()
   {
       
       
       
      try {
            java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pt = conn.prepareStatement("SELECT titre, nomCategorie FROM actualite, categorie where categorie.idcategorie = actualite.idcategorie");
            PreparedStatement pt1 = conn.prepareStatement("SELECT idcategorie, nomCategorie FROM categorie");
            ResultSet rs = pt.executeQuery();
            ResultSet rs1 = pt1.executeQuery();
            
            while(rs.next()){
                
                
               
               // ls.add(a);
                
                System.out.print(rs.getString(1));
                System.out.println(rs.getString(2));
//              System.out.println(rs1.getInt(0));
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
   }
   
   
   
}
