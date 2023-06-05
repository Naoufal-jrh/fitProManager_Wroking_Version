
package Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import Module.Module;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

public class OfferController {

    public static Offre selectedOffer;
    @FXML
    private ComboBox<String> category;
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
        fillDropList();
        filltable();
    }
    public void filltable(){
        Platform.runLater(() -> {
            Categorie categorie = Module.getCategorie("SELECT * FROM categorie where nomCategorie LIKE \""+category.getValue()+"\"").get(0);
            int id=categorie.getIdCategorie();
            // this function fills the table from the database
            // get the members from the database and return in a List
            List<Offre> catList = Module.getOffres("SELECT * FROM Offre where idCategorie LIKE \""+id+"\"");

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
    public void fillDropList(){
        List<Categorie> _Categorie = Module.getCategorie("select * from categorie");
        for (int i = 0; i < _Categorie.size(); i++) {
            category.getItems().add(_Categorie.get(i).getNomCategorie());
            if (i==0) category.setValue(_Categorie.get(i).getNomCategorie());
        }

    }

    @FXML
    void modifyOffer(MouseEvent event)throws IOException{
        //wait fo a click on the table
        if (event.getClickCount() == 2) {
            //if clicked get the table rows
            ObservableList<Offre> items = offerTable.getItems();
            //check wich row has been clicked
            for (Offre offre : items) {
                if (offre.equals(offerTable.getSelectionModel().getSelectedItem())) {
                    // ClickedId = person.getIdPersonne();
                    // selectedMember = new Membre(person);
                    selectedOffer = new Offre(offre);
                    toModifyOffer(event);
                    break;

                }
            }
        }
}

    private void toModifyOffer(MouseEvent event) throws IOException{
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/ModifyOffer.fxml");
    }
    }


