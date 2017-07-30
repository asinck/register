// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HeaderTest {
	
    String name = "Fred";	
    Header header = new Header(null);
    
    @Test
    public void testHeader() {
        header.setHeader(name);
        assertEquals(name, header.getHeaderText());
        System.out.println("All tests passed.");
    }
}
