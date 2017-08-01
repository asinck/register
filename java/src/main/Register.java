/*
  The purpose of this program is to demonstrate a software interaction
  system for use at cash registers.
 */


package register;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;


/**********************************************************************
 ** View
 **********************************************************************/

/**
 * This class runs the program. It instantiates a single instance of
 * the register GUI and runs that.
 * @author Adam Sinck
 * @version 0.0
 */
public class Register {
   // /**
   //  * Class constructor.
   //  * 
   //  */ 
   //  public Register() {
   //      RegisterGUI display = new RegisterGUI();
   //  }


   /**
    * Main method. This is your typical public static void main.
    * 
    * @param args  Any input arguments.
    */     
    public static void main(String[] args) {
        RegisterGUI display = new RegisterGUI();
        // Register register = new Register();
    }
}



/**********************************************************************
 ** View / Controller
 **********************************************************************/

/**
 * The Graphical interface for the program. This class defines the
 * view and contains the controller, making it both the View and
 * Controller of the MVC architecture. 
 * 
 * @author Adam Sinck
 * @version 0.0
 */

class RegisterGUI extends JFrame implements ActionListener {
    private JFrame myWindow;
    private ApplicationMenu applicationMenu;
    private Header header;
    private Receipt receipt;
    private Display display;
    private Footer footer;

    /**
     * The class constructor.
     * 
     * This arranges all the components of the GUI inside it. 
     */
    public RegisterGUI() {
        //This is the top level window.
        myWindow = new JFrame();
        myWindow.setTitle("Register");
        myWindow.setSize(1200,500);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myWindow.setLayout(new GridBagLayout());
        //specify how to lay stuff out
        GridBagConstraints layout = new GridBagConstraints();

        //Create the menu bar
        applicationMenu = new ApplicationMenu(this);
        myWindow.setJMenuBar(applicationMenu.getMenuBar());

        //fill the top horizontally
        header = new Header(this, "asinck");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.anchor = GridBagConstraints.FIRST_LINE_START;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 3;
        layout.weightx = 1.0;
        myWindow.add(header.getHeader(), layout);

        //make it fill the the left side up to the header and footer
        receipt = new Receipt(this);
        layout.fill = GridBagConstraints.BOTH;
        layout.anchor = GridBagConstraints.LINE_START;
        layout.gridx = 0;
        layout.gridy = 1;
        layout.weighty = 1.0;
        layout.gridwidth = 1;
        myWindow.add(receipt.getReceiptPanel(), layout);

        //For the main display, to the right of the receipt display.
        display = new Display(this);
        layout.fill = GridBagConstraints.BOTH;
        layout.anchor = GridBagConstraints.CENTER;
        layout.gridx = 1;
        layout.gridy = 1;
        layout.weightx = 1.0;
        layout.weighty = 1.0;
        layout.gridwidth = 2;
        myWindow.add(display.getDisplay(), layout);

        //have a footer for status and stuff
        footer = new Footer(this);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.anchor = GridBagConstraints.LAST_LINE_START;
        layout.gridx = 0;
        layout.gridy = 2;
        layout.weightx = 1.0;
        layout.weighty = 0.0;
        layout.gridwidth = 3;
        myWindow.add(footer.getFooter(), layout);


        //This is for the top of the GUI - the application menus and
        //the headers. 
        // topBar = new JPanel(new GridLayout());
        // myWindow.add(topBar);

        // receiptPanel = createReceiptModule();
        // myWindow.add(receiptPanel);

        //Turns out we want to see the window we just created. Who knew?
        myWindow.setVisible(true);
    }

    

