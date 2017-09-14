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
            c = DriverManager.getConnection("jdbc:sqlite:infosys.db");
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

    /**
     * Checks to make sure that the tables that are supposed to be in the database are actually
     * there.
     */
    private void tableCheck() {
        try {
            /*
            Check that there are tables for
             - Customers
             - Items
             */

            String sql = "SELECT name FROM sqlite_master WHERE type='table'";
            rs = stmt.executeQuery(sql);
            boolean hasCustomersTable = false;
            boolean hasItemsTable = false;

            while (rs.next()) {
                String name = rs.getString("name");

                if (name.equals("Customer")) {
                    hasCustomersTable = true;
                }
                else if (name.equals("Item")) {
                    hasItemsTable = true;
                }
            }

            if (!hasCustomersTable) {
                System.out.println("WARNING: Customer database not found.");
                sql = "CREATE TABLE Customer(" +
                        "Phone INT PRIMARY KEY NOT NULL, " +
                        "Email          CHAR(30), " +
                        "AddressL1      CHAR(50), " +
                        "AddressL2      CHAR(50), " +
                        "City           CHAR(30), " +
                        "State          CHAR(20), " +
                        "Zip            INT, " +
                        "Membership     INT, " +
                        "Subscription   INT)";

                stmt.executeUpdate(sql);
                c.commit();
            }

            if (!hasItemsTable) {
                System.out.println("WARNING: Item database not found.");
                sql = "CREATE TABLE Item(" +
                        "PLU   INT PRIMARY KEY    NOT NULL, " +
                        "Variety          TEXT    NOT NULL, " +
                        "Additional_info  REAL    NOT NULL," +
                        "Price            Float   NOT NULL)";

                stmt.executeUpdate(sql);
                c.commit();
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
        String sql = "SELECT * FROM Item WHERE PLU = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, code);
            rs = ps.executeQuery();
            // https://stackoverflow.com/a/6813771
            if (!rs.isBeforeFirst()) {
                System.out.println("Invalid PLU.");
                return null;
            }
            else {
                // System.out.printf("Result: %s", re.next());
                return new ReceiptItem(rs.getString("Variety"), 1, rs.getDouble("Price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // If Java was a modern language, it would know that I don't need this return statement.
        // But ok.
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
        String sql = "SELECT * FROM Customer WHERE Phone = ?";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, number);
            rs = ps.executeQuery();
            // https://stackoverflow.com/a/6813771
            if (!rs.isBeforeFirst()) {
                System.out.println("No such customer.");
                return null;
            }
            else {
                boolean isMember = rs.getInt("Membership") == 1;
                boolean isSubscribed = rs.getInt("Subscription") == 1;
                return new Customer(rs.getString("AddressL1"), rs.getString("AddressL2"),
                        rs.getString("City"), rs.getString("State"), rs.getInt("Zip"),
                        rs.getString("Email"), rs.getInt("Phone"), isMember, isSubscribed);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // If Java was a modern language, it would know that I don't need this return statement.
        // But ok.
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
