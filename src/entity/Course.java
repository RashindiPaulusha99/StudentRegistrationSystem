package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Course {
    @Id
    private String PID;
    private String courseName;
    private String duration;
    private double fee;
    @ManyToMany(mappedBy = "courseList")
    private List<Register> registerList;

    public Course() {
    }

    public Course(String PID, String courseName, String duration, double fee, List<Register> registerList) {
        this.PID = PID;
        this.courseName = courseName;
        this.duration = duration;
        this.fee = fee;
        this.registerList = registerList;
    }

    public Course(String PID, String courseName, String duration, double fee) {
        this.PID = PID;
        this.courseName = courseName;
        this.duration = duration;
        this.fee = fee;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<Register> registerList) {
        this.registerList = registerList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "PID='" + PID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
