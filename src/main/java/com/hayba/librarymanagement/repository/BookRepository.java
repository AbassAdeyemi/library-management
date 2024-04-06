package com.hayba.librarymanagement.repository;

import com.hayba.librarymanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
