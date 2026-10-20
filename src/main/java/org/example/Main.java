package org.example;

import org.example.menu.ConsoleMenu;
import org.example.model.*;
import org.example.shelter.Shelter;

/**
 * @author Karyna Litvinova
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Animal Shelter!");

        // create shelter object
        Shelter<Animal> shelter = new Shelter<>();

        // pre-load some data. both overloaded constructors are used
        shelter.addAnimal(new Dog(new AnimalId(),"Buddy",3));
        shelter.addAnimal(new Cat(new AnimalId(),"Luna",5));
        shelter.addAnimal(new Bird("Kiwi",1));
        shelter.addAnimal(new Rabbit("Snowball",2));

        // create a ConsoleMenu object
        ConsoleMenu consoleMenu = new ConsoleMenu(shelter);
        // start the menu loop
        consoleMenu.start();
    }
}