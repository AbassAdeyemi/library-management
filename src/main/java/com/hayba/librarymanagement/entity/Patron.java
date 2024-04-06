package com.hayba.librarymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;
import java.util.UUID;

@Table(name = "patrons")
@Entity
public class Patron {
    @Id
    private UUID id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phoneNumber;

    public Patron() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patron)) return false;
        Patron patron = (Patron) o;
        return Objects.equals(getId(), patron.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
