package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import dao.*;
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

public class RegisterDetailsFormController implements Initializable {

    public TextField txtSearch;
    public TableView<RegisterTM> tblStudents;
    public TextField txtFirstName;
    public TextField txtMiddleName;
    public TextField txtLastName;
    public TextField txtPName;
    public TextField txtFee;
    public ComboBox<String> cmbCourseIds;
    public Label lblRID;
    public CheckBox chPayment;
    public TextField txtDuration;
    public JFXDatePicker dpDate;

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

        ArrayList<Course> all = courseDAO.getAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Course course : all) {
            observableList.add(course.getPID());
        }
        cmbCourseIds.setItems(observableList);

        cmbCourseIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                Course course = courseDAO.search(newValue);
                txtPName.setText(course.getCourseName());
                txtDuration.setText(course.getDuration());
                txtFee.setText(String.valueOf(course.getFee()));
            }
        });

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Register Details?",yes,no);
            alert.setTitle("Confirmation Alert");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(no)==yes){

                if (registerDAO.delete(Value)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete Successful.").showAndWait();
                    txtFirstName.clear();
                    txtMiddleName.clear();
                    txtLastName.clear();
                    tblStudents.getItems().clear();
                    txtSearch.clear();
                    lblRID.setText(null);
                    dpDate.setValue(null);
                    chPayment.setSelected(false);
                    cmbCourseIds.getSelectionModel().clearSelection();
                    txtDuration.clear();
                    txtPName.clear();
                    txtFee.clear();
                }

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
                txtDuration.setText(tm.getDuration());
                lblRID.setText(tm.getRId());

                search = registerDAO.search(tm.getRId());

                dpDate.setValue(LocalDate.parse(search.getDate()));
                if (search.getPayment().equalsIgnoreCase("paid")){
                    chPayment.setSelected(true);
                }
            }
        });
    }

    ObservableList<RegisterTM> obList = FXCollections.observableArrayList();

    public void searchOnAction(ActionEvent event) {
        Student student = studentDAO.search(txtSearch.getText());
        txtFirstName.setText(student.getName().getFirstName());
        txtMiddleName.setText(student.getName().getMiddleName());
        txtLastName.setText(student.getName().getLastName());

        System.out.println(student.getRegisterList()+"list");
        for (Register register : student.getRegisterList()) {
            setUBtn();
            setDBtn();
            obList.add(new RegisterTM(register.getrId(),register.getCourse().getPID(),register.getCourse().getCourseName(),register.getCourse().getDuration(),register.getCourse().getFee(),buttonUpdate,buttonDelete));
            setOnActionForUpdate();
            setOnActionForDelete(register.getrId());
        }
        tblStudents.setItems(obList);
    }

    public int isExists(RegisterTM tm){
        for (int j = 0; j < obList.size(); j++) {
            if (tm.getRId().equals(obList.get(j).getRId())){
                return j;
            }
        }
        return -1;
    }

    public void addToTableOnAction(ActionEvent event) {

        setUBtn();
        setDBtn();
        RegisterTM registerTM = new RegisterTM(lblRID.getText(), cmbCourseIds.getValue(), txtPName.getText(), txtDuration.getText(), Double.parseDouble(txtFee.getText()), buttonUpdate, buttonDelete);
        setOnActionForUpdate();
        setOnActionForDelete(lblRID.getText());

        int exists = isExists(registerTM);

        if (exists == -1){

            tblStudents.getSelectionModel().clearSelection();
            obList.add(registerTM);
        }else {

            RegisterTM tm = obList.get(exists);

            RegisterTM register = new RegisterTM(
                    tm.getRId(),
                    cmbCourseIds.getValue(),
                    txtPName.getText(),
                    txtDuration.getText(),
                    Double.parseDouble(txtFee.getText()),
                    buttonUpdate,
                    buttonDelete
            );
            obList.remove(exists);
            obList.add(register);
        }

        tblStudents.setItems(obList);
    }

    public void updateStudentOnAction(ActionEvent event) {

        String payment = null;
        if (chPayment.isSelected()){
            payment = "Paid";
        }else {
            payment = "Not Paid";
        }

        if (cmbCourseIds.getValue() == null || txtDuration.getText().isEmpty() || txtPName.getText().isEmpty() || txtFee.getText().isEmpty() ||
                txtFirstName.getText().isEmpty() ||txtMiddleName.getText().isEmpty() || txtLastName.getText().isEmpty() || payment.equals("Not Paid")) {
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required.").show();
        } else {
            ObservableList<RegisterTM> items = tblStudents.getItems();

            Course  course = null;
            for (RegisterTM item : items) {
                course = new Course(item.getPID(),item.getCourseName(),item.getDuration(),item.getFee());
            }

            Student student = studentDAO.search(txtSearch.getText());
            Register register = new Register(lblRID.getText(),String.valueOf(dpDate.getValue()),payment,student,course);

            if (registerDAO.update(register)) {
                if (studentDAO.saveRegisterDetails(register, register.getStudentDetails().getsId())) {
                    if (courseDAO.saveRegisterDetails(register,register.getCourse().getPID())){
                        new Alert(Alert.AlertType.CONFIRMATION, "Update Successful.").showAndWait();

                        txtFirstName.clear();
                        txtMiddleName.clear();
                        txtLastName.clear();
                        tblStudents.getItems().clear();
                        txtSearch.clear();
                        lblRID.setText(null);
                        dpDate.setValue(null);
                        chPayment.setSelected(false);
                        cmbCourseIds.getSelectionModel().clearSelection();
                        txtDuration.clear();
                        txtPName.clear();
                        txtFee.clear();
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
        tblStudents.getItems().clear();
        txtSearch.clear();
        lblRID.setText(null);
        dpDate.setValue(null);
        chPayment.setSelected(false);
        cmbCourseIds.getSelectionModel().clearSelection();
        txtDuration.clear();
        txtPName.clear();
        txtFee.clear();
    }

}
