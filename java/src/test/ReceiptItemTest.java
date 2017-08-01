// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class ReceiptItemTest {
    ReceiptItem eggs = new ReceiptItem("eggs", 1, 3.33);
    
    ReceiptItem A1 = new ReceiptItem("A", 1, 3.33);
    ReceiptItem A2 = new ReceiptItem("A", 2, 3.33);
    ReceiptItem A3 = new ReceiptItem("A", 1, 1.00);
    ReceiptItem B1 = new ReceiptItem("B", 1, 3.33);
    ReceiptItem B2 = new ReceiptItem("B", 2, 3.33);
    ReceiptItem B3 = new ReceiptItem("B", 1, 1.00);
    
    @Test
    public void testReceiptItem() {
        double marginOfError = 0.0001;
        //test initialization
        assertEquals("eggs", eggs.getName());
        assertEquals(1, eggs.getCount());
        assertEquals(3.33, eggs.getPrice(), marginOfError);
        assertEquals(3.33, eggs.getTotal(), marginOfError);

        //test setting the name
        eggs.setName("ham");
        assertEquals("ham", eggs.getName());

        //test setting the count
        eggs.setCount(50);
        assertEquals(50, eggs.getCount());

        //test setting the price        
        eggs.setPrice(3.14);
        assertEquals(3.14, eggs.getPrice(), marginOfError);

        //make sure the total is correct
        assertEquals(50*3.14, eggs.getTotal(), marginOfError);

        //make sure the total is consistent with itself
        assertEquals(eggs.getCount() * eggs.getPrice(),
                     eggs.getTotal(), marginOfError);

        //test .equals
        /*
          A1 == A2
          B1 == B2
          
          The rest aren't equal.
         */
        assertEquals(A1, A2);
        assertEquals(B1, B2);

        assertNotEquals(A1, A3);
        assertNotEquals(A2, A3);

        assertNotEquals(B1, B3);
        assertNotEquals(B2, B3);
        
        assertNotEquals(A1, B1);
        assertNotEquals(A1, B2);
        assertNotEquals(A1, B3);

        assertNotEquals(A2, B1);
        assertNotEquals(A2, B2);
        assertNotEquals(A2, B3);

        assertNotEquals(A3, B1);
        assertNotEquals(A3, B2);
        assertNotEquals(A3, B3);
    }
}
