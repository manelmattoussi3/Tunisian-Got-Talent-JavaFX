/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Baleina
 */
public class ServiceCategorie {
   // Connection con;
   
 /*  public ServiceCategorie()
   {
       con = Conn.getInstance().getCnx();
   }*/
   
   public void ajouterCategorie(Categorie c)
   {
       
       
       try 
            {
                 java.sql.Connection conn = DbConnectinfo.Connect();
                 Statement st = conn.createStatement();
                 String req="insert into categorie values ("+c.getIdCategorie()+", '"+c.getNomCategorie()+"')";
                 st.executeUpdate(req);
            }    catch (SQLException ex) 
            {
                Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       System.out.println(c.toString());
   }
   
   public void supprimerCategorie(int idcategorie)
   {
       try {
           java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pt = conn.prepareStatement("delete from categorie where idcategorie=?");
            pt.setInt(1, idcategorie);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void modifierCategorie(String categorie, int idcategorie)
   {
       try {
           java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pt  =conn.prepareStatement("update categorie set nomCategorie=?  where idCategorie=?");
            pt.setString(1, categorie);
            pt.setInt(2, idcategorie);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
      
   }
   
   public List<Categorie> afficherCategorie() throws Exception
   {
       
            java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pt = conn.prepareStatement("select nomCategorie from categorie");
            ResultSet rs = pt.executeQuery();
            List<Categorie> cat = new ArrayList<>();
            while(rs.next()){
                
                Categorie c = new Categorie();
                c.setNomCategorie(rs.getString(1));
                cat.add(c);
            }
             
   return cat;
   }
}
