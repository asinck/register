package register;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The class for the simulation of the hardware scanner.
 *
 * This provides a substitute implementation of the scanner that cashiers use, either the one built
 * into the counter or the handheld one. This implements the HardwareInterface_Scanner class.
 *
 * @author asinck
 * @version 0.0
 */
class Scanner implements HardwareInterface_Scanner {

    private ItemInput input;

    private JTextField barcodeInput;

    private JButton itemAddButton;

    /**
     * Class constructor.
     *
     * @param listener the action listener
     */
    Scanner(ActionListener listener) {
        /*
        The window
         */
        JFrame ScannerWindow = new JFrame();
        ScannerWindow.setTitle("Scanner");
        ScannerWindow.setSize(500, 150);
        ScannerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ScannerWindow.setLayout(new GridBagLayout());
        /*
        The container and layout
         */
//        JPanel container = new JPanel();
//        container.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        /*
        For barcode handling
         */
        JLabel barcodeLabel = new JLabel("Barcode");
        barcodeInput = new JTextField();
        JButton barcodeSearchButton = new JButton("Lookup");
        barcodeSearchButton.addActionListener(e -> lookup());

        layout.fill      = GridBagConstraints.HORIZONTAL;
        layout.anchor    = GridBagConstraints.FIRST_LINE_START;
        layout.gridx     = 0;
        layout.gridy     = 0;
        ScannerWindow.add(barcodeLabel, layout);

        layout.anchor    = GridBagConstraints.FIRST_LINE_END;
        layout.gridx     = 2;
        ScannerWindow.add(barcodeSearchButton, layout);

        layout.gridx     = 1;
        layout.weightx   = 1.0;
        ScannerWindow.add(barcodeInput, layout);

        /*
        For the manual item input handling
         */
        input = new ItemInput();

        layout.fill      = GridBagConstraints.BOTH;
        layout.anchor    = GridBagConstraints.CENTER;
        layout.gridx     = 0;
        layout.gridy     = 1;
        layout.weighty   = 1.0;
        layout.gridwidth = 3;
        ScannerWindow.add(input.getItemInput(), layout);

        itemAddButton = new JButton("Add");
        itemAddButton.addActionListener(listener);

        layout.gridy     = 2;
        layout.weightx   = 0.0;
        layout.weighty   = 0.0;
        layout.anchor    = GridBagConstraints.LAST_LINE_START;
        ScannerWindow.add(itemAddButton, layout);


        ScannerWindow.setVisible(true);
    }

    /**
     * Return the item in the scanner.
     *
     * @return the currently inputted item, or null if any of the fields are empty.
     */
    public ReceiptItem getItem() {
        return input.getItem();
    }

    /**
     * Clear the input fields.
     */
    public void clear() {
        input.clear();
    }

    /**
     * Look up an item, given a barcode, and put it in the input fields.
     */
    public void lookup() {
        String barcodeInputText = barcodeInput.getText();
        Model model = new Model();
        if (barcodeInputText != null && !barcodeInputText.equals("")) {
            ReceiptItem item = null;

            try {
                int barcode = Integer.parseInt(barcodeInputText);
                item = model.itemLookup(barcode);
            } catch (NumberFormatException ignored) {}

            if (item != null) {
                input.setItem(item);
            }
            else {
                input.clear();
            }

        }
    }

    /**
     * Returns the item add button for the event listener
     *
     * @return the item add button
     */
    public JButton getItemAddButton() {
        return itemAddButton;
    }
}
