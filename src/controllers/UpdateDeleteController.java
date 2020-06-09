/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Formation;
import service.DbConnectinfo;
import service.Serviceconseil;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import models.Coach;
import service.CoachService;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class UpdateDeleteController implements Initializable {

    @FXML
    private TextField idf;

    @FXML
    private TextField debut;
    @FXML
    private TextField fin;

    @FXML
    private TextField categorie;
    @FXML
    private Label label20;
    @FXML
    private TextField description;
    @FXML
    private TextField dnbr;
    @FXML
    private ComboBox<String> combobox3;
    @FXML
    private Label labe11;
    @FXML
    private Label labe12;
    @FXML
    private Label labe13;
    @FXML
    private Label labe9;
    @FXML
    private Label labe14;
    @FXML
    private Label labe15;

    private ObservableList<String> list = FXCollections.observableArrayList("Mohamed Ali Tounsi", "Amel Safouni", "Yassine Ibrahim");
    @FXML
    private Label er4;
    @FXML
    private Label er1;
    @FXML
    private Label er2;
    @FXML
    private Label er3;
    @FXML
    private Label er5;
public Coach coach;
public Formation fom;
    public static int id;
    List<Coach> listCoachs;
    ObservableList<String> listNomCoach;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // combobox3.setItems(list);
       loadData();
    }

  /*  @FXML
    public void getFormation(ActionEvent event) throws IOException, ParseException {
        String sid = idf.getText();
        int Id = Integer.parseInt(sid);

        Formation fom = DbConnectinfo.getFormationid(Id);
        description.setText(fom.getDescription());
        debut.setText(fom.getDate_Debut());
        fin.setText(fom.getDate_Fin());
        String sd = dnbr.getText();
        dnbr.setText(fom.getNbr_Place());
        categorie.setText(fom.getCategorie());

    }*/
 public void loadData() {
        CoachService coachservice = new CoachService();
        listCoachs = coachservice.listerNomCoach();
        listNomCoach = FXCollections.observableArrayList();
        listNomCoach.addAll(listCoachs.stream().map(coach -> coach.getNom()).collect(Collectors.toList()));
        combobox3.setItems(listNomCoach);
    }
    @FXML
    public void updateFormation(ActionEvent event) throws IOException {
       /* String sid = idf.getText();
        int Id = Integer.parseInt(sid);
        int Nbr_Place = Integer.parseInt(sid);
        String txtdescription = description.getText();
        String txtdebut = debut.getText();
        String txtfin = fin.getText();
        //String txtcategorie = categorie.getText();
        String txtdnbr = dnbr.getText();
        //String txtcombobox3 = combobox3.getValue();
        Formation fom = new Formation();
        fom.setId(Id);
        fom.setDescription(txtdescription);
        fom.setDate_Debut(txtdebut);
        fom.setDate_Fin(txtfin);
        fom.setNbr_Place(txtdnbr);
        //fom.setCategorie(txtcategorie);
        int Status = DbConnectinfo.update(fom);
        if (Status > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifier Formation");
            alert.setHeaderText("Information");
            alert.setContentText("Formation bien modifié");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modifier Formation");
            alert.setHeaderText("Information");
            alert.setContentText("Formation non modifié");
            alert.showAndWait();

        }

    }

  /*  @FXML
    public void deleteFormation(ActionEvent event) throws IOException {
        String sid = idf.getText();
        int Id = Integer.parseInt(sid);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure to delete ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            int st = 0;
            int Status = DbConnectinfo.delete(Id);
            if (Status > 0) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Supprimer Formation");
                alert1.setHeaderText("Information");
                alert1.setContentText("Formation bien supprimé");
                alert1.showAndWait();
                try {
                    String sql = "DELETE FROM `formation` WHERE id=?";
                    Connection conn = DbConnectinfo.Connect();
                    PreparedStatement stat;
                    stat = (PreparedStatement) conn.prepareStatement(sql);
                    stat.setInt(1, Id);
                    st = stat.executeUpdate();

                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }*/
           
      int test = 0;

        if (description.getText().isEmpty() ) {
           er1.setText("Ce champ est obligatoire");
       // Alert alert = new Alert(Alert.AlertType.WARNING);
      // alert.setTitle("Description");
       // alert.setHeaderText(null);
       // alert.setContentText(" Ce champ est obligatoire");
        //alert.showAndWait();
            test -=1;
        } else {
            er1.setText("");
            test +=1;
        }
        if (categorie.getText().isEmpty()  ) {
            er2.setText("Ce champ est obligatoire");
       // Alert alert = new Alert(Alert.AlertType.WARNING);
       //alert.setTitle("Type");
        //alert.setHeaderText(null);
        //alert.setContentText(" Ce champ est obligatoire");
       // alert.showAndWait();
            test -=1;
        } else {
            er2.setText("");
            test +=1;
        }
        
        if (combobox3.getSelectionModel().isEmpty()) {
           er3.setText("Ce champ est obligatoire");
      // Alert alert = new Alert(Alert.AlertType.WARNING);
      // alert.setTitle("Type");
       // alert.setHeaderText(null);
       // alert.setContentText("Nom Candidat");
       // alert.showAndWait();
            test -=1;
        } else {
           er3.setText("");
            test +=1;
        }
         
       
           
             
       if( (test == 3 )&&validate()&&validatenbr()&&validatenbr7()){  
           Formation f=new Formation();
            Coach selectedCoach = listCoachs.get(combobox3.getSelectionModel().getSelectedIndex());
            Formation formation = new Formation(fom.getId(),selectedCoach.getIdcoach(), description.getText(),fom.getDate_Debut(),fom.getDate_Fin(),dnbr.getText(),categorie.getText() );
            DbConnectinfo dbc = new DbConnectinfo();
            dbc.update(formation);
             ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ExampleTableView.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        ExampleTableViewController afficherFormationController = loader.getController();
        afficherFormationController.loadScreenButton();
        primaryStage.setTitle("Lister Formation");
        primaryStage.show();
       }       
    }
       @FXML
    private void btnAnnulerAction(ActionEvent event) {
        description.setText("");
        dnbr.setText("");
        categorie.setText("");
      
        
        
    }
    private boolean validatenbr() {
     
         if( dnbr.getText().matches("[a-z]") &&  ! dnbr.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+-")){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("le nombre de place doit étre numerique");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
    
    public void loadFormation(int id) {
        System.out.println(id);
        DbConnectinfo dbc = new DbConnectinfo();
        fom = dbc.getFormation(id);
        description.setText(fom.getDescription());
         //String datedebut = debut.getText();
        //String datefin = fin.getText();
        debut.setText(fom.getDate_Debut());
        fin.setText(fom.getDate_Fin());
      //fom.setDate_Debut(datedebut);
        //fom.setDate_Fin(datefin);
        dnbr.setText(fom.getNbr_Place());
        categorie.setText(fom.getCategorie());
       int indexOfCoach = -1;
        for (int i = 0; i < listCoachs.size(); i++) {
            if(listCoachs.get(i).getIdcoach()== fom.getCoach_id()) {
                indexOfCoach = i;
            }
        }
        if(indexOfCoach != -1) {
           combobox3.getSelectionModel().select(indexOfCoach);
        }
       
      // txtdatedebut.setValue((sponsoring.getDate_debut()));
     // txtdatefin.setValue((sponsoring.getDate_fin()))
    
    }
     private boolean validate() {
         if( ( description.getText().length() < 2 )|(categorie.getText().length() < 2) ){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("Veuillez remplir plus que 2 lettres");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
     }
      private boolean validatenbr7() {
     
         if(!dnbr.getText().matches("^[0-9]*$") ){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("le nombre de place doit étre positif");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
}