    /**
     * The action listener. This is the Controller of the MVC
     * architecture. 
     * 
     * @param event   The event that was performed. 
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        //command gets the text of the button
        String command = event.getActionCommand();
        // System.out.println(event.getSource());
        // System.out.println(footer.printButton);
        // System.out.println(event.getSource() == footer.printButton);
        //The following line would be much better
        // if(evt.getSource()==jButtonClear) {
        if (event.getSource() == receipt.getPrintButton()) {
            footer.setStatus("Sending to printer...");
        }
        else if (event.getSource() == receipt.getEmailButton()) {
            footer.setStatus("Emailing virtual receipt...");
        }
        else if (event.getSource() == receipt.getRemoveButton()) {
            receipt.removeItem();
        }
        else if (event.getSource() == footer.getCancelButton()) {
            receipt.clear();
        }
        else {
            footer.setStatus("Received command: " + command);
        }
        
    }
}

/**
 * The Application Menu class. This class provides the basic
 * application menu functionality that a user would expect from a
 * program, as applicable and able. 
 * 
 * @author Adam Sinck
 * @version 0.0
 */

class ApplicationMenu {
    
    /**
     * The main menu bar. This contains the entire menu. 
     */
    private JMenuBar menuBar;
    
    /**
     * The root of the file menu
     */
    private JMenu file;
    private JMenuItem file_quit;
    
    /**
     * The root of the edit menu
     */
    private JMenu edit;
    private JMenuItem edit_undo, edit_redo;

    /**
     * The root of the preferences menu
     */
    private JMenu preferences;
    private JMenuItem preferences_options;

    private JMenu preferences_colorTheme;
    private JMenuItem preferences_colorTheme_light,
        preferences_colorTheme_dark;
    
        
    /**
     * Class constructor.
     * 
     * @param listener   The action listener.
     */
    public ApplicationMenu(ActionListener listener) {
        /*
          The following is a tree representation of the menu.

           File
             \- Quit
           Edit
             \- Undo
             \- Redo
           Preferences
             \- Color theme
                \- Light
                \- Dark
             \- Options
         */
        
        menuBar = new JMenuBar();

        /* Indentation is a little odd on purpose. It mirrors the
         * structure of the comment above. 
         */
        file = new JMenu("File");
          file_quit = new JMenuItem("Quit");
          file.add(file_quit);


        edit = new JMenu("Edit");
          edit_undo = new JMenuItem("Undo");
          edit.add(edit_undo);
          edit_redo = new JMenuItem("Redo");
          edit.add(edit_redo);
          
        preferences = new JMenu("Preferences");
          preferences_colorTheme = new JMenu("Color Theme");
          preferences_colorTheme_light = new JMenuItem("Light");
          preferences_colorTheme.add(preferences_colorTheme_light);
          preferences_colorTheme_dark = new JMenuItem("Dark");
          preferences_colorTheme.add(preferences_colorTheme_dark);
          preferences.add(preferences_colorTheme);
          preferences.addSeparator();
          preferences_options = new JMenuItem("Options");
          preferences.add(preferences_options);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(preferences);
    }

        
    /**
     * The getter function for the containing GUI of this class.
     * 
     * @return The menu bar containing the entire GUI of this class.
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }
    
}


/**
 * The header class for the GUI. 
 * 
 * Provides a basic header for the user interface, allowing a greeting
 * message. Future versions may provide more useful functionality, but
 * for now this may serve as an indicator of who's logged in to the
 * program (standard user, manager, etc).
 * 
 * @author Adam Sinck
 * @version 0.0
 */
class Header {
    
    /**
     * The container for the entire GUI representation of the
     * header. 
     */
    private JPanel container;
    private JLabel greeting;
        
    /**
     * Class constructor.
     * 
     * Calls the other constructor, providing no name to put in the
     * header. 
     * 
     * @param listener   The action listener.
     */
    public Header(ActionListener listener) {
        this(listener, "");
    }
            
    /**
     * Class constructor.
     * 
     * Initializes the header with a greeting, possibly with a name. 
     * 
     * @param listener   The action listener.
     * @param name       The name to be displayed. If it is empty, it
     *                   will omit it.  
     */
    public Header(ActionListener listener, String name) {
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        if (name.equals("")) {
            greeting = new JLabel("Welcome");
        }
        else {
            greeting = new JLabel("Welcome, " + name);
        }
        container.add(greeting);
    }
            
