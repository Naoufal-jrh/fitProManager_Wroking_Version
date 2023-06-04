package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
public class ClientViewController {
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
    void addCategorie(ActionEvent event) {

    }

    @FXML
    void deleteMember(ActionEvent event) {

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

