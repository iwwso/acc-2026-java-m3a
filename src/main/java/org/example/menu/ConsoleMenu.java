package org.example.menu;

import org.example.model.Animal;
import org.example.model.AnimalId;
import org.example.model.Bird;
import org.example.model.Cat;
import org.example.model.Dog;
import org.example.model.Rabbit;

import org.example.shelter.Shelter;

import java.util.List;
import java.util.Scanner;

/**
 * Console user interface for the animal shelter.
 *
 * @author Karyna Litvinova
 */
public class ConsoleMenu {
    private static final String SPECIES_HINT = "Dog/Cat/Bird/Rabbit";

    // menu items are records (immutable data carriers)
    private static final List<MenuOption> OPTIONS = List.of(
            new MenuOption(1, "Add animal"),
            new MenuOption(2, "List all animals"),
            new MenuOption(3, "Find animals by species"),
            new MenuOption(4, "List available animals"),
            new MenuOption(5, "Mark animal as adopted"),
            new MenuOption(6, "Sort animals by name"),
            new MenuOption(7, "Sort animals by age"),
            new MenuOption(0, "Exit")
    );

    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addAnimal();
                case 2 -> printAnimals(shelter.getAllAnimals(), "No animals in the shelter.");
                case 3 -> findBySpecies();
                case 4 -> printAnimals(shelter.findAvailableAnimals(), "No animals available for adoption.");
                case 5 -> markAsAdopted();
                case 6 -> printAnimals(shelter.sortedByName(), "No animals in the shelter.");
                case 7 -> printAnimals(shelter.sortedByAge(), "No animals in the shelter.");
                case 0 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Unknown option, try again.");
            }
            System.out.println();
        }
    }

    private void printMenu(){
        OPTIONS.forEach(o -> System.out.println(o.number() + ". " + o.label()));
        System.out.println();
    }

    private void addAnimal() {
        String species = readNonEmpty("Species (" + SPECIES_HINT + "): ");
        String name = readNonEmpty("Name: ");
        int age = readNonNegativeInt("Age: ");

        try {
            Animal animal = createAnimal(species, name, age);
            shelter.addAnimal(animal);
            System.out.println("Added: " + animal);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // a new animal type only needs one new line here
    private Animal createAnimal(String species, String name, int age) {
        AnimalId id = new AnimalId();
        return switch (species.trim().toLowerCase()) {
            case "dog" -> new Dog(id, name, age);
            case "cat" -> new Cat(id, name, age);
            case "bird" -> new Bird(id, name, age);
            case "rabbit" -> new Bird(id, name, age);
            default -> throw new IllegalArgumentException(
                    "Unknown species: " + species + ". Allowed: " + SPECIES_HINT + ".");
        };
    }

    private void findBySpecies() {
        String species = readNonEmpty("Species to search for: ");
        printAnimals(shelter.findBySpecies(species), "No animals of species '" + species + "' found.");
    }

    private void markAsAdopted() {
        String id = readNonEmpty("Animal id: ");
        try {
            shelter.markAsAdopted(id);
            System.out.println("Animal marked as adopted.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printAnimals(List<Animal> animals, String emptyMessage) {
        if (animals.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        animals.forEach(System.out::println);
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Value cannot be empty.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private int readNonNegativeInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value >= 0) {
                return value;
            }
            System.out.println("Value cannot be negative.");
        }
    }
}