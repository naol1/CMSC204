import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * 
 * @author Naol Tesfaye Gobena
 */
public class CourseDBStructureTest {
    CourseDBStructure cds, testStructure;

    @Before
    public void setUp() throws Exception {
        cds = new CourseDBStructure(20); // Initialize CourseDBStructure with a capacity of 20
        testStructure = new CourseDBStructure("Testing", 20); // Initialize with test constructor
    }

    @After
    public void tearDown() throws Exception {
        cds = testStructure = null; // Clean up after each test
    }

    /**
     * Test the table size for CourseDBStructures constructed
     * with both constructors
     */
    @Test
    public void testGetTableSize() {
        assertEquals(20, cds.getTableSize()); // Verify size is 20 for primary constructor
        assertEquals(20, testStructure.getTableSize()); // Verify size is 20 for test constructor
    }

    /**
     * Test the hashTable for CourseDBStructures constructed
     * with both constructors
     */
    @Test
    public void testHashTable() {
        // Verify hashTable size and functionality for main constructor
        assertEquals(20, cds.hashTable.length);
        CourseDBElement cde = new CourseDBElement("CMSC500", 39999, 4, "SC100", "Nobody InParticular"); 
        cds.add(cde);
        
        // Retrieve and test the CourseDBElement added
        LinkedList<CourseDBElement> list = cds.hashTable[cde.hashCode() % cds.getTableSize()];
        assertEquals(39999, list.get(0).getCRN()); // Verify that the CRN is correct

        // Verify hashTable size and functionality for test constructor
        cds = new CourseDBStructure("Testing", 20);
        assertEquals(20, cds.hashTable.length);  
        
        // Add element and check if it was added at correct index
        cds.add(cde);
        list = cds.hashTable[cde.hashCode() % 20];
        assertEquals(39999, list.get(0).getCRN()); // Verify CRN is correct for the element in test structure
    }
}
