/*
  The purpose of this program is to demonstrate a software interaction
  system for use at cash registers.
 */


package register;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;


/* *******************************************************************
 * View
 */

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
        new RegisterGUI();
    }
}


/* ********************************************************************
 * View / Controller
 */


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



/* ********************************************************************
 * Model
 */


