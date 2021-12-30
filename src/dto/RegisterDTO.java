package dto;

import entity.Course;
import entity.Student;

public class RegisterDTO {
    private String rId;
    private String date;
    private String payment;
    private Student student;
    private Course course;

    public RegisterDTO() {
    }

    public RegisterDTO(String rId, String date, String payment) {
        this.setrId(rId);
        this.setDate(date);
        this.setPayment(payment);
    }

    public RegisterDTO(String rId, String date, String payment, Student student, Course course) {
        this.rId = rId;
        this.date = date;
        this.payment = payment;
        this.setStudent(student);
        this.setCourse(course);
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "rId='" + rId + '\'' +
                ", date='" + date + '\'' +
                ", payment='" + payment + '\'' +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
