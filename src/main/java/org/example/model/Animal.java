package org.example.model;

import lombok.Getter;

/**
 * Base class of the sealed animal hierarchy.
 * Only the listed subclasses may extend it.
 * Lombok @Getter generates getId(), getName(), getAge(), getAdoptionStatus().
 *
 * @author Karyna Litvinova
 */
@Getter
public sealed abstract class Animal permits Dog, Cat, Bird {
    private final AnimalId id;
    private final String name;
    private final int age;
    private AdoptionStatus adoptionStatus;

    protected Animal(AnimalId id, String name, int age) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.id = id;
        this.name = name.trim();
        this.age = age;
        this.adoptionStatus = AdoptionStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return adoptionStatus == AdoptionStatus.AVAILABLE;
    }

    public void markAsAdopted(){
        this.adoptionStatus = AdoptionStatus.ADOPTED;
    }

    public abstract String getSpecies();

    @Override
    public String toString(){
        return id + " | " + name + " | " + age + " years old | " + getSpecies() + " | " + adoptionStatus;
    }
}