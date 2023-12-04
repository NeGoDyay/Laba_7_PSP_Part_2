import com.bsuir.nikitayasiulevich.Jelly;
import com.bsuir.nikitayasiulevich.JellyDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JellyDaoTest {
    private JellyDao jellyDao;
    private List<Jelly> testJellies;
    private static final String TEST_XML_FILE_PATH = "src/test/resources/test-jellies.xml";

    @Before
    public void setup() {
        jellyDao = new JellyDao();
        testJellies = new ArrayList<>();
        testJellies.add(new Jelly("1", "Strawberry", 5));
        testJellies.add(new Jelly("2", "Blueberry", 10));
        testJellies.add(new Jelly("3", "Raspberry", 7));

        // Create a test XML file
        jellyDao.saveJellies(testJellies);
    }

    @Test
    public void testAddJelly() {
        Jelly newJelly = new Jelly("4", "Cherry", 3);
        jellyDao.addJelly(newJelly);

        Jelly addedJelly = jellyDao.getJellyById("4");
        assertNotNull(addedJelly);
        assertEquals("Cherry", addedJelly.getFlavor());
        assertEquals(3, addedJelly.getQuantity());
    }

    @Test
    public void testGetAllJellies() {
        List<Jelly> jellies = jellyDao.getAllJellies();
        assertNotNull(jellies);
        assertEquals(3, jellies.size());

        Jelly firstJelly = jellies.get(0);
        assertEquals("1", firstJelly.getId());
        assertEquals("Strawberry", firstJelly.getFlavor());
        assertEquals(5, firstJelly.getQuantity());
    }

    @Test
    public void testUpdateJelly() {
        Jelly updatedJelly = new Jelly("2", "Mango", 15);
        jellyDao.updateJelly(updatedJelly);

        Jelly retrievedJelly = jellyDao.getJellyById("2");
        assertNotNull(retrievedJelly);
        assertEquals("Mango", retrievedJelly.getFlavor());
        assertEquals(15, retrievedJelly.getQuantity());
    }

    @Test
    public void testDeleteJelly() {
        jellyDao.deleteJelly("3");

        Jelly deletedJelly = jellyDao.getJellyById("3");
        assertNull(deletedJelly);
    }

    @Test
    public void testSaveAndRetrieveJelliesFromFile() {
        // Save the test jellies to a test XML file
        jellyDao.saveJelliesToFile(testJellies, TEST_XML_FILE_PATH);

        // Retrieve the jellies from the test XML file
        List<Jelly> retrievedJellies = jellyDao.retrieveJelliesFromFile(TEST_XML_FILE_PATH);
        assertNotNull(retrievedJellies);
        assertEquals(3, retrievedJellies.size());

        Jelly firstJelly = retrievedJellies.get(0);
        assertEquals("1", firstJelly.getId());
        assertEquals("Strawberry", firstJelly.getFlavor());
        assertEquals(5, firstJelly.getQuantity());


    }

    @After
    public void delete(){
        File testXmlFile = new File(TEST_XML_FILE_PATH);
        testXmlFile.delete();
    }
}
