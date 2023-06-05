package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

import Module.Module;
public class OfferAddController {

    @FXML
    private ComboBox<String> category;

    @FXML
    private Label erorMsg;

    @FXML
    private TextField offerduration;

    @FXML
    private TextField offername;

    @FXML
    private TextField offerprice;

    @FXML
    void addOffer(ActionEvent event) {

    }
    public void toOffers(ActionEvent event) throws IOException {
        Navigation nv=new Navigation();
        nv.goTo(event,"../View/Offers.fxml");
    }
    public void addnewOffer(ActionEvent event) throws IOException{
        Offre offre=getInfoOffre();
        if (offre!=null){
            Module.ajouterOffre(offre);
            toOffers(event);
        }
    }
    private Offre getInfoOffre() throws RuntimeException{
        String nom = offername.getText();
        int  duree =convertStringToInt(offerduration.getText());
        String prix = offerprice.getText();
        String categoryname = category.getValue();
        if (nom.length()>0&&duree>0&&prix.length()>0&&!(categoryname.isEmpty())){
            List<Categorie> categorie = Module.getCategorie("SELECT * FROM categorie where nomCategorie LIKE \""+category.getValue()+"\"");
            if (categorie.size()!=0) return new Offre(nom,prix,duree,categorie.get(0));
            else return null;
        }
        else {
            erorMsg.setText("fill all the requiered field");
            return null;
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
        List<Categorie> _Categorie = Module.getCategorie("select * from categorie");
        for (int i = 0; i < _Categorie.size(); i++) {
            category.getItems().add(_Categorie.get(i).getNomCategorie());
            if (i==0) category.setValue(_Categorie.get(i).getNomCategorie());
        }

    }
    @FXML
    public void initialize() {
        fillDropList();
    }

}
