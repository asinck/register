package register;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * The class for the main display.
 *
 * This is where most of the user interaction will occur. This will
 * contain functionality for the following:
 * * Item attribute viewing / editing (name, price, quantity)
 * * Customer operations such as taking their info for memberships,
 *   subscriptions, and the like
 * * Coupon input
 * * Manager overrides.
 *
 * @author Adam Sinck
 * @version 0.0
 */
class Display {
    /**
     * This is the container for the entire GUI representation of the
     * display.
     */
    private JPanel container;
    private JButton itemUpdateButton, itemAddButton;
    private ItemInput automaticInput, manualInput;
    private JTabbedPane tabbedPane;
    private final int AUTOMATIC_INPUT_TAB = 0;
    private final int MANUAL_INPUT_TAB = 1;
    private final int CUSTOMER_TAB = 2;

    CustomerInfo customerInfo;
    /**
     * Class constructor.
     *
     * @param listener   The action listener.
     */
    Display(ActionListener listener) {

        container = new JPanel();
        tabbedPane = new JTabbedPane();
        //every time a tab is changed, clear the item input fields
        tabbedPane.addChangeListener(e -> clear());

        container.add(tabbedPane);

        //The default tab - automatic input
        JPanel currentItemTab = new JPanel();
        currentItemTab.setLayout(new BoxLayout(currentItemTab,
                                               BoxLayout.Y_AXIS));
        automaticInput = new ItemInput();
        currentItemTab.add(automaticInput.getItemInput());
        itemUpdateButton = new JButton("Update");
        itemUpdateButton.addActionListener(listener);
        currentItemTab.add(itemUpdateButton);

        //the manual input tab
        JPanel manualInputTab = new JPanel();
        manualInputTab.setLayout(new BoxLayout(manualInputTab,
                                               BoxLayout.Y_AXIS));
        manualInput = new ItemInput();
        manualInputTab.add(manualInput.getItemInput());
        itemAddButton = new JButton("Add");
        itemAddButton.addActionListener(listener);
        manualInputTab.add(itemAddButton);

        //the customer info editor tab
        JPanel customerTab = new JPanel();
        customerTab.setLayout(new BoxLayout(customerTab,
                                            BoxLayout.Y_AXIS));
        customerInfo = new CustomerInfo(listener);
        customerTab.add(customerInfo.getCustomerInfo());

        tabbedPane.addTab("Current item", currentItemTab);
        tabbedPane.addTab("Manual input", manualInputTab);
        tabbedPane.addTab("Customer info", customerTab);
    }

    /**
     * The getter function for the item update button.
     *
     * @return The update button
     */
    JButton getItemAddButton() {
        return itemAddButton;
    }

    /**
     * The getter function for the item update button.
     *
     * @return The update button
     */
    JButton getItemUpdateButton() {
        return itemUpdateButton;
    }

    /**
     * Gets the item from the item input fields.
     *
     * @return the item from the item input fields, or null
     *         if it's not in an item input field.
     */
    ReceiptItem getItem() {
        int index = tabbedPane.getSelectedIndex();
        if (index == AUTOMATIC_INPUT_TAB) {
            return automaticInput.getItem();
        }
        else if (index == MANUAL_INPUT_TAB) {
            return manualInput.getItem();
        }
        return null;
    }

    /**
     * Returns the customer info add button for the event listener
     *
     * @return the customer info add button
     */
    JButton getCustomerInfoAddButton() {
        return customerInfo.getAddButton();
    }

    /**
     * Returns the customer info update button for the event listener
     *
     * @return the customer info update button
     */
    JButton getCustomerInfoUpdateButton() {
        return customerInfo.getUpdateButton();
    }

    /**
     * Returns the customer info delete button for the event listener
     *
     * @return the customer info delete button
     */
    JButton getCustomerInfoDeleteButton() {
        return customerInfo.getDeleteButton();
    }

    /**
     * Returns the customer info customer object
     *
     * @return the customer info object from the input fields
     */
    Customer getCustomer() {
        return customerInfo.getCustomer();
    }

    /**
     * Puts an item in the automatic input field. This can serve two purposes:
     * to display the just scanned item, or to allow editing of items.
     *
     * @param item the item to fill the fields with
     */
    void setItem(ReceiptItem item) {
        //switch to the automatic input tab
        tabbedPane.setSelectedIndex(AUTOMATIC_INPUT_TAB);

        automaticInput.setItem(item);
    }
    /**
     * Clears the item input fields.
     */
    void clear() {
        int index = tabbedPane.getSelectedIndex();
        if (index == AUTOMATIC_INPUT_TAB) {
            automaticInput.clear();
        }
        else if (index == MANUAL_INPUT_TAB) {
            manualInput.clear();
        }
    }
    /**
     * The getter function for the containing GUI of this class.
     *
     * @return The container containing the entire GUI of this class.
     */
    JPanel getDisplay() {
        return container;
    }
}
