/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Serveur.EnvoyeurMessage;
import Serveur.FrameInterface;
import Serveur.RecepteurMessage;
import Serveur.ServeurCode;
import java.io.IOException;
import javafx.event.ActionEvent;
//import java.net.URL;
//import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//import Serveur.EnvoyeurMessage;
//import Serveur.FrameInterface;
//import Serveur.RecepteurMessage;
//import Serveur.ServeurCode;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class ServeurchatController implements FrameInterface {

    private EnvoyeurMessage emessage;
    private RecepteurMessage remessage;
    @FXML
    private Pane contactpane;
    @FXML
    private TextField portserveur;
    @FXML
    private TextArea Affichageserveur;
    @FXML
    private TextField messageserveur;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    /* @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     */
    @FXML
     void connexionserveurCliquer(MouseEvent event) {
        int port = Integer.valueOf(portserveur.getText());
        ServeurCode servc = new ServeurCode(this, port);
        servc.start();

    }

    @FXML
     void EnvoyerServeurcliquer(MouseEvent event) {
        try {
            String recu=messageserveur.getText();
            emessage.envoyer(recu);
            Affichageserveur.appendText("Moi:"+recu.trim()+"\n");
            messageserveur.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void recoieMessage(String message) {
        Affichageserveur.appendText("Candidat:"+message.trim()+"\n");
    }

    @Override
    public void setEnvoyeur(EnvoyeurMessage e) {
        emessage=e;
    }

   

   

    @Override
    public void setRecepteur(RecepteurMessage r) {
        remessage=r;
        remessage.start();
    }
 @FXML
    public void DashBord(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/ExampleTableView.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
}
