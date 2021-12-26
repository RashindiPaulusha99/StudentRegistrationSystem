package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    private String PID;
    private String courseName;
    private String duration;
    private double fee;

    public Course() {
    }

    public Course(String PID, String courseName, String duration, double fee) {
        this.setPID(PID);
        this.setCourseName(courseName);
        this.setDuration(duration);
        this.setFee(fee);
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
