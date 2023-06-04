package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import Controller.Membre;

import Module.Module;
public class ClientModifyController {
    @FXML
    private TextField adhesiondated;
    @FXML
    private TextField adhesiondatem;
    @FXML
    private TextField adhesiondatey;
    @FXML
    private TextField adress;
    @FXML
    private TextField birthdated;
    @FXML
    private TextField birthdatem;
    @FXML
    private TextField birthdatey;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField firstname;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private TextField lastname;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public Membre setClient(int id){
        int a=convertStringToInt(adhesiondated.getText());
        int b =convertStringToInt(adhesiondatem.getText());
        int c=convertStringToInt(adhesiondatey.getText());
        int d=convertStringToInt (birthdated.getText());
        int e=convertStringToInt(birthdatem.getText());
        int f=convertStringToInt(birthdatey.getText());
        Date birthdate=new Date(d,e,f);
        Date adesiondate=new Date(a,b,c);
        Membre membre= new Membre(lastname.getText(),cin.getText(),firstname.getText(),email.getText(),adress.getText(),"3456789678",gender.getValue(),birthdate,adesiondate);
        return membre;
    }
    public void modifyClient(){
        int id=ClientController.selectedMember.getIdPersonne();
        Module.modifierMembre(id ,setClient(id) );
        System.out.println("modifi client got called");
    }

    public void toClients(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Clients.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void initialize() {
        // Code to execute when the FXML file is loaded
        System.out.println("FXML file loaded");
        // Call your desired function here
        filForm(ClientController.selectedMember.getIdPersonne());
        fillDropList();
    }
    public void filForm(int id){
        Membre membre= Module.getMembres("SELECT * FROM membre where idMembre LIKE \""+id+"\"").get(0);
        firstname.setText(membre.getPrenom());
        lastname.setText(membre.getNom());
        email.setText(membre.getEmail());
        cin.setText(membre.getCin());
        adress.setText(membre.getAdresse());
        gender.setValue(membre.getSexe());
        adhesiondated.setText( membre.getDateAdherence().getJour()+"");
        adhesiondatem.setText( membre.getDateAdherence().getMois()+"");
        adhesiondatey.setText( membre.getDateAdherence().getAnnée()+"");
        birthdated.setText( membre.getDateNaissance().getJour()+"");
        birthdatem.setText( membre.getDateNaissance().getMois()+"");
        birthdatey.setText( membre.getDateNaissance().getAnnée()+"");

    }
    public void fillDropList(){
        String[] _sexe = {"Male", "Female"};
        gender.getItems().addAll(_sexe);
    }
    public static int convertStringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}