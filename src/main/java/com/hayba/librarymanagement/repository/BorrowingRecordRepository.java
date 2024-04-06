package com.hayba.librarymanagement.repository;

import com.hayba.librarymanagement.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, UUID> {
    Optional<BorrowingRecord> findByBorrowed_IdAndBorrower_Id(UUID borrowedId, UUID borrowerId);
}
