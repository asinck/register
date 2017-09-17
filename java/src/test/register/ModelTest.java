package register;

import org.junit.Test;

public class ModelTest {
    private Model model;

    @Test
    public void testModel() {
        model = new Model();

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

        System.out.println("All tests passed.");
    }
}
