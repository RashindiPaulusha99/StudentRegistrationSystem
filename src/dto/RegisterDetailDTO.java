package dto;

public class RegisterDetailDTO {
    private String RId;
    private String date;
    private String payment;
    private String PId;
    private String name;
    private String duration;
    private double fee;

    public RegisterDetailDTO() {
    }

    public RegisterDetailDTO(String RId, String date, String payment, String PId, String name, String duration, double fee) {
        this.RId = RId;
        this.setDate(date);
        this.setPayment(payment);
        this.PId = PId;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }

    public String getRId() {
        return RId;
    }

    public void setRId(String RId) {
        this.RId = RId;
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

    @Override
    public String toString() {
        return "RegisterDetailDTO{" +
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
