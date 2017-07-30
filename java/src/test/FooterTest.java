// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FooterTest {
	
    String name = "Fred";	
    Footer footer = new Footer(null);
    
    @Test
    public void testFooter() {
        footer.setStatus(name);
        assertEquals(name, footer.getStatus());
        System.out.println("All tests passed.");
    }
}
