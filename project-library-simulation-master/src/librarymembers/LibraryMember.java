package librarymembers;
import java.util.ArrayList;
import books.*;
/**
 * abstract class to determine general content of a library member
 * @author ATASOY
 *
 */
public abstract class LibraryMember {
	/**
	 * id of a library member
	 */
	private int id;
	/**
	 * max number of a books which a library member can take
	 */
	private int maxNumberOfBooks;
	/**
	 * time limit for a library member to borrow a book
	 */
	private int timeLimit;
	/**
	 * type of a member whether it is a academic or student
	 */
	private String memberType;
	/**
	 * a list for the history of a whole books
	 */
	protected ArrayList<Book> history;
	/**
	 * a list for the current book which a library member has
	 */
	protected ArrayList<Book> current;
	/**
	 * number of books that borrowed from library
	 */
	protected int numberBorrowed;
	
	/**
	 * constructor of library member class to initialize fields
	 * @param id id of a person
	 * @param maxNumberOfBooks max number of a books which a library member can take
	 * @param timeLimit time limit for a library member to borrow a book
	 * @param memberType type of a member whether it is a academic or student
	 */
	public LibraryMember(int id, int maxNumberOfBooks, int timeLimit, String memberType) {
		this.id = id;
		this.maxNumberOfBooks = maxNumberOfBooks;
		this.timeLimit = timeLimit;
		this.memberType = memberType;
		history = new ArrayList<Book>();
		current = new ArrayList<Book>();
		numberBorrowed = 0;
	}
	
	public int getMaxNumberOfBooks() {
		return maxNumberOfBooks;
	}
	
	public int getTimeLimit() {
		return timeLimit;
	}
	
	/**
	 * abstract method to get the history list
	 * @return a list of a book history
	 */
	public abstract ArrayList<Book> getTheHistory();
	
	public ArrayList<Book> getCurrent() {
		return current;
	}
	public int getId() {
		return id;
	}
	public String getMemberType() {
		return memberType;
	}
	public int getNumberBorrowed() {
		return numberBorrowed;
	}
	public void setNumberBorrowed(int numberBorrowed) {
		this.numberBorrowed = numberBorrowed;
	}
}