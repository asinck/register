package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ModelTest {

//    @Test
//    public void testModelItem() {
//        // This makes sure that when an item is looked up in the database that it returns the
//        // expected values.
//        Model model = new Model();
//        ReceiptItem item = model.itemLookup(4011);
//        assertEquals(item, new ReceiptItem("BANANAS", 0, 82.04));
//        model.destruct();
//        System.out.println("All Model Item tests passed.");
//    }

    @Test
    public void testModelCustomer() {

        /*
        This needs to test all database functionality, for both customer and item.

        Items are expected to already be in the database - these will not be edited. Thus, it only
        needs to be checked that the correct items are returned. This can be done directly, by
        looking at the database.

        Customers can be added, edited, or removed. There are the following cases:

        add:
            customer does not already exist
            customer *does* already exist
            key collision

        update:
            customer exists
            customer *does not* exist
            key collision

        delete:
            customer exists
            customer *does not* exist

         Because this is a simple example database (at the moment), there are no foreign keys or
         odd constraints on the database - only that certain keys are not null.
         */

        Model model = new Model();

        // Test to make sure that the model can add a customer to the database.
        // These use negative numbers for the phone numbers, because those are invalid numbers and
        // won't exist in the database (in theory...).
        // TODO: make a unit test for the customer input fields to make sure that it only inputs
        // TODO: "valid" phone numbers

        Customer c1 = new Customer("Model Test 1 Street", "Model Test 1 L2",
                "Test1", "confusion", 12345, "user1@example.com",
                -1, false, true);
        Customer c2 = new Customer("Model Test 2 Street", "Model Test 2 L2",
                "Test2", "none", 54321, "user2@example.com",
                -10, true, false);
        // This has the same phone number as c2, but different other fields. It should not be
        // insertable into the database.
        Customer c3 = new Customer("Model Test 3 Street", "Model Test 3 L2",
                "Test3", "none", 72984010, "user3@example.com",
                -10, true, false);


        // If either of these returns a customer, then there's an issue somewhere. These customers
        // should not exist.
        assertEquals(model.customerLookup(c1), null);
        assertEquals(model.customerLookup(c2), null);

        // Add a customer and make sure that it gets back from the database successfully
        model.addCustomer(c1);
        assertEquals(model.customerLookup(c1), c1);
        assertNotEquals(model.customerLookup(c1), c2);

        // Delete the customer and make sure it's gone
        model.deleteCustomer(c1);
        assertEquals(model.customerLookup(c1), null);


        // Test persistence
        // We'll put c2 into the database, and then close the connection and form a new one. If
        // changes are not committed to the database they'll only last for the session.
        model.addCustomer(c2);
        assertEquals(model.customerLookup(c2), c2);
        model.destruct();
        model = new Model();
        assertEquals(model.customerLookup(c2), c2);

        // Test to make sure that customers with duplicate keys are not inserted into the database
        model.addCustomer(c3);
        assertEquals(model.customerLookup(c3), c2);

        //clean up
        model.deleteCustomer(c1);
        model.deleteCustomer(c2);
        model.deleteCustomer(c3);

        //test while we're here
        assertEquals(model.customerLookup(c1), null);
        assertEquals(model.customerLookup(c2), null);
        assertEquals(model.customerLookup(c3), null);

        //close the connection
        model.destruct();
        System.out.println("All Model Customer tests passed.");
    }
}
