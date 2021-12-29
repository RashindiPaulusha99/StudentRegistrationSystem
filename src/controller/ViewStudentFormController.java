package controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.tm.RegisterTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewStudentFormController implements Initializable {

    public TextField txtSearch;
    public TableView<RegisterTM> tblStudents;
    public TextField txtFirstName;
    public TextField txtMiddleName;
    public TextField txtLastName;
    public TextField txtAge;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEmail;
    public JFXDatePicker dpBirth;
    public JFXRadioButton rbtnMale;
    public JFXRadioButton rbtnFemale;
    public TextField txtPName;
    public TextField txtFee;
    public ComboBox<String> cmbCourseIds;
    public Label lblRID;
    public Label lblDate;
    public ComboBox<String> cmbDuration;
    public CheckBox chPayment;

    StudentDAO studentDAO = new StudentDAOImpl();
    RegisterDAO registerDAO = new RegisterDAOImpl();
    CourseDAO courseDAO = new CourseDAOImpl();

    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("rId"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("PID"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("update"));
        tblStudents.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("delete"));

        tblStudents.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                index = (int) newValue;
            }
        });

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

        ArrayList<Course> all = courseDAO.getAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Course course : all) {
            observableList.add(course.getCourseName());
        }
        cmbCourseIds.setItems(observableList);

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Details?",yes,no);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no)==yes){

                /*if (courseDAO.delete(Value)) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful.").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again.").showAndWait();
                }*/


            }else{
            }
        });
    }

    Register search = null;

    public void setOnActionForUpdate(){

        buttonUpdate.setOnAction((event) -> {

            if (index == -1) {
                new Alert(Alert.AlertType.WARNING, "Please Select A Raw.").show();
            } else {
                RegisterTM tm = tblStudents.getSelectionModel().getSelectedItem();

                cmbCourseIds.setValue(tm.getPID());
                txtPName.setText(tm.getCourseName());
                txtFee.setText(String.valueOf(tm.getFee()));
                cmbDuration.setValue(tm.getDuration());
                lblRID.setText(tm.getRId());

                search = registerDAO.search(tm.getRId());

                lblDate.setText(search.getDate());
                if (search.getPayment().equalsIgnoreCase("paid")){
                    chPayment.setSelected(true);
                }


                /*btnAdd.setOnAction(event1 -> {
                    if (courseDAO.update(new Course(txtPID.getText(), txtPName.getText(), cmbDuration.getValue(), Double.parseDouble(txtFee.getText())))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated New Course.").showAndWait();
                        loadAllCourses();
                    }else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Try Again.").show();
                    }
                });*/
            }
        });
    }

    public void searchOnAction(ActionEvent event) {
        Student search = studentDAO.search(txtSearch.getText());
        txtFirstName.setText(search.getName().getFirstName());
        txtMiddleName.setText(search.getName().getMiddleName());
        txtLastName.setText(search.getName().getLastName());
        txtAddress.setText(search.getAddress());
        txtContact.setText(String.valueOf(search.getPhoneNO()));
        txtAge.setText(String.valueOf(search.getAge()));
        txtEmail.setText(search.getEmail());
        dpBirth.setValue(LocalDate.parse(search.getDOB()));

        if (search.getGender().equalsIgnoreCase("male")){
            rbtnMale.setSelected(true);
        } else if (search.getGender().equalsIgnoreCase("female")) {
            rbtnFemale.setSelected(true);
        }

        ObservableList<RegisterTM> obList = FXCollections.observableArrayList();
        for (Register register : search.getRegisterList()) {
            setUBtn();
            setDBtn();
            obList.add(new RegisterTM(register.getrId(),register.getCourse().getPID(),register.getCourse().getCourseName(),register.getCourse().getDuration(),register.getCourse().getFee(),buttonUpdate,buttonDelete));
            setOnActionForUpdate();
            setOnActionForDelete(register.getrId());

        }
        tblStudents.setItems(obList);

    }

    public void addToTableOnAction(ActionEvent event) {

    }

    public void updateStudentOnAction(ActionEvent event) {

        String select = null;
        if (rbtnFemale.isSelected() || rbtnMale.isSelected()) {
            select = "selected";
        } else {
            select = "not selected";
        }

        String payment = null;
        if (chPayment.isSelected()){
            payment = "Paid";
        }else {
            payment = "Not Paid";
        }

        if (txtFirstName.getText().isEmpty() || txtMiddleName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtAddress.getText().isEmpty() ||
                txtAge.getText().isEmpty() || txtEmail.getText().isEmpty() || txtContact.getText().isEmpty() || select.equals("not selected") || dpBirth.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required.").show();
        } else {

            String gender = null;
            if (rbtnMale.isSelected()){
                gender = "male";
            }else if (rbtnFemale.isSelected()){
                gender = "female";
            }

            Name name = new Name(txtFirstName.getText(),txtMiddleName.getText(),txtLastName.getText());

            Student student = new Student(txtSearch.getText(), name, String.valueOf(dpBirth.getValue()), Integer.parseInt(txtAge.getText()), gender, txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText());

            ObservableList<RegisterTM> items = tblStudents.getItems();
            Course course = new Course(cmbCourseIds.getValue(),txtPName.getText(),cmbDuration.getValue(),Double.parseDouble(txtFee.getText()));
            /*for (Course tempTm : ) {
                course= new Course(tempTm.getPID(), tempTm.getCourseName(), tempTm.getDuration(), tempTm.getFee());
            }*/

            Register register = new Register(lblRID.getText(),lblDate.getText(),payment,student,course);

            /*if (studentDAO.add(student)) {
                if (registerDAO.add(register)) {
                    if (studentDAO.saveRegisterDetails(register, register.getStudentDetails().getsId())) {
                        if (courseDAO.saveRegisterDetails(register,register.getCourse().getPID())){
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
            }*/
        }
    }

    public void clearOnAction(ActionEvent event) {
        txtFirstName.clear();
        txtMiddleName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtAge.clear();
        txtEmail.clear();
        dpBirth.setValue(null);
        rbtnFemale.setSelected(false);
        rbtnMale.setSelected(false);
        tblStudents.getItems().clear();
        txtSearch.clear();
        lblRID.setStyle("");
        lblDate.setStyle("");
        chPayment.setSelected(false);
        cmbCourseIds.getSelectionModel().clearSelection();
        cmbDuration.getSelectionModel().clearSelection();
        txtPName.clear();
        txtFee.clear();
    }

}
