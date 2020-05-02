package interfaces;

import librarymembers.LibraryMember;
/**
 * interface for a books to read in library
 * @author ATASOY
 *
 */
public interface ReadInLibrary {
	
	/**
	 * customize fields according to reading a book
	 * @see interfaces.ReadInLibrary#readBook(librarymembers.LibraryMember)
	 * @param member the member which wants to read the book
	 */
	public void readBook(LibraryMember member);
	
}
