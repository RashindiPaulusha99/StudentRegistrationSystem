package view.tm;

import com.jfoenix.controls.JFXButton;

public class StudentTM {
    private String SId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String DOB;
    private int Age;
    private String Gender;
    private String Address;
    private int PhoneNO;
    private String Email;
    private JFXButton update;
    private JFXButton delete;

    public StudentTM() {
    }

    public StudentTM(String SId, String firstName, String middleName, String lastName, String DOB, int Age, String Gender, String Address, int PhoneNO, String Email) {
        this.setSId(SId);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setDOB(DOB);
        this.setAge(Age);
        this.setGender(Gender);
        this.setAddress(Address);
        this.setPhoneNO(PhoneNO);
        this.setEmail(Email);
    }

    public StudentTM(String SId, String firstName, String middleName, String lastName, String DOB, int Age, String Gender, String Address, int PhoneNO, String Email, JFXButton update, JFXButton delete) {
        this.SId = SId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.Age = Age;
        this.Gender = Gender;
        this.Address = Address;
        this.PhoneNO = PhoneNO;
        this.Email = Email;
        this.setUpdate(update);
        this.setDelete(delete);
    }

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getPhoneNO() {
        return PhoneNO;
    }

    public void setPhoneNO(int PhoneNO) {
        this.PhoneNO = PhoneNO;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public JFXButton getUpdate() {
        return update;
    }

    public void setUpdate(JFXButton update) {
        this.update = update;
    }

    public JFXButton getDelete() {
        return delete;
    }

    public void setDelete(JFXButton delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "SId='" + SId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Age=" + Age +
                ", Gender='" + Gender + '\'' +
                ", Address='" + Address + '\'' +
                ", PhoneNO=" + PhoneNO +
                ", Email='" + Email + '\'' +
                ", update=" + update +
                ", delete=" + delete +
                '}';
    }
}
