package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import dao.CourseDAO;
import dao.CourseDAOImpl;
import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CourseDetailsTM;
import view.tm.CourseTM;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterStudentFormController implements Initializable {

    public Label lblRegisterNO;
    public TextField txtStudentId;
    public TextField txtFirstName;
    public TextField txtMiddleName;
    public TextField txtLastName;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtContact;
    public TextField txtEmail;
    public Label lblDate;
    public JFXRadioButton rbtnMale;
    public JFXRadioButton rbtnFemale;
    public CheckBox chkPayment;
    public JFXDatePicker dpBirth;
    public TableView<CourseDetailsTM> tblCourse;

    CourseDAO courseDAO = new CourseDAOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PID"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblCourse.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        txtFirstName.setDisable(true);
        txtMiddleName.setDisable(true);
        txtLastName.setDisable(true);
        txtAddress.setDisable(true);
        txtAge.setDisable(true);
        txtContact.setDisable(true);
        txtEmail.setDisable(true);
        rbtnMale.setDisable(true);
        rbtnFemale.setDisable(true);
        chkPayment.setDisable(true);
        dpBirth.setDisable(true);

        loadDate();

        loadCourseDetails();
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
    }

    private void loadCourseDetails() {
        ArrayList<Course> all = courseDAO.getAll();
        ObservableList<CourseDetailsTM> obList = FXCollections.observableArrayList();
        for (Course c : all) {
            CheckBox checkBox = new CheckBox();
            obList.add(new CourseDetailsTM(c.getPID(),c.getCourseName(),c.getDuration(),c.getFee(),checkBox));
        }
        tblCourse.setItems(obList);
    }

    public void newStudentOnAction(ActionEvent event) {
        txtFirstName.setDisable(false);
        txtMiddleName.setDisable(false);
        txtLastName.setDisable(false);
        txtAddress.setDisable(false);
        txtAge.setDisable(false);
        txtContact.setDisable(false);
        txtEmail.setDisable(false);
        rbtnMale.setDisable(false);
        rbtnFemale.setDisable(false);
        chkPayment.setDisable(false);
        dpBirth.setDisable(false);
    }

    public void registerStudentOnAction(ActionEvent event) {

    }

    public void clearOnAction(ActionEvent event) {
        txtFirstName.clear();
        txtMiddleName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtAge.clear();
        txtContact.clear();
        txtEmail.clear();
        rbtnMale.setSelected(false);
        rbtnFemale.setSelected(false);
        chkPayment.setSelected(false);
        dpBirth.setValue(null);
        tblCourse.getSelectionModel().getSelectedItem().getCheckBox().setSelected(false);

    }

}
