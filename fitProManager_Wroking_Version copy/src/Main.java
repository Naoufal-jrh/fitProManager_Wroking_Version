
import Module.Module;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FitPro manager home page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch();
        //Module.ajouterExpiration(13,4);
        //Module.ajouterInsciption(13,4);
        /*System.out.println("modif expiration:");
        Module.modifierExpiration(79,18);
        Module.getNbrMembreCategorie(25);*/
     /*   System.out.println("nbr inscrits");
        Module.modifierNbrInscrits(25);
        System.out.println(Module.getNbrMembreCategorie(25));*/
    }



}

