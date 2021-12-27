package view.tm;

import javafx.scene.control.CheckBox;

public class CourseDetailsTM {
    private String PID;
    private String courseName;
    private String duration;
    private double fee;
    private CheckBox checkBox;

    public CourseDetailsTM() {
    }

    public CourseDetailsTM(String PID, String courseName, String duration, double fee, CheckBox checkBox) {
        this.setPID(PID);
        this.setCourseName(courseName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setCheckBox(checkBox);
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public String toString() {
        return "CourseDetailsTM{" +
                "PID='" + PID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", checkBox=" + checkBox +
                '}';
    }
}
