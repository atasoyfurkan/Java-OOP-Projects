package books;
import interfaces.*;
import librarymembers.*;

/**
 * @author ATASOY
 * a type of book class specialize to printed books
 */
public class Printed extends Book implements ReadInLibrary, Borrow {
	/**
	 * an integer shows the deadline of the book
	 */
	private int deadLine;
	/**
	 * a boolean to whether a book is extended
	 */
	private boolean isExtended;
	
	/**
	 * constructor to initialize printed's fields
	 * @param bookID id of a book
	 */
	public Printed(int bookID) {
		super(bookID, "P");
		deadLine = 0;
		isExtended = false;
	}
	
	/**
	 *  customize fields according to returning a book
	 * @see books.Book#returnBook(librarymembers.LibraryMember)
	 * @param member the member which wants to return the book
	 */
	public void returnBook(LibraryMember member) {
		isTaken = false;
		whoHas = null;
		deadLine = 0;
		isExtended = false;
	}
	
	/**
	 * customize fields according to reading a book
	 * @param member the member which wants to read the book
	 * @see interfaces.ReadInLibrary#readBook(librarymembers.LibraryMember)
	 */
	public void readBook(LibraryMember member) {
		isTaken = true;
		whoHas = member;
	}
	/**
	 * customize fields according to borrowing a book
	 * @param member the member which wants to borrow book
	 * @param tick an integer to show time
	 * @see interfaces.Borrow#borrowBook(librarymembers.LibraryMember, int)
	 */
	@Override
	public void borrowBook(LibraryMember member, int tick) {
		isTaken = true;
		whoHas = member;
		deadLine = tick + member.getTimeLimit();
	}
	/**
	 * customize fields according to extending a book
	 * @param member the member which wants to extend book
	 * @param tick an integer to show time 
	 * @see interfaces.Borrow#extend(librarymembers.LibraryMember, int)
	 */
	@Override
	public void extend(LibraryMember member, int tick) {
		deadLine += member.getTimeLimit();
		isExtended = true;
	}
	public boolean getIsExtended() {
		return isExtended;
	}
	public int getDeadLine() {
		return deadLine;
	}
}