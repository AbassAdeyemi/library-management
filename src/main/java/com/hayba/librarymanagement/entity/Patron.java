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

    private Patron(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setPhoneNumber(builder.phoneNumber);
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


    public static final class Builder {
        private UUID id;
        private @NotEmpty String name;
        private @NotEmpty String phoneNumber;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder name(@NotEmpty String val) {
            name = val;
            return this;
        }

        public Builder phoneNumber(@NotEmpty String val) {
            phoneNumber = val;
            return this;
        }

        public Patron build() {
            return new Patron(this);
        }
    }
}
