package Controller;

import Module.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

public class ClientViewController {
    @FXML
    private GridPane categorieGrid;
    @FXML
    private Label IdLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label adhDateLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label cinLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    void addCategorie(ActionEvent event)throws IOException {


        // Create a new stage for the popup GUI
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with the parent stage

        // Remove default window decorations
        //popupStage.initStyle(StageStyle.UNDECORATED);

        // Load the FXML file for the popup layout
        Parent popupLayout = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/addCategoriePopup.fxml")));

        // Create a scene and set it on the stage
        Scene popupScene = new Scene(popupLayout);
        popupStage.setScene(popupScene);



        // Show the popup stage
        popupStage.showAndWait();

    }

    @FXML
    void deleteMember(ActionEvent event) throws IOException{
        Navigation nv = new Navigation();
        ButtonBar.ButtonData choice = nv.showAlert("Delete this member","Are you sure you want to Delete this member?");
        if (choice == OK_DONE){
            Module.supprimerMembre(ClientController.selectedMember.getIdPersonne());
            nv.goTo(event,"../View/Clients.fxml");
        }
    }


    public void toModify(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/ModifyClient.fxml");
    }

    public void toClients(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Clients.fxml");
    }

    @FXML
    public void initialize(){
        setProfile(ClientController.selectedMember);
        setCategoriesTable();
    }

    private void setCategoriesTable() {
        //get the categories and experation date
        List<Expiration> expiration = Module.getExpiration("SELECT * FROM expiration WHERE idMembre = "+ClientController.selectedMember.getIdPersonne());
        //the expiration object have the categories of the member and it's experation date;
        assert expiration != null;
        int row =0;
        for (Expiration exp:expiration){
            //get and add the categorie
            Categorie cat = exp.getCategorie();
            addLabelToGrid(cat.getNomCategorie(),0,row);
            //get and add the exp date
            addLabelToGrid("Exp Date   "+exp.getDateExpiration().toString(),1,row);
            //add the buttons
            addButtonsToGrid(row);
            row++;
        }
    }

    private void addButtonsToGrid(int row) {
        Button button_add = new Button();
        Button button_delete = new Button();
        //styling button add
        button_add.setMnemonicParsing(false);
        button_add.setPrefHeight(35.0);
        button_add.setPrefWidth(100.0);
        button_add.setStyle("-fx-background-color: green;");
        button_add.setText("Add Offer");
        button_add.setTextFill(Paint.valueOf("#FFFF"));
        button_add.setFont(Font.font("Arial Rounded MT Bold",13.0));
        button_add.setOnAction(event -> {
            //Module.ajouterInsciption(idmembre,idOffre);
        });
        //styling button delete
        button_delete.setMnemonicParsing(false);
        button_delete.setPrefHeight(35.0);
        button_delete.setPrefWidth(100.0);
        button_delete.setStyle("-fx-background-color: red;");
        button_delete.setText("Delete");
        button_delete.setTextFill(Paint.valueOf("#FFFF"));
        button_delete.setFont(Font.font("Arial Rounded MT Bold",13.0));
        button_delete.setOnAction(event -> {

            //Module.supprimerExpiration();
        });
        //add the buttons to the gridpane
        categorieGrid.add(button_add,2,row);
        categorieGrid.add(button_delete,3,row);
    }

    private void addLabelToGrid(String content, int i, int row) {
        //creat the label object
        Label label = new Label();
        //add the proprties
        label.setPrefHeight(20.0);
        label.setText(content);
        label.setTextFill(Paint.valueOf("#1e1717"));
        label.setFont(Font.font("Arial Rounded MT Bold",16.0));
        categorieGrid.add(label,i,row);
    }


    private void setProfile(Membre membre) {
        IdLabel.setText("@"+membre.getIdPersonne());
        firstNameLabel.setText(membre.getNom());
        lastNameLabel.setText(membre.getPrenom());
        emailLabel.setText(membre.getEmail());
        genderLabel.setText(membre.getSexe());
        birthdayLabel.setText(membre.getDateNaissance().toString());
        adhDateLabel.setText(membre.getDateAdherence().toString());
        addressLabel.setText(membre.getAdresse());
        cinLabel.setText(membre.getCin());
    }


}

