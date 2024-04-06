package com.hayba.librarymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;
import java.util.UUID;

@Table(name = "books")
@Entity
public class Book {
    @Id
    private UUID id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    @NotEmpty
    private String publicationYear;
    @NotEmpty
    private String isbn;

    @Min(1)
    private Integer availableCopies;


    @Version
    private int version;

    public Book() {
    }

    private Book(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setAuthor(builder.author);
        setPublicationYear(builder.publicationYear);
        setIsbn(builder.isbn);
        setAvailableCopies(builder.availableCopies);
        version = builder.version;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public static final class Builder {
        private UUID id;
        private @NotEmpty String title;
        private @NotEmpty String author;
        private @NotEmpty String publicationYear;
        private @NotEmpty String isbn;
        private @Min(1) Integer availableCopies;
        private int version;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder title(@NotEmpty String val) {
            title = val;
            return this;
        }

        public Builder author(@NotEmpty String val) {
            author = val;
            return this;
        }

        public Builder publicationYear(@NotEmpty String val) {
            publicationYear = val;
            return this;
        }

        public Builder isbn(@NotEmpty String val) {
            isbn = val;
            return this;
        }

        public Builder availableCopies(@Min(1) Integer val) {
            availableCopies = val;
            return this;
        }

        public Builder version(int val) {
            version = val;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}


