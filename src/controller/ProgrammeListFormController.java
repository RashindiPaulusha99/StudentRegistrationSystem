package controller;

import bo.BOFactory;
import bo.QueryBO;
import dto.RegisterDetailDTO;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import view.tm.CourseDetailTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProgrammeListFormController implements Initializable {
    
    public AnchorPane pnProgramme1;
    public AnchorPane pnProgramme2;
    public AnchorPane pnProgramme3;
    public AnchorPane pnProgramme4;
    public AnchorPane pnProgramme5;
    public TableView<CourseDetailTM> tblCourses;

    private QueryBO queryBO = (QueryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.QUERY);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblCourses.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("RId"));
        tblCourses.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblCourses.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("payment"));
        tblCourses.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("PId"));
        tblCourses.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCourses.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblCourses.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("fee"));

        ArrayList<RegisterDetailDTO> details = queryBO.getDetails();
        ObservableList<CourseDetailTM> obList = FXCollections.observableArrayList();
        for (RegisterDetailDTO detail : details) {
            obList.add(new CourseDetailTM(detail.getRId(),detail.getDate(),detail.getPayment(),detail.getPId(),detail.getName(),detail.getDuration(),detail.getFee()));
        }
        tblCourses.setItems(obList);
    }

    public void mouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof AnchorPane) {
            AnchorPane pane = (AnchorPane) mouseEvent.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GOLD);
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
