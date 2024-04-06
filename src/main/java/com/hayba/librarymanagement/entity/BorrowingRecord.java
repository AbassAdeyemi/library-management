package com.hayba.librarymanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "borrower_id")
    private Patron borrower;
    private ZonedDateTime borrowedAt;
    private ZonedDateTime returnedAt;

    public BorrowingRecord() {
    }

    private BorrowingRecord(Builder builder) {
        setId(builder.id);
        setBorrowed(builder.borrowed);
        setBorrower(builder.borrower);
        setBorrowedAt(builder.borrowedAt);
        setReturnedAt(builder.returnedAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

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

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(ZonedDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public ZonedDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(ZonedDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }


    public static final class Builder {
        private UUID id;
        private Book borrowed;
        private Patron borrower;
        private ZonedDateTime borrowedAt;
        private ZonedDateTime returnedAt;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder borrowed(Book val) {
            borrowed = val;
            return this;
        }

        public Builder borrower(Patron val) {
            borrower = val;
            return this;
        }

        public Builder borrowedAt(ZonedDateTime val) {
            borrowedAt = val;
            return this;
        }

        public Builder returnedAt(ZonedDateTime val) {
            returnedAt = val;
            return this;
        }

        public BorrowingRecord build() {
            return new BorrowingRecord(this);
        }
    }
}
