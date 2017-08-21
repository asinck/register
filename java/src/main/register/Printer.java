package register;

/**
 * The class for a printer. This class mimics the function of a printer, due to
 * lack of hardware. This may be converted into a Java Interface or something
 * later.
 *
 * @author asinck
 * @version 0.0
 */
class Printer implements HardwareInterface_Printer {
    private ReceiptItem[] list;
    private double taxRate = 0.086;

    /**
     * Class constructor.
     *
     * @param _list The list of ReceiptItem's to initialize with
     */
    Printer (ReceiptItem[] _list) {
        list = _list;
    }

    /**
     * Prints the receipt, using the toString of the ReceiptItem class.
     */
    public void print() {
        double total = 0.0;
        for (ReceiptItem item : list) {
            System.out.println(item);
            total += item.getTotal();
        }
        System.out.println("____________________________________________");
        System.out.printf("Subtotal %-10.2f\n", total);
        System.out.printf("Tax      %-10.2f\n", (taxRate * total));
        System.out.printf("Total    %-10.2f\n", (total + (taxRate * total)));
        System.out.println("____________________________________________");
    }
}
