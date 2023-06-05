
import Module.Module;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FitPro manager home page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        //Module.ajouterExpiration(13,4);
        //Module.ajouterInsciption(13,4);
    }
}

