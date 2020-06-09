/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class AccueilController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ImageView I1;
    @FXML
    private ImageView I2;
    @FXML
    private ImageView I3;
    @FXML
    private ImageView I4;
    @FXML
    private ImageView I5;
    @FXML
    private ImageView I7;
    @FXML
    private ImageView I8;
    @FXML
    private ImageView I13;
    @FXML
    private ImageView I14;
    @FXML
    private ImageView I15;
    @FXML
    private ImageView I6;
    @FXML
    private MediaView mvt;
    @FXML
    private Button btnplay;
    @FXML
    private Button btnstop;
    @FXML
    private Label labe2;
    @FXML
    private Label labe3;
    MediaPlayer mp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String VUrl = "file:/C:/Users/DevelopAndroid/Desktop/javam/videoplayback.mp4";
        Media media = new Media(VUrl);
        mp = new MediaPlayer(media);
        mvt.setFitHeight(800);
        mvt.setFitWidth(600);
        mvt.setMediaPlayer(mp);
    }

    @FXML
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("ExampleTableView.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();

    }
     @FXML
    public void conseilScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();

    }
     @FXML
    public void DashBord(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
 @FXML
    public void profilcandidat(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
    
    
    @FXML
    private void onclickbtn_play(ActionEvent event) {
        if (mp.getStatus() == PLAYING) {
            mp.stop();
            mp.play();
        } else {
            mp.play();
        }
    }

    @FXML
    private void onclickbtn_stop(ActionEvent event) {
        mp.stop();
    }
     public void quiter(ActionEvent event){
    System.exit(0);
    
    }

}
