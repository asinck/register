package register;

/**
 * The class for a customer's info. Contains basic information.
 *
 * @author Adam Sinck
 * @version 0.0
 */
class Customer {
    private String addressL1, addressL2, city, state, email;
    private int zip, phoneNumber;
    private boolean member, subscribe;

    /**
     * Class constructor.
     *
     * @param _addressL1        Address line 1
     * @param _addressL2        Address line 2
     * @param _city             City
     * @param _state            State
     * @param _zip              Zip code
     * @param _email            Email address
     * @param _phoneNumber      Phone number
     * @param _member           member?
     * @param _subscribe        subscribe?
     */
    Customer(String _addressL1, String _addressL2, String _city,
             String _state, int _zip, String _email, int _phoneNumber,
             boolean _member, boolean _subscribe) {
        addressL1    = _addressL1;
        addressL2    = _addressL2;
        city         = _city;
        state        = _state;
        zip          = _zip;
        email        = _email;
        phoneNumber  = _phoneNumber;
        member       = _member;
        subscribe    = _subscribe;
    }

    /**
     * The getter function for addressL1
     *
     * @return addressL1
     */
    String getAddressL1() {
        return addressL1;
    }

    /**
     * The setter function for addressL1
     *
     * @param newAddressL1, the new addressL1
     */
    void setAddressL1(String newAddressL1) {
        addressL1 = newAddressL1;
    }
    /**
     * The getter function for addressL2
     *
     * @return addressL2
     */
    String getAddressL2() {
        return addressL2;
    }

    /**
     * The setter function for addressL2
     *
     * @param newAddressL2, the new addressL2
     */
    void setAddressL2(String newAddressL2) {
        addressL2 = newAddressL2;
    }
    /**
     * The getter function for city
     *
     * @return city
     */
    String getCity() {
        return city;
    }

    /**
     * The setter function for city
     *
     * @param newCity, the new city
     */
    void setCity(String newCity) {
        city = newCity;
    }
    /**
     * The getter function for state
     *
     * @return state
     */
    String getState() {
        return state;
    }

    /**
     * The setter function for state
     *
     * @param newState, the new state
     */
    void setState(String newState) {
        state = newState;
    }
    /**
     * The getter function for zip
     *
     * @return zip
     */
    int getZip() {
        return zip;
    }

    /**
     * The setter function for zip
     *
     * @param newZip, the new zip
     */
    void setZip(int newZip) {
        zip = newZip;
    }
    /**
     * The getter function for email
     *
     * @return email
     */
    String getEmail() {
        return email;
    }

    /**
     * The setter function for email
     *
     * @param newEmail, the new email
     */
    void setEmail(String newEmail) {
        email = newEmail;
    }
    /**
     * The getter function for phoneNumber
     *
     * @return phoneNumber
     */
    int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * The setter function for phoneNumber
     *
     * @param newPhoneNumber, the new phoneNumber
     */
    void setPhoneNumber(int newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }
    /**
     * The getter function for member
     *
     * @return member
     */
    boolean getMember() {
        return member;
    }

    /**
     * The setter function for member
     *
     * @param newMember, the new member
     */
    void setMember(boolean newMember) {
        member = newMember;
    }
    /**
     * The getter function for subscribe
     *
     * @return subscribe
     */
    boolean getSubscribe() {
        return subscribe;
    }

    /**
     * The setter function for subscribe
     *
     * @param newSubscribe, the new subscribe
     */
    void setSubscribe(boolean newSubscribe) {
        subscribe = newSubscribe;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n%s, %s %d\n%s  --  %d\nmember=%s, subscribe=%s",
                addressL1, addressL2, city, state, zip, email, phoneNumber, member, subscribe);
    }

    /**
     * The .equals() method for this class.
     *
     * http://www.geeksforgeeks.org/overriding-equals-method-in-java/
     *
     * @param obj       the other object to test
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        // If the object isn't even the right type, return false
        if (!(obj instanceof Customer)) {
            return false;
        }

        // typecast obj to Customer so that we can compare data members
        Customer other = (Customer) obj;

        // Compare the data members and return accordingly
        // Two customers are considered equal if they have the all the same values.

        return addressL1.equals(other.getAddressL1()) &&
                addressL2.equals(other.getAddressL2()) &&
                city.equals(other.getCity()) &&
                state.equals(other.getState()) &&
                zip == other.getZip() &&
                email.equals(other.getEmail()) &&
                phoneNumber == other.getPhoneNumber() &&
                member == other.getMember() &&
                subscribe == other.getSubscribe();
    }
}
