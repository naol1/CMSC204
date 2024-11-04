import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Course Data Structure
 * 
 * This structure manages a hash table of CourseDBElements,
 * supporting addition and retrieval of elements by CRN.
 * 
 * @author Naol Tesfaye Gobena
 */
public class CourseDBStructure implements CourseDBStructureInterface {
    public LinkedList<CourseDBElement>[] hashTable;

    /**
     * Constructor for testing purposes.
     * 
     * @param test         a string indicating this is a test instance
     * @param numOfCourses the initial size of the hash table
     */
    public CourseDBStructure(String test, int numOfCourses) {
        hashTable = new LinkedList[numOfCourses];
        for (int i = 0; i < numOfCourses; i++) {
            hashTable[i] = new LinkedList<CourseDBElement>();
        }
    }

    /**
     * Parameterized constructor.
     * 
     * @param numOfCourses the initial size of the hash table
     */
    public CourseDBStructure(int numOfCourses) {
        hashTable = new LinkedList[numOfCourses];
        for (int i = 0; i < numOfCourses; i++) {
            hashTable[i] = new LinkedList<CourseDBElement>();
        }
    }

    /**
     * Adds an element to the hash table if it doesn't already exist.
     * 
     * @param element the CourseDBElement to be added
     */
    public void add(CourseDBElement element) {
        int hashCode = element.hashCode();
        int index = hashCode % getTableSize();

        if (hashTable[index].isEmpty()) {
            hashTable[index].add(element);
        } else {
            boolean elementExists = false;
            for (int i = 0; i < hashTable[index].size(); i++) {
                if (hashTable[index].get(i).compareTo(element) == 0) {
                    elementExists = true;
                    break;
                }
            }
            if (!elementExists) {
                hashTable[index].add(element);
            }
        }
    }

    /**
     * Retrieves a CourseDBElement by its CRN if it exists.
     * 
     * @param crn the CRN of the CourseDBElement to retrieve
     * @return the CourseDBElement with the specified CRN
     * @throws IOException if the element is not found
     */
    public CourseDBElement get(int crn) throws IOException {
        String key = Integer.toString(crn);
        int indexHashCode = key.hashCode();
        int index = indexHashCode % getTableSize();

        for (int i = 0; i < hashTable[index].size(); i++) {
            if (hashTable[index].get(i).getCRN() == crn) {
                return hashTable[index].get(i);
            }
        }

        throw new IOException("CourseDBElement with CRN " + crn + " not found.");
    }

    /**
     * Returns the size of the hash table.
     * 
     * @return the number of indexes in the hash table
     */
    public int getTableSize() {
        return hashTable.length;
    }

	@Override
	public ArrayList<String> showAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
