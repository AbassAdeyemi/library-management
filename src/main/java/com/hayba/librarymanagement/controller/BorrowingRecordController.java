package com.hayba.librarymanagement.controller;

import com.hayba.librarymanagement.entity.BorrowingRecord;
import com.hayba.librarymanagement.service.BorrowingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable String bookId, @PathVariable String patronId) {
        return new ResponseEntity<>(borrowingRecordService.borrowBook(bookId, patronId), HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public void returnBook(@PathVariable String bookId, @PathVariable String patronId) {
        borrowingRecordService.returnBook(bookId, patronId);
    }
}
