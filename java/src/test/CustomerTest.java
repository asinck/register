// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    //http://www.fakenamegenerator.com/
    String al1        = "184 Broadcast Drive";
    String al2        = "";
    String city       = "Chantilly";
    String state      = "VA";
    int zip           = 22021;
    String email      = "example@example.com";
    int phoneNumber   = 1234567890;
    boolean member    = true;
    boolean subscribe = false;
    Customer customer =
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
        assertEquals(phoneNumber, customer.getPhonenumber());
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
        
        customer.setPhonenumber(phoneNumber);
        assertEquals(phoneNumber, customer.getPhonenumber());
        
        customer.setMember(member);
        assertEquals(member, customer.getMember());
        
        customer.setSubscribe(subscribe);
        assertEquals(subscribe, customer.getSubscribe());

    }
}
