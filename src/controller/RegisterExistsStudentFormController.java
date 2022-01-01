package controller;

import bo.BOFactory;
import bo.custom.CourseBO;
import bo.custom.RegisterBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXRadioButton;
import dto.CourseDTO;
import dto.RegisterDTO;
import dto.StudentDTO;
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
    public TableView<CourseDTO> tblCourse;
    public ComboBox<String> cmbCourses;
    public ComboBox<String> cmbStudentIds;
    public TextField txtDOB;
    public AnchorPane paneContext;
    public AnchorPane registerExistsContext;

    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    private CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    private RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTER);

    ObservableList<CourseDTO> observableList = FXCollections.observableArrayList();

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

        ArrayList<StudentDTO> details = studentBO.getStudent();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (StudentDTO student : details) {
            list.add(student.getsId());
        }
        cmbStudentIds.setItems(list);

        cmbStudentIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                StudentDTO student = studentBO.searchStudent(newValue);
                txtFirstName.setText(student.getFirstName());
                txtMiddleName.setText(student.getMiddleName());
                txtLastName.setText(student.getLastName());
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

        ArrayList<CourseDTO> all = courseBO.getCourses();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (CourseDTO course : all) {
            obList.add(course.getCourseName());
        }
        cmbCourses.setItems(obList);

        cmbCourses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                ArrayList<CourseDTO> allCourses = courseBO.getCourseDetails(newValue);
                for (CourseDTO c : allCourses) {
                    observableList.add(new CourseDTO(c.getPID(),c.getCourseName(),c.getDuration(),c.getFee()));
                }
                tblCourse.setItems(observableList);
            }
        });

    }

    private void generateRegisterIds() {
        lblRegisterNO.setText(registerBO.generateRegisterIds());
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

            StudentDTO student = studentBO.searchStudent(cmbStudentIds.getValue());

            ObservableList<CourseDTO> items = tblCourse.getItems();
            CourseDTO course = null;
            for (CourseDTO tempTm : observableList) {
                course = new CourseDTO(tempTm.getPID(), tempTm.getCourseName(), tempTm.getDuration(), tempTm.getFee());
            }

            RegisterDTO register = new RegisterDTO(lblRegisterNO.getText(), lblDate.getText(), payment, student, course);

            if (registerBO.saveRegister(register)) {
                if (studentBO.saveRegisterDetails(register, register.getStudent().getsId())) {
                    if (courseBO.saveRegisterDetails(register, register.getCourse().getPID())) {
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
