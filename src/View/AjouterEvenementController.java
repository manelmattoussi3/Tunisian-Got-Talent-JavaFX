/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Entity.Evenement;
import Service.EvenementService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Khawla
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private Button btn_annuler;
    @FXML
    private JFXDatePicker date_fin;
    private JFXTextField txt_localisation;
    @FXML
    private ComboBox<String> cb_categories;
    @FXML
    private JFXTextField txt_titre;
    @FXML
    private JFXTextField txt_description;
    @FXML
    private JFXDatePicker date_debut;
    private JFXTextField txt_etablissement;
    @FXML
    private JFXTextField nb_min_participant;
    private JFXTextField nb_actuel;
    @FXML
    private JFXTextField nb_max_participant;
    @FXML
    private JFXButton btn_image;
    LocalDate dateDebut,dateFin;
    String titre = "initialiation to avoid error";
    String local;
    String description,etabl;
    String nbMinPart,nbMaxPart,nbAct;
    Format formatter;
    String image;
    String isPublic ;
    String localisation ;
    int nbMin ,nbMax,nbActt;
    int prix;
    Date date = new Date();
    private EvenementService e = new EvenementService();  
    @FXML
    private JFXTextField txt_prix;
    @FXML
    private Button btn_ajouter;
    // AfficheEvenementController aec = new AfficheEvenementController();
    @FXML
    private ImageView immg;
    @FXML
    private Label description_label;
    @FXML
    private Label date_label;
    @FXML
    private Label titre_label;
    @FXML
    private Label categorie_label;
    @FXML
    private Label nb_min_label;
    @FXML
    private Label max_label;
    @FXML
    private Label prix_label;
    @FXML
    private Label label_image;
    @FXML
    private Hyperlink btn_map;
    @FXML
    private Label label;
    @FXML
    private Pane pnlEvenement;
    @FXML
    private Circle btnClose;
    /**
     * Initializes the controller class.
     */
    
    
    
 public    String photoEvenement = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
        "musique",
        "peinture",
        "dessin"
    );

         cb_categories.setItems(options);
         // cb_categories.getItems().add("");
       
    } 
    /***************************************************************************************/
  
     Evenement evenement;
   //Control saisie des input Number 
    public void controleNumber() {
        try {
                  nbMin = Integer.parseInt(nbMinPart);
                  nbMax = Integer.parseInt(nbMaxPart);

                
            }catch(Exception e) {
               // alert.setContentText("Nbr min/Nbr max doivent etre non texte");
            }
    }

    
    ObservableList<Evenement>o =FXCollections.observableArrayList();

  /***************************************************************************************/  
    @FXML
    private void handleMouseEvent(MouseEvent event) throws IOException {
         //System.exit(0);
          o.clear();
          o.add(evenement);
          e.afficherEvenements();
              Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
       
       // AjouterEvenementController ajoutEv = new AjouterEvenementController().;
       stage.show();
    }

    
    
    
    @FXML
    private void handleButtonAnnulerAction(ActionEvent event) {
        
        clear();
    }

    @FXML
    private void handleButtonAddEvnementAction(ActionEvent event) throws ParseException {
        
        String pattern = "dd/MM/yyyy";
        titre = txt_titre.getText().toString().toLowerCase();
        description = txt_description.getText().toString().toLowerCase();
        localisation = label.getText().toString();
                 System.out.println("tittre ="+titre);

      
        nbMaxPart = nb_max_participant.getText().toLowerCase();
        //nbAct = nb_actuel.getText();
    //    nbActt = Integer.parseInt(nbAct);
        image = imageUrl;
        cb_categories.setEditable(false);
        //DateFormat df = new SimpleDateFormat(LocalDate.parse(dateDebut));
        dateDebut = date_debut.getValue();
        dateFin = date_fin.getValue();
       // Alert alert =new Alert(AlertType.INFORMATION);
       // alert.setTitle(" Controle des champs");
      
       
    
         for(int i =0; i<txt_prix.getText().length();i++) {
             if(txt_prix.getText().charAt(i)>='a' && txt_prix.getText().charAt(i)<='z' && txt_prix.getText().equals(" ")) {
                    prix_label.setText("Verifier prix ");
                    return ;
             }
             else {
                 prix_label.setText(" ");
                             prix =Integer.parseInt(txt_prix.getText().toString());

             }
             }
            
            //Catch exception for Number 
            
            //
            
      int nbMaxx = Integer.valueOf(nb_max_participant.getText());
      int nbMinn = Integer.valueOf(nb_min_participant.getText());

      evenement = new Evenement(titre,description,localisation,cb_categories.getValue()
                  ,dateDebut.toString(),dateFin.toString(),nbMinn,nbMaxx,photoEvenement,prix);
       if(!ControleEvenement.controleTitre(evenement) || titre.equals("")) {
          titre_label.setText("le titre doit etre non erroné ou bien il est vide");
          return ;
      }
       if(ControleEvenement.controleTitre(evenement)) {
           titre_label.setText(" ");
       }
       
        if(!ControleEvenement.controleDescription(evenement) || description.equals("")) {
          description_label.setText("le description doit etre non erroné");
          return ;  
      }
        if(ControleEvenement.controleDescription(evenement)) {
                 description_label.setText(" ");
             }
        
      
      if(!ControleEvenement.controlePrix(evenement) || prix== 0) {
         prix_label.setText("Verifier prix ou bien il est 0 ");
          return ;
         }
        if(ControleEvenement.controlePrix(evenement)) {
                 prix_label.setText(" ");
             }
      if(!ControleEvenement.controleDate(evenement) || date_debut.equals(null) || date_fin.equals(null)) {
           date_label.setText("Verifier les dates ou bien le date nulls ");
          return ;

      }
      if(ControleEvenement.controleDate(evenement)) {
                 date_label.setText(" ");
             }
      
      if(!ControleEvenement.controleNbMax(evenement)){
          max_label.setText("nbr max doit contenir que  nombre");
          return ;

      }
 
      if(ControleEvenement.controleNbMax(evenement)){
          max_label.setText("");

      } 
       if(!ControleEvenement.controleNbMin(evenement)){
          nb_min_label.setText("nbr min doit contenir que  nombre");
          return ;

      }
 
      if(ControleEvenement.controleNbMin(evenement)){
          nb_min_label.setText("");

      }
      
      
      
      try{
    
      
    
      
               if(nbMaxx<nbMinn) {
              max_label.setText("Nombre max doit etre superieur");
              return;

               } 
               if(nbMaxx>nbMinn) {
                   nb_min_label.setText("");
                   
               }
               
              

               if(nbMinn<10) {
                  nb_min_label.setText("<10");
                  return;
                  
               }
               if(nbMinn>=10) {
                   nb_min_label.setText("");
               }
      }catch(Exception e) {
          nb_min_label.setText("min  doit etre non chaine");
          max_label.setText("max doit etre non chaine");
          return;
      }
               
               
                
                

        
      if(evenement != null) {
          System.out.println("hello add");
     
        e.ajoutEvenement(evenement);
          Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout evenement");
        alert.setHeaderText("Ajout avec succées");
        alert.showAndWait();
      }
        
       
    }

    
    String imageUrl;
    @FXML
    private void HandleActionButtonImage(ActionEvent event) throws ParseException, IOException {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images","*.jpg","*.png", "*.bmp"));
        File selected_file = file.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
        photoEvenement = selected_file.getAbsolutePath();
        BufferedImage bufferedImage = ImageIO.read(selected_file);
        WritableImage  imageW = SwingFXUtils.toFXImage(bufferedImage, null);
        immg.setImage(imageW);
        System.out.println("upload=" + imageW);
        
        
        nbMaxPart = nb_max_participant.getText().toLowerCase();
        //nbAct = nb_actuel.getText();
    //    nbActt = Integer.parseInt(nbAct);
        image = photoEvenement;
        cb_categories.setEditable(false);
        //DateFormat df = new SimpleDateFormat(LocalDate.parse(dateDebut));
        dateDebut = date_debut.getValue();
        dateFin = date_fin.getValue();
       // Alert alert =new Alert(AlertType.INFORMATION);
       // alert.setTitle(" Controle des champs");
      
       for(int i =0; i<txt_prix.getText().length();i++) {
             if(txt_prix.getText().charAt(i)>='a' && txt_prix.getText().charAt(i)<='z' && txt_prix.getText().equals(" ")) {
                    prix_label.setText("Verifier prix ");
                    return ;
             }
             else {
                 prix_label.setText(" ");
                             prix =Integer.parseInt(txt_prix.getText().toString());

             }
             }
            
            //Catch exception for Number 
            
            //
            System.out.println("file = "+image);
            
            
      int nbMaxx =Integer.valueOf(nb_max_participant.getText());
      int nbMinn = Integer.valueOf(nb_min_participant.getText());
      
      
      //error is down here
      
      evenement = new Evenement(titre,description,localisation,cb_categories.getValue()
                  ,dateDebut.toString(),dateFin.toString(),nbMinn,nbMaxx,image,prix);
        System.out.println("*******************"+evenement);
        
           if(!ControleEvenement.controleTitre(evenement) || titre.equals("")) {
          titre_label.setText("le titre doit etre non erroné ou bien il est vide");
          return ;
      }
       if(ControleEvenement.controleTitre(evenement)) {
           titre_label.setText(" ");
       }
       
        if(!ControleEvenement.controleDescription(evenement) || description.equals("")) {
          description_label.setText("le description doit etre non erroné");
          return ;  
      }
        if(ControleEvenement.controleDescription(evenement)) {
                 description_label.setText(" ");
             }
        
      
      if(!ControleEvenement.controlePrix(evenement) || prix== 0) {
         prix_label.setText("Verifier prix ou bien il est 0 ");
          return ;
         }
        if(ControleEvenement.controlePrix(evenement)) {
                 prix_label.setText(" ");
             }
      if(!ControleEvenement.controleDate(evenement) || date_debut.equals(null) || date_fin.equals(null)) {
           date_label.setText("Verifier les dates ou bien le date nulls ");
          return ;

      }
      if(ControleEvenement.controleDate(evenement)) {
                 date_label.setText(" ");
             }
      
      if(!ControleEvenement.controleNbMax(evenement)){
          max_label.setText("nbr max doit contenir que  nombre");
          return ;

      }
 
      if(ControleEvenement.controleNbMax(evenement)){
          max_label.setText("");

      } 
       if(!ControleEvenement.controleNbMin(evenement)){
          nb_min_label.setText("nbr min doit contenir que  nombre");
          return ;

      }
 
      if(ControleEvenement.controleNbMin(evenement)){
          nb_min_label.setText("");

      }
      
      
      
      try{
             if(nbMaxx<nbMinn) {
              max_label.setText("Nombre max doit etre superieur");
              return;

               } 
               if(nbMaxx>nbMinn) {
                   nb_min_label.setText("");
                   
               }
              if(nbMinn<10) {
                  nb_min_label.setText("<10");
                  return;
                  
               }
               if(nbMinn>=10) {
                   nb_min_label.setText("");
               }
      }catch(Exception e) {
          nb_min_label.setText("min  doit etre non chaine");
          max_label.setText("max doit etre non chaine");
          return;
      }
     if(evenement != null) {
     System.out.println("hello add");
     e.ajoutEvenement(evenement);
     Alert alert = new Alert(AlertType.INFORMATION);
     alert.setTitle("Ajout eve     alert.setHeaderText(\"Ajout avec succées\");\n" +
"nement");
     alert.showAndWait();
      }
   }

    @FXML
    private void HandleActionButtonGoMap(ActionEvent event) throws IOException {
        
        FXMLLoader loader  =new FXMLLoader();
        loader.setLocation(getClass().getResource("MapFxml.fxml"));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(s);
        stage.show();
    }
    
    
   public void clear() {
        txt_titre.setText("");
        txt_description.setText("");
        cb_categories.getItems().clear();
        nb_max_participant.setText(null);
        nb_min_participant.setText(null);
        txt_prix.setText(null);
        btn_image.setText("");
        immg.setImage(null);
       
    }   
   
   public void transferMessage(String loc ) {
       
       label.setText(loc);
   }
   
}
