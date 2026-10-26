package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a book in the Library Lending System.
 * Stores book metadata (ISBN, title, and author) and manages its availability state.
 *
 * @author [Saleh]
 */
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean available;

    /**
     * Constructs a new Book with the specified ISBN, title, and author.
     * New books start as available for borrowing.
     *
     * @param isbn   the unique ISBN identifier of the book
     * @param title  the title of the book
     * @param author the author of the book
     */
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    /**
     * Returns the ISBN of the book.
     *
     * @return the ISBN string
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author string
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Checks whether the book is currently available to be borrowed.
     *
     * @return true if the book is available; false if it is currently borrowed
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Marks the book as borrowed (unavailable) if it is currently available.
     *
     * @throws IllegalStateException if the book is already borrowed
     */
    public void borrowBook() {
        if (!available) {
            throw new IllegalStateException("Book with ISBN " + isbn + " is already borrowed.");
        }
        available = false;
    }

    /**
     * Marks the book as returned (available) if it is currently borrowed.
     *
     * @throws IllegalStateException if the book is already available
     */
    public void returnBook() {
        if (available) {
            throw new IllegalStateException("Book with ISBN " + isbn + " is already available.");
        }
        available = true;
    }

    /**
     * Returns a formatted string representation of the book and its current availability status.
     * Format: {@code <isbn> <title> by <author> [AVAILABLE|BORROWED]}
     *
     * @return formatted string representation of the book
     */
    @Override
    public String toString() {
        String status = available ? "[AVAILABLE]" : "[BORROWED]";
        return isbn + " " + title + " by " + author + " " + status;
    }
}
