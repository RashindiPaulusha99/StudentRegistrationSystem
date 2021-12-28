package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Register {
    @Id
    private String rId;
    @OneToOne
    private Student student;
    private String date;
    private String payment;
    @ManyToMany
    private List<Course> courseList;

    public Register() {
    }

    public Register(String rId, Student student, String date, String payment, List<Course> courseList) {
        this.setrId(rId);
        this.setStudent(student);
        this.setDate(date);
        this.setPayment(payment);
        this.setCourseList(courseList);
    }

    public Register(String text, Student student, String text1, String payment) {
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Register{" +
                "rId='" + rId + '\'' +
                ", student=" + student +
                ", date='" + date + '\'' +
                ", payment=" + payment +
                /*", courseList=" + courseList +*/
                '}';
    }
}
