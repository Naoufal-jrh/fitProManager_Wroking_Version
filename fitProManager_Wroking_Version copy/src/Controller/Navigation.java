package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public void goTo(ActionEvent event, String location)throws IOException {
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(location)));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goTo(MouseEvent event, String location)throws IOException {
        Scene scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(location)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public ButtonBar.ButtonData showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().orElse(ButtonType.CANCEL).getButtonData();
    }
}
