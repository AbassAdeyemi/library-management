package com.hayba.librarymanagement.service;

import com.hayba.librarymanagement.entity.Patron;
import com.hayba.librarymanagement.exception.ResourceNotFoundException;
import com.hayba.librarymanagement.repository.PatronRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public Page<Patron> getPatrons(Pageable pageable) {
        return patronRepository.findAll(pageable);
    }

    public Patron getPatron(String id) {
        //todo; validation
        UUID patronId = UUID.fromString(id);
        return patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron with id: %s not found".formatted(id)));
    }

    public Patron addPatron(Patron patron) {
        patron.setId(UUID.randomUUID());
        return patronRepository.save(patron);
    }

    public Patron updatePatronInformation(String id, Patron patron) {
        UUID patronId = UUID.fromString(id);
        Patron existing = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron with id: %s not found".formatted(id)));
        existing.setName(StringUtils.hasText(patron.getName())? patron.getName() : existing.getName());
        existing.setPhoneNumber(StringUtils.hasText(patron.getPhoneNumber())? patron.getPhoneNumber() : existing.getPhoneNumber());

        patronRepository.save(existing);
        return existing;
    }

    public void deletePatron(String id) {
        UUID patronId = UUID.fromString(id);

        patronRepository.findById(patronId)
                .ifPresentOrElse((patron) -> patronRepository.deleteById(patronId),
                        () -> new ResourceNotFoundException("Book with id: %s not found".formatted(id)) );
    }
}

