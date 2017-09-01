package register;

import java.sql.*;

/**
 * The Model class for the MVC architecture. This handles all
 * significant data operations, including adding, editing, and
 * deleting. As follows, this handles all database interaction.
 *
 * @author Adam Sinck
 * @version 0.0
 */
class Model {
    private Connection c;
    private Statement stmt;
    private ResultSet rs;
    /**
     * Class constructor.
     */
    Model() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            tableCheck();

        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * The class destructor. This is manually called by the controller when the GUI exits.
     *
     * The purpose of this is to close all connections, such as for the database.
     */
    void destruct() {
        try {
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void tableCheck() {
        try {

            String sql = "SELECT name FROM sqlite_master WHERE type='table'";
            rs = stmt.executeQuery(sql);

            int count = 0;
            while (rs.next()) {
                count += 1;
            }
            System.out.printf("%d results.\n", count);
            if (count == 0) {
                sql = "CREATE TABLE COMPANY " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " AGE            INT     NOT NULL, " +
                        " ADDRESS        CHAR(50), " +
                        " SALARY         REAL)";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
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
        System.out.printf("Searching for item %d\n", code);
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
        System.out.printf("Searching for customer %d\n", number);
        return null;
    }

    /**
     * add a customer to the database, if they aren't in it already
     *
     * @param customer the customer to add
     */
    void addCustomer(Customer customer) {
        if (customer != null) {
            System.out.printf("Adding customer %s\n", customer.getEmail());
        }
    }


    /**
     * update a customer in the database
     *
     * @param customer the customer to update
     */
    void updateCustomer(Customer customer) {
        if (customer != null) {
            System.out.printf("Updating customer %s\n", customer.getEmail());
        }
    }

    /**
     * delete a customer from the database
     *
     * @param customer the customer to delete
     */
    void deleteCustomer(Customer customer) {
        if (customer != null) {
            System.out.printf("Deleting customer %s\n", customer.getEmail());
        }
    }

}
