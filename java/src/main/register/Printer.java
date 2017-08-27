package register;

import java.awt.GridBagLayout;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

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
    private JFrame PrinterWindow;
    private TextArea output;
    /**
     * Class constructor.
     *
     * @param _list The list of ReceiptItem's to initialize with
     */
    Printer (ReceiptItem[] _list) {
        list = _list;

        PrinterWindow = new JFrame();
        PrinterWindow.setTitle("Printer");
        PrinterWindow.setSize(500, 150);
        PrinterWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        PrinterWindow.setLayout(new GridBagLayout());
//        PrinterWindow.setLayout(new BoxLayout(PrinterWindow, BoxLayout.Y_AXIS));

        output = new TextArea("");
        output.setEditable(false);

        PrinterWindow.add(output);

        PrinterWindow.setVisible(true);
    }

    /**
     * Prints the receipt, using the toString of the ReceiptItem class.
     */
    public void print() {
        double total = 0.0;
        StringBuilder stringOutput = new StringBuilder();
        for (ReceiptItem item : list) {
            stringOutput.append(item.toString());
            stringOutput.append("\n");
//            System.out.println(item);
            total += item.getTotal();
        }

        stringOutput.append("____________________________________________\n");
        stringOutput.append(String.format("Subtotal %-10.2f\n", total));
        stringOutput.append(String.format("Tax      %-10.2f\n", (taxRate * total)));
        stringOutput.append(String.format("Total    %-10.2f\n", (total + (taxRate * total))));
        stringOutput.append("____________________________________________");

        output.setText(stringOutput.toString());


    }
}
