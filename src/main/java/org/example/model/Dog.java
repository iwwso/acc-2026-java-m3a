package org.example.model;

/**
 * @author Karyna Litvinova
 */
public final class Dog extends Animal{
    // full constructor
    public Dog(AnimalId id, String name, int age){
        super(id,name,age);
    }

    // overloaded constructor/ id is generated automatically. constructor chaining
    public Dog(String name, int age){
        this(new AnimalId(), name, age);
    }

    @Override
    public String getSpecies(){
        return "Dog";
    }
}