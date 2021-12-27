package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Register {
    @Id
    private String rId;
    private String date;
    private String Courses;
    private String payment;

    public Register() {
    }

    public Register(String rId, String date, String courses, String payment) {
        this.setrId(rId);
        this.setDate(date);
        setCourses(courses);
        this.setPayment(payment);
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

    public String getCourses() {
        return Courses;
    }

    public void setCourses(String courses) {
        Courses = courses;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Register{" +
                "rId='" + rId + '\'' +
                ", date='" + date + '\'' +
                ", Courses='" + Courses + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}
