package com.hayba.librarymanagement.service;

import com.hayba.librarymanagement.entity.Book;
import com.hayba.librarymanagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith({MockitoExtension.class})
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    Book book = Book.builder().id(UUID.fromString("bc7d30fa-f74c-4846-a156-663dd2be43b8"))
            .title("The Grass is Always Greener")
            .author("Jeffrey Archer")
            .availableCopies(4)
            .publicationYear("1990")
            .isbn("1-86092-049-7")
            .build();

    @BeforeEach
    void setUp() {
        lenient().when(bookRepository.findById(any(UUID.class))).thenReturn(Optional.of(book));
        lenient().when(bookRepository.save(any(Book.class))).thenReturn(book);
    }

    @Test
    void addBook() {
        Book savedBook = bookService.addBook(book);
        assert(savedBook.getTitle().equals("The Grass is Always Greener"));
        assert (savedBook.getAuthor().equals("Jeffrey Archer"));
    }

    @Test
    void getBook() {
        Book savedBook = bookService.getBook("bc7d30fa-f74c-4846-a156-663dd2be43b8");
        assert(savedBook.getIsbn().equals("1-86092-049-7"));
        assert(savedBook.getPublicationYear().equals("1990"));
    }
}
