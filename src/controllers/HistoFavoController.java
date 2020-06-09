/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import Service.Conn;
import service.ServiceCategorie;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author Baleina
 */
public class HistoFavoController implements Initializable {
    //Connection con;
   /* public HistoFavoController()
    {
        con = Conn.getInstance().getCnx();
    }*/
    @FXML
    private AnchorPane note;
    @FXML
    private ListView<String> listview;
    @FXML
    private ListView<String> listview1;
    int idA,idU;
    private DbConnection dc;
    /**
     * Initializes the controller class.
     */
    private ObservableList<String> items = FXCollections.observableArrayList();
    private ObservableList<String> items1 = FXCollections.observableArrayList();
   
    
    public void listerFavoris()
    {
        try{
           Connection conn = dc.Connect();
            PreparedStatement pt = conn.prepareStatement("SELECT * FROM Favoris");
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
               //items.add((rs.getString(1)));
               items.add((rs.getString("titre")));
               
               //System.out.println(rs.getString(2));
            }
            
            pt.close();
        }
        catch(SQLException ex){
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listerHistorique()
    {
        try{
           Connection conn = dc.Connect();
            PreparedStatement pt = conn.prepareStatement("SELECT * FROM historique");
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
               //items.add((rs.getString(1)));
               items1.add((rs.getString("titre")));
               items1.add((rs.getString("dateAjout")));
               
               //System.out.println(rs.getString(2));
            }
            
            pt.close();
        }
        catch(SQLException ex){
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
         listview.setItems(items);
         listerFavoris();
         listview1.setItems(items1);
         listerHistorique();
    }    

    @FXML
    private void onTableItemSelect(MouseEvent event) {
    
        
    }

    @FXML
    private void consulterInformation(MouseEvent event) {
    }
    
}
