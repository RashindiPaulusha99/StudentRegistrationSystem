package controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import dao.*;
import embeded.Name;
import entity.Course;
import entity.Register;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public TableView<Course> tblCourse;
    public ComboBox<String> cmbCourses;

    CourseDAO courseDAO = new CourseDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    RegisterDAO registerDAO = new RegisterDAOImpl();

    ObservableList<Course> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PID"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("fee"));

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

        generateRegisterIds();
        generateStudentIds();

        ArrayList<Course> all = courseDAO.getAll();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (Course course : all) {
            obList.add(course.getCourseName());
        }
        cmbCourses.setItems(obList);

        cmbCourses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                ArrayList<Course> allCourses = courseDAO.getCourseDetails(newValue);
                for (Course c : allCourses) {
                    observableList.add(new Course(c.getPID(),c.getCourseName(),c.getDuration(),c.getFee()));
                }
                tblCourse.setItems(observableList);
            }
        });
    }

    private void generateStudentIds() {
        lblRegisterNO.setText(registerDAO.generateRegisterIds());
    }

    private void generateRegisterIds() {
        txtStudentId.setText(studentDAO.generateStudentIds());
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
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

        String select = null;
        if (rbtnFemale.isSelected() || rbtnMale.isSelected()){
            select = "selected";
        }else {
            select = "not selected";
        }

        String confirm = null;
        if (rbtnFemale.isSelected() || rbtnMale.isSelected()){
            confirm = "selected";
        }else {
            confirm = "not selected";
        }

        if (txtFirstName.getText().isEmpty() || txtMiddleName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtAddress.getText().isEmpty() ||
            txtAge.getText().isEmpty() || txtEmail.getText().isEmpty() || txtContact.getText().isEmpty() || select.equals("not selected") || confirm.equals("not selected") ||
                dpBirth.getValue() == null
        ){
            new Alert(Alert.AlertType.WARNING,"All Fields Are Required.").show();
        }else {

            String gender = null;
            if (rbtnMale.isSelected()){
                gender = "male";
            }else if (rbtnFemale.isSelected()){
                gender = "female";
            }

            String payment = null;
            if (chkPayment.isSelected()){
                payment = "Paid";
            }else {
                payment = "Not Paid";
            }

            Name name = new Name(txtFirstName.getText(),txtMiddleName.getText(),txtLastName.getText());

            Student student = new Student(txtStudentId.getText(), name, String.valueOf(dpBirth.getValue()), Integer.parseInt(txtAge.getText()), gender, txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText());

            List<Course> details = new ArrayList<>();

            for (Course tempTm : observableList) {
                details.add(new Course(
                        tempTm.getPID(),
                        tempTm.getCourseName(),
                        tempTm.getDuration(),
                        tempTm.getFee()
                ));
            }

            Register register = new Register(
                    lblRegisterNO.getText(),
                    student,
                    lblDate.getText(),
                    payment,
                    details
            );

            if (studentDAO.add(student) && registerDAO.add(register) && courseDAO.updateCourseList(register)){

                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Register.").showAndWait();

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
                cmbCourses.getSelectionModel().clearSelection();
                tblCourse.getItems().clear();

                generateStudentIds();

                generateRegisterIds();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again.").show();
            }
        }
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
        tblCourse.getItems().clear();
    }

}
