// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ItemInputTest {
    private ItemInput emptyItem = new ItemInput();
    private ItemInput nullItem  = new ItemInput(null);
    private ReceiptItem item    = new ReceiptItem("item", 1, 2.50);
    private ItemInput initdItem = new ItemInput(item);
    
    @Test
    public void testItemInput() {
        //make sure the empty item has a null item
        assertEquals(emptyItem.getItem(), null);
        //make sure the null item has a null item
        assertEquals(nullItem.getItem(), null);
        //make sure the initialized item has the item
        assertEquals(initdItem.getItem(), item);
        //set the item of the null item and make sure it has it
        nullItem.setItem(item);
        assertEquals(nullItem.getItem(), item);

        //make sure that the clear function works
        nullItem.clear();
        assertEquals(null, nullItem.getItem());
        initdItem.clear();
        assertEquals(null, initdItem.getItem());
    }
}
