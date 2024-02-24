package Management;

public abstract class SalesToManagement {
    private int saleID; 
    private int total; 
    private String paymentMethod;
    private ArrayList<String> dishList; // Added to align with the specifications received.

     public SalesToManagement(int saleID, int total, String method, ArrayList<String> dishList) {
        this.saleID = saleID;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.dishList = dishList;
    }

 public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String method) {
        this.method = paymentMethod;
    }

    public ArrayList<String> getDishList() {
        return dishList;
    }

    public void setDishList(ArrayList<String> dishList) {
        this.dishList = dishList;
    }
}