    /**
     * The getter function for the containing GUI of this class.
     * 
     * @return The container with the entire GUI of this class.
     */
    public JPanel getHeader() {
        return container;
    }


    /**
     * The getter function for the header text.
     * 
     * @return The header text.
     */
    public String getHeaderText() {
        return greeting.getText();
    }
    /**
     * Sets a new string in the header.
     * 
     * @param newContents   The new message to be displayed in the
     *                      header. 
     */
    public void setHeader(String newContents) {
        greeting.setText(newContents);
    }
}

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
    private JLabel infoBar;
    private double total;
    
    /**
     * The function buttons.
     */
    public JButton printButton, emailButton, removeButton;

    /**
     * Class constructor.
     * 
     * @param listener   The action listener.
     */
    public Receipt(ActionListener listener) {
        total = 0.0;
        container = new JPanel();
        //TODO: change the layout to something better.
        container.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        receiptList = new DefaultListModel<ReceiptItem>();
        receipt = new JList<ReceiptItem>(receiptList);

        // This will eventually have custom cells.
        // receipt.setCellRenderer(new ReceiptItemRenderer());
        receipt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        receipt.setLayoutOrientation(JList.VERTICAL);
        receipt.addListSelectionListener(this);
        receipt.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(receipt);

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
        layout.fill = GridBagConstraints.BOTH;
        layout.anchor = GridBagConstraints.PAGE_START;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.weightx = 1.0;
        layout.weighty = 1.0;
        layout.gridwidth = 3;
        layout.gridheight = 2;
        container.add(receipt, layout);
        
        layout.gridwidth = 1;
        layout.gridheight = 1;
        layout.weighty = 0.0;
        
        printButton = new JButton("Print");
        printButton.addActionListener(listener);
        layout.gridx = 0;
        layout.gridy = 2;
        container.add(printButton, layout);
        
        emailButton = new JButton("Email");
        emailButton.addActionListener(listener);
        layout.gridx = 1;
        layout.gridy = 2;
        container.add(emailButton, layout);
        
        removeButton = new JButton("Remove");
        removeButton.addActionListener(listener);
        layout.gridx = 2;
        layout.gridy = 2;
        container.add(removeButton, layout);


        infoBar = new JLabel("Total: $" + String.format("%.2f", total));
        
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.anchor = GridBagConstraints.PAGE_END;
        layout.gridx = 0;
        layout.gridy = 3;
        layout.gridwidth = 3;
        container.add(infoBar, layout);
    }
    
    /**
     * The getter function for the containing GUI of this class.
     * 
     * @return The container containing the entire GUI of this class.
     */
    public JPanel getReceiptPanel() {
        return container;
    }


    /**
     * Adds an item to the list.
     *
     * @param item      The item to be added to the list
     */
    public void addItem(ReceiptItem item) {
        receipt.clearSelection();
        DefaultListModel<ReceiptItem> items = (DefaultListModel<ReceiptItem>) receipt.getModel();
        items.addElement(item);
        total += item.getTotal();
    }

    /**
     * Updates an item in the list.
     * 
     * @param item      The item to update the current selection to
     */
    public void updateItem(ReceiptItem newItem) {
        DefaultListModel<ReceiptItem> items = (DefaultListModel<ReceiptItem>) receipt.getModel();
        int index = receipt.getSelectedIndex();
        if (index != -1) {
            ReceiptItem item = (ReceiptItem) items.getElementAt(index);
            total -= item.getTotal();
            total += newItem.getTotal();
            items.set(index, newItem);
        }
    }    
    
