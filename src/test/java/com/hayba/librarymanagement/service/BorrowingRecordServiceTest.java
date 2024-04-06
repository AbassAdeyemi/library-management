package com.hayba.librarymanagement.service;

import com.hayba.librarymanagement.entity.Book;
import com.hayba.librarymanagement.entity.BorrowingRecord;
import com.hayba.librarymanagement.entity.Patron;
import com.hayba.librarymanagement.repository.BookRepository;
import com.hayba.librarymanagement.repository.BorrowingRecordRepository;
import com.hayba.librarymanagement.repository.PatronRepository;
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
public class BorrowingRecordServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @InjectMocks
    private BorrowingRecordService borrowingRecordService;

    Book book = Book.builder().id(UUID.fromString("bc7d30fa-f74c-4846-a156-663dd2be43b8"))
            .title("The Grass is Always Greener")
            .author("Jeffrey Archer")
            .availableCopies(4)
            .publicationYear("1990")
            .isbn("1-86092-049-7")
            .build();

    Patron patron = Patron.builder().
            id(UUID.fromString("1453ae45-2ad9-45b4-b147-e3069e688d46"))
            .name("Ola Ayo").phoneNumber("+2345678934234").build();

    BorrowingRecord borrowingRecord = BorrowingRecord.builder()
            .id(UUID.fromString("389e19d8-51a8-4700-9482-b36e9421d0af"))
            .borrowed(book).borrower(patron).build();

    @BeforeEach
    void setUp() {
        lenient().when(bookRepository.findById(any(UUID.class))).thenReturn(Optional.of(book));
        lenient().when(patronRepository.findById(any(UUID.class))).thenReturn(Optional.of(patron));
        lenient().when(borrowingRecordRepository.findByBorrowed_IdAndBorrower_Id(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(borrowingRecord));
    }

    @Test
    void borrowBook() {
        int copiesBeforeBorrowing = book.getAvailableCopies();
        borrowingRecordService.borrowBook(book.getId().toString(), patron.getId().toString());

        assert(book.getAvailableCopies() == copiesBeforeBorrowing - 1 );
    }

    @Test
    void returnBook() {
        borrowingRecordService.borrowBook(book.getId().toString(), patron.getId().toString());

        int copiesAfterBorrowing = book.getAvailableCopies();

        borrowingRecordService.returnBook(book.getId().toString(), patron.getId().toString());

        assert(book.getAvailableCopies() == copiesAfterBorrowing + 1 );
    }

}
