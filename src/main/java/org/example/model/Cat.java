package org.example.model;

/**
 * @author Karyna Litvinova
 */
public final class Cat extends Animal{
    // full constructor
    public Cat(AnimalId id, String name, int age){
        super(id,name,age);
    }

    // overloaded constructor/ id is generated automatically. constructor chaining
    public Cat(String name, int age){
        this(new AnimalId(), name, age);
    }

    @Override
    public String getSpecies(){
        return "Cat";
    }
}