package com.hayba.librarymanagement.controller;

import com.hayba.librarymanagement.entity.Patron;
import com.hayba.librarymanagement.service.PatronService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all patrons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application.json", schema = @Schema(implementation = Patron.class))
            })
    })
    @GetMapping
    public Page<Patron> getPatrons(Pageable pageable) {
        return patronService.getPatrons(pageable);
    }

    @Operation(summary = "Get patron by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application.json", schema = @Schema(implementation = Patron.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatron(@PathVariable String id) {
        return ResponseEntity.ok(patronService.getPatron(id));
    }

    @Operation(summary = "Add patron")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application.json", schema = @Schema(implementation = Patron.class))
            })
    })
    @PostMapping
    public ResponseEntity<Patron> addBook(@Valid Patron patron) {
        return new ResponseEntity<>(patronService.addPatron(patron), HttpStatus.CREATED);
    }

    @Operation(summary = "Update patron information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application.json", schema = @Schema(implementation = Patron.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updateBookInformation(@PathVariable String id, Patron patron) {
        return ResponseEntity.ok(patronService.updatePatronInformation(id, patron));
    }

    @Operation(summary = "Delete patron")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public void deletePatron(@PathVariable String id) {
        patronService.deletePatron(id);
    }
}
