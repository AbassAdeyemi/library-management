package com.hayba.librarymanagement.service;

import com.hayba.librarymanagement.Constants;
import com.hayba.librarymanagement.entity.Book;
import com.hayba.librarymanagement.entity.BorrowingRecord;
import com.hayba.librarymanagement.entity.Patron;
import com.hayba.librarymanagement.exception.BookUnavailableException;
import com.hayba.librarymanagement.exception.ResourceNotFoundException;
import com.hayba.librarymanagement.repository.BookRepository;
import com.hayba.librarymanagement.repository.BorrowingRecordRepository;
import com.hayba.librarymanagement.repository.PatronRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.hayba.librarymanagement.Constants.UTC;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository,
                                  BookRepository bookRepository,
                                  PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Transactional
    public BorrowingRecord borrowBook(String bookId, String patronId) {
        Book book = bookRepository.findById(UUID.fromString(bookId))
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: %s".formatted(bookId)));

        Patron patron = patronRepository.getReferenceById(UUID.fromString(patronId));

        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                        .id(UUID.randomUUID())
                                .borrowed(book)
                                        .borrower(patron)
                                                .borrowedAt(ZonedDateTime.now(ZoneId.of(UTC)))
                                                        .build();
        borrowingRecordRepository.save(borrowingRecord);

        if(book.getAvailableCopies() <= 0) {
            throw new BookUnavailableException("Book with id: %s is not available for borrowing".formatted(bookId));
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return borrowingRecord;
    }

    @Transactional
    public void returnBook(String bookId, String patronId) {
        Book book = bookRepository.findById(UUID.fromString(bookId))
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: %s".formatted(bookId)));

        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBorrowed_IdAndBorrower_Id(UUID.fromString(bookId), UUID.fromString(patronId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("No borrowing record found for bookId: %s and patronId: %s".formatted(bookId, patronId)));

        if (borrowingRecord.getReturnedAt() != null) {
            return;
        }

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        borrowingRecord.setReturnedAt(ZonedDateTime.now(ZoneId.of(UTC)));
        borrowingRecordRepository.save(borrowingRecord);
    }
}
