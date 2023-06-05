package Controller;

import Module.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddCategoriePopup {
    @FXML
    private ComboBox<String> category;

    @FXML
    private ComboBox<String> offer;

    @FXML
    void addCategorie(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Parent parent = button.getScene().getRoot();
        System.out.println("values :   "+category.getValue()+"    "+offer.getValue());

        if (!Objects.equals(category.getValue(), null)&&!Objects.equals(offer.getValue(), null)){
            Module.ajouterExpiration(ClientController.selectedMember.getIdPersonne(),Module.getOffres("SELECT * FROM offre WHERE nomOffre = "+offer.getValue()).get(0).getIdOffre());
            Button addbutton = (Button) event.getSource();
            Stage currentStage = (Stage) addbutton.getScene().getWindow();
            currentStage.close();
            //Navigation nv = new Navigation();
            //nv.goTo(event,"../View/ViewClient.fxml");

        }

    }

    private ArrayList<String> getCatList(int idPersonne) {
        //get the categories and experation date
        List<Expiration> expiration = Module.getExpiration("SELECT * FROM expiration WHERE idMembre = "+idPersonne);
        ArrayList<String> categories = new ArrayList<>();
        for (Expiration exp: expiration){
            categories.add(exp.getCategorie().getNomCategorie());
        }
        return categories;
    }

    public void initialize(){
        fillDropLists(ClientController.selectedMember.getIdPersonne());
    }


    private void fillDropLists(int memberId) {
        List<Categorie> Categorie = Module.getCategorie("select * from categorie");
        ArrayList<String> CategorieName = getCatList(memberId);
        for (int i = 0; i < Categorie.size(); i++) {
            if (!CategorieName.contains(Categorie.get(i).getNomCategorie())) category.getItems().add(Categorie.get(i).getNomCategorie());
        }
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



}
