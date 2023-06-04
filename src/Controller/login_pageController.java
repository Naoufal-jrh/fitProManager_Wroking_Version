package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import Module.Module;

public class login_pageController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Label errorLabel;


    @FXML
    void login(ActionEvent event) {
        if (authenticateUser()){
            //the infos are right
            //we give the access to the user
            try {
                toApplication(event);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            errorLabel.setText("The email or password is wrong, please retry.");
        }
    }

    @FXML
    void toSignupPage(MouseEvent event) throws IOException {
        Scene scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/signup_page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    //this function returns true if the email and password are true or false if one of them is not
    private boolean authenticateUser(){
        String email = this.email.getText();
        String password = this.password.getText();
        //connect to database
        Connection conn = Module.connectToDb();

        try {
            //check the database for the username and password
            // Create the SQL statement with placeholders for username and password
            String sql = "SELECT * FROM user WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the username and password values
            stmt.setString(1, email);

            // Execute the query
            ResultSet result = stmt.executeQuery();
            // Check if any rows are returned
            if (result.next()){
                String passwordhash = result.getString("password_hash");
                Module.closeConnection(conn,stmt,result);
                //return true if the password is true and false if not
                return Module.isPasswordMatch(password,passwordhash);
            }else return false;//it means that the username is false

        }catch (SQLException e){
            e.printStackTrace();
            return false;//return false if there is an exception
        }
    }

    public void toApplication(ActionEvent event) throws IOException {
        Stage stage ;
        Scene scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/home.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
