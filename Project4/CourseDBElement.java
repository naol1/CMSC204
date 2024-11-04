/**
 * Course Data Element
 * @author Naol Gobena
 *
 */
public class CourseDBElement implements Comparable {
	private String id; // the course ID
	private int crn; // the CRN
	private int credits; // the number of credits
	private String roomNum; // the room number
	private String instructor; // the instructor name
	
	/**
	 * CDE parameter constructor
	 * @param courseID
	 * @param courseCRN
	 * @param courseCredits
	 * @param courseRoom
	 * @param courseInstructor
	 */
	public CourseDBElement(String courseID, int courseCRN, int courseCredits, String courseRoom, String courseInstructor) {
		id = courseID;
		crn = courseCRN;
		credits = courseCredits;
		roomNum = courseRoom;
		instructor = courseInstructor;
	}
	
	/**
	 * Compares this course data element to the given course data element
	 * @param element - course data element
	 * @return a negative integer if x.compareTo(y) < 0, zero if x.compareTo(y) == 0, and a positive integer if x.compareTo(y) > 0
	 */
	public int compareTo(CourseDBElement element) {
		int otherCRN = element.getCRN();
		if(crn == otherCRN) {
			return 0;
		} else if(crn > otherCRN) {
			return 1;
		} else {
			return -1;
		}
	}
	
	/**
	 * Returns the hashCode from the course CRN
	 */
	public int hashCode() {
		String key = Integer.toString(crn);
		return key.hashCode();
	}
	
	/**
	 * Returns the course CRN
	 * @return crn
	 */
	public int getCRN() {
		return crn;
	}
	
	/**
	 * Returns the course data element as a string
	 */
	public String toString() {
		String course = "\nCourse:" + id + " CRN:" + Integer.toString(crn) + " Credits:" + Integer.toString(credits) + " Instructor:" + instructor + " Room:" + roomNum;
		return course;
	}

	public void setCRN(int parseInt) {
		// TODO Auto-generated method stub
		
	}
}