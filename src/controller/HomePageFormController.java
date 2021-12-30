package controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class HomePageFormController {

    public AnchorPane homePageContext;
    public Label lblUser;
    public AnchorPane paneRegisterStudent;
    public AnchorPane paneStudentList;
    public AnchorPane paneProgrammeList;
    public AnchorPane paneNewProgrammes;
    public Label lblStudentCount;
    public Label lblProgrammesCount;

    public void setUserAndName(String user){
        lblUser.setText(user);
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
                    loader = FXMLLoader.load(this.getClass().getResource("../view/RegisterDetailsForm.fxml"));
                    break;
                case "paneProgrammeList":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/ProgrammeListForm.fxml"));
                    break;
                case "paneNewProgrammes":
                    loader = FXMLLoader.load(this.getClass().getResource("../view/AddNewProgrammesForm.fxml"));
                    break;
            }

            if (loader != null) {
                homePageContext.getChildren().setAll(loader);
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
}
