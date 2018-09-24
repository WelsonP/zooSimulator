//Ver. 1 June 6th, 2016
/*Edited by: Henry Li
 * created the class 
 * 
//Ver. 2 June 7th, 2016
/* Edited by: Henry Li
 * -same thing for AmazonRainforest
 * - should there be an AmazonSpec class?? Constructor is very clustered right now
 * created the constructors and accesors for the amazon class
 * statusReport and assignTasks functionalities still undecided
 */
//Ver. 3 June 8th, 2016
/* added constructor 
 * 
 * 
 * 
 */
/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: AmazonRainforest
 * Author: Henry Li
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: The AmazonRainforest class is a child class of the 
       Region class. This class can be instantiated and 
            has the fields of an AmazonRainforest. 
            
            Its functionalities are those of the Region class: 
             1. Print out a status report
              2. Check if the Region is open 
              3. Provide string representations of itself both 
                 to the user and the Zoo class to be saved.
 */

//add maxSpecies and numSpecies and then update toString accordingly

public class AmazonRainforest extends Region {
  private boolean treesTrimmed; //True if the trees are properly trimmed, false if they are not
  private double humidity;  //A double which represents the humidity of the region as a percentage
  private int daysSinceRain; //An integer indicating the number of days that the Region has gone without rain
  private int daysSinceGrassTrimmed; //An integer indicating the number of days that the Region hasn't had its grass trimmed.
  
  private static final int MAX_DAYS_SINCE_RAIN = 7; //The maximum acceptable days without rain in the Amazon Rainforest 
  private static final int MAX_DAYS_SINCE_GRASSTRIM = 8; //The maximum acceptables days without trimming the grass 
  private static final double MIN_HUMIDITY = 70; //The minimum percent humidity of the Amazon Rainforest 
  
  private static final double DEF_HUMIDITY = 80; 
  
  /* Constructor: Takes in the same fields as the Region constructor as well as one boolean, one double and two integers.
  */ 
  public AmazonRainforest (Staff[] regionStaff, Animal[] regionAnimals, String regionName, RegionSpec regSpec, int daysSinceAccident, boolean treesTrimmed, 
                           double humidity, int daysSinceRain, int daysSinceGrassTrimmed) {
    super(regionStaff, regionAnimals, regionName, regSpec, daysSinceAccident);
    this.treesTrimmed = treesTrimmed;
    this.humidity = humidity;
    this.daysSinceRain = daysSinceRain;
    this.daysSinceGrassTrimmed = daysSinceGrassTrimmed;
  }
  
  public AmazonRainforest (Staff[] regionStaff, Animal[] regionAnimals, String regionName, RegionSpec regSpec) {
    super(regionStaff, regionAnimals, regionName, regSpec);
    treesTrimmed = false; 
    humidity = DEF_HUMIDITY;
    daysSinceRain = 0;
    daysSinceGrassTrimmed = 0;
  }
  /* An empty constructor which sets all objects to null and initializes all things to default values
  */

    
   /* Accessors and Mutators: 
    Any accessor returns the value of the private field which it is responsible for to the class 
     calling it. 
     
     Any mutator returns sets the value of the fields to a given input which the user declares.
  */
  public boolean getTreesTrimmed () {
    return treesTrimmed;
  }
  
  public void setTreesTrimmed (boolean in) {
    treesTrimmed = in;
  }
  
  public double getHumidity () {
    return humidity; 
  }
  
  public void setHumidity (double in) {
    humidity = in;
  }
  
  public int getDaysSinceRain () {
    return daysSinceRain;
  }
  
  public void setDaysSinceRain (int in) {
    daysSinceRain = in;
  }
  
  public int getDaysSinceGrassTrimmed () { //probably want to make this thing smaller 
    return daysSinceGrassTrimmed;
  }
 
  public void setDaysSinceGrassTrimmed (int in) {
    daysSinceGrassTrimmed = in;
  }
  
  /* statusReport: 
  
   Prints out a bunch of statements to the console depending on the current state of 
    the region. From reading these series of print statements, the user will be able to 
    determine which tasks must urgently be done within the region.
  
  */ 
  public void statusReport() { //not really sure what to do with this method right now
    //maybe make it more specific or less specific
    //currently, it seems to have nearly similar functionality to a toString method
    System.out.println("Status Report: ");
    if (!treesTrimmed) {
      System.out.println("The trees need to be trimmed.");
    } else {
      System.out.println("The trees are trimmed.");
    }
    
    if (daysSinceRain > MAX_DAYS_SINCE_RAIN) {
      System.out.println("The forest needs rain");
    } else {
      System.out.println("There has been " + daysSinceRain + " days since rain.");
    }
    
    if (daysSinceGrassTrimmed > MAX_DAYS_SINCE_GRASSTRIM) {
      System.out.println("The grass needs to be trimmed."); 
    } else {
      System.out.println("There has been " + daysSinceGrassTrimmed + " days since the grass was trimmed.");
    }
    
    if (humidity < MIN_HUMIDITY) {
      System.out.println("The humidity of the rainforest is too low.");
    } else {
      System.out.println("The humidity is currently: " + humidity);
    }
  }
  
  /* checkOpen: 
     For any Amazon Rainforest exhibit to be open, the trees must be trimmed, 
     the humidity must exceed 70% and there must have been at least a week 
     since the last major accident occured. 
  */ 
  public boolean checkOpen() {
    if (treesTrimmed && humidity > MIN_HUMIDITY && checkDays() >= 0) {
      return true;
    } else {
    return false;
    }
  }
  
  /* toFile: 
     Returns the string which will be written by the BufferedWriter in the Zoo class to save
     the information of this Region in a .txt file.
 
  */ 
  public String toFile () {
   return "Amazon Rainforest\r\n" + regionName + "\r\n" + regSpec.getMaxStaff() + "\r\n" + regSpec.getNumStaff() + "\r\n" + regionStaffFile() + regSpec.getMaxAnimals() 
      + "\r\n" + regSpec.getNumAnimals() + "\r\n" + regionAnimalFile() + regSpec.getNumVisits() + "\r\n" + regSpec.getCost() + "\r\n" + daysSinceAccident 
      + "\r\n" + treesTrimmed + "\r\n" + humidity + "\r\n" + daysSinceRain + "\r\n" + daysSinceGrassTrimmed + "\r\n";
  }
  
  /* toString: 
    This method returns a String which prints out all the information related to a Region  
     in a readable manner. 
  */
  public String toString () {
    return regionName + "\nRegion Type: Amazon Rainforest\n" + super.toString() + "Region Specific Details:\nTrees trimmed: " + treesTrimmed + "\nHumidity: " + humidity + "%\nDays since last rainfall: " + daysSinceRain +
      "\nDays since the grass was last trimmed: " + daysSinceGrassTrimmed + "\n\n";
  }
}