package register;

/**
 * The class for items on a receipt. Contains basic item information.
 *
 * @author Adam Sinck
 * @version 0.0
 */
class ReceiptItem {
    private String name;
    private int count;
    private double price;
    private boolean priceOverride;

    /**
     * Class constructor.
     *
     * @param _name     The name of the item.
     * @param _count    The number of the item being purchased.
     * @param _price    The price of this item.
     */
    ReceiptItem(String _name, int _count, double _price) {
        name          = _name;
        count         = _count;
        price         = _price;
        priceOverride = false;
    }

    /**
     * The getter function for the item name.
     *
     * @return The item name.
     */
    String getName() {
        return name;
    }

    /**
     * The setter function for the item name.
     *
     * @param newName   The new item name.
     */
    void setName(String newName) {
        name = newName;
    }

    /**
     * The getter function for the count.
     *
     * @return The count.
     */
    int getCount() {
        return count;
    }

    /**
     * The setter function for the count
     *
     * @param newCount   The new count.
     */
    void setCount(int newCount) {
        count = newCount;
    }

    /**
     * The getter function for the price.
     *
     * @return The price per item.
     */
    double getPrice() {
        return price;
    }

    /**
     * The setter function for the price
     *
     * @param newPrice   The new price per item.
     */
    void setPrice(double newPrice) {
        price = newPrice;
        priceOverride = true;
    }


    /**
     * Returns the total cost for this item (price * count)
     *
     * @return the total cost for this item (price * count)
     */
    double getTotal() {
        return price * count;
    }

    /**
     * The toString of the class.
     *
     * @return The String representation of an item.
     */
    public String toString() {
        String returnString = name + ": " + count + " x $" + String.format("%.2f", price);
        if (priceOverride) {
            returnString += "(override)";
        }
        returnString += " = $" + String.format("%.2f", (count * price));
        return returnString;
    }



    /**
     * The .equals() method for this class.
     *
     * http://www.geeksforgeeks.org/overriding-equals-method-in-java/
     *
     * @param obj       the other object to test
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }
        
        // If the object isn't even the right type, return false
        if (!(obj instanceof ReceiptItem)) {
            return false;
        }

        // typecast obj to a ReceiptItem so that we can compare data members
        ReceiptItem ri = (ReceiptItem) obj;

        // Compare the data members and return accordingly
        return name.equals(ri.getName()) &&
                Double.compare(price, ri.getPrice()) == 0;
    }
}