    /**
     * Removes an item from the list. 
     * 
     * No parameters; this checks what item is selected. 
     */
    public void removeItem() {
        //A better implementation might be to strikethrough the item
        DefaultListModel items = (DefaultListModel) receipt.getModel();
        int index = receipt.getSelectedIndex();
        if (index != -1) {
            ReceiptItem item = (ReceiptItem) items.getElementAt(index);
            total -= item.getTotal();
            items.remove(index);
        }
    }

    /**
     * Clear the entire receipt list.
     */
    public void clear() {
        DefaultListModel items = (DefaultListModel) receipt.getModel();
        items.removeAllElements();
        total = 0.0;
    }
            
    /**
     * Sets the selection in the receipt display.
     * 
     * @param index     the index to set the selection to
     */
    public void setSelection(int index) {
        receipt.setSelectedIndex(index);
    }
    
    /**
     * Gets the index of the currently selected item in the receipt
     * list. 
     * 
     * @return the index of the currently selected item in the receipt
     * list 
     */
    public int getSelection() {
        return receipt.getSelectedIndex();
    }

    /**
     * The getter function for the subtotal
     * 
     * Tax? Never heard of it.
     * 
     * @return The subtotal
     */
    public double getTotal() {
        return total;
    }


    /**
     * The getter function for the currently selected item.
     * 
     * @return The currently selected item, or null if no item is
     *         selected. 
     */
    public ReceiptItem getItem() {
        DefaultListModel items = (DefaultListModel) receipt.getModel();
        int index = receipt.getSelectedIndex();
        if (index != -1) {
            return (ReceiptItem) items.get(index);
        }
        return null;
    }
    
    /**
     * The getter function for the print button. Useful for
     * identifying what button was pressed.
     * 
     * @return The print button.
     */
    public JButton getPrintButton() {
        return printButton;
    }

    /**
     * The getter function for the email button. Useful for
     * identifying what button was pressed.
     * 
     * @return The email button.
     */    
    public JButton getEmailButton() {
        return emailButton;
    }
    
    /**
     * The getter function for the remove button. Useful for
     * identifying what button was pressed.
     * 
     * @return The remove button.
     */
    public JButton getRemoveButton() {
        return removeButton;
    }
    
    /**
     * The action listener for the user clicking on an item in the
     * receipt item list.
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
    private CustomerInfo ci;
    
    /**
     * Class constructor.
     * 
     * @param listener   The action listener.
     */
    public Display(ActionListener listener) {
        container = new JPanel();
        ci = new CustomerInfo(listener);
        container.add(ci.getCustomerInfo());
    }

    /**
     * The getter function for the containing GUI of this class.
     * 
     * @return The container containing the entire GUI of this class.
     */
    public JPanel getDisplay() {
        return container;
    }
}

/**
 * The footer class for the GUI. 
 * 
 * Provides a basic footer for the user interface. This also contains
 * function buttons - Print receipt, email virtual receipt, remove
 * item from receipt, and cancel transaction. 
 * 
 * @author Adam Sinck
 * @version 0.0
 */
class Footer {
    /**
     * This is the container for the entire GUI representation of the
     * footer class. 
     */
    private JPanel container;
    /**
     * The status message to be displayed
     */
    private JLabel status;
    
    //These buttons might be better placed somewhere else
    
    public JButton cancelButton;
    
    /**
     * Class constructor.
     * 
     * Calls the other constructor, telling it that the initial
     * message is "ready". 
     * 
     * @param listener   The action listener.
     */
    public Footer(ActionListener listener) {
        this(listener, "Ready");
    }
    
    /**
     * Class constructor specifying the status message to be
     * displayed. 
     * 
     * @param listener   The action listener.
     * @param message    The status message to be displayed. 
     */
    public Footer(ActionListener listener, String message) {
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(listener);
        container.add(cancelButton);

        status = new JLabel(message);
        status.setBorder(new EmptyBorder(0,10,0,10));
        container.add(status);
    }

    
    /**
     * The setter function for the status message.
     * 
     * @param newStatus   The new status message.
     */
    public void setStatus(String newStatus) {
        status.setText(newStatus);
    }

