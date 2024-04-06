package com.hayba.librarymanagement.service;

import com.hayba.librarymanagement.entity.Book;
import com.hayba.librarymanagement.exception.ResourceNotFoundException;
import com.hayba.librarymanagement.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBook(String id) throws ResourceNotFoundException{
        UUID bookId = UUID.fromString(id);
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id: %s not found".formatted(id)));
    }

    public Book addBook(Book book) {
        book.setId(UUID.randomUUID());
        return bookRepository.save(book);
    }

    public Book updateBookInformation(String id, Book book) throws ResourceNotFoundException{
        UUID bookId = UUID.fromString(id);
        Book existing = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id: %s not found".formatted(id)));
        existing.setAuthor(StringUtils.hasText(book.getAuthor())? book.getAuthor() : existing.getAuthor());
        existing.setIsbn(StringUtils.hasText(book.getIsbn())? book.getIsbn() : existing.getIsbn());
        existing.setTitle(StringUtils.hasText(book.getTitle())? book.getTitle() : existing.getTitle());
        existing.setPublicationYear(StringUtils.hasText(book.getPublicationYear())? book.getPublicationYear() : existing.getPublicationYear());

        bookRepository.save(existing);
        return existing;
    }

    public void deleteBook(String id) throws ResourceNotFoundException {
        UUID bookId = UUID.fromString(id);
        bookRepository.findById(bookId)
                .ifPresentOrElse((book) -> bookRepository.deleteById(bookId),
                        () -> new ResourceNotFoundException("Book with id: %s not found".formatted(id)) );
    }
}
