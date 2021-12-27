package view.tm;

import javafx.scene.control.ToggleButton;

public class CourseDetailsTM {
    private String PID;
    private String courseName;
    private String duration;
    private double fee;
    private ToggleButton toggleButton;

    public CourseDetailsTM() {
    }

    public CourseDetailsTM(String PID, String courseName, String duration, double fee, ToggleButton toggleButton) {
        this.setPID(PID);
        this.setCourseName(courseName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setToggleButton(toggleButton);
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

    public ToggleButton getToggleButton() {
        return toggleButton;
    }

    public void setToggleButton(ToggleButton toggleButton) {
        this.toggleButton = toggleButton;
    }

    @Override
    public String toString() {
        return "CourseDetailsTM{" +
                "PID='" + PID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", toggleButton=" + toggleButton +
                '}';
    }
}