    /**
     * The getter function for the status message. 
     * 
     * @return The status message.
     */
    public String getStatus() {
        return status.getText();
    }


    /**
     * The getter function for the cancel button. Useful for
     * identifying what button was pressed.
     * 
     * @return The cancel button.
     */
    public JButton getCancelButton() {
        return cancelButton;
    }
    
    /**
     * The getter function for the containing GUI of this class.
     * 
     * @return The container containing the entire GUI of this class.
     */
    public JPanel getFooter() {
        return container;
    }
}


// /**
//  * The class for custom rendering of receipt items. 
//  * 
//  * @author Adam Sinck
//  * @version 0.0
//  */
// class ReceiptItemRenderer extends JCheckBox implements ListCellRenderer<ReceiptItem> {

//     // private ReceiptItem receiptItem;
    
//     public ReceiptItemRenderer() {
//         // receiptItem = new ReceiptItem("Pickles", 50, 1.75);
//     }

//     public Component getListCellRendererComponent(JList<? extends ReceiptItem> list,
//                                                   ReceiptItem value,
//                                                   int index,
//                                                   boolean isSelected,
//                                                   boolean cellHasFocus) {
//         // ReceiptItem item = (ReceiptItem) value;
//         setEnabled(list.isEnabled());
//         // setSelected(value.isSelected());
//         setFont(list.getFont());
//         setBackground(list.getBackground());
//         setForeground(list.getForeground());
//         setText(value.toString());
//         return this;
//     }
// }


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
    public ReceiptItem(String _name, int _count, double _price) {
        name = _name;
        count = _count;
        price = _price;
        priceOverride = false;
    }

    /**
     * The getter function for the item name. 
     * 
     * @return The item name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * The setter function for the item name. 
     * 
     * @param newName   The new item name.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * The getter function for the count.
     * 
     * @return The count.
     */
    public int getCount() {
        return count;
    }
    
    /**
     * The setter function for the count
     * 
     * @param newCount   The new count.
     */
    public void setCount(int newCount) {
        count = newCount;
    }
    
    /**
     * The getter function for the price.
     * 
     * @return The price per item.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * The setter function for the price
     * 
     * @param newPrice   The new price per item.
     */
    public void setPrice(double newPrice) {
        price = newPrice;
        priceOverride = true;
    }


    /**
     * Returns the total cost for this item (price * count)
     *
     * @return the total cost for this item (price * count)
     */
    public double getTotal() {
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
}


/**
 * The class for manual input of an item.
 *
 * Provides a GUI for inputting or editing an item.
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
    private JLabel header, itemName, priceLabel, quantityLabel;
    private JButton plusButton, minusButton;
    private JTextField nameOverrideInput, priceOverrideInput, quantityOverrideInput;
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
    public ItemInput() {
        this(null);
    }
    
    /**
     * Class constructor, accepting an initialization item. 
     * 
     * Takes an item and prepopulates the fields with it. 
     * 
     * @param _item     The item to initialize with.
     */
    public ItemInput(ReceiptItem _item) {
        item = _item;
    }

    /**
     * The getter function for the receipt item.
     * 
     * This parses all the user input fields and returns that. 
     * 
     * @return The receipt item.
     */
    public ReceiptItem getItem() {
        return item;
    }
    /**
     * The setter function for the receipt item.
     * 
     * This sets the item to the provided one. 
     * 
     * @param newItem   The new item
     */
    public void setItem(ReceiptItem newItem) {
        item = newItem;
    }

    
    /**
     * The getter function for the containing GUI of this class.
     * 
     * @return The container containing the entire GUI of this class.
     */
    public JPanel getItemInput() {
        return container;
    }
}



/**
 * The customer info GUI for the main GUI. 
 * 
 * Provides an interface for filling in all the customer's
 * information. 
 * 
 * @author Adam Sinck
 * @version 0.0
 */
