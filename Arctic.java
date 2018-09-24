//Ver. 1 June 6th, 2016 
/*Edited by: Henry Li 
 *
 */
//Ver. 3 June 8th, 2016
/* added constructor 
 * 
 * 
 */
/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Arctic
 * Author: Henry Li
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: The Arctic class is a child class of the 
       Arctic class. This class can be instantiated and 
            has the fields of an Aquarium. 
            
            Its functionalities are those of the Region class: 
             1. Print out a status report
              2. Check if the Region is open 
              3. Provide string representations of itself both 
                 to the user and the Zoo class to be saved.
 */

public class Arctic extends Region {
  private double waterTemp; //The water temperature in degrees Celsius; represented as a double.
  private double snowVolume; //The volume of snow in the Region in cubic metres; represented as a double.
  private boolean animalsFed;  //Boolean indicating whether the animals are fed or not. 
  
  private static final double MAX_WATER_TEMP = 10; //The maximum acceptable water temperature.
  private static final double MIN_SNOW_VOLUME = 50; //The minimum acceptable volume of snow.
  
  private static final double DEF_WATER_TEMP = -10;
  private static final double DEF_SNOW_VOLUME = 80;
  
   /* Constructor: Takes in the same fields as the Region constructor as well as two doubles indicating the waterTemp and snowVolume fields, and a
             boolean indicating if the animals are fed or not. 
    */
  public Arctic (Staff[] regionStaff, Animal[] regionAnimals, String regionName, RegionSpec regSpec, int daysSinceAccident, double waterTemp, double snowVolume,
                   boolean animalsFed) {
    super(regionStaff, regionAnimals, regionName, regSpec, daysSinceAccident);
    this.waterTemp = waterTemp;
    this.snowVolume = snowVolume;
    this.animalsFed = animalsFed;
  }
  
  public Arctic (Staff[] regionStaff, Animal[] regionAnimals, String regionName, RegionSpec regSpec) {
    super(regionStaff, regionAnimals, regionName, regSpec);
    waterTemp = DEF_WATER_TEMP;
    snowVolume = DEF_SNOW_VOLUME;
    animalsFed = false;
  }
  
  /* Constructor: Empty constructor setting everything to null and setting everything to default values.
  
   */ 
  public Arctic() {
    super(null, null, "Arctic", null, 0);
    waterTemp = 0;
    snowVolume = 0;
    animalsFed = true;
  }

  /** Accessors and Mutators: 
    Any accessor returns the value of the private field which it is responsible for to the class 
     calling it. 
     
     Any mutator returns sets the value of the fields to a given input which the user declares.
  */
  
  public double getWaterTemp() {
    return waterTemp;
  }

  public void setWaterTemp(double waterTemp) {
    this.waterTemp = waterTemp;
  }

  public double getSnowVolume() {
    return snowVolume;
  }

  public void setSnowVolume(double snowVolume) {
    this.snowVolume = snowVolume;
  }

  public boolean isAnimalsFed() {
     return animalsFed;
  }

  public void setAnimalsFed(boolean animalsFed) {
     this.animalsFed = animalsFed;
  }
  
 public static double getMaxWaterTemp() {
      return MAX_WATER_TEMP;
  }

  public static double getMinSnowVolume() {
      return MIN_SNOW_VOLUME;
  }
  
  /* checkOpen: 
  
     For any Arctic region to be open, the animals must be fed and the temperature must be below
     the maximum acceptable temperature. There also must have been at least one week since the 
     last major accident occured. 
     
   */ 
  public boolean checkOpen() { //arbitrary open condition 
    if (animalsFed && waterTemp < MAX_WATER_TEMP && checkDays() >= 0) { //compare to see if its openable or not
      return true;
    }
    return false; 
  }
  
  /* statusReport: 
  
   Prints out a bunch of statements to the console depending on the current state of 
    the region. From reading these series of print statements, the user will be able to 
    determine which tasks must urgently be done within the region.
  
  */ 
  public void statusReport () { //maybe change statusReport to tasks needed to do? 
    System.out.println("Status report: ");
    if (!animalsFed) {
      System.out.println("The animals need to be fed: ");
    }
    if (snowVolume < MIN_SNOW_VOLUME) {
      System.out.println("The snow volume is too low. Increase it by at least" + (MIN_SNOW_VOLUME - snowVolume) + "m^3."); {
      }
    }
    if (waterTemp > MAX_WATER_TEMP) {
      System.out.println("The current temperature of the water is too high. Decrease it by " + waterTemp + "degrees C");
    }
  }   
 
  /* toFile: 
     Returns the string which will be written by the BufferedWriter in the Zoo class to save
     the information of this Region in a .txt file.
 
  */ 
  public String toFile() {
    return "Arctic\r\n" + regionName + "\r\n" + regSpec.getMaxStaff() + "\r\n" + regSpec.getNumStaff() + "\r\n" + regionStaffFile() + regSpec.getMaxAnimals() + "\r\n" + 
                   regSpec.getNumAnimals() + "\r\n" + regionAnimalFile() + regSpec.getNumVisits() + "\r\n" + regSpec.getCost() + "\r\n" + daysSinceAccident +
                   "\r\n" + waterTemp + "\r\n" + snowVolume + "\r\n" + animalsFed + "\r\n";
    
  }
 
  /* toString: 
    This method returns a String which prints out all the information related to a Region  
     in a readable manner. 
  */
  public String toString() {
    return regionName + "\nRegion Type: Arctic\n" + super.toString() + "Region Specific Details:\nWater temperature: " + waterTemp + " degrees Celcius\nSnow Volume: " + snowVolume + " L\nAnimalsFed: " + animalsFed + "\n\n";
  }
  
}