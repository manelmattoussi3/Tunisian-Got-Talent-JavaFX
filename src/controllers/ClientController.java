/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Client.EnvoyeurMessage;
import Client.FrameInterface;
import Client.RecepteurMessage;
import java.io.IOException;
import java.net.Socket;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class ClientController implements FrameInterface {

    private EnvoyeurMessage envoimessage;
    private RecepteurMessage resmessage;
    @FXML
    private TextField portclient;
    @FXML
    private TextArea Affichageclient;
    @FXML
    private TextField messageclient;
    @FXML
    private TextField adresseipclient;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void EnvoyerClientcliquer(MouseEvent event) {
        try {
            String recu = messageclient.getText();
            envoimessage.envoyer(recu);
            Affichageclient.appendText("Moi:" + recu.trim() + "\n");
            messageclient.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Connexioncliquer(MouseEvent event) {
        try {

            int port = Integer.valueOf(portclient.getText());
            String address = adresseipclient.getText();
            Socket sendStream = new Socket(address, port);
            envoimessage = new EnvoyeurMessage(sendStream.getOutputStream());

            resmessage = new RecepteurMessage(sendStream.getInputStream(), this);
            this.setEnvoyeur(envoimessage);
            this.setRecepteur(resmessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    @Override
    public void setEnvoyeur(EnvoyeurMessage e) {
        envoimessage = e;
    }

    @Override
    public void setRecepteur(RecepteurMessage r) {
        resmessage = r;
        resmessage.start();
    }

    @Override
    public void recoieMessage(String message) {
         Affichageclient.appendText("Coach:" + message.trim() + "\n");
    }
@FXML
    public void DashBord(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/EspaceConseil.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
}
