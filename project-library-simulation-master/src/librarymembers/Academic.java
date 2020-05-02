package librarymembers;
import java.util.ArrayList;
import books.*;
/**
 * a type of a library member which is academician
 * @author ATASOY
 *
 */
public class Academic extends LibraryMember {
	/**
	 * 
	 * constructor to initialize a academic person's fields
	 * @param id
	 */
	public Academic(int id) {
		super(id, 20, 50, "A");
	}
	/** 
	 * in order to get the history list
	 * @see librarymembers.LibraryMember#getTheHistory()
	 */
	@Override
	public ArrayList<Book> getTheHistory() {
		return history;
	}
}