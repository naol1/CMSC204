import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Course Data Manager
 * @author Naol Gobena
 *
 */
public class CourseDBManager implements CourseDBManagerInterface {
	private CourseDBStructure courseDataStructure;
	private static int DEFAULT_SIZE = 10;
	
	/**
	 * CDM constructor
	 */
	public CourseDBManager() {
		courseDataStructure = new CourseDBStructure(DEFAULT_SIZE);
	}
	
	/**
	 * Adds a course to the course data structure
	 * @param id - course id
	 * @param crn - course crn
	 * @param credits - course credits
	 * @param roomNum - course room number
	 * @param instructor - course instructor name
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		courseDataStructure.add(element);
	}
	
	/**
	 * Gets the course data element of the given course crn
	 * @param crn - course crn
	 */
	public CourseDBElement get(int crn) {
		try {
			return courseDataStructure.get(crn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Adds the course data elements from the given file to the course data structure
	 * @param input - given file
	 */
	public void readFile(File input) throws FileNotFoundException {
		try {
			Scanner reader = new Scanner(input);
			
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String [] data = line.split(" ",5);
				CourseDBElement element = new CourseDBElement(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], data[4]);
				courseDataStructure.add(element);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a string array list of all courses in the course data structure
	 */
	public ArrayList<String> showAll() {
		ArrayList<CourseDBElement> courses = new ArrayList<CourseDBElement>();
		for(int i = 0; i < courseDataStructure.getTableSize(); i++) {
			if(!courseDataStructure.hashTable[i].isEmpty()) { 
				for(int j = 0; j < courseDataStructure.hashTable[i].size(); j++) {
					courses.add(courseDataStructure.hashTable[i].get(j)); 
				} 
			} 
		}
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < courses.size(); i++) {
			list.add(courses.get(i).toString());
		}
		return list;
	}
}