// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import java.util.ArrayList;

/**
 * This class tests the Printer class of the Register program. The purpose of
 * the the Printer class is to simulate printing the receipt. Thus, it only
 * has one function: print. Therefore, the most that can be reasonably tested
 * is if initialization works and no errors are thrown for printing.
 */
public class PrinterTest {

    @Test
    public void testPrinter() {
        HardwareInterface_Printer printer;
        ReceiptItem[] empty= {};
        printer = new Printer(empty);
        printer.print();

        ReceiptItem[] ri = {new ReceiptItem("milk", 1, 2.00)};
        printer = new Printer(ri);
        printer.print();

        ArrayList<ReceiptItem> list = new ArrayList<>();
        list.add(new ReceiptItem("eggs",     1,    3.33));
        list.add(new ReceiptItem("bacon",    12,   50.00));
        list.add(new ReceiptItem("toast",    5,    1.00));
        list.add(new ReceiptItem("butter",   1,    0.20));
        list.add(new ReceiptItem("sausage",  500,  0.01));
        list.add(new ReceiptItem("coffee",   10,   1.50));
        list.add(new ReceiptItem("orange juice", 2,   2.00));

        printer = new Printer(list.toArray(new ReceiptItem[0]));
        printer.print();

        System.out.println("All tests passed.");
    }
}
