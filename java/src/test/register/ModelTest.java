package register;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

public class ModelTest {

    // These use negative numbers for the phone numbers, because those are invalid numbers and
    // won't exist in the database (in theory...).
    // TODO: make a unit test for the customer input fields to make sure that it only inputs
    // TODO: "valid" phone numbers
    private Customer c1 = new Customer("Model Test 1 Street", "Model Test 1 L2",
            "Test1", "confusion", 12345, "user1@example.com",
            -1, false, true);
    private Customer c2 = new Customer("Model Test 2 Street", "Model Test 2 L2",
            "Test2", "none", 54321, "user2@example.com",
            -10, true, false);
    // This has the same phone number as c1, but different other fields. It should not be
    // insertable into the database, because the phone number is the primary key.
    private Customer c3 = new Customer("Model Test 3 Street", "Model Test 3 L2",
            "Test3", "none", 72984010, "user3@example.com",
            -1, true, false);

    //TODO: dependency injection?

//    @Test
//    public void testModelItem() {
//        // This makes sure that when an item is looked up in the database that it returns the
//        // expected values.
//        Model model = new Model();
//        ReceiptItem item = model.itemLookup(4011);
//        assertEquals(item, new ReceiptItem("BANANAS", 0, 82.04));
//        model.destruct();
//        System.out.println("Item lookup tests passed.");
//    }


    /**
     * Tests to make sure that the add functionality works.
     */
    @Test
    public void testModelDestruct() {
        Model model = new Model();
        model.destruct();
    }

    /**
     * Tests to make sure that the add functionality works.
     */
    @Test
    public void testAdd() {
        Model model = new Model();

        // Test a null addition. If this doesn't crash, nothing should have happened.
        model.addCustomer(null);


        // If either of these returns a customer, then there's an issue somewhere. These customers
        // should not exist.
        assertEquals(model.customerLookup(c1), null);
        assertEquals(model.customerLookup(c2), null);
        assertEquals(model.customerLookup(c3), null);

        // Add a customer and make sure that it gets back from the database successfully
        model.addCustomer(c1);
        assertEquals(model.customerLookup(c1), c1);
        assertNotEquals(model.customerLookup(c1), c3);

        // Test duplicate insertion.
        model.addCustomer(c1);
        assertEquals(model.customerLookup(c1), c1);

        // Test to make sure that customers with duplicate keys are not inserted into the database.
        // In this case, the insert will be ignored - thus, looking up c3 should yield c1 and *not*
        // c3 because it does the lookup by phone number.
        model.addCustomer(c3);
        assertEquals(model.customerLookup(c3), c1);
        assertNotEquals(model.customerLookup(c3), c3);

        //clean up
        model.deleteCustomer(c1);

        //close the connection
        model.destruct();
        System.out.println("Customer addition tests passed.");
    }

    /**
     * Tests to make sure that the update functionality works.
     */
    @Test
    public void testUpdate() {
        Model model = new Model();

        // Test a null update. If this doesn't crash, nothing should have happened.
        model.updateCustomer(null);


        model.addCustomer(c1);
//        assertEquals(model.customerLookup(c3), c1);
//        assertNotEquals(model.customerLookup(c3), c3);

        // Because c3 has the same key as c1, it will update all the fields.
        model.updateCustomer(c3);
        assertEquals(model.customerLookup(c3), c3);
        assertNotEquals(model.customerLookup(c3), c1);

        //clean up
        model.deleteCustomer(c3);

        //close the connection
        model.destruct();
        System.out.println("Customer update tests passed.");
    }

    /**
     * Tests to make sure that the delete functionality works.
     */
    @Test
    public void testDelete() {
        Model model = new Model();

        // Test a null update. If this doesn't crash, nothing should have happened.
        model.deleteCustomer(null);

        // Test deletion of nonexistent customer
        assertEquals(model.customerLookup(c1), null);
        model.deleteCustomer(c1);
        assertEquals(model.customerLookup(c1), null);

        // Test actual delete
        model.addCustomer(c1);
        model.deleteCustomer(c1);
        assertEquals(model.customerLookup(c1), null);

        // Clean up
        model.deleteCustomer(c1);

        // Close the connection
        model.destruct();

        System.out.println("Customer deletion tests passed.");
    }

    /**
     * Tests to make sure that the database is persistent.
     */
    @Test
    public void testPersistence() {
        /*
        First, connect to the database, insert a customer, and disconnect from the database.
        Second, connect to the database and check to make sure that the customer is still there.
         */
        Model model = new Model();
        model.addCustomer(c1);
        assertEquals(model.customerLookup(c1), c1);
        model.destruct();

        model = new Model();
        assertEquals(model.customerLookup(c1), c1);

        // Clean up
        model.deleteCustomer(c1);

        // Close the connection
        model.destruct();

        System.out.println("Customer database persistence tests passed.");
    }

}
