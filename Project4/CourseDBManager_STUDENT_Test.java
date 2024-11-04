import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test for the CourseDBManager class
 * 
 * @author Naol Tesfaye Gobena
 */
public class CourseDBManager_STUDENT_Test {
    private CourseDBManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new CourseDBManager();
    }

    @After
    public void tearDown() throws Exception {
        manager = null;
    }

    @Test
    public void testAdd() {
        try {
            manager.add("ENGL101", 34567, 3, "LC102", "John Smith");
            manager.add("PHYS121", 45678, 4, "SC300", "Jane Doe");
            manager.add("HIST210", 56789, 3, "HB110", "Richard Roe");
        } catch (Exception e) {
            fail("This should not have caused an Exception");
        }
    }

    @Test
    public void testGet() {
        manager.add("ENGL101", 34567, 3, "LC102", "John Smith");
        manager.add("PHYS121", 45678, 4, "SC300", "Jane Doe");
        manager.add("HIST210", 56789, 3, "HB110", "Richard Roe");
        try {
            manager.get(34567);
            manager.get(45678);
            manager.get(56789);
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
    }

    @Test
    public void testReadFile() {
        try {
            File inputFile = new File("Test.txt");
            PrintWriter inFile = new PrintWriter(inputFile);
            inFile.println("ENGL101 34567 3 LC102 John Smith");
            inFile.println("PHYS121 45678 4 SC300 Jane Doe");
            inFile.println("HIST210 56789 3 HB110 Richard Roe");
            inFile.close();
            manager.readFile(inputFile);
        } catch (Exception e) {
            fail("Should not have thrown an exception");
        }
    }

    
}
