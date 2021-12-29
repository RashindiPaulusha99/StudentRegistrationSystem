package view.tm;

import com.jfoenix.controls.JFXButton;

public class RegisterTM {
    private String RId;
    private String PID;
    private String courseName;
    private String duration;
    private double fee;
    private JFXButton update;
    private JFXButton delete;

    public RegisterTM() {
    }

    public RegisterTM(String RId, String PID, String courseName, String duration, double fee, JFXButton update, JFXButton delete) {
        this.setRId(RId);
        this.setPID(PID);
        this.setCourseName(courseName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setUpdate(update);
        this.setDelete(delete);
    }

    public String getRId() {
        return RId;
    }

    public void setRId(String RId) {
        this.RId = RId;
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

    public JFXButton getUpdate() {
        return update;
    }

    public void setUpdate(JFXButton update) {
        this.update = update;
    }

    public JFXButton getDelete() {
        return delete;
    }

    public void setDelete(JFXButton delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "RegisterTM{" +
                "RId='" + RId + '\'' +
                ", PID='" + PID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee='" + fee + '\'' +
                ", update=" + update +
                ", delete=" + delete +
                '}';
    }
}
