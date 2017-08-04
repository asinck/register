// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    //http://www.fakenamegenerator.com/
    private String al1        = "184 Broadcast Drive";
    private String al2        = "";
    private String city       = "Chantilly";
    private String state      = "VA";
    private int zip           = 22021;
    private String email      = "example@example.com";
    private int phoneNumber   = 1234567890;
    private boolean member    = true;
    private boolean subscribe = false;
    private Customer customer =
            new Customer(al1, al2, city, state, zip, email,
                    phoneNumber, member, subscribe);
    
    @Test
    public void testFooter() {
        
        assertEquals(al1, customer.getAddressL1());
        assertEquals(al2, customer.getAddressL2());
        assertEquals(city, customer.getCity());
        assertEquals(state, customer.getState());
        assertEquals(zip, customer.getZip());
        assertEquals(email, customer.getEmail());
        assertEquals(phoneNumber, customer.getPhoneNumber());
        assertEquals(member, customer.getMember());
        assertEquals(subscribe, customer.getSubscribe());
        
        al1 = "1920 Radford Street";
        al2 = "random L2";
        city = "Louisville";
        state = "KY";
        zip = 40027;
        email = "user@example.com";
        phoneNumber = 987654321;
        member = false;
        subscribe = true;
        
        customer.setAddressL1(al1);
        assertEquals(al1, customer.getAddressL1());
        
        customer.setAddressL2(al2);
        assertEquals(al2, customer.getAddressL2());
        
        customer.setCity(city);
        assertEquals(city, customer.getCity());
        
        customer.setState(state);
        assertEquals(state, customer.getState());
        
        customer.setZip(zip);
        assertEquals(zip, customer.getZip());
        
        customer.setEmail(email);
        assertEquals(email, customer.getEmail());
        
        customer.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, customer.getPhoneNumber());
        
        customer.setMember(member);
        assertEquals(member, customer.getMember());
        
        customer.setSubscribe(subscribe);
        assertEquals(subscribe, customer.getSubscribe());

    }
}
