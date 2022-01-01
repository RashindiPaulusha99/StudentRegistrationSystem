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

public class LoginFormController {

    public AnchorPane loginContext;
    public TextField txtUsername;
    public TextField txtPassword;

    private LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    public void DashBoardFormOnAction(ActionEvent event) throws IOException {

        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required.").show();
        }else {

            LoginDTO data = loginBO.SearchLogin(txtUsername.getText());

            if (data.getUserName().equals(txtUsername.getText()) && data.getPassword().equals(txtPassword.getText())){

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/DashBoardForm.fxml"));
                Parent load = loader.load();
                DashBoardFormController controller = loader.<DashBoardFormController>getController();
                controller.setUserAndName(data.getUserName(),data.getName());
                Stage window = (Stage) loginContext.getScene().getWindow();
                window.setScene(new Scene(load));
                window.centerOnScreen();
            }else {
                new Alert(Alert.AlertType.WARNING, "Please Check Correct User Detail.").show();
            }
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
