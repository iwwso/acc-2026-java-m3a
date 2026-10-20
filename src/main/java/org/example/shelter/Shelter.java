package org.example.shelter;

import org.example.model.AdoptionRecord;
import org.example.model.Animal;
import org.example.model.AnimalId;
import org.example.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Generic shelter. T is bounded by Animal, so only animals can be stored
 * and methods of Animal (getSpecies(), getAge() ...) can be called on T.
 *
 * @author Karyna Litvinova
 */
public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();
    private final List<AdoptionRecord> adoptionHistory = new ArrayList<>();

    public void addAnimal(T animal){
        if (animal == null) {
            throw new IllegalArgumentException("Animal cannot be null");
        }
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        // read-only copy, so the internal list cannot be changed from outside
        return List.copyOf(animals);
    }

    public List<T> findBySpecies(String species){
        if (species == null || species.isBlank()) {
            return List.of();
        }
        String wanted = species.trim();
        return CollectionUtils.filter(animals, a -> a.getSpecies().equalsIgnoreCase(wanted));
    }

    public List<T> findAvailableAnimals(){
        return CollectionUtils.filter(animals, Animal::isAvailable);
    }

    public Optional<T> findById(String id){
        AnimalId wanted = new AnimalId(id);
        // AnimalId.equals() compares values, not references
        return animals.stream()
                .filter(a -> a.getId().equals(wanted))
                .findFirst();
    }

    public void markAsAdopted(String id){
        markAsAdopted(id, "Unknown");
    }

    // overloaded method: also records who adopted the animal
    public void markAsAdopted(String id, String adopterName){
        T animal = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No animal found with id: " + id));

        if (!animal.isAvailable()) {
            throw new IllegalStateException(animal.getName() + " is already adopted");
        }
        AdoptionRecord record = new AdoptionRecord(animal, LocalDate.now(), adopterName);
        animal.markAsAdopted();
        adoptionHistory.add(record);
    }

    public List<AdoptionRecord> getAdoptionHistory(){
        return List.copyOf(adoptionHistory);
    }

    public List<T> sortedByName(){
        return CollectionUtils.sorted(animals,
                Comparator.comparing(Animal::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public List<T> sortedByAge(){
        return CollectionUtils.sorted(animals, Comparator.comparingInt(Animal::getAge));
    }
}