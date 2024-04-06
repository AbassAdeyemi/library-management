package com.hayba.librarymanagement.controller;

import com.hayba.librarymanagement.entity.BorrowingRecord;
import com.hayba.librarymanagement.service.BorrowingRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Borrow book from library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application.json", schema = @Schema(implementation = BorrowingRecord.class))
            })
    })
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable String bookId, @PathVariable String patronId) {
        return new ResponseEntity<>(borrowingRecordService.borrowBook(bookId, patronId), HttpStatus.CREATED);
    }

    @Operation(summary = "Return book to library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public void returnBook(@PathVariable String bookId, @PathVariable String patronId) {
        borrowingRecordService.returnBook(bookId, patronId);
    }
}
