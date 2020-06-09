/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

/**
 *
 * @author DevelopAndroid
 */
public class Launch extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Serveur/serveurchat.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args) {
       launch(args);
    }

}
