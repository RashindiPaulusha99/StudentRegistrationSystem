package entity;

import embeded.Name;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    private String sId;
    @Embedded
    private Name name;

    private String DOB;
    private int age;
    private String gender;
    private String address;
    private int phoneNO;
    private String email;

    @OneToMany(mappedBy = "studentDetails",fetch = FetchType.EAGER)
    private List<Register> registerList = new ArrayList<>();

    public Student() {
    }

    public Student(String sId, Name name, String DOB, int age, String gender, String address, int phoneNO, String email) {
        this.setsId(sId);
        this.setName(name);
        this.setDOB(DOB);
        this.setAge(age);
        this.setGender(gender);
        this.setAddress(address);
        this.setPhoneNO(phoneNO);
        this.setEmail(email);
    }

    public Student(String sId, Name name, String DOB, int age, String gender, String address, int phoneNO, String email, List<Register> registerList) {
        this.sId = sId;
        this.name = name;
        this.DOB = DOB;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNO = phoneNO;
        this.email = email;
        this.setRegisterList(registerList);
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
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
        return "Student{" +
                "sId='" + sId + '\'' +
                ", name=" + name +
                ", DOB='" + DOB + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNO=" + phoneNO +
                ", email='" + email + '\'' +
                '}';
    }
}
