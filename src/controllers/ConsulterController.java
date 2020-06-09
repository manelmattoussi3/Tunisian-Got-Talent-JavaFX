/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Sponsoring;
import models.SponsoringReponse;
import service.SponsoringService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.DbConnectinfo;
import utils.DbConnection;
/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class ConsulterController implements Initializable {
    

    @FXML
    private Button btnBack;
    @FXML
    private ImageView imageView;
   // public Sponsoring newSponsoring ; 
    public Sponsoring sponsoring ; 
    
    @FXML
    private Label txtDescription;
    
    @FXML
    private Label description;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          DropShadow shadow = new DropShadow();}
      
          
      @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/test.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        TestController afficherSponsoringController = loader.getController();
        afficherSponsoringController.AfficheSponsoring();
        primaryStage.setTitle("Lister Publicité");
        primaryStage.show();
    }    

    public void loadSponsoring(int idSponsoring) {
      
       /* System.out.println(idSponsoring);
         SponsoringService dc = new SponsoringService();
       
        ArrayList<SponsoringReponse> listSponsoringReponse = dc.getSponsoring(int id);
       
        
     
        
         
        SponsoringReponse = f2.getIdSponsoring();
        lblNom.setText(f2.getNom());
        lblPrenom.setText(f2.getPrenom());
        lblNominf.setText(f2.getNom());
        lblLogininf.setText(f2.getLogin());
        lblEmailinf.setText(f2.getMail());
        lblPrenominf.setText(f2.getPrenom());*/
       //java.sql.Connection conn = DbConnectinfo.Connect();
        System.out.println(idSponsoring);
        SponsoringService sponsoringService = new SponsoringService();
        sponsoring = sponsoringService.getSponsoring(idSponsoring);
        System.out.println(sponsoring.getImage());

        File file = new File(sponsoring.getImage());
        Image image = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), true, true);;
        imageView.setImage(image);
        txtDescription.setText(sponsoring.getDescription()); 
       // final String destinationUrl = "src/Photo/default.jpg"; 
       //final Image image = new Image(destinationUrl);
         sponsoringService.getSponsoring(sponsoring.getId());
    }
}  
