package com.hayba.librarymanagement.service;

import com.hayba.librarymanagement.entity.Patron;
import com.hayba.librarymanagement.repository.PatronRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith({MockitoExtension.class})
public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    Patron patron = Patron.builder().
            id(UUID.fromString("1453ae45-2ad9-45b4-b147-e3069e688d46"))
            .name("Ola Ayo").phoneNumber("+2345678934234").build();

    @BeforeEach
    void setUp() {
        lenient().when(patronRepository.findById(any(UUID.class))).thenReturn(Optional.of(patron));
        lenient().when(patronRepository.save(any(Patron.class))).thenReturn(patron);
    }

    @Test
    void addBook() {
        Patron savedPatron = patronService.addPatron(patron);
        assert (savedPatron.getName().equals("Ola Ayo"));
        assert (savedPatron.getPhoneNumber().equals("+2345678934234"));
    }

    @Test
    void getBook() {
        Patron savedPatron = patronService.getPatron("1453ae45-2ad9-45b4-b147-e3069e688d46");
        assert (savedPatron.getName().equals("Ola Ayo"));
        assert (savedPatron.getPhoneNumber().equals("+2345678934234"));
    }
}
