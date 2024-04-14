package Kitchen.Management;

import java.util.ArrayList;;

// abstract class representing sales information sent to management
public abstract class SalesToManagement {
    private final int saleID; // unique identifier for sale
    private final Boolean nhsDisc; // checker for NHS discount
    private final Boolean armyDisc; // checker for army discount
    private final Boolean optionalCharge; // checker for optional service charge
    private final double total; // total amount spent (currency)
    private final String paymentMethod; // payment method used (card, cash, ..., etc)
    private final ArrayList<Integer> dishList; // List containing the IDs of dishes sold in that sale
    

     public SalesToManagement(int saleID, boolean nhsDisc, boolean armyDisc, boolean optionalCharge, 
             double total, String paymentMethod, ArrayList<Integer> dishList) {
        this.saleID = saleID;
        this.nhsDisc = nhsDisc;
        this.armyDisc = armyDisc;
        this.optionalCharge = optionalCharge;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.dishList = dishList;
    }

    public int getSaleID() { return saleID; }

    public Boolean getNhsDisc() { return nhsDisc; }

    public Boolean getArmyDisc() { return armyDisc; }

    public Boolean getOptionalCharge() { return optionalCharge; }

    public double getTotal() { return total;}

    public String getPaymentMethod() { return paymentMethod; }

    public ArrayList<Integer> getDishList() { return dishList; }
}
