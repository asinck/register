// https://www.tutorialspoint.com/junit/junit_executing_tests.htm

package register;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HeaderTest {
    
    private Header header = new Header(null);
    
    @Test
    public void testHeader() {
        String name = "Fred";
        header.setHeader(name);
        assertEquals(name, header.getHeaderText());
        System.out.println("All tests passed.");
    }
}
