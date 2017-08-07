package register;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The class for manual input of an item.
 *
 * Provides a GUI for inputting or editing an item.
 *
 * This implements its own action listener, because the two buttons in
 * question edit a field that this class contains.
 *
 * @author Adam Sinck
 * @version 0.0
 */
class ItemInput {
    /**
     * This is the container for the entire GUI representation of the
     * item input class.
     */
    private JPanel container;
//    private JLabel nameLabel, priceLabel, quantityLabel;
    private JTextField nameInput, priceInput, quantityInput;
    /**
     * The item that the GUI works with and returns.
     */
    private ReceiptItem item;


    /**
     * Class constructor.
     *
     * Takes no arguments, calls the other constructor with a null
     * item as initialization.
     */
    ItemInput() {
        this(null);
    }

    /**
     * Class constructor, accepting an initialization item.
     *
     * Takes an item and prepopulates the fields with it.
     *
     * @param _item     The item to initialize with.
     */
    ItemInput(ReceiptItem _item) {
        item = _item;

        container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        /*
          +---------+-------+
          |  Current Item   |
          +---------+-------+
          |  Name   | [___] |
          +---------+-------+
          |Price ($)| [___] |
          +---------+-------+
          |quantity | [___] |
          +---------+-------+
         */

        JLabel nameLabel         = new JLabel("Name");
        JLabel priceLabel        = new JLabel("Price ($)");
        JLabel quantityLabel     = new JLabel("Quantity");

        nameInput         = new JTextField(10);
        priceInput        = new JTextField(10);
        quantityInput     = new JTextField(10);

        if (item != null) {
            nameInput.setText(item.getName());
            priceInput.setText("" + item.getPrice());
            quantityInput.setText("" + item.getCount());
        }

        layout.fill       = GridBagConstraints.HORIZONTAL;
        layout.anchor     = GridBagConstraints.LINE_START;

        layout.gridx      = 0;
        layout.gridy      = 0;
        layout.weightx    = 1.0;
        layout.weighty    = 1.0;

        container.add(nameLabel, layout);

        layout.gridy = 1;
        container.add(priceLabel, layout);

        layout.gridy = 2;
        container.add(quantityLabel, layout);

        layout.anchor = GridBagConstraints.LINE_END;

        layout.gridx = 1;
        layout.gridy = 0;
        container.add(nameInput, layout);

        layout.gridy = 1;
        container.add(priceInput, layout);

        layout.gridy = 2;
        container.add(quantityInput, layout);

    }

    /**
     * The getter function for the receipt item.
     *
     * This parses all the user input fields and returns that.
     *
     * @return The receipt item.
     */
    ReceiptItem getItem() {
        String parsedName     = nameInput.getText();
        String parsedPrice    = priceInput.getText();
        String parsedQuantity = quantityInput.getText();

        //if any of the fields are empty, then stop
        if (parsedName.equals("") ||
            parsedPrice.equals("") ||
            parsedQuantity.equals("")) {
            return null;
        }

        try {
            double price  = Double.parseDouble(parsedPrice.trim());
            int quantity  = Integer.parseInt(parsedQuantity.trim());
            item          = new ReceiptItem(parsedName, quantity, price);
            return item;
        }
        catch (NumberFormatException e) {
            return null;
        }

    }
    /**
     * The setter function for the receipt item.
     *
     * This sets the item to the provided one.
     *
     * @param newItem   The new item
     */
    void setItem(ReceiptItem newItem) {
        if (newItem != null) {
            nameInput.setText(newItem.getName());
            priceInput.setText("" + newItem.getPrice());
            quantityInput.setText("" + newItem.getCount());
        }
        item = newItem;
    }

    /**
     * Clears the input fields.
     */
    void clear() {
        nameInput.setText("");
        priceInput.setText("");
        quantityInput.setText("");
    }

    /**
     * The getter function for the containing GUI of this class.
     *
     * @return The container containing the entire GUI of this class.
     */
    JPanel getItemInput() {
        return container;
    }
}
