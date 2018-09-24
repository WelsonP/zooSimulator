//Ver. 1 June 7th, 2016
/* Created the RegionSpec class to make the Region constructor less cumbersome;
 * 
 * 
 */

/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: RegionSpec 
 * Author: Henry Li
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: The purpose of the RegionSpec class is to decrease the amount of 
      fields read in the constructor of any of the Region classes. Every single 
            Region child class is composed of a RegionSpec object which contains the 
            number of staff and maximum staff per region; number of animals and maximum animal
            types per region; the number of visits and the cost of each visit. 
            
 */
public class RegionSpec {
  private int numStaff; 
  private int maxStaff;
  private int numAnimals;
  private int maxAnimals;
  private int numVisits; 
  private double cost;
  
  /* METHOD HEADER: 
   * Constructor: Takes in 5 integers and a double and creates a RegionSpec object.
  */
  
  public RegionSpec(int numStaff, int maxStaff, int numAnimals, int maxAnimals, int numVisits, double cost) {
    this.numStaff = numStaff;
    this.maxStaff = maxStaff;
    this.numAnimals = numAnimals;
    this.maxAnimals = maxAnimals;
    this.numVisits = numVisits;
    this.cost = cost;
  }
  
  public RegionSpec(int maxStaff, int maxAnimals, double cost) {
  	numStaff = 0;
	numAnimals = 0;
	numVisits = 0;
	this.maxStaff = maxStaff;
	this.maxAnimals = maxAnimals;
	this.cost = cost;
  }
  
  /* Accessors and Mutators: 
    Any accessor returns the value of the private field which it is responsible for to the class 
     calling it. 
     
     Any mutator returns sets the value of the fields to a given input which the user declares.
  */
  
  public int getNumStaff() {
    return numStaff;
  }
  
  public int getMaxStaff() {
    return maxStaff;
  }
  
  public int getNumVisits() {
    return numVisits;
  }
  
  public void setNumVisits(int vi) {
    numVisits = vi;
  }
  
  public void setMaxStaff(int max) {
    maxStaff = max;
  }
  
  public void setNumStaff(int num) {
    numStaff = num;
  }
  
  public void setNumAnimals(int num) {
    numAnimals = num;
  }
  
  public void setMaxAnimals(int max) {
    maxAnimals = max;
  }
  
  public int getMaxAnimals() {
    return maxAnimals;
  }

  public int getNumAnimals() { 
   return numAnimals;
  }
  
  public void setCost(double money) {
    cost = money;
  }

  public double getCost () {
    return cost; 
  }
  
  /* Method Header: 
   * This method returns a String which prints out all the information related to a RegionSpec 
   * in a readable manner. 
   */ 
  public String toString () {
    return ("Number of staff/Max staff capacity: " + numStaff + "/" + maxStaff + "\nNumber of visits: " + numVisits +
            "\nCost: $" + cost + "\n");
  }

}