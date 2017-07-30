// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ReceiptItemTest {
    ReceiptItem eggs = new ReceiptItem("eggs", 1, 3.33);
    
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
    }
}
