package com.hayba.librarymanagement.repository;

import com.hayba.librarymanagement.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatronRepository extends JpaRepository<Patron, UUID> {
}
