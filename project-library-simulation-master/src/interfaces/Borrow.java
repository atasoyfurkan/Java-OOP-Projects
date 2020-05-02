package interfaces;

import librarymembers.LibraryMember;

/**
 * @author ATASOY
 * interface for borrowable books
 */
public interface Borrow {
	/**
	 * customize fields according to borrowing a book
	 * @param member the member which wants to borrow the book
	 * @param tick an Integer to show current time
	 */
	public void borrowBook(LibraryMember member, int tick);
	public void extend(LibraryMember member, int tick);
	
}
