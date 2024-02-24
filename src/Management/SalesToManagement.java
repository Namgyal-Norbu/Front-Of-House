package Management;

public abstract class SalesToManagement {
    private String saleId;
    private double totalAmount;
    private String paymentMethod;
    private LocalDateTime saleTime;

    public SalesToManagement(String saleId, double totalAmount, String paymentMethod, LocalDateTime saleTime) {
        this.saleId = saleId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.saleTime = saleTime;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
    }
}

