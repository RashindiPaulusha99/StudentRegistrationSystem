package view.tm;

public class CourseDetailTM {
    private String RId;
    private String date;
    private String payment;
    private String PId;
    private String name;
    private String duration;
    private double fee;

    public CourseDetailTM() {
    }

    public CourseDetailTM(String RId, String date, String payment, String PId, String name, String duration, double fee) {
        this.setRId(RId);
        this.setDate(date);
        this.setPayment(payment);
        this.setPId(PId);
        this.setName(name);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public String getRId() {
        return RId;
    }

    public void setRId(String RId) {
        this.RId = RId;
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

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "CourseDetailTM{" +
                "RId='" + RId + '\'' +
                ", date='" + date + '\'' +
                ", payment='" + payment + '\'' +
                ", PId='" + PId + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
