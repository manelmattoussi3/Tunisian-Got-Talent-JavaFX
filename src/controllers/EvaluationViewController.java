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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Candidat;
import models.Formation;
import org.controlsfx.control.Rating;
import models.Participant;
import models.Rate;
import service.CandidatService;
import service.DbConnectinfo;
import utils.DbConnection;

public class EvaluationViewController implements Initializable {

     @FXML private StackPane menupane;
    @FXML
    private Pane contactpane;
    @FXML
    private Pane expertopane;
     private final String MENU_CONTACTS = "Menu";
    private final String MENU_LIST = "Evaluation";
    private final String MENU_EXPORT = "Proposition";
    
    private final String MENU_EXIT = "Quiter";
    @FXML
    private TableView<Participant> table;
    @FXML
    private TextField inputpseudo;
    @FXML
    private TextField inputnom;
    @FXML
    private TextField inputprenom;
    
   // private final ObservableList<Participant>data=FXCollections.observableArrayList(new Participant("ffgff","ghgyhh","cgfgv@fg.com","dhghhjh"),new Participant("ffgff","ghgyhh","cgfgv@fg.com","dhghhjh"));
    public ObservableList<Participant> data;
    @FXML
    private TextArea inputproposition;
    private Rating rating;
   
     int Value;
    @FXML
    private Label labe151;
    @FXML
    private ComboBox CmbCandidat;
    @FXML
    private ComboBox Cmbformation;
    @FXML
    private Label labe1511;
    @FXML
    private Label labe15111;
     public static int id;
     ObservableList<String> listNomCondidat;
    List<Candidat> listCondidat;
    public Candidat candidat;
     ObservableList<String> listNomFormation;
    List<Formation> listFormation;
    public Formation formation;
    @FXML
    private TextField avis;
    public DbConnection dc;
    @FXML
    private TableColumn<Formation,String> txtnom;
    @FXML
    private TableColumn<Formation,String> txtprenom;
    @FXML
    private TableColumn<Formation,String> txtmail;
    @FXML
    private TableColumn<Formation,String> txtproposition;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           dc = new DbConnection();
        setMenuData() ;
        //setTableData();
        loadScreenButton();
        loadData();
        loadFormation();
      /* rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               // msg.setText("Rating: " + newValue);
               Value=newValue.intValue();
                System.out.println(Value);
            }
            
        });*/
    }   
    //retour vers formation
     @FXML
   public void passeScreenButtonPushed(ActionEvent event) throws IOException {
    Parent tableviewParent =FXMLLoader.load(getClass().getResource("/fxml/ExampleTableView.fxml"));
    Scene tableviewScene =new Scene(tableviewParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(tableviewScene);
    window.show();
   }
   //retour vers Accueil
    public void modifeScreenButtonPushed(ActionEvent event) throws IOException {
    Parent tableviewParent =FXMLLoader.load(getClass().getResource("/fxml/Accueil.fxml"));
    Scene tableviewScene =new Scene(tableviewParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(tableviewScene);
    window.show();
    
    } 
    //retour vers statistique 
     public void StatistiqueScreenButtonPushed(ActionEvent event) throws IOException {
    Parent tableviewParent =FXMLLoader.load(getClass().getResource("/fxml/StatistiqueView.fxml"));
    Scene tableviewScene =new Scene(tableviewParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(tableviewScene);
    window.show();
   }
     //retour vers conseil 
      public void conseilScreenButtonPushed(ActionEvent event) throws IOException {
    Parent tableviewParent =FXMLLoader.load(getClass().getResource("/fxml/ConseilView.fxml"));
    Scene tableviewScene =new Scene(tableviewParent);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(tableviewScene);
    window.show();
   }
      private void setMenuData() {
        TreeItem<String> treeItemRoot1 = new TreeItem<>("Menu");
        TreeView<String> treeView = new TreeView<>(treeItemRoot1);
        treeView.setShowRoot(false);

        TreeItem<String> nodeItemA = new TreeItem<>(MENU_CONTACTS);
        
        TreeItem<String> nodeItemB = new TreeItem<>(MENU_EXIT);

        nodeItemA.setExpanded(true);

      /*  Node contactsNode = new ImageView(new Image(getClass().getResourceAsStream("img/010.png")));
        Node exportNode = new ImageView(new Image(getClass().getResourceAsStream("img/020.png")));*/
        TreeItem<String> nodeItemA1 = new TreeItem<>(MENU_LIST);
        TreeItem<String> nodeItemA2 = new TreeItem<>(MENU_EXPORT);

        nodeItemA.getChildren().addAll(nodeItemA1, nodeItemA2);
        treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB);

        menupane.getChildren().add(treeView);

        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
           @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                String selectedMenu;
                selectedMenu = selectedItem.getValue();

                if (null != selectedMenu) {
                    switch (selectedMenu) {
                        case MENU_CONTACTS:
                            selectedItem.setExpanded(true);
                            break;
                        case MENU_LIST:
                            contactpane.setVisible(true);
                            expertopane.setVisible(false);
                            break;
                        case MENU_EXPORT:
                            contactpane.setVisible(false);
                            expertopane.setVisible(true);
                            break;
                           
                        case MENU_EXIT:
                            System.exit(0);
                            break;
                    }
                }

            }

            
        });

    }

   /* @FXML
    private void AddContact(ActionEvent event) {
     //  data.add(new Participant(inputnom.getText(),inputprenom.getText(),inputpseudo.getText(),inputproposition.getText()));
        String Mail = inputpseudo.getText();
        if (Mail.length() > 3 && Mail.contains("@") && Mail.contains(".")) {
            data.add(new Participant(inputnom.getText(), inputprenom.getText(), Mail,inputproposition.getText()));
            inputnom.clear();
            inputprenom.clear();
           inputpseudo.clear();
           inputproposition.clear();
    }}
   /*  public void setTableData() {
        TableColumn lastNameCol = new TableColumn("Nom");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Participant, String>("Nom"));

        lastNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Participant,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Participant, String> t) {
                ((Participant) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setNom(t.getNewValue());
            }
        }
        );

        TableColumn firstNameCol = new TableColumn("Prenom");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Participant, String>("Prenom"));

        firstNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Participant, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Participant, String> t) {
                ((Participant) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setPrenom(t.getNewValue());
            }
        }
        );

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<Participant, String>("mail"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());

        emailCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Participant, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Participant, String> t) {
                ((Participant) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setMail(t.getNewValue());
            }
        }
        );

        TableColumn PROPCol = new TableColumn("Proposition");
        PROPCol.setMinWidth(200);
        PROPCol.setCellValueFactory(new PropertyValueFactory<Participant, String>("Proposition"));
        PROPCol.setCellFactory(TextFieldTableCell.forTableColumn());

       PROPCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Participant, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Participant, String> t) {
                ((Participant) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setProposition(t.getNewValue());
            }
        }
        );
        table.getColumns().addAll(lastNameCol, firstNameCol, emailCol,PROPCol);
        table.setItems(data);
    }
   /*   @FXML
    private void addrate(MouseEvent event) throws SQLException{
        Rate r =new Rate();
        DbConnectinfo dbc = new  DbConnectinfo();
        dbc.rate(r);
        
        r.getId();
        r.setCandidat_id(can.getId());
        r.setFormation_id(fo.getId());
        r.setAvis(Value);
    }*/
    @FXML
    public void btnAjouterAction(ActionEvent event) throws IOException {
       // String av = avis.getText();
        if (validatenbr7()&&validatenbr()) {
        Candidat selectedCandidat = listCondidat.get(CmbCandidat.getSelectionModel().getSelectedIndex());
        Formation selectedFormation = listFormation.get(Cmbformation.getSelectionModel().getSelectedIndex());
        Rate cons = new Rate(id,selectedCandidat.getId(),selectedFormation.getId(),avis.getText());
        DbConnectinfo dbc = new DbConnectinfo();
        dbc.rate(cons);
        //int status = serviceconseil.save(cons);

        //  if (status > 0) {
       
 
    
      /*  }else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Information");
        alert.setContentText("le note doit étre entre 0 et 10");
        alert.showAndWait();}
       */
      
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Ajouter Evaluation");
        alert1.setHeaderText("Information");
        alert1.setContentText("Evaluation Envoyé");
        alert1.showAndWait();
 ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/EspaceFormation.fxml"));
        AnchorPane frame = loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
       // EspaceFormationController AfficheConseilController = loader.getController();

      //  AfficheConseilController.initialize(url, rb);
        primaryStage.setTitle("Liste des Evaluations");
        primaryStage.show();
         
       
        
        }
}
     private boolean validatenbr7() {
     
         if(!avis.getText().matches("[0-9]") ){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("le note doit étre entre 0 et 10");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
     private boolean validatenbr() {
     
         if(avis.getText().matches("[a-z]") ){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Avertissement");
                alert.setContentText("le note doit étre numerique");
                alert.showAndWait();
    
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
     public void loadData() {
        CandidatService candidatService = new CandidatService();
        listCondidat = candidatService.listerNomCandidat();
        listNomCondidat = FXCollections.observableArrayList();
        listNomCondidat.addAll(listCondidat.stream().map(condidat -> condidat.getNom()).collect(Collectors.toList()));
        CmbCandidat.setItems(listNomCondidat);
    }
      public void loadFormation() {
        DbConnectinfo dbc = new DbConnectinfo();
       
        
        listFormation = dbc.listerNomFormation();
        listNomFormation = FXCollections.observableArrayList();
        listNomFormation.addAll(listFormation.stream().map(formation -> formation.getDescription()).collect(Collectors.toList()));
        Cmbformation.setItems(listNomFormation);
    }
  /*  private void addratioo(MouseEvent event) throws SQLException{
        Rate r =new Rate();
        DbConnectinfo dbc = new  DbConnectinfo();
        dbc.rate(r);
        
        r.getId();
        //r.setCandidat_id(can.getId());
        //r.setFormation_id(fo.getId());
        r.setAvis(Value);
    }*/
      private boolean validatefield() {
        if (avis.getText().isEmpty() | Cmbformation.getValue().equals(null) |  CmbCandidat.getValue().equals(null)) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Valider les champs");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez remplir tous les champs");
            alert1.showAndWait();
            
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
       public void loadScreenButton() {
            
        try {
           Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT `nom`, `prenom`, `mail`, `contenu` FROM `proposition` ");
            //"select Co.id ,C.nom as nomCandidat,Coa.nom as nomCoach,Co.description ,Co.categoricons, Co.image  FROM conseil as Co , Candidat as C , Coach as Coa where  Co.Candidat_id=C.id AND Co.Coach_id=Coa.id"
            while (rs.next()) {
                data.add(new Participant(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
                
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur" + ex);
        }
        
        txtnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        txtprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        txtmail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
        txtproposition.setCellValueFactory(new PropertyValueFactory<>("Proposition"));
       
        table.setItems(null);
        table.setItems(data);
      
    }
        @FXML
    public void btnenvoyerAction(ActionEvent event) throws IOException {
       
       if(validatefieldp()&&validatdp()&&validate()){
        Participant cons = new Participant(id,inputnom.getText(),inputprenom.getText(),inputpseudo.getText(),inputproposition.getText());
        DbConnectinfo serviceconseil = new DbConnectinfo();
        serviceconseil.saveproposition(cons);
        //int status = serviceconseil.save(cons);

        //  if (status > 0) {
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter Conseil");
        alert.setHeaderText("Information");
        alert.setContentText("Conseil bien ajouté");
        alert.showAndWait();*/

        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/EvaluationView.fxml"));
        AnchorPane frame = loader.load();
        Parent p = loader.getRoot();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(p));
        //ConseilViewController AfficheConseilController = loader.getController();

        //AfficheConseilController.AfficheConseil();
        primaryStage.setTitle("Liste des propositions");
        primaryStage.show();
    }}
    private boolean validatefieldp() {
        if (inputnom.getText().isEmpty() |inputprenom.getText().isEmpty()| inputpseudo.getText().isEmpty()|inputproposition.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Valider les champs");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez remplir tous les champs");
            alert1.showAndWait();
            
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
     private boolean validatdp() {
      // String Mail = inputpseudo.getText();
        if ((inputpseudo.getText().length() < 3 )|( !inputpseudo.getText().contains("@") )|(!inputpseudo.getText().contains("."))) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Avertissement");
            alert1.setHeaderText(null);
            alert1.setContentText("le mail doit contenir @ et .");
            alert1.showAndWait();
            
            return false;
        }
        
        {
            
            return true;
            
        }
        
    }
    
       private boolean validate() {
         if( (inputproposition.getText().length() < 2 )|(inputpseudo.getText().length() < 2)|(inputprenom.getText().length() < 2)|(inputnom.getText().length() < 2) ){
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
    }

