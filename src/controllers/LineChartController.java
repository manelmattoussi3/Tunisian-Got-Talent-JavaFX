/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import service.SponsoringService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.chart.BarChart;


/**
 * FXML Controller class
 *
 * @author Khoubaib
 */
public class LineChartController implements Initializable {
    
    @FXML BarChart barChart ; 
    @FXML private CategoryAxis categoryAxis ; 
    @FXML private NumberAxis numberAxis ; 
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barChart.setTitle("Croissance de nombre de publicités en fonction du temps");
        categoryAxis.setLabel("Mois");
        categoryAxis.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(
        "Janvier",
        "Fevrier",
        "Mars",
        "Avril",
        "Mai",
        "Juin",
        "Juillet",
        "Aout", 
        "Septembre",
        "Octobre",
        "Novembre",
        "Décembre"
        )));
        numberAxis.setLabel("Nombre des publicités");
        SponsoringService chartSponsoring = new SponsoringService();
        barChart.setData(chartSponsoring.dataChartSecond(1));
    }
    
   
    
     DropShadow shadow = new DropShadow();
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
   
}