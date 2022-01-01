package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import dto.LoginDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegisterFormController {

    public AnchorPane RegisterContext;
    public TextField txtUsername;
    public TextField txtPassword;
    public TextField txtUserId;
    public TextField txtName;

    private LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    public void loginFormOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) RegisterContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void forgotPasswordFormOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/ForgotPasswordForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) RegisterContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void homePageOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/WelcomePage.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) RegisterContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void registerOnAction(ActionEvent event) {

        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || txtUserId.getText().isEmpty() || txtName.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"All Fields Are Required.").show();
        }else {

            if (loginBO.saveLogin(new LoginDTO(txtUserId.getText(), txtUsername.getText(), txtPassword.getText(),txtName.getText()))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration Successful.").showAndWait();
                txtUserId.clear();
                txtUsername.clear();
                txtPassword.clear();
                txtName.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again.").show();
            }
        }

    }

}
