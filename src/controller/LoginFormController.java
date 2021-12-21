package controller;

import dao.LoginDAOImpl;
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

public class LoginFormController {

    public AnchorPane loginContext;
    public TextField txtUsername;
    public TextField txtPassword;

    LoginDAOImpl loginDAO = new LoginDAOImpl();

    public void DashBoardFormOnAction(ActionEvent event) throws IOException {

        if (loginDAO.searchCorrectUsernameAndPassword(txtUsername.getText(),txtPassword.getText())){
            URL resource = getClass().getResource("../view/DashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage window = (Stage) loginContext.getScene().getWindow();
            window.setScene(scene);
            window.centerOnScreen();
        }else {
            new Alert(Alert.AlertType.WARNING, "Please Check Correct Username And Password.").show();
        }

    }

    public void registerFormOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/RegisterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void forgotPasswordFormOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/ForgotPasswordForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void homePageOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/WelcomePage.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }
}
