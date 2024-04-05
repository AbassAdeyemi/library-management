package com.hayba.librarymanagement.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Table(name = "borrowing_records")
@Entity
public class BorrowingRecord {
    @Id
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "borrowed_id")
    private Book borrowed;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "borrower_id")
    private Patron borrower;
    private ZonedDateTime borrowedAt;
    private ZonedDateTime returnedAt;

    public Patron getBorrower() {
        return borrower;
    }

    public void setBorrower(Patron borrower) {
        this.borrower = borrower;
    }

    public Book getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Book borrowed) {
        this.borrowed = borrowed;
    }
}
