/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Entity.Evenement;
import Entity.Participationn;
import Service.ParticipationnService;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class DetailEvenementController implements Initializable ,MapComponentInitializedListener{

    private GoogleMap map;
    @FXML
    private Circle btnClose;
    private ImageView ImageEvent;
    @FXML
    private Label labelTitre;
    private JFXButton btnDescr;
    @FXML
    private JFXButton btn_participer;
    @FXML
    private ImageView btnReturns;
    @FXML
    private Label labelDate;
    @FXML
    private Label labellocalisation;
    @FXML
    private Label labelDesc;
    @FXML
    private Pane pnlDeta;
    @FXML
    private Pane pnlMAp;
    private Hyperlink consMap;
    @FXML
    private JFXButton GoMap;
    @FXML
    private GoogleMapView mapView1;
    @FXML
    private ImageView imgEvenet;
    @FXML
    private ImageView btnReturnsDet;
    @FXML
    private Rating note;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    } 
   int etat ;
   int idEvent;
   String titrEvenement;
        public void transfertMessage(int id_evenement,int e,String titre, String description, String datedebut,String datefin,String etablissement,String localisation,String image) {
            
        labelTitre.setText("Titre:"+titre);
        labelDesc.setText("Description:"+description);
        labelDate.setText("debut:"+datedebut+" Fin: "+datefin);
        //LabelEtablissement.setText("Etablissement :"+etablissement);
        labellocalisation.setText("Localisation :"+localisation);
      imgEvenet.setImage(new Image(
              image,120, 120, true, true));

        etat =e;
        idEvent = id_evenement;
        titrEvenement = titre;
   }

    @FXML
    private void handleActionButtonParticiper(ActionEvent event) {
      if(etat ==0) {
            
                    Image goodImage = new Image("http://icons.iconarchive.com/icons/paomedia/small-n-flat/72/sign-check-icon.png", true);

             Notifications notificationBuild = Notifications.create()
                                      .title("Demande de participation")
                                      .text("Votre demande envoyé avec success :) ")
                                      .graphic(new ImageView(goodImage))
                                      .hideAfter(Duration.seconds(5))
                                      .position(Pos.TOP_RIGHT)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      
                                      
                                  }
                                  
                              });
             
                              notificationBuild.darkStyle();
                              notificationBuild.show();
                              System.out.println("id evemenet = "+idEvent+"is public == "+etat);
                           Participationn participationn = new Participationn(idEvent,"en cours", 1);
                                   //lors de participation ispulibc  = 0 ba3d kif y confirmer admin tw1i 1 
                                   ParticipationnService participationnService = new ParticipationnService();
                                   participationnService.ajouterParticipation(participationn);
                                      
            
            
        }
        else 
        {
            //lahné twli is public  =1
            
                 Participationn participationn = new Participationn(idEvent,"confirmé", 1);
                                   //lors de participation ispulibc  = 0 ba3d kif y confirmer admin tw1i 1 
                                   ParticipationnService participationnService = new ParticipationnService();
                                   participationnService.updateEtat(participationn);
                                      
                 
          //  btnParticiper.se(Color.RED);
          Alert a = new Alert(Alert.AlertType.ERROR);
          a.setTitle("Evenement Confirmé");
          
          a.setContentText("Vous avez deja Participer ");
           a.showAndWait();
            
        
        }
        
        
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) throws IOException {
        
        
            if(event.getSource().equals(btnReturns)) {
             Parent root = FXMLLoader.load(getClass().getResource("ConsulterEvenement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
            }
            else if(event.getSource().equals(btnReturnsDet)) {
                pnlMAp.setVisible(false);
                new FadeIn(pnlDeta).play();
                pnlDeta.toFront();
              
            }
            else 
                System.exit(0);
    }


    @FXML
    private void handleActionButtonGoMap(ActionEvent event) {
      
            
        if(event.getSource().equals(GoMap)) {
           pnlDeta.setVisible(false);
           System.out.println("click map");
           mapView1.addMapInializedListener(this);
           new FadeIn(pnlMAp).play();
           pnlMAp.toFront();
           chooseCity(35.828829,10.640525);
        
        }
    }
        
       
            
    public void chooseCity(double Lat,double Long) {

   MapOptions options = new MapOptions();

        options.center(new LatLong(Lat, Long)) 
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        
         map = mapView1.createMap(options); 
    }   
 @Override
    public void mapInitialized() {
    
    }
 
}
