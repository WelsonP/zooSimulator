//Ver. 1 June 6th, 2016
/* Edited by: Henry Li 
 * Future tasks and functionalities: 
 * checkOpen should be decided by some fields in the Region class as well
 * figure out assignTasks
 */
//Ver. 2 June 7th, 2016
/* Edited by: Henry Li
 * Added RegionSpec and created the RegionSpec class
 * -moved the large number of fields into a separate class 
 * -now the code in region is somewhat more cumbersome; constructor is cleaner
 * moved the daysSinceAccident back into the Region class 
 * 
 * 
 */
//Ver. 3 June 8th, 2016
/* documentation more clean 
 * TOOK ASSIGNTASKS OUT FOR THE TIME BEING
 * 
 * made variable names constant; fixed any unintentional errors
 * make toString 
 */

/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Region
 * Author: Henry Li
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: Regions are essentially the exhibits of the zoo which visitors 
      would pay money to visit. A Zoo object contains an array of Regions 
            and Region is abstract since different exhibits have different properties
            and thus methods within Region are implemented differently. 
            
            Region objects themselves contain a RegionSpec object, a name represented by
            a String, an array of Staff objects representing the staff working in the region, 
            an array of Animals representing the Animals within the area that visitors come 
            to visit. Each region also has an integer tracker that keeps track of how many days
            have passed since the last major accident in the region.
            
            The Region class is responsible for providing information on how much money 
            it has made through visitations, updating the accident days, and giving a status report 
            on the current state of the Region.
 */

//add maxSpecies and numSpecies and then update toString accordingly

abstract class Region {
  
  // make a class called regionspec around these fields? 
  // would have to call the fields using the accessor mutator methods 
  protected RegionSpec regSpec; //A regionSpec object; contains basic information about the Region (check RegionSpec class file for details).
  protected String regionName; //The name of a region; for example, an AmazonRainforest object could have a regionName such as "Forest Canopy".
  protected Staff[] regionStaff; //An array of Staff objects; these Staff are the workers responsible for this Region.
  protected Animal[] regionAnimals; //An array of Animal objects; these Animals are the animals which are inside the Region.
  protected int daysSinceAccident; //Accident days; how many days have passed since the last major accident occured.
  
  //This constant is the minimum number of accident days before an exhibit is able to reopen.
  private static final int DAY_OPEN_SINCE_ACCIDENT = 7;
  
  /* Constructor: Takes in an array of Staff, an array of Animal, a String representing the name, a RegionSpec object, 
          and an integer as the days since an accident last occured. 
  */
  
  public Region (Staff[] regionStaff, Animal[] regionAnimals, String regionName, RegionSpec regSpec, int daysSinceAccident) {
    this.regionName = regionName;
    this.regionAnimals = regionAnimals;
    this.regSpec = regSpec;
    this.daysSinceAccident = daysSinceAccident;
    this.regionStaff = regionStaff;
    
  }
  
  public Region(Staff[] regionStaff, Animal[] regionAnimals, String regionName, RegionSpec regSpec) {
  this.regionName = regionName;
  this.regionAnimals = regionAnimals;
  this.regSpec = regSpec;
  daysSinceAccident = 0;
  this.regionStaff = regionStaff;
  }
  
  //zoo needs to have a loadlist method that uses the constructor from region to make its array of staff and animals 
  
  /* Accessors and Mutators: 
    Any accessor returns the value of the private field which it is responsible for to the class 
     calling it. 
     
     Any mutator returns sets the value of the fields to a given input which the user declares.
  */
  public RegionSpec getRegionSpec() {
    return regSpec;
  }
  
  public void setRegionSpec(RegionSpec other) {
   regSpec = other; 
  }
  
  public String getRegionName() {
    return regionName;
  }
  
  public void setRegionName(String other) {
   regionName = other;
  }
  
  public Staff[] getRegionStaff() {
    return regionStaff;
  }
  
  public Animal[] getRegionAnimals() {
    return regionAnimals;
  }
  public int getDaysSinceAccident() {
    return daysSinceAccident;
  }
    
  /* -----------------------------------------------------------------------------------------
                            Abstract methods: 
     -----------------------------------------------------------------------------------------
   * The statusReport method is a void method which essentially prints out a series of
     statements depending on various fields in the Region child classes and the Region parent
     class. The status report should provide essential information to a manager or 
     custodian of the tasks which should be accomplished for the zoo to successfully run. 
     
     For example, if the statusReport() in the AmazonRainforest class were to be called, then 
     it would print a series of statements indicating if the trees were trimmed or not, and 
     if the forest needs rain as well as how many days it has been since rain.
   *
   */
  abstract void statusReport();
  
  /* The checkOpen method is a boolean method which returns true if the Region is currently 
     allowing visits and false if the Region has shut down due to violating certain set rules 
     or not meeting certain requirements which differ per Region type. 

   This method is abstract because the open conditions differ in different regions. For example,
     the 'open' condition in any AmazonRainforest region would be different from the 'open' 
     condition in the Aquarium class as both objects have drastically different fields 
     which define them.
   */
  abstract boolean checkOpen();
  
  /* The toFile method is a String which is not meant to be used by the user. This method is 
     called by the saveRegionList method in the Zoo class and returns a String which the 
     saveRegionList method then writes to a file to save the current state of the zoo. 
     
     This method is abstract because differing Regions have different fields which are 
     written to a .txt file differently. 
  */
  abstract String toFile();
  
