package librarymembers;
import java.util.ArrayList;
import books.*;
/**
 * a type of a library member which is student
 * @author ATASOY
 *
 */
public class Student extends LibraryMember {
	/**
	 * constructor to initialize fields
	 * @param id id of a student
	 */
	public Student(int id) {
		super(id, 10, 20, "S");
	}
	
	/** 
	 * to get the history list
	 * @see librarymembers.LibraryMember#getTheHistory()
	 */
	@Override
	public ArrayList<Book> getTheHistory() {
		return history;
	}
}