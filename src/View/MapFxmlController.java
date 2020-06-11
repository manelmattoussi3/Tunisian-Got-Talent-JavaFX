/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Outils.AutoCompleteComboBoxListener;
import com.jfoenix.controls.JFXComboBox;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.sun.prism.PhongMaterial.MapType;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class MapFxmlController   implements Initializable ,MapComponentInitializedListener  {

    @FXML
    private GoogleMapView mapView ;
    
    private GoogleMap map;
    @FXML
    private Pane pnlMap;
    @FXML
    private JFXComboBox<String> combox;
    @FXML
    private ImageView btn_return;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hii");
        mapView.addMapInializedListener(this);
        
        
        combox.getItems().addAll("Tunis","Ben arous","ariana","Benzart","Zaghouen","Nebel","Beja","Jandouba"
        ,"Selyena","Kef","Mestir","Soussa","Tozeur","gassrine","Sfax","Sidi bouzid","Gafsa","gbeli","Kairouan","Tataouin","Mahdia","Manouba","Mednin");
        
        
    

                new AutoCompleteComboBoxListener<>(combox);

        
        
        
    
    
    }    
     
    
    
    public void chooseCity(double Lat,double Long) {

                   LatLong joeSmithLocation = new LatLong(Lat, Long);
   
        
        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(joeSmithLocation)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(9);
                   
        map = mapView.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);
        
        MarkerOptions markerOptions2 = new MarkerOptions();
        
       
        
        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
 
        
        map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
    
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, joshAndersonMarker);
    }   

        
    

    

    @FXML
    private void handleActionVombox(ActionEvent event) {
        
       // new AutoCompleteComboBoxListener<>(combox);
        System.out.println("city = "+combox.getValue());
        
      
        if(combox.getValue()!=null && combox.getValue().equals("Tunis"))
            
        {        mapInitialized();

            chooseCity(36.84427963493555,10.209748859375004);
        }
         
   else if(combox.getValue()!=null && combox.getValue().equals("Soussa")) 
            
       chooseCity(35.828829,10.640525);
        

    else if(combox.getValue()!=null && combox.getValue().equals("Tozeur")) 
            
        chooseCity(33.913485,8.1118241);
         
    else  if(combox.getValue()!=null && combox.getValue().equals("Nebel")) 
            
      chooseCity(36.4823676,10.6707197);
        
     else   if(combox.getValue()!=null && combox.getValue().equals("Mestir")) 
            
        chooseCity(35.7398724,10.7988397);
        
        else   if(combox.getValue()!=null && combox.getValue().equals("Sfax")) 
            
        chooseCity(34.7231273,10.3358789);
        
        
         
    }


        private void loadSceneAndSendMessage(MouseEvent event) {
        try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             AjouterEvenementController a = loader.getController();
               String localisation = combox.getValue();
            //Pass whatever data you want. You can have multiple method calls here
           a.transferMessage(localisation);
            // FXMLLoader loader  =new FXMLLoader();
            // Parent root = loader.load();
            Scene s = new Scene(root);
            stage.setScene(s);
          //  stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        } catch (IOException ex) {
                                  System.err.println(ex);
        }
    }

    @FXML
    private void handleActionReturnAjoutEvenement(MouseEvent event) {
        loadSceneAndSendMessage(event);
        
    }

    @Override
    public void mapInitialized() {
    }
}
      
      
        
    
    
        
        
    
        
        
        
    
