/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.Actualite;
import models.Commentaire;
import models.Favoris;
import models.Historique;
import models.Note;
import models.Utilisateur;
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
public class ServiceUtilisateur {
  //  Connection con;
   
  /* public ServiceUtilisateur()
   {
       con = Conn.getInstance().getCnx();
   }*/
   
   public int recupererId()
    {
        Utilisateur u = new Utilisateur();
        
        return u.getIdUtilisateur();
    }
   
   public void noter(int idactu, double valeur, int u)
   {
      // int ac, ut, no;
         
      // ut = u.getIdUtilisateur();
       //ac = a.getIdActualite();
       
       Note n = new Note();
       
       n.setUtilisateur_id(u);
       n.setActualite_id(idactu);
       n.setValeur(valeur);
       
      
           try 
            {
                 java.sql.Connection conn = DbConnectinfo.Connect();
                 Statement st = conn.createStatement();
                 String req="insert into note values ("+n.getIdNote()+", "+n.getValeur()+", "+n.getActualite_id()+", "+n.getUtilisateur_id()+")";
                 st.executeUpdate(req);
            }    catch (SQLException ex) 
            {
                Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
           
       
       
   }
   
   public void ajouterActuFavoris(int idactu, String titre, int idu)
   {
  
       Actualite a = new Actualite();
       Favoris f = new Favoris();
       
       f.setTitre(titre);
       f.setUtilisateur_id(idu);
       f.setActualite_id(idactu);
       
        try 
            {
                 java.sql.Connection conn = DbConnectinfo.Connect();
                 Statement st = conn.createStatement();
                 String req="insert into favoris values ("+f.getIdFavoris()+", '"+f.getTitre()+"', "+f.getUtilisateur_id()+", '"+f.getActualite_id()+"')";
                 st.executeUpdate(req);
            }    catch (SQLException ex) 
            {
                Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
           
   }
   
   public void ajouterCommentaire(int ida,String contenu, int idu)
   {
       Date date = new Date();
        
       Commentaire c = new Commentaire();
       c.setActualite_id(ida);
       c.setContenu(contenu);
       c.setUtilisateur(idu);   
       c.setDateAjout(date.toString());
       try 
            {
                 java.sql.Connection conn = DbConnectinfo.Connect();
                 Statement st = conn.createStatement();
                 String req="insert into commentaire1 values ("+c.getIdCommentaire()+", '"+c.getContenu()+"', '"+c.getDateAjout()+"', '"+c.getActualite_id()+"', '"+c.getUtilisateur()+"')";
                 st.executeUpdate(req);
            }    catch (SQLException ex) 
            {
                Logger.getLogger(ServiceActualite.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       System.out.println(c.toString());
   }
   
   public void ajouterHistorique(int idA, int idU, String titre)
   {
       int art, user;
       Date date = new Date();
       
       
       Historique h = new Historique();
       
       h.setArticleId(idA);
       h.setUtilisateurId(idU);
       h.setTitre(titre);
       h.setDateAjout(date.toString());
       
       try 
            {
                 java.sql.Connection conn = DbConnectinfo.Connect();
                 Statement st = conn.createStatement();
                 String req="insert into historique values ("+h.getIdHistorique()+", '"+h.getTitre()+"','"+h.getDateAjout()+"', "+h.getArticleId()+", "+h.getUtilisateurId()+")";
                 st.executeUpdate(req);
            }    catch (SQLException ex) 
            {
                Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
      
   }
   
   public void supprimerActuFavoris(int favoris)
   {
      try {
             java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pt1 = conn.prepareStatement("select idFavoris from favoris");
            ResultSet rs = pt1.executeQuery();
            while(rs.next()){
              rs.getInt(1);
                System.out.print("----");
            if(favoris == rs.getInt(1))
            {
                try {
                    
            PreparedStatement pt = conn.prepareStatement("delete from favoris where idFavoris=?");
            pt.setInt(1, rs.getInt(1));
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
                 

            }
            
                       
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      
   }
   
   public void supprimerArtuHistorique(int histo)
   {
       try {
             java.sql.Connection conn = DbConnectinfo.Connect();
            PreparedStatement pt1 = conn.prepareStatement("select idHistorique from historique");
            ResultSet rs = pt1.executeQuery();
            while(rs.next())
            {
                rs.getInt(1);
                System.out.print("----");
                if(histo == rs.getInt(1))
                {
                    try {
                            PreparedStatement pt = conn.prepareStatement("delete from historique where idHistorique=?");
                            pt.setInt(1, rs.getInt(1));
                            pt.executeUpdate();
                        } catch (SQLException ex) 
                            {
                                 Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
                            }
                }
            
            }
            } catch (SQLException ex) 
            {
                Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
          
   }
      
       
        
}

