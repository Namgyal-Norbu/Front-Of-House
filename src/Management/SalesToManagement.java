package Management;

// abstract class representing sales information sent to management
public abstract class SalesToManagement {
    private int saleID; // unique identifier 
    private double total; // total amount spent (currency)
    private String paymentMethod; // payment method used (card, cash, ..., etc)
    private ArrayList<String> dishList; // List containing the names of dishes sold in that sale
    
    /**
     * Constructor to initialise the SalesToManagement
     * @param saleID 
     * @param total
     * @param paymentMethod
     * @param dishList
     */
     public SalesToManagement(int saleID, int total, String paymentMethod, ArrayList<String> dishList) {
        this.saleID = saleID;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.dishList = dishList;
    }
    
    /**
     * Getter method that returns saleID
     * @return saleID
     */
    public int getSaleID() { return saleID; }

    /**
     * Getter method that returns total
     * @return total
     */
    public int getTotal() { return total;}

    /**
     * Getter method that returns paymentMethod
     * @return paymentMethod
     */
    public String getPaymentMethod() { return paymentMethod; }

    /**
     * Getter method that returns dishList
     * @return dishList
     */
    public ArrayList<String> getDishList() { return dishList; }
}

