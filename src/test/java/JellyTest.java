import com.bsuir.nikitayasiulevich.Jelly;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JellyTest {
    @Test
    public void testGettersAndSetters() {
        Jelly jelly = new Jelly("123", "Strawberry", 5);

        assertEquals("123", jelly.getId());
        assertEquals("Strawberry", jelly.getFlavor());
        assertEquals(5, jelly.getQuantity());

        jelly.setId("456");
        jelly.setFlavor("Blueberry");
        jelly.setQuantity(10);

        assertEquals("456", jelly.getId());
        assertEquals("Blueberry", jelly.getFlavor());
        assertEquals(10, jelly.getQuantity());
    }
}
