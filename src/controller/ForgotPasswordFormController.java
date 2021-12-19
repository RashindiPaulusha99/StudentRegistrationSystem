package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ForgotPasswordFormController {

    public AnchorPane forgotPasswordContext;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public PasswordField txtConfirmPassword;

    public void loginFormOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) forgotPasswordContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void registerFormOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/RegisterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) forgotPasswordContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void resetOnAction(ActionEvent event) {

    }
}
