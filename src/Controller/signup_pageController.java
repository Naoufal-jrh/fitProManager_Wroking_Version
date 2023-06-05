package Controller;

import Module.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

public class signup_pageController {

    @FXML
    private TextField Email;

    @FXML
    private PasswordField confPassword;

    @FXML
    private Label errorLabel;

    @FXML
    private Label loginLab;

    @FXML
    private PasswordField password;

    @FXML
    private Button signupBtn;

    @FXML
    private TextField userName;

    @FXML
    void toLoginByClick(MouseEvent event) throws IOException {
        Scene scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/Login_page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void sign_up(ActionEvent event) {
        try {
            addAccount(event);

        }catch (SQLException e){
            errorLabel.setText("this email allready exists");
        }
    }









    private void addAccount(ActionEvent event) throws SQLException {
        String validate = validatData();
        if (validate==null){
            //connecting to db
            Connection connection = Module.connectToDb();
            try {
                // Prepare the SQL statement
                String sql = "INSERT INTO user (userName, email, password_hash) VALUES (?, ?, ?)";
                assert connection != null;
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, userName.getText());
                statement.setString(2, Email.getText());
                statement.setString(3, Module.hashPassword(password.getText()));
                //execute the statement
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                    try {
                        toLogin(event);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }


                //close the connection
                Module.closeConnection(connection,statement);
            }catch (SQLException | NullPointerException e){
                if (e instanceof SQLIntegrityConstraintViolationException){
                    throw e;
                }
                e.printStackTrace();
            }
        }else {
            errorLabel.setText(validate);
        }
    }
    private String validatData(){
        String userName = this.userName.getText();
        String Email = this.Email.getText();
        String password = this.password.getText();
        String confPassword = this.confPassword.getText();
        if (userName.length()<8){
            return "the user Name is less than 8 characters";
        }else if ( Email.length()<12) {
            return "The email is less than 12 characters";
        } else if (password.length()<8){
            return "Password is less than 8";
        }else if (!password.equals(confPassword)) {
            return "The password confirmation field is different than the passwoed field";
        }else return null;
    }

    public void toLogin(ActionEvent event) throws IOException {
        Scene scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/Login_page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
