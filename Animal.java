//Version 1
/* Created by: Henry Li
 * June 8th, 2016
 * 
 * Animal is still a misleading name; as one 'Animal' object does not correspond to exactly one monkey or one zebra, 
 * since it contains a population of many of the same animal; maybe call it AnimalCount?
 */
/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Animal
 * Author: Henry Li
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: The Animal class is supposed to represent a specific
        species of animal and its population within the zoo. 
            It has no class responsibilites other than its toString 
            method, and they make up the array of animals within 
            each region of the zoo. 
            
            Each Animal object contains a String indicating the 
            species name of the Animal, and an integer indicating 
            how many of those species there are within the Region. 
 */

public class Animal {
  private String species; //Species name: String
  private int population; //Population of animals: Integer 
  
  /* Constructor: An animal constructor taking in the 
    species of animal and the population of animals
  */
  public Animal (String species, int population) {
    this.species = species;
    this.population = population;
  }
  
  
  /* Accessors and Mutators: 
    Any accessor returns the value of the private field which it is responsible for to the class 
     calling it. 
     
     Any mutator returns sets the value of the fields to a given input which the user declares.
  */
  public String getSpecies() {
    return species;
  }

  public int getPopulation() {
    return population;
  }
  
  public void setSpecies(String spec) {
    species = spec;
  }
  
  public void setPopulation(int pop){
    population = pop;
  }
 
  
  /* toString: 
    This method returns a String which prints out all the information related to a Region  
     in a readable manner. 
  */
  public String toString () { //maybe need to add a \n
    return "Species: " + species + "\nPopulation: " + population;
  }
}