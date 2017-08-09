package register;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
     * Class constructor.
     *
     * @param listener   The action listener.
     */
    ApplicationMenu(ActionListener listener) {
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

        //The root of the file menu
        JMenu file = new JMenu("File");
        JMenuItem file_quit = new JMenuItem("Quit");
          file.add(file_quit);
          file_quit.addActionListener(e -> System.exit(0));


        //The root of the edit menu
        JMenu edit = new JMenu("Edit");
          JMenuItem edit_undo = new JMenuItem("Undo");
          edit.add(edit_undo);
          JMenuItem edit_redo = new JMenuItem("Redo");
          edit.add(edit_redo);

        //The root of the preferences menu
        JMenu preferences = new JMenu("Preferences");
        JMenu preferences_colorTheme = new JMenu("Color Theme");
          JMenuItem preferences_colorTheme_light = new JMenuItem("Light");
          preferences_colorTheme.add(preferences_colorTheme_light);
          JMenuItem preferences_colorTheme_dark = new JMenuItem("Dark");
          preferences_colorTheme.add(preferences_colorTheme_dark);
          preferences.add(preferences_colorTheme);
          preferences.addSeparator();
          JMenuItem preferences_options = new JMenuItem("Options");
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
    JMenuBar getMenuBar() {
        return menuBar;
    }

}
