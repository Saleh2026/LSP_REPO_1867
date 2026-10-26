package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Book objects and coordinates library lending operations.
 * Enforces unique ISBNs and a maximum limit of three concurrently borrowed books.
 *
 * @author [Saleh]
 */
public class Library {
    private static final int MAX_BORROWED_BOOKS = 3;
    private final Map<String, Book> books;

    /**
     * Constructs an empty Library instance using a Map keyed by ISBN.
     */
    public Library() {
        this.books = new LinkedHashMap<>();
    }

    /**
     * Adds a new book to the library collection if its ISBN is not already present.
     *
     * @param book the Book instance to add to the library
     * @throws IllegalArgumentException if a book with the same ISBN already exists or if book is null
     */
    public void addBook(Book book) {
        if (book == null || book.getIsbn() == null) {
            throw new IllegalArgumentException("Book and its ISBN cannot be null.");
        }
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists in the library.");
        }
        books.put(book.getIsbn(), book);
    }

    /**
     * Finds and returns the Book matching the specified ISBN.
     *
     * @param isbn the ISBN to search for
     * @return the matching Book instance, or null if absent
     */
    public Book findBook(String isbn) {
        if (isbn == null) {
            return null;
        }
        return books.get(isbn);
    }

    /**
     * Borrows a book with the given ISBN from the library.
     * Locates the book, verifies the library's three-book borrowing limit, and delegates
     * the state change to the Book object.
     *
     * @param isbn the ISBN of the book to borrow
     * @throws IllegalArgumentException if the ISBN is not found in the library
     * @throws IllegalStateException    if the book is already borrowed or if three books are already borrowed
     */
    public void borrowBook(String isbn) {
        Book book = findBook(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " does not exist in the library.");
        }
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book with ISBN " + isbn + " is already borrowed.");
        }
        if (getBorrowedBookCount() >= MAX_BORROWED_BOOKS) {
            throw new IllegalStateException("Borrowing limit reached: no more than " + MAX_BORROWED_BOOKS + " books may be borrowed at one time.");
        }
        book.borrowBook();
    }

    /**
     * Returns a borrowed book with the given ISBN to the library.
     * Locates the book and delegates the state change to the Book object.
     *
     * @param isbn the ISBN of the book to return
     * @throws IllegalArgumentException if the ISBN is not found in the library
     * @throws IllegalStateException    if the book is currently available (not borrowed)
     */
    public void returnBook(String isbn) {
        Book book = findBook(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " does not exist in the library.");
        }
        if (book.isAvailable()) {
            throw new IllegalStateException("Book with ISBN " + isbn + " is already available.");
        }
        book.returnBook();
    }

    /**
     * Retrieves all books in the library that are currently available for borrowing.
     *
     * @return a List of available Book instances, or an empty List if none are available
     */
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    /**
     * Retrieves all books in the library written by the specified author.
     * Matching is exact (case-sensitive) and includes both available and borrowed books.
     *
     * @param author the author name to match exactly
     * @return a List of matching Book instances, or an empty List if no matches are found
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> matches = new ArrayList<>();
        if (author == null) {
            return matches;
        }
        for (Book book : books.values()) {
            if (author.equals(book.getAuthor())) {
                matches.add(book);
            }
        }
        return matches;
    }

    /**
     * Retrieves all books in the library sorted by ISBN in ascending lexicographical order.
     * Does not modify the internal library collection or any book's availability state.
     *
     * @return a new List of all Book instances sorted by ISBN ascending, or an empty List if empty
     */
    public List<Book> getBooksSortedByIsbn() {
        List<Book> sortedBooks = new ArrayList<>(books.values());
        sortedBooks.sort(Comparator.comparing(Book::getIsbn));
        return sortedBooks;
    }

    /**
     * Returns the current number of borrowed books in the library.
     *
     * @return the count of currently borrowed (unavailable) books
     */
    public int getBorrowedBookCount() {
        int count = 0;
        for (Book book : books.values()) {
            if (!book.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}
