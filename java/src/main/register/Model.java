package register;

/**
 * The Model class for the MVC architecture. This handles all
 * significant data operations, including adding, editing, and
 * deleting. As follows, this handles all database interaction.
 *
 * @author Adam Sinck
 * @version 0.0
 */
class Model {

    /**
     * Class constructor.
     */
    Model() {

    }

    /**
     * Looks up the item code in the database and returns the item if
     * found and null otherwise.
     *
     * @param code      The item code to look up
     * @return the item corresponding to the given item code, or null
     *         if not found
     */
    ReceiptItem itemLookup(int code) {
        System.out.printf("Searching for item %d", code);
        return null;
    }

    /**
     * Looks up the customer phone number in the database and returns
     * the account if found and null otherwise.
     *
     * @param number    The number to look up
     * @return the customer corresponding to the given number, or
     *         null if not found
     */
    Customer customerLookup(int number) {
        System.out.printf("Searching for customer %d", number);
        return null;
    }

    /**
     * add a customer to the database, if they aren't in it already
     *
     * @param customer the customer to add
     */
    void addCustomer(Customer customer) {
        System.out.printf("Adding customer %s", customer.getEmail());
    }


    /**
     * update a customer in the database
     *
     * @param customer the customer to update
     */
    void updateCustomer(Customer customer) {
        System.out.printf("Updating customer %s", customer.getEmail());
    }

    /**
     * delete a customer from the database
     *
     * @param customer the customer to delete
     */
    void deleteCustomer(Customer customer) {
        System.out.printf("Deleting customer %s", customer.getEmail());

    }

}
