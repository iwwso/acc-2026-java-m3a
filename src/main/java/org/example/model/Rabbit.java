package org.example.model;

/**
 * @author Karyna Litvinova
 */
public final class Rabbit extends Animal{
    // full constructor
    public Rabbit(AnimalId id, String name, int age){
        super(id,name,age);
    }

    // overloaded constructor: id is generated automatically. constructor chaining
    public Rabbit(String name, int age){
        this(new AnimalId(), name, age);
    }

    @Override
    public String getSpecies(){
        return "Rabbit";
    }
}