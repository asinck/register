package register;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The receipt class for the GUI.
 *
 * Provides a visual display of the receipt, with edit functionality.
 *
 * @author Adam Sinck
 * @version 0.0
 */
class Receipt implements ListSelectionListener {
    /**
     * This is the container for the entire GUI representation of the
     * receipt.
     */
    private JPanel container;
    /**
     * The receipt list.
     */
    private JList<ReceiptItem> receipt;
    private DefaultListModel<ReceiptItem> receiptList;
    private double total;

    /**
     * The function buttons.
     */
    private JButton printButton, emailButton, removeButton;

    /**
     * The information bar. Mostly for displaying the total.
     */
    private JLabel infoBar;

    /**
     * Class constructor.
     *
     * @param listener   The action listener.
     */
    Receipt(ActionListener listener) {
        total = 0.0;
        container = new JPanel();
        //TODO: change the layout to something better.
        container.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        receiptList = new DefaultListModel<>();
        receipt = new JList<>(receiptList);

        // This will eventually have custom cells.
        // receipt.setCellRenderer(new ReceiptItemRenderer());
        receipt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        receipt.setLayoutOrientation(JList.VERTICAL);
        receipt.addListSelectionListener(this);
        receipt.setVisibleRowCount(5);
//        JScrollPane listScrollPane = new JScrollPane(receipt);

        // //This is an example item. This should be removed once any
        // //implementation is in place. Also, this should use this
        // //class's function for adding the item.
        // receiptList.addElement(new ReceiptItem("Carrots", 2, 1.50));
        /*
          This is in a 3x4 grid; the receipt display is 3x2 units.
        +----------------------+
        | Carrots: 4 $2=$8     |
        | Peanuts: 12*$4=$48   |
        |                      |
        |                      |
        |                      |
        |                      |
        |                      |
        |                      |
        |                      |
        |                      |
        +----------------------+
        |[print][email][remove]|
        +----------------------+
        | Total: $56.00        |
        +----------------------+

        */
        layout.fill       = GridBagConstraints.BOTH;
        layout.anchor     = GridBagConstraints.PAGE_START;
        layout.gridx      = 0;
        layout.gridy      = 0;
        layout.weightx    = 1.0;
        layout.weighty    = 1.0;
        layout.gridwidth  = 3;
        layout.gridheight = 2;
        container.add(receipt, layout);

        layout.gridwidth  = 1;
        layout.gridheight = 1;
        layout.weighty    = 0.0;

        printButton       = new JButton("Print");
        printButton.addActionListener(listener);
        layout.gridx      = 0;
        layout.gridy      = 2;
        container.add(printButton, layout);

        emailButton       = new JButton("Email");
        emailButton.addActionListener(listener);
        layout.gridx      = 1;
        layout.gridy      = 2;
        container.add(emailButton, layout);

        removeButton      = new JButton("Remove");
        removeButton.addActionListener(listener);
        layout.gridx      = 2;
        layout.gridy      = 2;
        container.add(removeButton, layout);


        infoBar = new JLabel();
        updateInfoBar();

        layout.fill       = GridBagConstraints.HORIZONTAL;
        layout.anchor     = GridBagConstraints.PAGE_END;
        layout.gridx      = 0;
        layout.gridy      = 3;
        layout.gridwidth  = 3;
        container.add(infoBar, layout);
    }

    /**
     * The getter function for the containing GUI of this class.
     *
     * @return The container containing the entire GUI of this class.
     */
    JPanel getReceiptPanel() {
        return container;
    }


    /**
     * Adds an item to the list.
     *
     * @param item      The item to be added to the list
     */
    void addItem(ReceiptItem item) {
        if (item != null) {
            receipt.clearSelection();
            receiptList.addElement(item);
            total += item.getTotal();
            updateInfoBar();
        }
    }

    /**
     * Updates an item in the list.
     *
     * @param newItem      The item to update the current selection to
     */
    void updateItem(ReceiptItem newItem) {
        if (newItem != null) {
            int index = receipt.getSelectedIndex();
            if (index != -1) {
                ReceiptItem item = receiptList.getElementAt(index);
                total -= item.getTotal();
                total += newItem.getTotal();
                receiptList.set(index, newItem);
            }
            updateInfoBar();
        }
    }

    /**
     * Removes an item from the list.
     *
     * No parameters; this checks what item is selected.
     */
    void removeItem() {
        //A better implementation might be to strikethrough the item
        int index = receipt.getSelectedIndex();
        if (index != -1) {
            ReceiptItem item = receiptList.getElementAt(index);
            total -= item.getTotal();
            receiptList.remove(index);
        }
        updateInfoBar();
    }

    /**
     * Clear the entire receipt list.
     */
    void clear() {
        receiptList.removeAllElements();
        total = 0.0;
        updateInfoBar();
    }

    /**
     * Sets the selection in the receipt display.
     *
     * @param index     the index to set the selection to
     */
    void setSelection(int index) {
        receipt.setSelectedIndex(index);
    }

    /**
     * Gets the index of the currently selected item in the receipt
     * list.
     *
     * @return the index of the currently selected item in the receipt
     * list
     */
    int getSelection() {
        return receipt.getSelectedIndex();
    }

    /**
     * The getter function for the subtotal
     *
     * Tax? Never heard of it.
     *
     * @return The subtotal
     */
    double getTotal() {
        return total;
    }


    /**
     * The getter function for the currently selected item.
     *
     * @return The currently selected item, or null if no item is
     *         selected.
     */
    ReceiptItem getItem() {
        int index = receipt.getSelectedIndex();
        if (index != -1) {
            return receiptList.get(index);
        }
        return null;
    }

    /**
     * This updates the information bar with the current total.
     */
    private void updateInfoBar() {
        infoBar.setText("Total: $" + String.format("%.2f", total));
    }
    /**
     * The getter function for the print button. Useful for
     * identifying what button was pressed.
     *
     * @return The print button.
     */
    JButton getPrintButton() {
        return printButton;
    }

    /**
     * The getter function for the email button. Useful for
     * identifying what button was pressed.
     *
     * @return The email button.
     */
    JButton getEmailButton() {
        return emailButton;
    }

    /**
     * The getter function for the remove button. Useful for
     * identifying what button was pressed.
     *
     * @return The remove button.
     */
    JButton getRemoveButton() {
        return removeButton;
    }

    /**
     * Prints the receipt.
     */
    void print() {
        ArrayList<ReceiptItem> ri = new ArrayList<>();
        for (int i = 0; i < receiptList.getSize(); i++) {
            ri.add(receiptList.get(i));
        }
        Printer printer = new Printer(ri.toArray(new ReceiptItem[0]));
        printer.print();
    }
    /**
     * The action listener for the user clicking on an item in the
     * receipt item list.
     *
     * @param e         The list selection event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

        // if (e.getValueIsAdjusting() == false) {

        //     if (list.getSelectedIndex() == -1) {
        //         //No selection, disable fire button.
        //         fireButton.setEnabled(false);

        //     }
        //     else {
        //         //Selection, enable the fire button.
        //         fireButton.setEnabled(true);
        //     }
        // }
    }

}
