package controller;

import com.jfoenix.controls.JFXRadioButton;
import dao.*;
import entity.Course;
import entity.Register;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterExistsStudentFormController implements Initializable {

    public Label lblRegisterNO;
    public TextField txtFirstName;
    public TextField txtMiddleName;
    public TextField txtLastName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEmail;
    public Label lblDate;
    public JFXRadioButton rbtnMale;
    public JFXRadioButton rbtnFemale;
    public CheckBox chkPayment;
    public TextField txtAge;
    public TableView<Course> tblCourse;
    public ComboBox<String> cmbCourses;
    public ComboBox<String> cmbStudentIds;
    public TextField txtDOB;
    public AnchorPane paneContext;
    public AnchorPane registerExistsContext;

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
        txtDOB.setDisable(true);
        rbtnMale.setDisable(true);
        rbtnFemale.setDisable(true);
        chkPayment.setDisable(true);
        cmbStudentIds.setDisable(true);
        cmbCourses.setDisable(true);

        loadDate();

        generateRegisterIds();

        ArrayList<Student> details = studentDAO.getAll();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Student student : details) {
            list.add(student.getsId());
        }
        cmbStudentIds.setItems(list);

        cmbStudentIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                Student student = studentDAO.search(newValue);
                txtFirstName.setText(student.getName().getFirstName());
                txtMiddleName.setText(student.getName().getMiddleName());
                txtLastName.setText(student.getName().getLastName());
                txtAddress.setText(student.getAddress());
                txtAge.setText(String.valueOf(student.getAge()));
                txtContact.setText(String.valueOf(student.getPhoneNO()));
                txtEmail.setText(student.getEmail());
                txtDOB.setText(student.getDOB());

                if (student.getGender().equalsIgnoreCase("male")){
                    rbtnMale.setSelected(true);
                } else if (student.getGender().equalsIgnoreCase("female")) {
                    rbtnFemale.setSelected(true);
                }
            }
        });

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

    private void generateRegisterIds() {
        lblRegisterNO.setText(registerDAO.generateRegisterIds());
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
    }

    public void newStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane loader = FXMLLoader.load(this.getClass().getResource("../view/RegisterStudentForm.fxml"));
        registerExistsContext.getChildren().setAll(loader);
    }

    public void existsStudentOnAction(ActionEvent event) {
        txtFirstName.setDisable(false);
        txtMiddleName.setDisable(false);
        txtLastName.setDisable(false);
        txtAddress.setDisable(false);
        txtAge.setDisable(false);
        txtContact.setDisable(false);
        txtEmail.setDisable(false);
        txtDOB.setDisable(false);
        rbtnMale.setDisable(false);
        rbtnFemale.setDisable(false);
        chkPayment.setDisable(false);
        cmbStudentIds.setDisable(false);
        cmbCourses.setDisable(false);
    }

    public void registerStudentOnAction(ActionEvent event) {
        String select = null;
        if (rbtnFemale.isSelected() || rbtnMale.isSelected()) {
            select = "selected";
        } else {
            select = "not selected";
        }

        if (txtFirstName.getText().isEmpty() || txtMiddleName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtAddress.getText().isEmpty() ||
                txtAge.getText().isEmpty() || txtEmail.getText().isEmpty() || txtContact.getText().isEmpty() || select.equals("not selected") || txtDOB.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required.").show();
        } else {

            String payment = null;
            if (chkPayment.isSelected()) {
                payment = "Paid";
            } else {
                payment = "Not Paid";
            }

            Student student = studentDAO.search(cmbStudentIds.getValue());

            ObservableList<Course> items = tblCourse.getItems();
            Course course = null;
            for (Course tempTm : observableList) {
                course = new Course(tempTm.getPID(), tempTm.getCourseName(), tempTm.getDuration(), tempTm.getFee());
            }

            Register register = new Register(lblRegisterNO.getText(), lblDate.getText(), payment, student, course);

            if (registerDAO.add(register)) {
                if (studentDAO.saveRegisterDetails(register, register.getStudentDetails().getsId())) {
                    if (courseDAO.saveRegisterDetails(register, register.getCourse().getPID())) {
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
                        txtDOB.clear();
                        cmbCourses.getSelectionModel().clearSelection();
                        tblCourse.getItems().clear();
                        cmbStudentIds.getSelectionModel().clearSelection();

                        generateRegisterIds();

                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again.").show();
                    }
                }
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
        txtDOB.clear();
        tblCourse.getItems().clear();
        cmbCourses.getSelectionModel().clearSelection();
        cmbStudentIds.getSelectionModel().clearSelection();
    }

}
