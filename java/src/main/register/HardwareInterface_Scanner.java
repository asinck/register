package register;

import javax.swing.JButton;

interface HardwareInterface_Scanner {
    ReceiptItem getItem();
    void lookup();
    JButton getItemAddButton();
    void clear();
}
