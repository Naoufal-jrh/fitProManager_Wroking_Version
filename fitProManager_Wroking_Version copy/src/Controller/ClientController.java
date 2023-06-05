package Controller;

import Module.Module;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

public class ClientController {
    public static Membre selectedMember;
    @FXML
    private TableColumn<Membre, Date> ClientAdateColumn;

    @FXML
    private TableColumn<Membre, String> ClientCinColumn;

    @FXML
    private TableColumn<Membre, String> ClientFirstNameColumn;

    @FXML
    private TableColumn<Membre, Integer> ClientIdColumn;

    @FXML
    private TableColumn<Membre, String> ClientLastNameColumn;

    @FXML
    private TableColumn<Membre, String> ClientMailColumn;

    @FXML
    private TableView<Membre> clientTable;

    @FXML
    private TextField searchBar;


    public void toHome(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Home.fxml");
    }
    public void toCategories(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Categories.fxml");
    }

    public void toOffers(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Offers.fxml");
    }
    public void toLogin(ActionEvent event) throws IOException {
        //Navigation class provides goto function that changes the scean and show alert function
        Navigation nv = new Navigation();
        ButtonBar.ButtonData choice = nv.showAlert("You are loginOut","are you sure you want to logout !!");
        if (choice == OK_DONE){
            nv.goTo(event,"../View/login_page.fxml");
        }
    }
    @FXML
    public void toAddClient(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/addClient.fxml");
    }
    @FXML
    public void toViewClient(ActionEvent event)throws IOException{
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/ViewClient.fxml");
    }
    public void toViewClient(MouseEvent event)throws IOException{
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/ViewClient.fxml");
    }


    @FXML
    public void initialize(){
        //this function delay the execution of the operation until after it shows the interface
        Platform.runLater(() -> {
            // this function fills the table from the database

            // get the members from the database and return in a List
            List<Membre> membersList = Module.getMembres("SELECT * FROM membre");

            // select what properties of the object Membre will be displayed in the columns
            ClientIdColumn.setCellValueFactory(new PropertyValueFactory<>("idPersonne"));
            ClientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            ClientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            ClientMailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            ClientCinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
            ClientAdateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdherence"));

            // transform the List to an ObservableList
            ObservableList<Membre> observableMemberList = FXCollections.observableArrayList(membersList);
            // Set the ObservableList as the data source for the table
            clientTable.setItems(observableMemberList);
        });
    }


    @FXML
    public void searchClient(){

        String searchTerm=searchBar.getText();

        String query = "SELECT * FROM membre WHERE nom LIKE '" + searchTerm + "%' OR prenom LIKE '" + searchTerm + "%' OR cin LIKE '" + searchTerm + "%' OR email LIKE '" + searchTerm + "%' OR CAST(idMembre AS CHAR) LIKE '" + searchTerm + "%' OR tel LIKE '" + searchTerm + "%'";

        if(searchBar.getText()==null) {initialize(); return;}
        // get the members from the database and return in a List
        List<Membre> membersList = Module.getMembres(query);

        // select what properties of the object Membre will be displayed in the columns
        ClientIdColumn.setCellValueFactory(new PropertyValueFactory<>("idPersonne"));
        ClientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ClientLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ClientMailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ClientCinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ClientAdateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdherence"));

        // transform the List to an ObservableList
        ObservableList<Membre> observableMemberList = FXCollections.observableArrayList(membersList);
        // Set the ObservableList as the data source for the table
        clientTable.setItems(observableMemberList);

    }





    @FXML
    void getMemberProfile(MouseEvent event)throws IOException{
        int ClickedId;
        //wait fo a click on the table
        if (event.getClickCount() == 2) {
            //if clicked get the table rows
            ObservableList<Membre> items = clientTable.getItems();
            //check wich row has been clicked
            for (Membre person : items) {
                if (person.equals(clientTable.getSelectionModel().getSelectedItem())) {
                   // ClickedId = person.getIdPersonne();
                   // selectedMember = new Membre(person);
                    selectedMember = new Membre(person);
                    toViewClient(event);
                    break;

                }
            }
        }
    }










}