class CustomerInfo {    
    /**
     * This is the container for the entire GUI representation of the
     * customer input class.
     */
    private JPanel container;
    private JLabel header, addressLabel, phoneNumberLabel,
        emailLabel, memberLabel, subscribeLabel;
    public JButton subscribeYesButton, subscribeNoButton, 
        memberYesButton, memberNoButton, addButton,
        updateButton, deleteButton;
    //TODO: make "state" a dropdown
    private JTextField addressL1Field, addressL2Field, cityField,
        stateField, emailField;
    private JFormattedTextField zipCodeField, phoneNumberField;
    
    /**
     * Class constructor.
     * 
     * @param listener   The action listener.
     */
    public CustomerInfo(ActionListener listener) {
        container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints layout;
        
        final int longFieldLength = 75;
        final int medFieldLength = 50;
        final int shortFieldLength = 25;
        final int tinyFieldLength = 10;

        /*
          First, make all the components
         */
        header             = new JLabel("Customer Info");
        addressLabel       = new JLabel("Address");
        phoneNumberLabel   = new JLabel("Phone Number");
        emailLabel         = new JLabel("Email Address");
        memberLabel        = new JLabel("Member?");
        subscribeLabel     = new JLabel("Subscribe?");

        subscribeYesButton = new JButton("Yes");
        subscribeNoButton  = new JButton("No");
        memberYesButton    = new JButton("Yes");
        memberNoButton     = new JButton("No");
        addButton          = new JButton("Add");
        updateButton       = new JButton("Update");
        deleteButton       = new JButton("Delete");

        subscribeYesButton.addActionListener(listener);
        subscribeNoButton.addActionListener(listener);
        memberYesButton.addActionListener(listener);
        memberNoButton.addActionListener(listener);
        addButton.addActionListener(listener);
        updateButton.addActionListener(listener);
        deleteButton.addActionListener(listener);


        addressL1Field     = new JTextField(medFieldLength);
        addressL2Field     = new JTextField(medFieldLength);
        cityField          = new JTextField(shortFieldLength);
        stateField         = new JTextField(shortFieldLength);
        emailField         = new JTextField(shortFieldLength);
        
        zipCodeField       = new JFormattedTextField("#####");
        phoneNumberField   = new JFormattedTextField("###-###-####");
        
        /*
          Now pack them
        */
        
        // fill, anchor, gridx, gridy,
        // weightx, weighty, gridwidth, gridheight
        
        // final int hz = GridBagConstraints.HORIZONTAL;
        // final int vt = GridBagConstraints.VERTICAL;
        // final int both = GridBagConstraints.BOTH;


        // labels
        layout = layoutGenerator(GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, 0, 0, 1, 0, 3, 1);
        container.add(header, layout);

        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 0, 1, 1, 0, 2, 1);
        container.add(addressLabel, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 0, 4, 1, 0, 1, 1);
        container.add(phoneNumberLabel, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 0, 5, 1, 0, 1, 1);
        container.add(emailLabel, layout);

        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 0, 6, 1, 0, 1, 1);
        container.add(memberLabel, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 0, 7, 1, 0, 1, 1);
        container.add(subscribeLabel, layout);


        // buttons

        // fill, anchor, gridx, gridy,
        // weightx, weighty, gridwidth, gridheight

        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 1, 6, 0, 0, 1, 1);
        container.add(memberYesButton, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 2, 6, 0, 0, 1, 1);
        container.add(memberNoButton, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 1, 7, 0, 0, 1, 1);
        container.add(subscribeYesButton, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 2, 7, 0, 0, 1, 1);
        container.add(subscribeNoButton, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 0, 8, 0, 0, 1, 1);
        container.add(addButton, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 1, 8, 0, 0, 1, 1);
        container.add(updateButton, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 2, 8, 0, 0, 1, 1);
        container.add(deleteButton, layout);

        // input fields
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_END, 1, 1, 0, 0, 2, 1);
        container.add(addressL1Field, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_END, 1, 2, 0, 0, 2, 1);
        container.add(addressL2Field, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_START, 0, 3, 0, 0, 1, 1);
        container.add(cityField, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.CENTER, 1, 3, 0, 0, 1, 1);
        container.add(stateField, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_END, 2, 3, 0, 0, 2, 1);
        container.add(zipCodeField, layout);
        
        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_END, 1, 4, 0, 0, 2, 1);
        container.add(phoneNumberField, layout);

        layout = layoutGenerator(GridBagConstraints.BOTH, GridBagConstraints.LINE_END, 1, 5, 0, 0, 2, 1);
        container.add(emailField, layout);



        // layout.fill = GridBagConstraints.HORIZONTAL;
        // layout.anchor = GridBagConstraints.PAGE_START;
        // layout.gridx = 0;
        // layout.gridy = 0;
        // layout.weightx = 1.0;
        // layout.weighty = 1.0;
        // layout.gridwidth = 3;
        // layout.gridheight = 1;
        // container.add(receipt, layout);
        
        
        // layout.fill = GridBagConstraints.HORIZONTAL;
        // layout.anchor = GridBagConstraints.PAGE_END;
        // layout.gridx = 1;
        // layout.gridy = 2;
        // layout.weighty = 0.0;
        // layout.gridwidth = 3;
        // layout.gridheight = 1;
        // container.add(infoBar, layout);
        
    }


    /**
     * The getter function for the containing GUI of this class.
     * 
     * @return The container containing the entire GUI of this class.
     */
    public JPanel getCustomerInfo() {
        return container;
    }

    
    /**
     * Generates a layout and returns it. This method was written to
     * shorten the code, allowing a single function call for a new
     * layout specification. 
     * 
     * There is a tradeoff though: it makes the code much cleaner and
     * more readable, but all eight attributes used per layout are set
     * every time. If this function was not used, many of these
     * assignments could be skipped: they would still be set from the
     * previous time. 
     * 
     * @param fill              the layout fill parameter
     * @param anchor            the layout anchor parameter
     * @param gridy             the layout gridx parameter
     * @param weightx           the layout weightx parameter
     * @param weighty           the layout weighty parameter
     * @param gridwidth         the layout gridwidth parameter
     * @param gridheight        the layout gridheight parameter
     *
     * @return layout           the layout generated with the params.
     */
    private GridBagConstraints layoutGenerator(int fill, int anchor, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight) {
        GridBagConstraints layout = new GridBagConstraints();
        layout.fill = fill;
        layout.anchor = anchor;
        layout.gridx = gridx;
        layout.gridy = gridy;
        layout.weightx = weightx;
        layout.weighty = weighty;
        layout.gridwidth = gridwidth;
        layout.gridheight = gridheight;
        return layout;
    }
}


