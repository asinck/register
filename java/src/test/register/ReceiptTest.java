// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ReceiptTest {
	
    private Receipt receipt = new Receipt(null);

    //No, I was not hungry when I wrote this.
    //Yes, I like perfect formatting.
    private ReceiptItem eggs     = new ReceiptItem("eggs",     1,    3.33);
    private ReceiptItem bacon    = new ReceiptItem("bacon",    12,   50.00);
    private ReceiptItem toast    = new ReceiptItem("toast",    5,    1.00);
    private ReceiptItem butter   = new ReceiptItem("butter",   1,    0.20);
    private ReceiptItem sausage  = new ReceiptItem("sausage",  500,  0.01);
    private ReceiptItem coffee   = new ReceiptItem("coffee",   10,   1.50);
    private ReceiptItem orange_juice = new ReceiptItem("orange juice", 2,   2.00);
    
    @Test
    public void testReceipt() {
        double myTotal = 0.0;
        double marginOfError = 0.0001;
        //Make sure that the list initialization has a total of $0
        assertEquals(0.0, receipt.getTotal(), marginOfError);
        
        //test indexing
        receipt.setSelection(0);
        assertEquals(-1, receipt.getSelection());

        //test null on add
        receipt.addItem(null);
        assertEquals(myTotal, receipt.getTotal(), marginOfError);
        assertEquals(-1, receipt.getSelection());


        //Add eggs
        receipt.addItem(eggs);
        myTotal += eggs.getTotal();
        assertEquals(myTotal, receipt.getTotal(), marginOfError);

        //test null on update
        receipt.setSelection(0);
        receipt.updateItem(null);
        receipt.setSelection(0);
        assertEquals(eggs, receipt.getItem());

        //Add bacon
        receipt.addItem(bacon);
        myTotal += bacon.getTotal();
        assertEquals(myTotal, receipt.getTotal(), marginOfError);
        
        //test indexing
        receipt.setSelection(0);
        assertEquals(0, receipt.getSelection());
        assertEquals(eggs, receipt.getItem());

        receipt.setSelection(1);
        assertEquals(1, receipt.getSelection());
        assertEquals(bacon, receipt.getItem());

        //this should still have 1 because that's the last element
        receipt.setSelection(2);
        assertEquals(1, receipt.getSelection());

        
        //remove eggs
        receipt.setSelection(0);
        receipt.removeItem();
        myTotal -= eggs.getTotal();
        assertEquals(myTotal, receipt.getTotal(), marginOfError);

        //remove bacon
        receipt.setSelection(0);
        receipt.removeItem();
        myTotal -= bacon.getTotal();
        assertEquals(myTotal, receipt.getTotal(), marginOfError);


        //put two items in, take them both out, and make sure
        //the total is still $0. 
        receipt.addItem(eggs);
        receipt.addItem(bacon);
        receipt.setSelection(0);
        receipt.removeItem();
        receipt.setSelection(0);
        receipt.removeItem();
        assertEquals(myTotal, receipt.getTotal(), marginOfError);

        //clear and do an update / null test
        receipt.clear();
        receipt.setSelection(0);
        receipt.updateItem(null);
        assertEquals(-1, receipt.getSelection());
        assertEquals(myTotal, receipt.getTotal(), marginOfError);


        //add all the food
        receipt.addItem(eggs);
        receipt.addItem(bacon);
        receipt.addItem(toast);
        receipt.addItem(butter);
        receipt.addItem(sausage);

        myTotal += eggs.getTotal();
        myTotal += bacon.getTotal();
        myTotal += toast.getTotal();
        myTotal += butter.getTotal();
        myTotal += sausage.getTotal();
        
        assertEquals(myTotal, receipt.getTotal(), marginOfError);


        //replace toast with coffee
        receipt.setSelection(2);
        receipt.updateItem(coffee);
        myTotal -= toast.getTotal();
        myTotal += coffee.getTotal();
        assertEquals(myTotal, receipt.getTotal(), marginOfError);
        
        //replace butter with orange juice
        receipt.setSelection(3);
        receipt.updateItem(orange_juice);
        myTotal -= butter.getTotal();
        myTotal += orange_juice.getTotal();
        assertEquals(myTotal, receipt.getTotal(), marginOfError);
        
        //replace sausage with more coffee
        receipt.setSelection(4);
        receipt.updateItem(coffee);
        myTotal -= sausage.getTotal();
        myTotal += coffee.getTotal();
        assertEquals(myTotal, receipt.getTotal(), marginOfError);
        

        //test clear
        receipt.clear();
        myTotal = 0.0;
        assertEquals(myTotal, receipt.getTotal(), marginOfError);
    }
}
