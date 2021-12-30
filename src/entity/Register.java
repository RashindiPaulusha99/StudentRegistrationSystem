package entity;

import javax.persistence.*;

@Entity
public class Register {
    @Id
    private String rId;
    private String date;
    private String payment;

    @ManyToOne
    private Student studentDetails;
    @ManyToOne
    private Course course;

    public Register() {
    }

    public Register(String rId, String date, String payment, Student studentDetails, Course course) {
        this.rId = rId;
        this.date = date;
        this.payment = payment;
        this.studentDetails = studentDetails;
        this.course = course;
    }

    public Register(String text, Student student, String text1, String payment) {
    }

    public Register(String text, String text1, String payment) {
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
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

    public Student getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(Student studentDetails) {
        this.studentDetails = studentDetails;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Register{" +
                "rId='" + rId + '\'' +
                ", date='" + date + '\'' +
                ", payment='" + payment + '\'' +
                ", studentDetails=" + studentDetails +
                ", course=" + course +
                '}';
    }
}
