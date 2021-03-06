package register;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The Graphical interface for the program. This class defines the
 * view and contains the controller, making it both the View and
 * Controller of the MVC architecture.
 *
 * @author Adam Sinck
 * @version 0.0
 */

class RegisterGUI extends JFrame implements ActionListener, ListSelectionListener {
    private JFrame myWindow;
    private ApplicationMenu applicationMenu;
    private Header header;
    private Receipt receipt;
    private Display display;
    private CustomerInfo customerInfo;
    private Footer footer;
    private HardwareInterface_Scanner scanner;
    private Model model;
    /**
     * The class constructor.
     *
     * This arranges all the components of the GUI inside it.
     */
    RegisterGUI() {
        model = new Model();

        //this is the top level window.
        myWindow = new JFrame();
        myWindow.setTitle("Register");
        myWindow.setSize(1200,500);

        // https://stackoverflow.com/a/16372860
        myWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        myWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                destruct();
            }
        });

        myWindow.setLayout(new GridBagLayout());
        //specify how to lay stuff out
        GridBagConstraints layout = new GridBagConstraints();

        //create the menu bar
        applicationMenu = new ApplicationMenu(this);
        myWindow.setJMenuBar(applicationMenu.getMenuBar());

        //fill the top horizontally
        header           = new Header(this, "asinck");
        layout.fill      = GridBagConstraints.HORIZONTAL;
        layout.anchor    = GridBagConstraints.FIRST_LINE_START;
        layout.gridx     = 0;
        layout.gridy     = 0;
        layout.gridwidth = 3;
        layout.weightx   = 1.0;
        myWindow.add(header.getHeader(), layout);

        //make it fill the the left side up to the header and footer
        receipt          = new Receipt(this, this);
        layout.fill      = GridBagConstraints.BOTH;
        layout.anchor    = GridBagConstraints.LINE_START;
        layout.gridx     = 0;
        layout.gridy     = 1;
        layout.weighty   = 1.0;
        layout.gridwidth = 1;
        myWindow.add(receipt.getReceiptPanel(), layout);


        //for the main display, to the right of the receipt display.
        customerInfo = new CustomerInfo(this);


        display          = new Display(this, customerInfo);
        layout.fill      = GridBagConstraints.BOTH;
        layout.anchor    = GridBagConstraints.CENTER;
        layout.gridx     = 1;
        layout.gridy     = 1;
        layout.weightx   = 1.0;
        layout.weighty   = 1.0;
        layout.gridwidth = 2;
        myWindow.add(display.getDisplay(), layout);

        //have a footer for status and stuff
        footer           = new Footer(this);
        layout.fill      = GridBagConstraints.HORIZONTAL;
        layout.anchor    = GridBagConstraints.LAST_LINE_START;
        layout.gridx     = 0;
        layout.gridy     = 2;
        layout.weightx   = 1.0;
        layout.weighty   = 0.0;
        layout.gridwidth = 3;
        myWindow.add(footer.getFooter(), layout);

        /*
        hardware interfaces
         */

        scanner = new Scanner(this);

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

        /* receipt */
        if (event.getSource() == receipt.getPrintButton()) {
            receipt.print();
        }
        else if (event.getSource() == receipt.getRemoveButton()) {
            receipt.removeItem();
        }

        /* footer */
        else if (event.getSource() == footer.getCancelButton()) {
            receipt.clear();
        }

        /* display */
        else if (event.getSource() == display.getItemAddButton()) {
            ReceiptItem item = display.getItem();
            receipt.addItem(item);
            display.clear();
        }
        else if (event.getSource() == display.getItemUpdateButton()) {
            receipt.updateItem(display.getItem());
        }
        else if (event.getSource() == customerInfo.getAddButton()) {
            model.addCustomer(customerInfo.getCustomer());
        }
        else if (event.getSource() == customerInfo.getUpdateButton()) {
            model.updateCustomer(customerInfo.getCustomer());
        }
        else if (event.getSource() == customerInfo.getDeleteButton()) {
            model.deleteCustomer(customerInfo.getCustomer());
        }
        else if (event.getSource() == customerInfo.getLookupButton()) {
            //TODO: This needs to be tested as soon as the Model is ready.
            Customer customer = model.customerLookup(customerInfo.getLookupKey());
            customerInfo.setCustomer(customer);
        }

        /* scanner (hardware) */
        else if (event.getSource() == scanner.getItemAddButton()) {
            ReceiptItem item = scanner.getItem();
            if (item != null) {
                scanner.clear();
                receipt.addItem(item);
                receipt.setSelection(-1);
                display.setItem(item);
            }
        }
        else {
            footer.setStatus("Received command: " + command);
        }
    }


    /**
     * The list selection listener for the user clicking on an item
     * in the receipt item list.
     *
     * @param e         The list selection event.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            ReceiptItem item = receipt.getItem();
//            System.out.println(item);
            display.setItem(item);
        }
    }

    /**
     * The destructor for this class. Tells the model to clean up after itself.
     */
    private void destruct() {
        model.destruct();
        myWindow.dispose();
        System.exit(0);
    }
}
