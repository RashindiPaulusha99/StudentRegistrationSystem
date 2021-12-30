package dto;

import entity.Register;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO {
    private String sId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String DOB;
    private int age;
    private String gender;
    private String address;
    private int phoneNO;
    private String email;
    private List<Register> registerList = new ArrayList<>();

    public StudentDTO() {
    }

    public StudentDTO(String sId, String firstName, String middleName, String lastName, String DOB, int age, String gender, String address, int phoneNO, String email) {
        this.setsId(sId);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.setDOB(DOB);
        this.setAge(age);
        this.setGender(gender);
        this.setAddress(address);
        this.setPhoneNO(phoneNO);
        this.setEmail(email);
    }

    public StudentDTO(String sId, String firstName, String middleName, String lastName, String DOB, int age, String gender, String address, int phoneNO, String email, List<Register> registerList) {
        this.sId = sId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNO = phoneNO;
        this.email = email;
        this.registerList = registerList;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
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
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(int phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<Register> registerList) {
        this.registerList = registerList;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "sId='" + sId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DOB='" + DOB + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNO=" + phoneNO +
                ", email='" + email + '\'' +
                '}';
    }
}
