package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class DashBoardFormController {

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
                case "paneStudentList":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/ViewStudentForm.fxml"));
                    break;
                case "paneProgrammeList":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/ProgrammeListForm.fxml"));
                    break;
                case "paneNewProgrammes":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/AddNewProgrammesForm.fxml"));
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
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/LoginForm.fxml"));
        AnchorPane pane = loader.load();
        dashboardPaneContext.getChildren().setAll(pane);
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
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/ViewStudentForm.fxml"));
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
