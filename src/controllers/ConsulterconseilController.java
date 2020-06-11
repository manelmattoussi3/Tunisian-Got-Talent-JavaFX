/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Conseil;
import models.ConseilModel;
import service.Serviceconseil;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class ConsulterconseilController implements Initializable {
public Conseil conseil;
    
    @FXML
    private Label txtDescription;
     private FileChooser filechooser;
    @FXML
    private ImageView imageview;
    private DbConnection dc;
    @FXML
    private Label txtDescription1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       dc = new DbConnection();
    }    
    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/EspaceConseil.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
       
     public void loadConseil(int idConseil) {
          Connection conn = dc.Connect();
         filechooser = new FileChooser();
        // String path = new File("src/img/kk.jpg").getAbsolutePath();
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
        System.out.println(idConseil);
       Serviceconseil conseilService = new Serviceconseil();
        conseil = conseilService.getConseil(idConseil);
        System.out.println(conseil.getImagecons());

        File file = new File(conseil.getImagecons());
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);
        txtDescription.setText(conseil.getCategoricons()); 
        txtDescription1.setText(conseil.getDescriptioncons()); 
       // final String destinationUrl = "src/Photo/default.jpg"; 
       //final Image image = new Image(destinationUrl);
        conseilService.getConseil(conseil.getIdcons());
    }
}
