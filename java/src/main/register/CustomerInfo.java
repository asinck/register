package register;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
//    private JLabel header, addressLabel, phoneNumberLabel,
//        emailLabel, memberLabel, subscribeLabel;
    private JButton subscribeYesButton, subscribeNoButton, memberYesButton, memberNoButton, addButton, updateButton, deleteButton;
//    private JButton memberYesButton;
//    private JButton memberNoButton;
//    private JButton addButton;
//    private JButton updateButton;
//    private JButton deleteButton;
    //TODO: make "state" a dropdown
    private JTextField addressL1Field, addressL2Field, cityField,
        stateField, emailField;
    private JFormattedTextField zipCodeField, phoneNumberField;

    /**
     * Class constructor.
     *
     * @param listener   The action listener.
     */
    CustomerInfo(ActionListener listener) {
        container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints layout;

        final int longFieldLength  = 75;
        final int medFieldLength   = 50;
        final int shortFieldLength = 25;
        final int tinyFieldLength  = 10;

        /*
          First, make all the components
         */
        JLabel header = new JLabel("Customer Info");
        JLabel addressLabel       = new JLabel("Address");
        JLabel phoneNumberLabel   = new JLabel("Phone Number");
        JLabel emailLabel         = new JLabel("Email Address");
        JLabel memberLabel        = new JLabel("Member?");
        JLabel subscribeLabel     = new JLabel("Subscribe?");

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
    JPanel getCustomerInfo() {
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
        layout.fill       = fill;
        layout.anchor     = anchor;
        layout.gridx      = gridx;
        layout.gridy      = gridy;
        layout.weightx    = weightx;
        layout.weighty    = weighty;
        layout.gridwidth  = gridwidth;
        layout.gridheight = gridheight;
        return layout;
    }
}