  // abstract void assignTasks(int staffLocation);
  //should assign tasks ask for an employee and also the new task theyre getting?
  
  
  /* calcEarnings method: 
    Calculates the earnings of the region as the number of visits multiplied by the 
     cost of each visitation to the region. Returns a double.
  
  */ 
  public double calcEarnings() {
    return (regSpec.getNumVisits() * regSpec.getCost()); //should it be getNumVisits() * getCost()? or make a method in regionspec to calculate the cost as a double?
  }
  
  /* accidentReset: 
    If an accident were to occur, this method would have to be called and the number 
     of days since an accident has occured would be set to 0. 
  */
  public void accidentReset() { //same deal with these methods; the coding seems somewhat unclean
    daysSinceAccident = 0;
  }
  
  /*accidentUpdate: 
    Updates the days since an accident last occured.
  */
  public void accidentUpdate(int in) {
    daysSinceAccident = in;
  }
  
  
  /*checkDays: 
   Returns the number of days since an accident occured minus the minimum days 
    before the Region can open up again. 
    
    This is used in the checkOpen methods, and if checkDays() returns a negative
    integer then the Region is not suitable to open yet. 
  */
  public int checkDays () {
    return daysSinceAccident - DAY_OPEN_SINCE_ACCIDENT; 
  }
  
  /*updateStaff: 
   This method is called in removeStaff() to get rid of any pointers in regionStaff
   it checks if an employee was deleted by looking for null strings
   When it finds the null it will fill it with the last value from the array and update the numStaff of the region
    
   */
  public void updateStaff() { //checks list for null Strings and fills them in with the last value
    for (int i = 0; i < regSpec.getNumStaff(); i++) {
      if (regionStaff[i].getStaffName() == null) {
         regionStaff[i] = regionStaff[regSpec.getNumStaff() - 1];
         regionStaff[regSpec.getNumStaff() - 1] = null;
          regSpec.setNumStaff(regSpec.getNumStaff() - 1);
       }
     }
    } // @@@@@@@@@@@@@@@@THIS METHOD MISSING SOMETHING IN FIRST IF STATEMENT
  
  
 /*regionStaffString: 
   This method creates a long string which calls on the toString method in Staff. 
  The String contains all the information of every Staff object in the Region.
  */ 
  public String regionStaffString() {
    String current = "";
    for (int i = 0; i < regSpec.getNumStaff(); i++) {
      current = current + regionStaff[i].toString() + "\n"; //depends on the functionality of Staff toString
    }
    return current;
  }
  
 /*animalListString: 
   This method creates a long string which calls on the toString method in Animal. 
    The String contains all the information on every Animal object in the Region.
  
  */ 
  public String animalListString() {
    String current = "";
    for (int i = 0; i < regSpec.getNumAnimals(); i++) {
      current = current + regionAnimals[i].toString() + "\n"; //depends on the functionality of Animal toString
    }
    return current; 
  }

 /*regionStaffFile: 
   This method helps the toFile method by looping through the list of Staff in the Region 
    and concatenating the Staff IDs of each member onto an empty String. This method is meant 
    to be called by the toFile method in the Region child classes. 
  
  */
  
  public String regionStaffFile() {
   String current = "";
    for (int i = 0; i < regSpec.getNumStaff(); i++) {
     current = current + regionStaff[i].getStaffNum() + "\r\n";
    }
   return current;
  }

 /*regionAnimalFile: 
   This method helps the toFile method by looping through the list of Animals in the Region 
    and concatenating the species and population of each Animal onto an empty String. This method is meant 
    to be called by the toFile method in the Region child classes. 
  */ 

  public String regionAnimalFile() {
    String current = ""; 
    for (int i = 0; i < regSpec.getNumAnimals(); i++) {
      current = current + regionAnimals[i].getSpecies() + "\r\n" + regionAnimals[i].getPopulation() + "\r\n";
    }
    return current;
  }
  
  /*toString: 
   This method returns a String which prints out all the information related to a Region  
    in a readable manner. 
  */
  public String toString() {
   return regSpec.toString() + "\nRegion Staff:\n" + regionStaffString() + "Animal List:\n" + animalListString() + "\nTotal Population of animals in this region: " + countAnimalPopn() + "\n\n"; 
  }
  
 /*countAnimalPopn:
   This method returns the population of all animals within the zoo.  
   */
  public int countAnimalPopn() {
    int count = 0;
    for (int i = 0; i < regSpec.getNumAnimals(); i++) {
     count += regionAnimals[i].getPopulation();
    }
   return count;
  }
 
  
  //hey sean look here
   private void fixRegionList() {
      for (int i = 0; i < regSpec.getMaxStaff() - regSpec.getNumStaff(); i++) {
         int position = 0; 
         while (regionStaff[position] != null) {
            position++;
         }
         
         while (position < regSpec.getMaxStaff()-i-1) {
            regionStaff[position] = regionStaff[position+1];
            position++;
         }
         
         regionStaff[position] = null;
      } 
   }

 public void createNewAnimal(String name, int pop) {
   regionAnimals[regSpec.getNumAnimals()] = new Animal(name, pop);
   regSpec.setNumAnimals(regSpec.getNumAnimals()+1);
 }
  
}