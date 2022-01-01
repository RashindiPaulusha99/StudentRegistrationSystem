package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.tm.StudentTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentDetailsFormController implements Initializable {

    public TextField txtSearch;
    public TableView<StudentTM> tblStudents;
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

    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("SId"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("DOB"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Age"));
        tblStudents.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Gender"));
        tblStudents.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblStudents.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("PhoneNO"));
        tblStudents.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblStudents.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("update"));
        tblStudents.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("delete"));

        loadDetails();

        tblStudents.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                index = (int) newValue;
            }
        });
    }

    public void loadDetails(){
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        ArrayList<StudentDTO> all = studentBO.getStudent();
        for (StudentDTO student : all) {
            setUBtn();
            setDBtn();
            obList.add(new StudentTM(student.getsId(),student.getFirstName(),student.getMiddleName(),student.getLastName(),
                    student.getDOB(),student.getAge(),student.getGender(),student.getAddress(),student.getPhoneNO(),student.getEmail(),buttonUpdate,buttonDelete));
            setOnActionForUpdate();
            setOnActionForDelete(student.getsId());
        }
        tblStudents.setItems(obList);
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Student?",yes,no);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no)==yes){

                if (studentBO.deleteStudent(Value)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete Successful.").showAndWait();
                    loadDetails();
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
                }else {
                    new Alert(Alert.AlertType.WARNING, "Try Again.").showAndWait();
                }

            }else{
            }
        });
    }

    String id = null;
    public void setOnActionForUpdate(){
        buttonUpdate.setOnAction((event) -> {

            if (index == -1) {
                new Alert(Alert.AlertType.WARNING, "Please Select A Raw.").show();
            } else {
                StudentTM student = tblStudents.getSelectionModel().getSelectedItem();

                txtFirstName.setText(student.getFirstName());
                txtMiddleName.setText(student.getMiddleName());
                txtLastName.setText(student.getLastName());
                txtAge.setText(String.valueOf(student.getAge()));
                txtContact.setText(String.valueOf(student.getPhoneNO()));
                txtEmail.setText(student.getEmail());
                txtAddress.setText(student.getAddress());
                txtSearch.setText(student.getSId());
                dpBirth.setValue(LocalDate.parse(student.getDOB()));
                id = student.getSId();

                if (student.getGender().equalsIgnoreCase("male")){
                    rbtnMale.setSelected(true);
                } else if (student.getGender().equalsIgnoreCase("female")) {
                    rbtnFemale.setSelected(true);
                }
            }
        });
    }

    public void searchOnAction(ActionEvent event) {
        StudentDTO student = studentBO.searchStudent(txtSearch.getText());
        txtFirstName.setText(student.getFirstName());
        txtMiddleName.setText(student.getMiddleName());
        txtLastName.setText(student.getLastName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(String.valueOf(student.getPhoneNO()));
        txtAge.setText(String.valueOf(student.getAge()));
        txtEmail.setText(student.getEmail());
        dpBirth.setValue(LocalDate.parse(student.getDOB()));

        if (student.getGender().equalsIgnoreCase("male")){
            rbtnMale.setSelected(true);
        } else if (student.getGender().equalsIgnoreCase("female")) {
            rbtnFemale.setSelected(true);
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
        txtSearch.clear();
    }

    public void updateStudentOnAction(ActionEvent event) {

        String gender = null;
        if (rbtnMale.isSelected()){
            gender = "male";
        }else if (rbtnFemale.isSelected()){
            gender = "female";
        }

        StudentDTO student = new StudentDTO(id, txtFirstName.getText(),txtMiddleName.getText(),txtLastName.getText(), String.valueOf(dpBirth.getValue()), Integer.parseInt(txtAge.getText()), gender, txtAddress.getText(), Integer.parseInt(txtContact.getText()),txtEmail.getText());

        if (studentBO.updateStudent(student)){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successful.").showAndWait();
            loadDetails();
            txtFirstName.clear();
            txtMiddleName.clear();
            txtLastName.clear();
            txtAddress.clear();
            txtContact.clear();
            txtAge.clear();
            txtEmail.clear();
            txtSearch.clear();
            dpBirth.setValue(null);
            rbtnFemale.setSelected(false);
            rbtnMale.setSelected(false);
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again.").showAndWait();
        }
    }
}
