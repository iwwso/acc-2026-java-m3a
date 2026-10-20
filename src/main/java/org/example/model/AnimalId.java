package org.example.model;

import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Immutable identifier of an animal
 * - class is final
 * - field is private final
 * - no setters
 * Lombok @EqualsAndHashCode makes two ids with the same value equal
 *
 * @author Karyna Litvinova
 */
@EqualsAndHashCode
public final class AnimalId {
    private final String value;

    // Constructor overloading: random id
    public AnimalId(){
        this(UUID.randomUUID().toString());
    }

    // id from an existing value (e.g. typed by the user)
    public AnimalId(String value){
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Id cannot be empty");
        }
        this.value = value.trim();
    }

    @Override
    public String toString(){
        return value;
    }
}