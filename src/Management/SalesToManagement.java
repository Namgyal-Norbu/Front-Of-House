package Management;

import java.util.ArrayList;;

// abstract class representing sales information sent to management
public abstract class SalesToManagement {
    private int saleID; // unique identifier for sale
    private Boolean nhsDisc; // checker for NHS discount
    private Boolean armyDisc; // checker for army discount
    private Boolean optionalCharge; // checker for optional service charge
    private double total; // total amount spent (currency)
    private String paymentMethod; // payment method used (card, cash, ..., etc)
    private ArrayList<Integer> dishList; // List containing the IDs of dishes sold in that sale
    
    /**
     * Constructor to initialise the SalesToManagement
     * @param saleID
     * @param nhsDisc
     * @param armyDisc
     * @param optionalCharge
     * @param total
     * @param paymentMethod
     * @param dishList
     * @apiNote: Specification document detailed integer for 'total' but double is used since this is money
     */
     public SalesToManagement(int saleID, boolean nhsDisc, boolean armyDisc, boolean optionalCharge, 
             int total, String paymentMethod, ArrayList<Integer> dishList) {
        this.saleID = saleID;
        this.nhsDisc = nhsDisc;
        this.armyDisc = armyDisc;
        this.optionalCharge = optionalCharge;
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
     * Getter method that returns nhsDisc
     * @return nhsDisc
     */
    public Boolean getNhsDisc() { return nhsDisc; }

    /**
     * Getter method that returns armyDisc
     * @return armyDisc
     */
    public Boolean getArmyDisc() { return armyDisc; }

    /**
     * Getter method that returns optionalCharge
     * @return optionalCharge
     */
    public Boolean getOptionalCharge() { return optionalCharge; }

    /**
     * Getter method that returns total
     * @return total
     */
    public double getTotal() { return total;}

    /**
     * Getter method that returns paymentMethod
     * @return paymentMethod
     */
    public String getPaymentMethod() { return paymentMethod; }

    /**
     * Getter method that returns dishList
     * @return dishList
     */
    public ArrayList<Integer> getDishList() { return dishList; }
}

