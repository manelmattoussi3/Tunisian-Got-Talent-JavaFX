/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class VoirPlusController implements Initializable {

    @FXML
    private MediaView mediaview1;
    @FXML
    private MediaView mediaview2;
    @FXML
    private Slider slider;
    @FXML
    private Slider slider1;
    @FXML
    private Rating rating;
    @FXML
    private Rating rating1;
private FileChooser filechooser;
private FileChooser filechooser1;
 private MediaPlayer mediaplayer1;
    private Media media1;
     private MediaPlayer mediaplayer2;
    private Media media2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         filechooser = new FileChooser();
         String path = new File("src/Video/piano gospel lessons.mp4.mp4").getAbsolutePath();
       
        media1 = new Media(new File(path).toURI().toString());
        mediaplayer1 = new MediaPlayer(media1);
        mediaview1.setMediaPlayer(mediaplayer1);
        mediaplayer1.setAutoPlay(true);
        slider1.setValue(mediaplayer1.getVolume() * 100);
        slider1.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaplayer1.setVolume(slider1.getValue() / 100);
                
            }
        });
         filechooser1 = new FileChooser();
         String path1 = new File("src/Video/girlsaloud guitar lesson.mp4.mp4").getAbsolutePath();
       
        media2 = new Media(new File(path1).toURI().toString());
        mediaplayer2 = new MediaPlayer(media2);
        mediaview2.setMediaPlayer(mediaplayer2);
        mediaplayer2.setAutoPlay(true);
        slider.setValue(mediaplayer2.getVolume() * 100);
        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaplayer2.setVolume(slider.getValue() / 100);
                
            }
        });
        
    }    

     @FXML
    public void DashBord(ActionEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/EspaceFormation.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }

    @FXML
    private void quiter(ActionEvent event) {
         System.exit(0);
    }

    @FXML
    private void onclickbtn_play1(ActionEvent event) {
         mediaplayer1.play();
    }

    @FXML
    private void onclickbtn_pause2(ActionEvent event) {
         mediaplayer2.pause();
    }

    @FXML
    private void onclickbtn_pause1(ActionEvent event) {
         mediaplayer1.pause();
    }

    @FXML
    private void onclickbtn_stop2(ActionEvent event) {
        mediaplayer2.seek(mediaplayer2.getStopTime());
        mediaplayer2.stop();
    }

    @FXML
    private void onclickbtn_stop1(ActionEvent event) {
        mediaplayer1.seek(mediaplayer1.getStopTime());
        mediaplayer1.stop();
    }

    @FXML
    private void addrate(MouseEvent event) {
    }

    @FXML
    private void onclickbtn_play2(ActionEvent event) {
         mediaplayer2.play();
    }
    @FXML
    public void Evaluation(MouseEvent event) throws IOException {
        Parent tableviewParent = FXMLLoader.load(getClass().getResource("/fxml/EvaluationView.fxml"));
        Scene tableviewScene = new Scene(tableviewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableviewScene);
        window.show();
    }
}
