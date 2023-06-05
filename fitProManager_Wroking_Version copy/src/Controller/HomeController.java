package Controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

public class HomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    public void toClients(ActionEvent event) throws IOException {
        Navigation nv = new Navigation();
        nv.goTo(event,"../View/Clients.fxml");
    }
    public void toCategories(ActionEvent event){
        Navigation nv = new Navigation();
        try {
            nv.goTo(event,"../View/Categories.fxml");
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
