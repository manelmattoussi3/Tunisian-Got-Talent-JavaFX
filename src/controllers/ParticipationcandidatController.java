/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Rate;
import utils.DbConnection;

/**
 * FXML Controller class
 *
 * @author DevelopAndroid
 */
public class ParticipationcandidatController implements Initializable {

    @FXML
    private TableView<Rate> table;
    @FXML
    private TableColumn<Rate,String> txtnom;
    @FXML
    private TableColumn<Rate,String> txtformation;
    public ObservableList<Rate> data;
    private DbConnection dc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          dc = new DbConnection();
         loadScreenButton();
    }    

    @FXML
    private void btnCandidatAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/accueilcoach.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
       // ListeCandidatsController listeCadidatsController = loader.getController();
       // listeCadidatsController.AfficheCandidat();
        //stage.getIcons().add(new Image("pidev/gui/img/icone.png"));
        primaryStage.setTitle("Liste des Candidats");
        primaryStage.show();
    }
     public void loadScreenButton() {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select R.id,C.nom as nom_candidat ,F.description as formation from rating as R,Candidat as C ,Formation as F where R.Candidat_id=C.id AND R.Formation_id=F.id");
            //"select Co.id ,C.nom as nomCandidat,Coa.nom as nomCoach,Co.description ,Co.categoricons, Co.image  FROM conseil as Co , Candidat as C , Coach as Coa where  Co.Candidat_id=C.id AND Co.Coach_id=Coa.id"
            while (rs.next()) {
                data.add(new Rate(rs.getInt(1),rs.getString(2), rs.getString(3)));
                
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur" + ex);
        }
        
       txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_candidat"));
        txtformation.setCellValueFactory(new PropertyValueFactory<>("formation"));
      
       
        table.setItems(null);
        table.setItems(data);
}

    
}
