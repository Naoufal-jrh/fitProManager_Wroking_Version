package Controller;

import Module.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

public class ClientAddController  {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label erorMessage;
    @FXML
    private Label erorMessage1;
    @FXML
    private TextField adhesiondateD;
    @FXML
    private TextField adhesiondateM;
    @FXML
    private TextField adhesiondateY;
    @FXML
    private TextField adresse;
    @FXML
    private TextField birthdateD;
    @FXML
    private TextField birthdateM;
    @FXML
    private TextField birthdateY;
    @FXML
    private ComboBox<String> category;
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
    @FXML
    private ComboBox<String> offer;
    public void toClients(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Clients.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private Membre getInfoClient() throws RuntimeException{
        String nom = lastname.getText();
        String prenom = firstname.getText();
        String mail = email.getText();
        String Cin = cin.getText();
        String adress = adresse.getText();
        String sexe = gender.getValue();
        int BDd = convertStringToInt(birthdateD.getText());
        int BDm = convertStringToInt(birthdateM.getText());
        int BDy = convertStringToInt(birthdateY.getText());
        int ADd = convertStringToInt(adhesiondateD.getText());
        int ADm = convertStringToInt(adhesiondateM.getText());
        int ADy = convertStringToInt(adhesiondateY.getText());
        Date birthdate=new  Date(BDd,BDm,BDy);
        Date adesiondate=new  Date(ADd,ADm,ADy);
        boolean p1=!(BDd<0||BDd>32)&&!(BDm<0||BDm>13)&&!(BDy<1920||BDy>2023);
        boolean p2=!(ADd<0||ADd>32)&&!(ADm<0||ADm>13)&&!(ADy<1920||ADy>2023);
        if (nom.length()>2&&prenom.length()>2&&Cin.length()>2&&mail.length()>2&&adress.length()>2&&p1&&p2&&!(sexe.isEmpty())){
            return new Membre(nom,Cin,prenom,mail,adress,"3456789678",sexe,birthdate,adesiondate);
        }
        else {
            if (p1&&p2){
                System.out.println("invalid data (text less than 3 carcters)");
                erorMessage.setText("invalid data (text less than 3 carcters)");
            }
            else {
                System.out.println("invalid date");
                erorMessage.setText("invalid date");
            }
            return null;
        }
    }


    public void addClient(ActionEvent event)throws IOException,SQLException{


        //the member to be added
        Membre newMembre = getInfoClient();
        //the selected offer
        String selectedOffer = offer.getValue();
        if(newMembre!=null && !(selectedOffer.length()==0)){
            Offre selectedOfferObj = Module.getOffres("SELECT * FROM offre where nomOffre LIKE \""+selectedOffer+"\"").get(0);
            //add the new member to the dataBase

            try {
                // add the new member to the database
                Module.ajouterMembre(newMembre);
            } catch (SQLException e) {
                Module.showCustomAlert(Alert.AlertType.ERROR, "Le Membre est déjà enregistré", "../view/addClient.fxml",event);
                // Exception was caught in ajouterMembre, stop the execution of the function because member exists
                return;
            }


            //get the idMembre and the idOffer
            int idMembre = Module.getMembres("SELECT * FROM membre WHERE cin = \""+newMembre.getCin()+"\" " +
                    "ORDER BY idMembre").get(0).getIdPersonne();
            int idCategorie = Module.getCategorie("SELECT * FROM categorie where nomCategorie LIKE \""+category.getValue()+"\"").get(0).getIdCategorie();
            int idOffer = Module.getOffres("SELECT * FROM offre WHERE nomOffre = \""+selectedOffer+"\" AND idCategorie = "+idCategorie).get(0).getIdOffre();

            newMembre.setIdPersonne(idMembre);

            Module.ajouterExpiration(idMembre,idOffer);

            //show confirmation alert and go back to client list
            Module.showCustomAlert(Alert.AlertType.NONE,"Membre ajouté avec succès","../view/Clients.fxml",event);









        }

    }





    public static int convertStringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public void fillDropList(){
        String categorie =  category.getValue();
        String[] _sexe = {"Male", "Female"};
        List<Categorie> _Categorie = Module.getCategorie("select * from categorie");
        gender.getItems().addAll(_sexe);
        for (int i = 0; i < _Categorie.size(); i++) {
            category.getItems().add(_Categorie.get(i).getNomCategorie());
        }
    }

    public void setAdhToCurrentDate(){
        LocalDate currentDate =LocalDate.now();
        adhesiondateD.setText(String.valueOf(currentDate.getDayOfMonth()));
        adhesiondateM.setText(String.valueOf(currentDate.getMonthValue()));
        adhesiondateY.setText(String.valueOf(currentDate.getYear()));

    }

    @FXML
    public void filterOffers(){
        offer.getItems().clear();
        String categorie =  category.getValue();
        List<Offre> _Offre = Module.getOffres("SELECT * FROM `offre` WHERE idCategorie = (SELECT `idcategorie` FROM `categorie` WHERE `nomcategorie`=\""+categorie+"\");");
        for (int i = 0; i < _Offre.size(); i++) {
            offer.getItems().add(_Offre.get(i).getNomOffre());
        }
    }
    @FXML
    public void initialize() {fillDropList();
        setAdhToCurrentDate();
    }
}