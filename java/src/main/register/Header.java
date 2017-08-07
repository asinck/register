package register;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    Header(ActionListener listener) {
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
    Header(ActionListener listener, String name) {
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
    JPanel getHeader() {
        return container;
    }


    /**
     * The getter function for the header text.
     *
     * @return The header text.
     */
    String getHeaderText() {
        return greeting.getText();
    }
    /**
     * Sets a new string in the header.
     *
     * @param newContents   The new message to be displayed in the
     *                      header.
     */
    void setHeader(String newContents) {
        greeting.setText(newContents);
    }
}
