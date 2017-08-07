package register;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * The class for custom rendering of receipt items.
 *
 * @author Adam Sinck
 * @version 0.0
 */
class ReceiptItemRenderer extends JCheckBox implements ListCellRenderer<ReceiptItem> {

    // private ReceiptItem receiptItem;

    public ReceiptItemRenderer() {
        // receiptItem = new ReceiptItem("Pickles", 50, 1.75);
    }

    public Component getListCellRendererComponent(JList<? extends ReceiptItem> list,
                                                  ReceiptItem value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        // ReceiptItem item = (ReceiptItem) value;
        setEnabled(list.isEnabled());
        // setSelected(value.isSelected());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(value.toString());
        return this;
    }
}
