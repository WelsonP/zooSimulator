//Version 1: June 7th, 2016
/* took out the algae trimmed 
 * percentage calculation still arbitrary
 * percentage might have to be taken out; difficult to make an algorithm without creating more constants 
 * 
 * put algae back in
 * 
 * toString still needs to be finished
 * tasksUpdate still needs to be finished
 * calcPercentage needs to be finished
 */
//Ver. 2 June 8th, 2016
/* 
 * added a constructor 
 * added a new field called healthySoil to make percentage a cleaner value (1/3 not as nice as 1/4).
 * figured out the calcPercentage method
 * figured out the checkOpen method
 * wokring on the toString
 */

/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Aquarium
 * Author: Henry Li
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: The Aquarium class is a child class of the 
       Region class. This class can be instantiated and 
            has the fields of an Aquarium. 
            
            Its functionalities are those of the Region class: 
             1. Print out a status report
              2. Check if the Region is open 
              3. Provide string representations of itself both 
                 to the user and the Zoo class to be saved.
 */


public class Aquarium extends Region {

    private boolean tankClean; //boolean: true if fish tanks are clean, false if they are dirty
    private boolean fishesFed; //boolean: true if the fishes are fed, and false if they are not
    private boolean algaeTrimmed;  //boolean: true if the algae are trimmed, and false if they are not 
    private boolean healthySoil; //boolean: true if the soil has sufficient nutrients to sustain the fish, false if not
    private int daysSinceWaterChange; //int: days since the water in the tanks were last changed 

    private static final int NUM_CONDITIONS = 4; // the number of boolean conditions in the Aquarium
    private static final double PERCENT_NEEDED =  0.50; //the minimum percent of boolean conditions that must be met (2/4)
    private static final int MAX_DAYS_WATER_CHANGE = 7; //the maximum days that the Aquarium can go without changing its water

    /* Constructor: Takes in the same fields as the Region constructor as well as four booleans representing the boolean Aquarium fields, and 
         an integer indicating the days since the water was last changed.
    */
    public Aquarium (Staff[] regionStaff, Animal[] regionAnimals, String regionName, RegionSpec regSpec, int daysSinceAccident, boolean tankClean, boolean fishesFed,
                     boolean algaeTrimmed, boolean healthySoil, int daysSinceWaterChange) {
        super(regionStaff, regionAnimals, regionName, regSpec, daysSinceAccident);
        this.tankClean = tankClean;
        this.fishesFed = fishesFed;
        this.algaeTrimmed = algaeTrimmed;
        this.healthySoil = healthySoil;
        this.daysSinceWaterChange = daysSinceWaterChange;
    }
    
    public Aquarium(Staff[] regionStaff, Animal[] regionAnimals, String regionName, RegionSpec regSpec) {
      super(regionStaff, regionAnimals, regionName, regSpec);
      this.tankClean = false;
      this.fishesFed = false;
      this.algaeTrimmed = false;
      this.healthySoil = false;
      this.daysSinceWaterChange = 0;
    }

    /* An empty constructor which sets all objects to null and initializes all things to default values
    */
  
     //Accessors and Mutators: 
    //Any accessor returns the value of the private field which it is responsible for to the class 
     //calling it. 
    public boolean getTankClean() {
        return tankClean;
    }

    public void setTankClean(boolean tankClean) {
        this.tankClean = tankClean;
    }

    public boolean getFishesFed() {
        return fishesFed;
    }

    public void setFishesFed(boolean fishesFed) {
        this.fishesFed = fishesFed;
    }

    public boolean getAlgaeTrimmed() {
        return algaeTrimmed;
    }

    public void setAlgaeTrimmed(boolean algaeTrimmed) {
        this.algaeTrimmed = algaeTrimmed;
    }

    public boolean getHealthySoil() {
        return healthySoil;
    }

    public void setHealthySoil(boolean healthySoil) {
        this.healthySoil = healthySoil;
    }

    public int getDaysSinceWaterChange() {
        return daysSinceWaterChange;
    }

    public void setDaysSinceWaterChange(int daysSinceWaterChange) {
        this.daysSinceWaterChange = daysSinceWaterChange;
    }
    
   //Accessors for Constants
    public static int getMaxDaysWaterChange() {
        return MAX_DAYS_WATER_CHANGE;
    }

    public static double getPercentNeeded() {
        return PERCENT_NEEDED;
    }

    public static int getNumConditions() {
        return NUM_CONDITIONS;
    }
   //Any mutator returns sets the value of the fields to a given input which the user declares.



  /* statusReport: 
  
   Prints out a bunch of statements to the console depending on the current state of 
    the region. From reading these series of print statements, the user will be able to 
    determine which tasks must urgently be done within the region.
  
   */ 
    public void statusReport () {
        System.out.println("Status report: ");
        if (!tankClean) {
            System.out.println("The tank needs to be cleaned.");
        }
        if (!fishesFed) {
            System.out.println("The fishes have to be fed.");
        }
        if (!algaeTrimmed) {
            System.out.println("The algae need to be trimmed.");
        }
        if (!healthySoil) {
            System.out.println("The soil is unhealthy.");
        }

        if (daysSinceWaterChange > MAX_DAYS_WATER_CHANGE) {
            System.out.println("The water needs to changed. It has been " + (daysSinceWaterChange - MAX_DAYS_WATER_CHANGE) + " days overdue.");
        }
    }

   /* checkOpen: 
    
     For any Aquarium exhibit to be open, at least two of four boolean conditions 
     must be met, and the fishes must be fed. There must have been at least a week 
     since the last major accident and also at most a week since the water was last changed.
   */ 
    public boolean checkOpen() {
        double percentage = this.calcPercentage();
        if (percentage > PERCENT_NEEDED && daysSinceWaterChange < MAX_DAYS_WATER_CHANGE && fishesFed && checkDays() >= 0) {
            return true;
        }
        return false;
    }

  /* calcPercentage: 
       
       Calculates the number of boolean conditions met as a percentage; 
       returns a double value. 
    */ 
    private double calcPercentage() {
        int count = 0;
        if (tankClean)
            count++;
        if (fishesFed)
            count++;
        if (algaeTrimmed)
            count++;
        if (healthySoil) {
            count++;
        }

        return (count/NUM_CONDITIONS);
    }

   /* toString: 
    
    This method returns a String which prints out all the information related to a Region  
     in a readable manner. 
   */
    public String toString () {
        return regionName + "\nRegion Type: Aquarium\n" + super.toString() + "Region Specific Details:\nTanks cleaned: " + tankClean + "\nFishes fed: " + fishesFed + "\nAlgae trimmed: " +
                algaeTrimmed + "\nHealthy soil: " + healthySoil + "\nDays since water change: " + daysSinceWaterChange + "\n\n";
    }


    /* toFile: 
    
     Returns the string which will be written by the BufferedWriter in the Zoo class to save
     the information of this Region in a .txt file.
   */ 
    public String toFile () {
        return "Aquarium\r\n" + regionName + "\r\n" +  regSpec.getMaxStaff() + "\r\n" + regSpec.getNumStaff() + "\r\n" + regionStaffFile() + regSpec.getMaxAnimals() + "\r\n" +
                regSpec.getNumAnimals() + "\r\n" + regionAnimalFile() + regSpec.getNumVisits() + "\r\n" + regSpec.getCost() + "\r\n" + daysSinceAccident
                + "\r\n" + tankClean + "\r\n" + fishesFed + "\r\n" + algaeTrimmed + "\r\n" + healthySoil + "\r\n" + daysSinceWaterChange + "\r\n";
    } //do we need super? 
}
