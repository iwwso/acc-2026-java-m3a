package org.example.model;

import java.time.LocalDate;

/**
 * One entry of adoption history. A record is an immutable data carrier:
 * constructor, accessors, equals/hashCode and toString are generated.
 *
 * @author Karyna Litvinova
 */
public record AdoptionRecord(Animal animal, LocalDate adoptionDate, String adopterName) {

    // compact constructor for validation
    public AdoptionRecord {
        if (adopterName == null || adopterName.isBlank()) {
            throw new IllegalArgumentException("Adopter name cannot be empty");
        }
    }

    @Override
    public String toString() {
        return adoptionDate + " | " + animal.getName() + " (" + animal.getSpecies() + ") adopted by " + adopterName;
    }
}