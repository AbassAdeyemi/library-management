package com.hayba.librarymanagement.controller;

import com.hayba.librarymanagement.entity.Patron;
import com.hayba.librarymanagement.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patrons")
public class PatronController {

    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public Page<Patron> getPatrons(Pageable pageable) {
        return patronService.getPatrons(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatron(@PathVariable String id) {
        return ResponseEntity.ok(patronService.getPatron(id));
    }

    @PostMapping
    public ResponseEntity<Patron> addBook(@Valid Patron patron) {
        return new ResponseEntity<>(patronService.addPatron(patron), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updateBookInformation(@PathVariable String id, Patron patron) {
        return ResponseEntity.ok(patronService.updatePatronInformation(id, patron));
    }
}
