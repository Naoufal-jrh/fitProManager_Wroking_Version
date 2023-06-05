
package Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import Module.Module;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

public class OfferController {
    @FXML
    private TableColumn<Offre, Integer> offerDuration;

    @FXML
    private TableColumn<Offre, Integer> offerId;

    @FXML
    private TableColumn<Offre, String> offerName;

    @FXML
    private TableColumn<Offre, Integer> offerPrice;

    @FXML
    private TableView<Offre> offerTable;


    public void initialize(){
        Platform.runLater(() -> {
            // this function fills the table from the database

            // get the members from the database and return in a List
            List<Offre> catList = Module.getOffres("SELECT * FROM offre");

            // select what properties of the object Membre will be displayed in the columns
            offerId.setCellValueFactory(new PropertyValueFactory<>("idOffre"));
            offerName.setCellValueFactory(new PropertyValueFactory<>("nomOffre"));
            offerDuration.setCellValueFactory(new PropertyValueFactory<>("dureeOffre"));
            offerPrice.setCellValueFactory(new PropertyValueFactory<>("prixOffre"));

            // transform the List to an ObservableList
            ObservableList<Offre> observableMemberList = FXCollections.observableArrayList(catList);
            // Set the ObservableList as the data source for the table
            offerTable.setItems(observableMemberList);
        });
    }

    public void toHome(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Home.fxml");
    }

    @FXML
    public void toAddOffre(ActionEvent event)throws IOException{
        Navigation nv =new Navigation();
        nv.goTo(event,"../View/addOffre.fxml");
    }
    public void toLogin(ActionEvent event) throws IOException {
        //Navigation class provides goto function that changes the scean and show alert function
        Navigation nv = new Navigation();
        ButtonBar.ButtonData choice = nv.showAlert("You are loginOut","are you sure you want to logout !!");
        if (choice == OK_DONE){
            nv.goTo(event,"../View/login_page.fxml");
        }
    }
    public void toClients(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Clients.fxml");
    }
    public void toCategories(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Categories.fxml");
    }

}
