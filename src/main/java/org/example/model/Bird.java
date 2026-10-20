package org.example.model;

/**
 * @author Karyna Litvinova
 */
public final class Bird extends Animal{
    // full constructor
    public Bird(AnimalId id, String name, int age){
        super(id,name,age);
    }

    // overloaded constructor/ id is generated automatically. constructor chaining
    public Bird(String name, int age){
        this(new AnimalId(), name, age);
    }

    @Override
    public String getSpecies(){
        return "Bird";
    }
}