package controller;

import com.jfoenix.controls.JFXButton;
import dao.CourseDAO;
import dao.CourseDAOImpl;
import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.tm.CourseTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddNewProgrammesFormController implements Initializable {

    public TextField txtPID;
    public TextField txtPName;
    public TextField txtFee;
    public ComboBox<String> cmbDuration;
    public TableView<CourseTM> tblProgrammes;
    public JFXButton btnAdd;

    CourseDAO courseDAO = new CourseDAOImpl();

    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        generateCourseIDS();

        tblProgrammes.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PID"));
        tblProgrammes.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblProgrammes.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblProgrammes.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblProgrammes.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("btnUpdate"));
        tblProgrammes.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        tblProgrammes.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            index = (int) newValue;
        });

        txtPName.setDisable(true);
        txtFee.setDisable(true);
        cmbDuration.setDisable(true);

        loadAllCourses();

        ObservableList<String> obList = FXCollections.observableArrayList(
                "30 days",
                "45 days",
                "2 month",
                "3 month",
                "6 month",
                "1 Year",
                "2 year",
                "3 year",
                "4 year"
        );
        cmbDuration.setItems(obList);
    }

    private void generateCourseIDS() {
        txtPID.setText(courseDAO.generateCourseIds());
    }

    JFXButton buttonDelete;
    public void setDBtn(){
        buttonDelete = new JFXButton();
        buttonDelete.setStyle("-fx-border-color: red");
        Image img = new Image("assets/icons/icons8-delete-64.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(26);
        view.setFitWidth(35);
        buttonDelete.setGraphic(view);
    }

    JFXButton buttonUpdate;
    public void setUBtn(){
        buttonUpdate = new JFXButton();
        buttonUpdate.setStyle("-fx-border-color: blueviolet");
        Image img = new Image("assets/icons/icons8-edit-48.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(26);
        view.setFitWidth(35);
        buttonUpdate.setGraphic(view);
    }

    public void setOnActionForDelete(String Value){

        buttonDelete.setOnAction((event) -> {
            ButtonType yes= new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no= new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Course?",yes,no);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no)==yes){

                if (courseDAO.delete(Value)) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful.").showAndWait();

                    loadAllCourses();

                    txtPName.clear();
                    txtFee.clear();
                    txtPName.setDisable(true);
                    txtFee.setDisable(true);
                    cmbDuration.setDisable(true);
                    cmbDuration.getSelectionModel().clearSelection();

                    generateCourseIDS();

                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again.").showAndWait();
                }

            }else{
            }
        });
    }

    public void setOnActionForUpdate(){

        buttonUpdate.setOnAction((event) -> {

            if (index == -1) {
                new Alert(Alert.AlertType.WARNING, "Please Select A Raw.").show();
            } else {
                CourseTM tm = tblProgrammes.getSelectionModel().getSelectedItem();

                txtPID.setText(tm.getPID());
                txtPName.setText(tm.getCourseName());
                txtFee.setText(String.valueOf(tm.getFee()));
                cmbDuration.setValue(tm.getDuration());

                txtPName.setDisable(false);
                txtFee.setDisable(false);
                cmbDuration.setDisable(false);

                btnAdd.setText("Update");

                btnAdd.setOnAction(event1 -> {
                    if (courseDAO.update(new Course(txtPID.getText(), txtPName.getText(), cmbDuration.getValue(), Double.parseDouble(txtFee.getText())))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated New Course.").showAndWait();
                        loadAllCourses();
                        txtPName.clear();
                        txtFee.clear();
                        txtPName.setDisable(true);
                        txtFee.setDisable(true);
                        cmbDuration.setDisable(true);
                        cmbDuration.getSelectionModel().clearSelection();

                    }else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Try Again.").show();
                    }
                });
            }
        });
    }

    private void loadAllCourses() {
        ArrayList<Course> all = courseDAO.getAll();
        ObservableList<CourseTM> obList = FXCollections.observableArrayList();

        for (Course c : all) {

            setUBtn();
            setDBtn();

            obList.add(new CourseTM(c.getPID(),c.getCourseName(),c.getDuration(),c.getFee(),buttonUpdate,buttonDelete));

            setOnActionForDelete(c.getPID());
            setOnActionForUpdate();
        }
        tblProgrammes.setItems(obList);
    }

    public void addNewCourseOnAction(ActionEvent event) {
        txtPName.setDisable(false);
        txtFee.setDisable(false);
        cmbDuration.setDisable(false);
    }

    public void addCourseOnAction(ActionEvent event) {
        if (txtPName.getText().isEmpty() || txtFee.getText().isEmpty() || cmbDuration.getValue() == null){
            new Alert(Alert.AlertType.WARNING,"All Fields Are Required.").show();
        }else {

            if (courseDAO.add(new Course(txtPID.getText(), txtPName.getText(), cmbDuration.getValue(), Double.parseDouble(txtFee.getText())))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added New Course.").showAndWait();

                loadAllCourses();

                txtPName.clear();
                txtFee.clear();
                txtPName.setDisable(true);
                txtFee.setDisable(true);
                cmbDuration.setDisable(true);
                cmbDuration.getSelectionModel().clearSelection();

                generateCourseIDS();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again.").show();
            }
        }
    }

    public void clearCourseOnAction(ActionEvent event) {
        txtPName.clear();
        txtFee.clear();
        cmbDuration.getSelectionModel().clearSelection();
    }

}
