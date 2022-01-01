package controller;

import bo.BOFactory;
import bo.custom.CourseBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {

    public StackPane dashboardPaneContext;
    public AnchorPane paneRegisterStudent;
    public AnchorPane paneStudentList;
    public AnchorPane paneProgrammeList;
    public AnchorPane paneNewProgrammes;
    public Label lblStudentCount;
    public Label lblProgrammesCount;
    public Label lblName;
    public Label lblUser;
    public ImageView imgDashBoard;
    public ImageView imgRegisterStudent;
    public ImageView imgStudentList;
    public ImageView imgAddProgrammes;
    public ImageView imgProgrammesList;
    public JFXButton btnDashBoard;
    public JFXButton btnRegisterStudent;
    public JFXButton btnStudentList;
    public JFXButton btnAddProgrammes;
    public JFXButton btnProgrammesList;
    public ImageView imgRegisterList1;
    public JFXButton btnRegisterList;
    public AnchorPane paneRegisterList;

    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    private CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblStudentCount.setText(String.valueOf(studentBO.countStudent()));
        lblProgrammesCount.setText(String.valueOf(courseBO.countCourses()));
    }

    public void setUserAndName(String user, String name){
        lblUser.setText(user);
        lblName.setText(name);
    }

    public void mouseClickedAnimation(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) mouseEvent.getSource();

            AnchorPane loader = null;

            switch (pane.getId()) {
                case "paneRegisterStudent":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/RegisterStudentForm.fxml"));
                    break;
                case "paneRegisterList":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/RegisterDetailsForm.fxml"));
                    break;
                case "paneProgrammeList":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/ProgrammeListForm.fxml"));
                    break;
                case "paneNewProgrammes":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/AddNewProgrammesForm.fxml"));
                    break;
                case "paneStudentList":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/StudentDetailsForm.fxml"));
                    break;
            }

            if (loader != null) {
                dashboardPaneContext.getChildren().setAll(loader);
            }
        }

    }

    public void mouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            pane.setEffect(glow);
        }
    }

    public void mouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            pane.setEffect(null);
        }
    }

    public void logOutOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = (Stage) dashboardPaneContext.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void DashboardOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/HomePageForm.fxml"));
        AnchorPane pane = loader.load();
        HomePageFormController controller = loader.<HomePageFormController>getController();
        controller.setUserAndName(lblUser.getText());
        dashboardPaneContext.getChildren().setAll(pane);
    }

    public void RegisterStudentOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/RegisterStudentForm.fxml"));
        AnchorPane pane = loader.load();
        dashboardPaneContext.getChildren().setAll(pane);
    }

    public void StudentListOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/StudentDetailsForm.fxml"));
        AnchorPane pane = loader.load();
        dashboardPaneContext.getChildren().setAll(pane);
    }

    public void NewProgrammesOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/AddNewProgrammesForm.fxml"));
        AnchorPane pane = loader.load();
        dashboardPaneContext.getChildren().setAll(pane);
    }

    public void ProgrammeDetailsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/ProgrammeListForm.fxml"));
        AnchorPane pane = loader.load();
        dashboardPaneContext.getChildren().setAll(pane);
    }

    public void RegisterDetailsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/RegisterDetailsForm.fxml"));
        AnchorPane pane = loader.load();
        dashboardPaneContext.getChildren().setAll(pane);
    }

    public void mouseEnterAnimationForBtn(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof JFXButton) {
            JFXButton btn = (JFXButton) mouseEvent.getSource();
            btn.setStyle("-fx-text-fill: deeppink");
        }
    }

    public void mouseExitAnimationForBtn(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof JFXButton) {
            JFXButton btn = (JFXButton) mouseEvent.getSource();
            btn.setStyle("-fx-text-fill: white");
        }
    }
}
