package library;
import java.util.Scanner;
import books.*;
import librarymembers.*;
/**
 * Library class is the center of the all processing.
 * organizes all actions and sending request to the other class.
 * @author ATASOY
 *
 */
public class Library {
	/**
	 * Collection of Book instances which library has
	 */
	private Book Book[];
	/**
	 * Collection of LibraryMember instances which is registered to library
	 */
	private LibraryMember LibraryMember[];
	/**
	 * total fee that is payed to library
	 */
	private int totalFee;
	/**
	 * scanner object to read input and making valid request to other class
	 */
	private Scanner scanner;
	/**
	 * next id will be given to the book that will register to the library
	 */
	private int nextBookID;
	/**
	 * next id will be given to the member that will register to the library
	 */
	private int nextMemberID;
	/**
	 * construct Book and LibraryMember array and initialize nextBookID and nextMemberID
	 * @param scanner to read input and initialize field scanner
	 */
	public Library(Scanner scanner) {
		this.scanner = scanner;
		Book = new Book[1000000];
		LibraryMember = new LibraryMember[1000000];
		nextBookID = 1;
		nextMemberID = 1;
		totalFee = 0;
	}
	/**
	 * Register new book to the library
	 */
	public void addBook() {
		String bookType = scanner.next(); 
		
		if(nextBookID == 1000000 + 1) return;
		Book newBook;
		if(bookType.equals("H")) 
			newBook = new Handwritten(nextBookID++);
		else
			newBook = new Printed(nextBookID++);
		Book[nextBookID - 2] = newBook;	
//		System.out.printf("Created new book: %s [id: %d]\n", newBook.getBookType(), newBook.getBookID());
	}
	/**
	 * Register new member to the library
	 */
	public void addMember() {
		String memberType = scanner.next();
		
		if(nextMemberID == 1000000 + 1) return;
		LibraryMember newMember;
		if(memberType.equals("A"))
			newMember = new Academic(nextMemberID++);
		else
			newMember = new Student(nextMemberID++);
		LibraryMember[nextMemberID - 2] = newMember;
//		System.out.printf("Created new member: %s [id: %d]\n", newMember.getMemberType(), newMember.getId());
	}
	/**
	 * checks the validity of borrowing book after that makes necessary request to other class in order to borrow book from library
	 * @param tick The integer to show time.
	 */
	public void borrowBook(int tick) {
		int bookID = scanner.nextInt();
		int memberID = scanner.nextInt();
		
		if(bookID < nextBookID && memberID < nextMemberID) {
			Book cBook = Book[bookID - 1];
			LibraryMember cMember = LibraryMember[memberID - 1];
			if(cBook.getBookType().equals("P")
			   && !cBook.getIsTaken()
			   && cMember.getMaxNumberOfBooks() > cMember.getNumberBorrowed()) {
				boolean flag = false; 
				for(Book element: cMember.getCurrent()) {
					if(tick > ((Printed) element).getDeadLine())
						flag = true;
				}
				if(!flag) {
					((Printed) cBook).borrowBook(cMember, tick);
					if(!cMember.getTheHistory().contains(cBook)) 
						cMember.getTheHistory().add(cBook);
					cMember.getCurrent().add(cBook);
					cMember.setNumberBorrowed(cMember.getNumberBorrowed() + 1);
//					System.out.printf("The book [%d] was borrowed by member [%d]\n",cBook.getBookID(), cMember.getId());
				}
			} 
		}
	}
	/**
	 * checks the validity of returning book after makes necessary request to other class in order to return books to library.
	 * @param tick The integer to show time.
	 */
	public void returnBook(int tick) {
		int bookID = scanner.nextInt();
		int memberID = scanner.nextInt();
		
		if(bookID < nextBookID && memberID < nextMemberID) {
			Book cBook = Book[bookID - 1];
			LibraryMember cMember = LibraryMember[memberID - 1];
			if(cMember.getCurrent().contains(cBook)) {
				if(cBook.getBookType().equals("P")) { 
					cMember.setNumberBorrowed(cMember.getNumberBorrowed() - 1);
					if(((Printed) cBook).getDeadLine() != 0
					   && tick > ((Printed) cBook).getDeadLine()) {
						totalFee += tick - ((Printed) cBook).getDeadLine();
//						System.out.print("Fee:" + totalFee + " ");
				   }
				}
				cBook.returnBook(cMember);
				cMember.getCurrent().remove(cBook);
	//			System.out.printf("The book [%d] was returned by member [%d]\n",cBook.getBookID(), cMember.getId());
			}
		}
	}
	/**
	 * checks the validity of extending book after that makes request to other class in order to extend book.
	 * @param tick The integer to show time.
	 */
	public void extendBook(int tick) {
		int bookID = scanner.nextInt();
		int memberID = scanner.nextInt();
		
		if(bookID < nextBookID && memberID < nextMemberID) {
			Book cBook = Book[bookID - 1];
			LibraryMember cMember = LibraryMember[memberID - 1];
			if(cMember.getCurrent().contains(cBook)
			   && cBook.getBookType().equals("P")
			   && tick <= ((Printed) cBook).getDeadLine()
			   && !((Printed) cBook).getIsExtended()) {
				((Printed) cBook).extend(cMember, tick);
//				System.out.printf("The book [%d] was extended by member [%d]\n",cBook.getBookID(), cMember.getId());
			}
		}
	}
	/**
	 * checks the validity of reading in library after that makes request to other class in order to read the book in library.
	 */
	public void readInLibrary() {
		int bookID = scanner.nextInt();
		int memberID = scanner.nextInt();
		
		if(bookID < nextBookID && memberID < nextMemberID) {
			Book cBook = Book[bookID - 1];
			LibraryMember cMember = LibraryMember[memberID - 1];
			if(cBook.getBookType().equals("P")
			   && !cBook.getIsTaken()) {
				((Printed) cBook).readBook(cMember);
//				System.out.printf("The book [%d] was readInLibrary by member [%d]\n",cBook.getBookID(), cMember.getId());
				if(!cMember.getTheHistory().contains(cBook)) 
					cMember.getTheHistory().add(cBook);
				cMember.getCurrent().add(cBook);
			}
			if(cBook.getBookType().equals("H")
			   && !cBook.getIsTaken()
			   && cMember instanceof Academic) {
				((Handwritten) cBook).readBook(cMember);
//				System.out.printf("The book [%d] was readInLibrary by member [%d]\n",cBook.getBookID(), cMember.getId());
				if(!cMember.getTheHistory().contains(cBook)) 
					cMember.getTheHistory().add(cBook);
				cMember.getCurrent().add(cBook);
			}
		}
	}
	
	public int getTotalFee() {
		return totalFee;
	}

	public Book[] getBook() {
		return Book;
	}

	public LibraryMember[] getLibraryMember() {
		return LibraryMember;
	}
}