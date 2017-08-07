package register;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

    private JButton cancelButton;

    /**
     * Class constructor.
     *
     * Calls the other constructor, telling it that the initial
     * message is "ready".
     *
     * @param listener   The action listener.
     */
    Footer(ActionListener listener) {
        this(listener, "Ready");
    }

    /**
     * Class constructor specifying the status message to be
     * displayed.
     *
     * @param listener   The action listener.
     * @param message    The status message to be displayed.
     */
    Footer(ActionListener listener, String message) {
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
    void setStatus(String newStatus) {
        status.setText(newStatus);
    }

    /**
     * The getter function for the status message.
     *
     * @return The status message.
     */
    String getStatus() {
        return status.getText();
    }


    /**
     * The getter function for the cancel button. Useful for
     * identifying what button was pressed.
     *
     * @return The cancel button.
     */
    JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * The getter function for the containing GUI of this class.
     *
     * @return The container containing the entire GUI of this class.
     */
    JPanel getFooter() {
        return container;
    }
}
