// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FooterTest {
	
    String name = "Fred";	
    // MessageUtil messageUtil = new MessageUtil(message);
    Footer footer = new Footer(null);
    
    // public static void main(String[] args) {
    //     testFooter();
    // }
    
    @Test
    public void testFooter() {
        footer.setStatus(name);
        assertEquals(name, footer.getStatus());
        System.out.println("All tests passed.");
    }
}
