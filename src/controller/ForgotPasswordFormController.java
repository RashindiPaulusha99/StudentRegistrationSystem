package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import dto.LoginDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

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

    public void homePageOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/WelcomePage.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) forgotPasswordContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void resetOnAction(ActionEvent event) {

        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"All Fields Are Required.").show();
        }else {

            if (txtPassword.getText().equals(txtConfirmPassword.getText())){

                LoginDTO data = loginBO.SearchLogin(txtUsername.getText());
                LoginDTO login = new LoginDTO(data.getUserId(),txtUsername.getText(),txtPassword.getText(),data.getName());

                if (loginBO.updateLogin(login)) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Your Password was Changed.").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again.").show();
                }

            }else {
                new Alert(Alert.AlertType.WARNING,"Check Your Password.").show();
            }
        }

    }
}