/**********************************************************************
 ** Model
 **********************************************************************/


/**
 * The Model class for the MVC architecture. This handles all
 * significant data operations, including adding, editing, and
 * deleting. As follows, this handles all database interaction. 
 * 
 * @author Adam Sinck
 * @version 0.0
 */
class Model {
    
    /**
     * Class constructor.
     */
    public Model() {
        
    }

    /**
     * Looks up the item code in the database and returns the item if
     * found and null otherwise.
     * 
     * @param code      The item code to look up
     * @return the item correstponding to the given item code, or null
     *         if not found
     */
    public ReceiptItem itemLookup(int code) {
        return null;
    }
    
    /**
     * Looks up the customer phone number in the database and returns
     * the account if found and null otherwise.
     * 
     * @param number    The number to look up
     * @return the customer correstponding to the given number, or
     *         null if not found
     */
    public ReceiptItem customerLookup(int number) {
        return null;
    }
}


/**
 * The class for a customer's info. Contains basic information.
 * 
 * @author Adam Sinck
 * @version 0.0
 */
class Customer {
    private String addressL1, addressL2, city, state, email;
    private int zip, phoneNumber;
    private boolean member, subscribe;

    /**
     * Class constructor.
     * 
     * @param addressL1         Address line 1
     * @param addressL2         Address line 2
     * @param city              City
     * @param state             State
     * @param zip               Zip code
     * @param email             Email address
     * @param phoneNumber       Phone number
     * @param member            member?
     * @param subscribe         subscribe?
     */
    Customer(String _addressL1, String _addressL2, String _city,
             String _state, int _zip, String _email, int _phoneNumber,
             boolean _member, boolean _subscribe) {
        addressL1 = _addressL1;
        addressL2 = _addressL2;
        city = _city;
        state = _state;
        zip = _zip;
        email = _email;
        phoneNumber = _phoneNumber;
        member = _member;
        subscribe = _subscribe;
    }


