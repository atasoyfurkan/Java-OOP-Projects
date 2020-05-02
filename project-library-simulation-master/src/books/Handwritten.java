package books;
import interfaces.ReadInLibrary;
import librarymembers.*;

/**
 * @author ATASOY
 * a type of book class specialize to handwritten books
 */
public class Handwritten extends Book implements ReadInLibrary {
	/**
	 * constructor to initialize fields
	 * @param bookID id of a book
	 */
	public Handwritten(int bookID) {
		super(bookID, "H");
	}
	
	/** 
     * customize fields according to returning a book
	 * @see books.Book#returnBook(librarymembers.LibraryMember)
	 * @param member the member which wants to return the book
	 */
	@Override
	public void returnBook(LibraryMember member) {
		isTaken = false;
		whoHas = null;
	}
	
	/** 
	 * customize fields according to reading a book
	 * @see interfaces.ReadInLibrary#readBook(librarymembers.LibraryMember)
	 * @param member the member which wants to read the book
	 */
	public void readBook(LibraryMember member) {
		isTaken = true;
		whoHas = member;
	}
	
}