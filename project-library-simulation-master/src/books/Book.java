package books;
import librarymembers.*;
/**
 * 
 * abstract class to determine general content of a book
 * @author ATASOY
 *
 */
public abstract class Book {
	/**
	 * id of a book
	 */
	private int bookID;
	/**
	 * type of a book whether is a "Printed" or "Handwritten"
	 */
	private String bookType;
	/**
	 * a boolean to control whether the book is taken or not
	 */
	protected boolean isTaken;
	/**
	 * member which has the book
	 */
	protected LibraryMember whoHas;
	/**
	 * constructor according to book id and book type initialize fields of book
	 * @param bookID id of a book
	 * @param bookType type of a book
	 */
	public Book(int bookID, String bookType) {
		this.bookID = bookID;
		this.bookType = bookType;
		isTaken = false;
		whoHas = null;
	}
	
	public String getBookType() {
		return bookType;
	}
	public boolean getIsTaken() {
		return isTaken;
	}
	public int getBookID() {
		return bookID;
	}
	/**
	 * abstract method to sending request to return book
	 * @param member which wants to return book
	 */
	public abstract void returnBook(LibraryMember member);
}