package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DisplayTest {

    @Test
    public void testDisplay() {
        CustomerInfo customerInfo = new CustomerInfo(null);
        Display display = new Display(null, customerInfo);

        //test for null
        assertEquals(null, display.getItem());

        //test setting an item
        ReceiptItem ri = new ReceiptItem("eggs", 1, 3.33);
        display.setItem(ri);
        assertEquals(ri, display.getItem());

        //test clearing
        display.clear();
        assertEquals(null, display.getItem());

        System.out.println("All tests passed.");

        //TODO: test the customer input fields
    }
}
