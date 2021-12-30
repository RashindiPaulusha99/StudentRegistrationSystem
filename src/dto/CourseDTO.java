package dto;

import entity.Register;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private String PID;
    private String courseName;
    private String duration;
    private double fee;
    private List<Register> registerList = new ArrayList<>();

    public CourseDTO() {
    }

    public CourseDTO(String PID, String courseName, String duration, double fee) {
        this.setPID(PID);
        this.setCourseName(courseName);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public CourseDTO(String PID, String courseName, String duration, double fee, List<Register> registerList) {
        this.PID = PID;
        this.courseName = courseName;
        this.duration = duration;
        this.fee = fee;
        this.setRegisterList(registerList);
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
        return "CourseDTO{" +
                "PID='" + PID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
