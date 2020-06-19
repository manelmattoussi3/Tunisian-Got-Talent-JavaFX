/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import models.Actualite;
import models.Categorie;
//import Service.Conn;
import service.ServiceActualite;
import service.ServiceCategorie;
import java.awt.Desktop.Action;
import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.DbConnection;

/**
 *
 * @author Baleina
 */
public class FXMLDocumentController implements Initializable {
  //  Connection con;
  /*  public FXMLDocumentController()
    {
        con = Conn.getInstance().getCnx();
    }*/
    @FXML
    private ListView<String> listview;
    
    
    private Label label;
    @FXML
    private TextField tftitre;
    @FXML
    private ComboBox<Categorie> tfcombo;
    @FXML
    private TextArea tfcontenu;
    int idA;
    int idC;
     private DbConnection dc;
    final ObservableList datacategorie = FXCollections.observableArrayList();
    private ObservableList<String> items = FXCollections.observableArrayList();
     
    public void listerActualite()
    {
        
         try{
           Connection conn = dc.Connect();
            PreparedStatement pt = conn.prepareStatement("SELECT * FROM actualite");
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
     
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
   public void RefreshActu()
    {
        items.clear();
        listerActualite();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         dc = new DbConnection();
        listview.setItems(items);
        listerActualite();
    
        
        try{
            Connection conn = dc.Connect();
            PreparedStatement pt = conn.prepareStatement("SELECT idcategorie, nomcategorie from categorie");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                this.datacategorie.add(new Categorie(rs.getInt(1), (rs.getString(2))));
                
            }
           
        }
        catch(SQLException ex){
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }

         this.tfcombo.setItems(this.datacategorie);
        
           
    }    
    
    public void clearData(ActionEvent event)
  {
      this.tftitre.setText("");
      this.tfcontenu.setText("");
     // this.tfcombo.getItems().clear();
      //this.tfcombo.setItems(null);
  }

    @FXML
    private void AjouterActualite(ActionEvent event) {
        
        if(tftitre.getText().isEmpty())
        {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Vérification");
 
        // alert.setHeaderText("Results:");
            alert.setContentText("Vous devez saisir un titre");
 
            alert.showAndWait();
        }
        
        else if( tfcombo.getValue() == null)
        {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Vérification");
 
        // alert.setHeaderText("Results:");
            alert.setContentText("Vous devez choisir une catégorie");
 
            alert.showAndWait();     
                }
        else if(tfcontenu.getText().isEmpty())
        {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Vérification");
 
        // alert.setHeaderText("Results:");
            alert.setContentText("Contenu absent");
 
            alert.showAndWait();
         }
       
        else
        {
            
            ServiceActualite sa = new ServiceActualite();
            
            Actualite a = new Actualite();
            
            Categorie c = new Categorie();
            
            a.setTitre(tftitre.getText());
            a.setContenu(tfcontenu.getText());
            
            int h ;
            
            h = tfcombo.getSelectionModel().getSelectedItem().getIdCategorie();
            
            System.out.println(h);
            
            a.setIdcategorie(h);
            
            tfcombo.setOnAction(e ->{
            
                try {
                    Connection conn = dc.Connect();
                    String query = "select idCategorie from categorie where nomCategorie = ?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, (String)tfcombo.getSelectionModel().getSelectedItem().getNomCategorie());
                    ResultSet rs = pst.executeQuery();
                    
                    while(rs.next())
                    {
                        System.out.println(rs.getInt(1));
                        
                        
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            RefreshActu();
            
            });
            
           
           sa.ajouterActualite(a);
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Ajout d'actualités");
           alert.setHeaderText(null);
           alert.setContentText("Actualité ajouté avec succès");
           alert.showAndWait();
            
            clearData(event);
            
            RefreshActu();
        }
            
    }
    
    
    @FXML
    public void OpenCategorie(ActionEvent event) throws Exception
    {
        Stage primarystage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Categorie.fxml"));
        Scene scene = new Scene(root);
        primarystage.setScene(scene);
        primarystage.show();
    }

    @FXML
    private void modifierActualite(ActionEvent event) {
        if(tftitre.getText().isEmpty())
        {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Vérification");
 
        // alert.setHeaderText("Results:");
            alert.setContentText("Vous devez saisir un titre");
 
            alert.showAndWait();
        }
        
        else if( tfcombo.getValue() == null)
        {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Vérification");
 
        // alert.setHeaderText("Results:");
            alert.setContentText("Vous devez choisir une catégorie");
 
            alert.showAndWait();     
                }
        else if(tfcontenu.getText().isEmpty())
        {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Vérification");
 
        // alert.setHeaderText("Results:");
            alert.setContentText("Contenu absent");
 
            alert.showAndWait();
         }
       
        else
        {
             ServiceActualite sa = new ServiceActualite();
        
        
            
        idC = tfcombo.getSelectionModel().getSelectedItem().getIdCategorie();
        
        sa.modifierActualite(tftitre.getText(), tfcontenu.getText(),idC, idA);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Mise à jour d'un article");
           alert.setHeaderText(null);
           alert.setContentText("Article mise à jour avec succès");
           alert.showAndWait();
            clearData(event);
            RefreshActu();
        }
        
        
       
    }

    @FXML
    private void supprimerActualite(ActionEvent event) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Suppression d'une catégorie");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment supprimer ?");
               // alert.showAndWait();
                clearData(event);

                 Optional<ButtonType> result = alert.showAndWait();
                 if (result.get() == ButtonType.OK){
                        ServiceActualite sa = new ServiceActualite();
     sa.supprimerActualite(idA);
     RefreshActu();
                    } else {
                        
                    }
                 
        
    
     
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
         try {
             Connection conn = dc.Connect();
                    String query = "select * from actualite,categorie where titre=? and actualite.idcategorie = categorie.idcategorie";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, (String)listview.getSelectionModel().getSelectedItem());
                    ResultSet rs = pst.executeQuery();
                    
                    while(rs.next())
                    {
                        tftitre.setText(rs.getString("titre"));
                        tfcontenu.setText(rs.getString("contenu"));
                        idC = rs.getInt(5);
                        idA = rs.getInt(1);
                
                        
                        System.out.println(idC);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }
    
   @FXML
    public void Chat(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    } 
  
}