    //python script ftw
    // addressL1
    // addressL2
    // city
    // state
    // zip
    // email
    // phoneNumber
    // member
    // subscribe;

    // /**
    //  * The getter function for %s
    //  * 
    //  * @return %s
    //  */
    // public String get%s() {
    //     return %s;
    // }
    
    // /**
    //  * The setter function for %s
    //  * 
    //  * @param new%s, the new %s
    //  */
    // public String set%s(String new%s) {
    //     %s = new%s;
    // }

    /**
     * The getter function for addressL1
     * 
     * @return addressL1
     */
    public String getAddressL1() {
        return addressL1;
    }
    
    /**
     * The setter function for addressL1
     * 
     * @param newAddressL1, the new addressL1
     */
    public void setAddressL1(String newAddressL1) {
        addressL1 = newAddressL1;
    }
    /**
     * The getter function for addressL2
     * 
     * @return addressL2
     */
    public String getAddressL2() {
        return addressL2;
    }
    
    /**
     * The setter function for addressL2
     * 
     * @param newAddressL2, the new addressL2
     */
    public void setAddressL2(String newAddressL2) {
        addressL2 = newAddressL2;
    }
    /**
     * The getter function for city
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * The setter function for city
     * 
     * @param newCity, the new city
     */
    public void setCity(String newCity) {
        city = newCity;
    }
    /**
     * The getter function for state
     * 
     * @return state
     */
    public String getState() {
        return state;
    }
    
    /**
     * The setter function for state
     * 
     * @param newState, the new state
     */
    public void setState(String newState) {
        state = newState;
    }
    /**
     * The getter function for zip
     * 
     * @return zip
     */
    public int getZip() {
        return zip;
    }
    
    /**
     * The setter function for zip
     * 
     * @param newZip, the new zip
     */
    public void setZip(int newZip) {
        zip = newZip;
    }
    /**
     * The getter function for email
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * The setter function for email
     * 
     * @param newEmail, the new email
     */
    public void setEmail(String newEmail) {
        email = newEmail;
    }
    /**
     * The getter function for phoneNumber
     * 
     * @return phoneNumber
     */
    public int getPhonenumber() {
        return phoneNumber;
    }
    
    /**
     * The setter function for phoneNumber
     * 
     * @param newPhoneNumber, the new phoneNumber
     */
    public void setPhonenumber(int newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }
    /**
     * The getter function for member
     * 
     * @return member
     */
    public boolean getMember() {
        return member;
    }
    
    /**
     * The setter function for member
     * 
     * @param newMember, the new member
     */
    public void setMember(boolean newMember) {
        member = newMember;
    }
    /**
     * The getter function for subscribe
     * 
     * @return subscribe
     */
    public boolean getSubscribe() {
        return subscribe;
    }
    
    /**
     * The setter function for subscribe
     * 
     * @param newSubscribe, the new subscribe
     */
    public void setSubscribe(boolean newSubscribe) {
        subscribe = newSubscribe;
    }
    
}
