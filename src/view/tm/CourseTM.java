package view.tm;

import com.jfoenix.controls.JFXButton;

public class CourseTM {
    private String PID;
    private String courseName;
    private String duration;
    private double fee;
    private JFXButton btnUpdate;
    private JFXButton btnDelete;

    public CourseTM() {
    }

    public CourseTM(String PID, String courseName, String duration, double fee, JFXButton btnUpdate, JFXButton btnDelete) {
        this.setPID(PID);
        this.setCourseName(courseName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setBtnUpdate(btnUpdate);
        this.setBtnDelete(btnDelete);
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

    public JFXButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JFXButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JFXButton getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(JFXButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    @Override
    public String toString() {
        return "CourseTM{" +
                "PID='" + PID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", btnUpdate=" + btnUpdate +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
