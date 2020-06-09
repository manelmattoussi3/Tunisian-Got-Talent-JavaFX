 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class AccueilFXMLController implements Initializable {
     @FXML
    private Button btnPublicite;
     @FXML
    private Button btnDemande;
     @FXML
    private Button btnCandidat;
    private ImageView imageview;
     @FXML
    private Button btnDeconnecter;
    @FXML
    private ImageView img3;
    
    @FXML
    private ImageView img5;
    @FXML
    private TextField email;
     

     
     public void myFunction( String Text )
        {
           email.setText(Text);
        }
     
    private void handleButton(ActionEvent event) {
        Image image =new Image("Photo/image.jpg");
        imageview.setImage(image);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
   /* @FXML
    private void btnPubliciteAction(ActionEvent event) throws IOException {
       
       ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficherSponsoring.fxml"));
            loader.load();
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            AfficherSponsoringController afficherSponsoringController = loader.getController();
            afficherSponsoringController.AfficheSponsoring();
            //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
            primaryStage.setTitle("Liste des publicités");
             primaryStage.show();
        
    }*/
      @FXML
    private void btnPubliciteAction(ActionEvent event) throws IOException {
       
       ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/test.fxml"));
            loader.load();
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            TestController afficherSponsoringController = loader.getController();
           afficherSponsoringController.AfficheSponsoring();
            primaryStage.setTitle("Liste des publicités");
            primaryStage.show();
        
    }
    @FXML
     private void btnDemandeAction(ActionEvent event) throws IOException {
       
         ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/AfficherDemande.fxml"));
            loader.load();
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            AfficherDemandeController afficherDemandeController = loader.getController();
            afficherDemandeController.AfficheDemande();
            //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
            primaryStage.setTitle("Liste des Demandes");
             primaryStage.show();
    }
     
    @FXML
     private void btnCandidatAction(ActionEvent event) throws IOException {
       ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ListeCandidats.fxml"));
            loader.load();
            Parent p = loader.getRoot();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(p));
            ListeCandidatsController listeCadidatsController = loader.getController();
           listeCadidatsController.AfficheCandidat();
            //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
            primaryStage.setTitle("Liste des Candidats");
            primaryStage.show();
    }
     @FXML
    private void btnDeconnecterAction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnDeconnecter.getScene().getWindow();
        primaryStage.close();
    }
     @FXML
     private void btnActualite(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ActualiteUsers.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Actualite");
        stage.show();
}
       @FXML
    private void handleButton9Action(MouseEvent event) throws MessagingException 
    {
          try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/EditUserSponsor.fxml")); 

                    Parent root = (Parent) loader.load();
                    EditUserSponsor utcontroller = loader.getController(); 
                    utcontroller.myFunction(email.getText());
                    
                    System.out.println(email.getText());
               
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                               
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                  
                   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
       
    }
    @FXML
    private void handleButton10Action(MouseEvent event) throws MessagingException 
    {
       try {
                   FXMLLoader loader =  new  FXMLLoader(getClass().getResource("/fxml/ReclamationSponsor.fxml")); 

                    Parent root = (Parent) loader.load();
                    ReclamationSponsorController utcontroller = loader.getController(); 
                    utcontroller.myFunction(email.getText());
               
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                               
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
               
                } catch (IOException ex) {
                    Logger.getLogger(ReclamationSponsorController.class.getName()).log(Level.SEVERE, null, ex);
                }
       
    }
    
    
    
}