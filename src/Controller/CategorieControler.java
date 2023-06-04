
package Controller;

import Module.Module;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

public class CategorieControler {
    @FXML
    private Label errorMess;
    @FXML
    private TextField catgorieNameField;

    @FXML
    private TableView<Categorie> categoryTable;
    @FXML
    private TableColumn<Categorie, Integer> idCategorie;

    @FXML
    private TableColumn<Categorie, Integer> membersCategorie;

    @FXML
    private TableColumn<Categorie, String> nameCategorie;


    public void initialize(){
        //this function delay the execution of the operation until after it shows the interface
        Platform.runLater(() -> {
            // this function fills the table from the database

            // get the members from the database and return in a List
            List<Categorie> catList = Module.getCategorie("SELECT * FROM categorie");

            // select what properties of the object Membre will be displayed in the columns
            idCategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
            nameCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));

            // transform the List to an ObservableList
            ObservableList<Categorie> observableMemberList = FXCollections.observableArrayList(catList);
            // Set the ObservableList as the data source for the table
            categoryTable.setItems(observableMemberList);
        });
    }








    public void toHome(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Home.fxml");
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

    public void toClient(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Clients.fxml");
    }
    public void addCategorie(){
        if (catgorieNameField.getText().length()==0){
            errorMess.setText("please entrer a categorie name");
        }
        else {
            int isCategorieExist= Module.getCategorie("select * from categorie where nomCategorie LIKE\""+catgorieNameField.getText()+"\"").toArray().length;
            if (isCategorieExist!=0){
                errorMess.setText("categorie alredy exist");
            }
            else {
                Categorie objCategorie=new Categorie(catgorieNameField.getText());
                Module.ajouterCategorie(objCategorie);
                errorMess.setText("Categorie inserted successfully");
            }
            initialize();
        }

    }
}