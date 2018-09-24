//Ver.1 June 6  


/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Zoo
 * Author: Henry Wu, Henry Li, Welson Pan, Sean He
 * Date: June 6th to June 15th
 * School: A.Y.Jackson 
 * Purpose: The purpose of the zoo is the central hub for all region and staff information, as well as methods
 regarding the 2 such as search algorithms, creating, deleting, and moving of objects. The methods will
 subsequently be used by the Menu class to perform functions.
 
 A zoo object contains an array of Regions and an array of Staff; this array of staff contains all staff
 members working in every single region. 
 
 
 June 6
 Henry W - constructor, accessors and mutators
 - calcTotalEarnings
 
 Welso - saveStaffList &loadStaffList
 - SaveReigionList & loadReigionList
 
 Sean  - fixed calcTotalEarnings
 - both findStaff()s
 
 june 7 - 
 
 june 8 - 
 Henry W - created methods for region, findHighestEarningRegion, findLowestEarningRegion, printAllRegions
 - created methods for personalMenu()
 
 Sean - moved findEmp here, removeStaff rewrite
 
 june 9 -
 
 june 10 -
 
 */

import java.io.*;
import java.util.*;
public class Zoo {
  
  private int maxRegions; //The maximum number of Region objects 
  private int numRegions; //The current number of Region objects
  private int maxStaff; //The maximum number of Staff 
  private int numStaff; //The current number of Staff
  private Region[] regionList; //The array of Regions
  private Staff[] staffList; //The array of Staff
  static Scanner sc = new Scanner(System.in); // Scanner to take input
  
  /* Constructor: The constructor for Zoo takes in two Strings; one .txt file for 
   the Staff list and another separate .txt file for the Region list. The number
   of regions and the number of staff are initialized to 0. 
   
   */ 
  
  public Zoo(String f1Name, String f2Name) {
    numRegions = 0;
    numStaff = 0;
    loadStaffList(f1Name);
    loadRegionList(f2Name);
  } 
  
  /* Accessors and Mutators: 
   Any accessor returns the value of the private field which it is responsible for to the class 
   calling it. 
   
   Any mutator returns sets the value of the fields to a given input which the user declares.
   */
  public int getMaxRegions() {
    return maxRegions;
  }
  
  public void setMaxRegions(int maxRegions) {
    this.maxRegions = maxRegions;
  }
  
  public Staff[] getStaffList() {
    return staffList;
  }
  
  public void setStaffList(Staff[] staffList) {
    this.staffList = staffList;
  }
  
  public int getNumRegions() {
    return numRegions;
  }
  
  public void setNumRegions(int numRegions) {
    this.numRegions = numRegions;
  }
  
  public int getMaxStaff() {
    return maxStaff;
  }
  
  public void setMaxStaff(int maxStaff) {
    this.maxStaff = maxStaff;
  }
  
  public int getNumStaff() {
    return numStaff;
  }
  
  public void setNumStaff(int numStaff) {
    this.numStaff = numStaff;
  }
  
  public Region[] getRegionList() {
    return regionList;
  }
  
  public void setRegionList(Region[] regionList) {
    this.regionList = regionList;
  }
  
  /*
   --------------------------------------------------------------------------------------
   FILE I/O
   --------------------------------------------------------------------------------------
   
   loadRegionList: takes the name of the file as a parameter (String) and reads the file;
   creating a list of initialized Region objects. Before this method can be used, the 
   list of Staff must be loaded and initialized in the Zoo first. 
   
   */
  
  public void loadRegionList(String fileName) {
    try {
      BufferedReader in = new BufferedReader(new FileReader(fileName));
      maxRegions = Integer.parseInt(in.readLine());  
      regionList = new Region[maxRegions];
      int numRegionsInFile = Integer.parseInt(in.readLine());
      
      for (int i = 0; i < numRegionsInFile; i++) {
        String type = in.readLine();
        /* createAmazonRainforest, createArctic, and createAquarium are void static
         methods which takes in the BufferedReader and initializes all Region objects 
         in the RegionList array using the Regions.txt file
         */
        if (type.equals("Amazon Rainforest")) {
          createAmazonRainforest(in); 
        } else if (type.equals("Arctic")) {
          createArctic(in);
        } else if (type.equals("Aquarium")) {
          createAquarium(in);
        }
        
        if (i == 2) {
          regionList[i].toString();  //temp 
        }
      }
      
      
      
    } catch (IOException iox) {
      System.out.println("Error reading " + fileName);
    } catch (NumberFormatException ex) {
      System.out.println("Problem with file formatting. Please check the file and try again.");
    }
  }
  
  
  /* saveRegionList: writes all fields of the RegionList into the Regions.txt file 
   */
  public void saveRegionList(String fileName) {
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
      out.write(maxRegions+"\r\n");
      out.write(numRegions+"\r\n"); 
      
      for (int i = 0; i < numRegions; i++) {
        out.write(regionList[i].toFile());
      }
      
      out.close();
    } catch (IOException iox) {
      System.out.println("Error saving to " + fileName);
    }
  }
  
  /* loadStaffList: takes the name of the file as a parameter (String) and reads the file;
   creating a list of initialized Staff objects. 
   */
  public void loadStaffList(String fileName) {
    try {
      BufferedReader in = new BufferedReader(new FileReader(fileName));
      maxStaff = Integer.parseInt(in.readLine());
      staffList = new Staff[maxStaff];
      int numStaffInFile = Integer.parseInt(in.readLine());
      
      for (int i = 0; i < numStaffInFile; i++){
        
        String staffType = in.readLine();
        if (staffType.equals("Labourer")) {
          staffList[i] = new Labourer(in.readLine(), Integer.parseInt(in.readLine()), in.readLine(), Double.parseDouble(in.readLine()), Integer.parseInt(in.readLine()));
          numStaff++;
        } else if (staffType.equals("Manager")) /*;*/
        {
          staffList[i] = new Manager(in.readLine(), Integer.parseInt(in.readLine()), in.readLine(), Double.parseDouble(in.readLine()));
          numStaff++;
        }
      }
      
    } catch (IOException iox) {
      System.out.println("Error reading " + fileName);
      
    } catch (NumberFormatException ex) {
      System.out.println("Problem with file formatting. Please check the file and try again.");
    }
  }
  
  /* saveStaffList: writes all the fields of each individual Staff into the Staff.txt file 
   */
  public void saveStaffList(String fileName) {
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
      out.write(maxStaff+"\r\n");
      
      out.write(numStaff+"\r\n");
      
      
      for (int i = 0; i < numStaff; i++) {
        if (staffList[i] instanceof Labourer) {
          out.write(staffList[i].toFile());
        } else if (staffList[i] instanceof Manager) {
          out.write(staffList[i].toFile());
        }
      }
      out.close();
    } catch (IOException iox) {
      System.out.println("Error saving to " + fileName);
    }
  }
  
  
  /*
   ------------------------------------------------------------------------
   Searching Algorithms
   ------------------------------------------------------------------------
   
   There are two different findStaff methods; one searches the array using 
   the staffNum (id) of the staff. Both methods use the binary search 
   recursively. Returns a staff object or null if the staff doesn't exist.
   */
  
  public Staff findStaff(int staffNum, int start, int end) { //because recursive
    int mid = (start + end) / 2;
    if (end - start > 1) {
      if (findStaff(staffNum, start, mid) != null) {
        return findStaff(staffNum, start, mid);
      } else {
        return findStaff(staffNum, mid, end);
      }
    } else {
      if (staffList[start].getStaffNum() == staffNum) {
        return staffList[start];
      } else if (staffList[end].getStaffNum() == staffNum) {
        return staffList[end]; 
      } else {
        return null; 
      }
    }
  }
  
  /* findStaff (staffName): This findStaff method uses the name of the staff instead 
   of the staffNum. Returns a staff object or null if not found.
   */
  public Staff findStaff(String staffName, int start, int end) { //because recursive
    int mid = (start + end) / 2;
    if (end - start > 1) {
      if (findStaff(staffName, start, mid) != null) {
        return findStaff(staffName, start, mid);
      } else {
        return findStaff(staffName, mid, end);
      }
    } else {
      if (staffList[start].getStaffName().equals(staffName)) {
        return staffList[start];
      } else if (staffList[end].getStaffName().equals(staffName)) {
        return staffList[end]; 
      } else {
        return null; 
      }
    }
  }
  
  
  public Staff searchStaffTemp(int id) {
    for (int i = 0; i < numStaff; i++) {
      if (id == staffList[i].getStaffNum()){
        return staffList[i];
      }
    }
    return null;
  }
  
  /* searchStaff: This search method works both ways; the user inputs either a 
   String or an integer. 
   
   */
  //Searches staff using a string or number, differentiated by determining input type
  public Staff searchStaff() { //takes in inputs from user find a Staff
    while (true) { //will keep looping util returns a value
      System.out.print("Search by entering Name or Employee Number: ");
      String findEmp = ""; 
      try {
        findEmp = sc.nextLine();
        if (findStaff(Integer.parseInt(findEmp), 0, numStaff - 1) != null) { //if can parse search for emp num
          return findStaff(Integer.parseInt(findEmp), 0, numStaff - 1);
        } else {
          System.out.println("Cannot be found");
        }
      } catch (NumberFormatException NFX) { //if cannot parse search for emp name
        if (findStaff(findEmp, 0, numStaff - 1) != null) {
          return findStaff(findEmp, 0, numStaff - 1);
        } else {
          System.out.println("Cannot be found");
        }
      }
    }
  }//searchStaff method
  
  // checks id to find the staff to remove, then removes them and shifts array up to accomodate 
  /* public void removeStaff(Staff find) {
   int index = -1;
   
   for (int i = 0; i < numStaff; i++) {
   if (staffList[i].getStaffNum() == find.getStaffNum()) {
   index = i;
   }
   }
   
   for (int i = index; i < numStaff - 1; i++) {
   staffList[i] = staffList[i+1];
   }
   staffList[numStaff-1] = null;
   }*/
  /*
  will remove a Staff from both staffList and regionList if there is one
  
  */
  
  
   public void removeStaff() {    //delets a staff & fills the gap w/ last value
   Staff remove = searchStaff();
   remove.setStaffName(null);
   Region removeIn = findRegion(remove); //to remove pointer from the region later
   int index = -1;
   for (int i = 0; i < numStaff; i++) {
    if (staffList[i].equals(remove)) {
     index = i;
    }
   }
   staffList[index] = staffList[numStaff - 1];
   staffList[numStaff - 1] = null;
   numStaff--;
   /*
   also fill the null in regionStaffList*/
    if (removeIn != null) {
     removeIn.updateStaff(); 
    }
   } 
  
  public void editStaffList(Staff newStaff, int place) { 
    staffList[place] = newStaff;
  }
  
  /*
   -----------------------------------------------------------------------------------------
   Region Methods 
   -----------------------------------------------------------------------------------------
   
   
   printRegionNames: prints out the names of all regions.
   */ 
  
  public void printRegionsNames() {
    for (int i = 0; i < regionList.length; i++) {
      System.out.println("Region #" + (i + 1) + ": " + regionList[i].getRegionName());
    }
  }//printRegionsNames method
  
  /*printAllRegionsEarnings: prints out the earnings of each region individually.
   */
  public void printAllRegionsEarnings() {
    for (int i = 0; i < numRegions; i++) {
      System.out.println("The earning of " + regionList[i].getRegionName() + " is $" + regionList[i].calcEarnings());
    } //hey we need to change the double with the good proper formatting
  }//printAllRegions method
  
  /*findHighestEarningRegion: returns the region who made the most earnings.
   */
  public Region findHighestEarningRegion() {
    Region highest = regionList[0];
    for (int i = 1; i < numRegions; i++) {
      if (highest.calcEarnings() <= regionList[i].calcEarnings())
        highest = regionList[i];
    }
    return highest;
  }//highestEarningRegion method
  
  /*findLowestEarningRegion: returns the region who made the least earnings.
   * 
   */
  public Region findLowestEarningRegion() {
    Region low = regionList[0];
    for (int i = 1; i < numRegions; i++) {
      if (low.calcEarnings() >= regionList[i].calcEarnings())
        low = regionList[i];
    }
    return low;
  }//lowestEarningRegion method
  
  /*calcTotalEarnings: returns the total earnings of the region as a double. 
   * 
   */
  public double calcTotalEarnings() {
    double total = 0;
    for (int i = 0; i < numRegions; i++) {
      total += regionList[i].calcEarnings();
    }
    return total;
  }//calcTotalEarnings method
  
  
  
  /*avgRegionEarnings: returns the average earnings of ALL region as a double. 
  */
  public double avgRegionEarnings() {
    double total = 0;
    total = calcTotalEarnings();
    return total/numRegions;
  }
  
  /*findRegion: finds a region by taking in a region name as a parameter.
   Uses binary search recursively. Returns a null if a region 
   is not found, otherwise returns the region object.
   */
  public Region findRegion(String name, int start, int end) { //recursion
    int middle = (start + end) / 2;  
    if (end - start > 1) {
      if (findRegion(name, start, middle) != null) {
        return findRegion(name, start, middle);
      } else {
        return findRegion(name, middle, end);
      }
    } else {
      if (regionList[start].getRegionName().equals(name)) {
        return regionList[start];
      } else if (regionList[end].getRegionName().equals(name)) {
        return regionList[end];
      } else {
        return null; 
      }
    }
  }
  
  //temp method
  public Region regionChoice(int in) {
    return regionList[in-1];
  }
  
  /*findHighestEarningEmployee: Finds the highest wage employee by looping once through the staffList
       and comparing the wage of each Labourer.
  */
  public Staff findHighestEarningLabour() {
    Staff highest = new Labourer(null,0,null,0,0);
    
    for (int i = 0; i < staffList.length; i++) { 
      if (staffList[i] instanceof Labourer) {
        if (((Labourer)staffList[i]).getWage() >= ((Labourer)highest).getWage()){
        highest = staffList[i];
       }
      }
  }
    return highest;
  }
  
  /*findLowestEarningEmployee: Finds the lowest wage employee by looping once through the staffList
       and comparing the wage of each Labourer.
  */
  public Staff findLowestEarningLabour() {
    Staff lowest = new Labourer(null,0,null,1000,0); //??###################@@@@@@@@@@@@@@@@@@
    
    for (int i = 0; i < staffList.length; i++) { 
      if (staffList[i] instanceof Labourer) {
        if (((Labourer)staffList[i]).getWage() <= ((Labourer)lowest).getWage()){
        lowest = staffList[i];
       }
      }
  }
    return lowest;
  }
  
  /*findAvgWage: Adds up the total wage and divides it by the number of Labourers
  */
  public double findAvgWage() {
   int numLabourer = 0;
    double total = 0;
    
    for (int i = 0; i < staffList.length; i++) {
      if (staffList[i] instanceof Labourer) {
       total += ((Labourer) staffList[i]).getWage();
        numLabourer ++;
      }
    }
  
    return total/numLabourer;
  }
  
  //Changing the region of an employee@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  public void changeEmployeeRegion() {
    /*
    
    
    */ // writing atm
    //gl :D
  }
  
  
  
  
  /* printRegionStatus: 
   Prints out all Regions and their current status (Open/closed)
   
   */ 
  public void printRegionStatus() {
    for (int i = 0; i < numRegions; i++) {
      System.out.print(regionList[i].getRegionName() + " is ");
      if (regionList[i].checkOpen()) {
        System.out.println("open. :D");
      } else {
        System.out.println("closed. D:");
      }
    }
  }
  
  /* Creation methods: 
   * 
   These methods create and initialize the values of any AmazonRainforest, Aquarium or 
   Arctic class by reading in the information from the Region.txt file. These methods are 
   called by the loadRegionList method above in the same class. 
   
   All the createRegion methods create local variables of the fields for each type of 
   Region, and calls the constructor of the respective region at the bottom of the method 
   and fills the global regionList array containing all the Regions.
   
   */ 
  
  //Creation method for AmazonRainforest
  private void createAmazonRainforest (BufferedReader in) {
    try {
      String tempRegionName = in.readLine();
      int tempMaxStaff = Integer.parseInt(in.readLine());
      int tempNumStaff = Integer.parseInt(in.readLine());
      
      Staff[] readStaffList = new Staff[tempMaxStaff]; //creating a local array of Staff; later to be fed as a field to the constructor 
      
      for (int i = 0; i < tempNumStaff; i++) {
        int  id = Integer.parseInt(in.readLine());
         
        //readStaffList[i] = this.searchStaffTemp(id);
        readStaffList[i] = findStaff(id, 0, this.numStaff); //id is the same thing as staffNum in the staff class 
        //creates an array of initialized staff objects by searching 
        //using the staffNum read from the file
      }
      
      int tempMaxAnimals = Integer.parseInt(in.readLine());
      int tempNumAnimals = Integer.parseInt(in.readLine());
      
      Animal[] readAnimalList = new Animal[tempMaxAnimals]; //creating a local array of Animals;
      
      for (int i = 0; i < tempNumAnimals; i++) {
        readAnimalList[i] = new Animal(in.readLine(), Integer.parseInt(in.readLine()));
      }
      
      RegionSpec tempSpec = new RegionSpec (tempNumStaff, tempMaxStaff, tempNumAnimals, tempMaxAnimals, Integer.parseInt(in.readLine()),
                                            Double.parseDouble(in.readLine()));
      
      regionList[numRegions] = new AmazonRainforest(readStaffList, readAnimalList, tempRegionName, tempSpec, Integer.parseInt(in.readLine()),
                                                    Boolean.parseBoolean(in.readLine()), Double.parseDouble(in.readLine()), Integer.parseInt(in.readLine()),
                                                    Integer.parseInt(in.readLine())); 
    } catch (IOException iox) {
      System.out.println("Error reading file");
    }
    this.numRegions++;
  }
  
  //Creation method for Arctic      
  private void createArctic(BufferedReader in)  {
    try {
      String tempRegionName = in.readLine();
      int maxStaff = Integer.parseInt(in.readLine());
      int numStaff = Integer.parseInt(in.readLine());
      
      Staff[] readStaffList = new Staff[maxStaff]; //creating a local array of Staff; later to be fed as a field to the constructor 
      for (int i = 0; i < numStaff; i++) {
        int id = Integer.parseInt(in.readLine());
        //readStaffList[i] = this.searchStaffTemp(id);
        readStaffList[i] = findStaff(id, 0, this.numStaff); //id is the same thing as staffNum in the staff class 
        //creates an array of initialized staff objects by searching 
        //using the staffNum read from the file
      }
      
      int maxAnimals = Integer.parseInt(in.readLine());
      int numAnimals = Integer.parseInt(in.readLine());
      
      Animal[] animals = new Animal[numAnimals]; //creating a local array of Animals;
      for (int i = 0; i < numAnimals; i++) {
        animals[i] = new Animal(in.readLine(), Integer.parseInt(in.readLine()));
      }
      
      regionList[this.numRegions] = new Arctic(readStaffList, animals, tempRegionName, new RegionSpec(numStaff, maxStaff, numAnimals, maxAnimals, Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine())), 
                                               Integer.parseInt(in.readLine()), Double.parseDouble(in.readLine()), Double.parseDouble(in.readLine()), Boolean.parseBoolean(in.readLine()));
      //constructor call 
    } catch (IOException iox) {
      System.out.println("Error reading file");
    }
    this.numRegions++; //updates the global field numRegions
  }
  
  //Creation method for Aquarium
  private void createAquarium (BufferedReader in) { 
    try {
      String tempRegionName = in.readLine();
      int tempMaxStaff = Integer.parseInt(in.readLine());
      int tempNumStaff = Integer.parseInt(in.readLine());
      
      Staff[] readStaffList = new Staff[tempMaxStaff]; //creating a local array of Staff; later to be fed as a field to the constructor
      
      for (int i = 0; i < tempNumStaff; i++) {
        int id = Integer.parseInt(in.readLine());
        //readStaffList[i] = this.searchStaffTemp(id);
        readStaffList[i] = findStaff(id, 0, this.numStaff); //id is the same thing as staffNum in the staff class 
        //creates an array of initialized staff objects by searching 
        //using the staffNum read from the file
      }
      
      int tempMaxAnimals = Integer.parseInt(in.readLine());
      int tempNumAnimals = Integer.parseInt(in.readLine());
      
      Animal[] readAnimalList = new Animal[tempMaxAnimals]; //creating a local array of Animals;
      
      for (int i = 0; i < tempNumAnimals; i++) {
        readAnimalList[i] = new Animal(in.readLine(), Integer.parseInt(in.readLine()));
      }
      
      RegionSpec tempSpec = new RegionSpec(tempNumStaff, tempMaxStaff, tempNumAnimals, tempMaxAnimals, Integer.parseInt(in.readLine()), 
                                           Double.parseDouble(in.readLine()));
      
      regionList[numRegions] = new Aquarium(readStaffList, readAnimalList, tempRegionName, tempSpec, Integer.parseInt(in.readLine()), 
                                            Boolean.parseBoolean(in.readLine()), Boolean.parseBoolean(in.readLine()), Boolean.parseBoolean(in.readLine()), Boolean.parseBoolean(in.readLine()),
                                            Integer.parseInt(in.readLine()));
      //maybe we left out regionName in the constructor for saving and loading the files
      
    } catch (IOException iox) {
      System.out.println("Error reading file");
    }
    numRegions++; //check over this /updates the global field numRegions
    
    
  }//createAquarium method  
  //Using a species name, it returns the Animal object with a matching name
  
  /* findAnimalZoo 
   * finds the Animal given species
   */
  
  public Animal findAnimalZoo(String species) {
    for (int i = 0; i < numRegions; i++) {
      Animal[] temp = regionList[i].getRegionAnimals();                      //regionList[i].getAnimalList(); @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ NO GETANIMALIST METHOD YET@@@@@@@@@@@@@@@@@@@@@@@@@
      for (int j = 0; j < regionList[i].getRegionSpec().getNumAnimals(); j++) {
        if (temp[j].getSpecies().equals(species)) {
          return temp[i]; 
        }
      }
    }
    return null;
  }//findAnimalZoo method
  
  public void printAllAnimals() {
    for (int i = 0; i < numRegions; i++) {
      for (int j = 0; j < regionList[i].getRegionSpec().getNumAnimals(); j++) {
        System.out.println(regionList[i].getRegionAnimals());
        }
      }
  }//printAllAnimals method

  //Returns the Region that a Staff object belongs to
  public Region findRegion(Staff find) {
    for (int i = 0; i < numRegions; i++) {
      Staff[] temp = regionList[i].getRegionStaff();
      for (int j = 0; j < regionList[i].getRegionSpec().getNumStaff(); j++) {
        if (temp[j].equals(find)) {
          return regionList[i]; 
        }
      }
    }
    return null;
  }//findRegion method

  //Outputs all the Regions
  public String toString() {
    String output = "";
    for (int i = 0; i < numRegions; i++) {
      if (regionList[i] instanceof Arctic) 
        output += (i+1) + ". " + ((Arctic)regionList[i]).toString();
      else if (regionList[i] instanceof Aquarium) 
        output += (i+1) + ". " + ((Aquarium)regionList[i]).toString();
      else if (regionList[i] instanceof AmazonRainforest) 
        output += (i+1) + ". " + ((AmazonRainforest)regionList[i]).toString();
    }
    return output;
  }
  
  
  
//maybe need it later
  /* public Region findRegionAnimalIn(String species) {
   for (int i = 0; i < numRegions; i++) {
   Animal[] temp = regionList[i].getAnimalList();
   for (int j = 0; j < regionList[i].getNumAnimals(); j++) {
   if (temp[j].equals(species)) {
   return regionList[i]; 
   }
   }
   }
   return null;
   }*/
  
  
  
 //hey sean look here
  private void fixList () {      
      for (int i = 0; i < maxStaff - numStaff; i++) {
         int position = 0; 
         while (staffList[position] != null) {
            position++;
         }
         
         while (position < maxStaff-i-1) {
            staffList[position] = staffList[position+1];
            position++;
         }
         
         staffList[position] = null;
      }
  }
 
  public void userCreateArctic () {
    System.out.println("Enter your region name: ");
    String tempRegionName = sc.nextLine();
   System.out.println("Enter the maximum number of staff: ");
   int tempMaxStaff = Menu.userIntIn();
   System.out.println("Enter the maximum number of animals: ");
   int tempMaxAnimals = Menu.userIntIn();
   System.out.println("Enter the cost of the region: ");
   double tempCost = Menu.userDoubleIn();
 
   Staff[] tempStaffList = new Staff[tempMaxStaff];
   Animal[] tempAnimalList = new Animal[tempMaxAnimals];
   RegionSpec tempSpec = new RegionSpec(tempMaxStaff, tempMaxAnimals, tempCost);
 
   regionList[numRegions] = new Arctic(tempStaffList, tempAnimalList, tempRegionName, tempSpec);
   
   
   System.out.println("Nice! A new arctic region was added to the list. Remember, staff and animals must be added to the region." + numRegions);
   numRegions++;
  }
  
  public void userCreateAquarium () {
    System.out.println("Enter your region name: ");
    String tempRegionName = sc.nextLine();
   System.out.println("Enter the maximum number of staff: ");
   int tempMaxStaff = Menu.userIntIn();
   System.out.println("Enter the maximum number of animals: ");
   int tempMaxAnimals = Menu.userIntIn();
   System.out.println("Enter the cost of the region: ");
   double tempCost = Menu.userDoubleIn();
 
   Staff[] tempStaffList = new Staff[tempMaxStaff];
   Animal[] tempAnimalList = new Animal[tempMaxAnimals];
   RegionSpec tempSpec = new RegionSpec(tempMaxStaff, tempMaxAnimals, tempCost);
 
   regionList[numRegions] = new Aquarium(tempStaffList, tempAnimalList, tempRegionName, tempSpec);
   
   System.out.println("Nice! A new aquarium region was added to the list. Remember, staff and animals must be added to the region." + numRegions);
   numRegions++;
  }
  
  public void userCreateAmazonRainforest () {
    System.out.println("Enter your region name: ");
    String tempRegionName = sc.nextLine();
   System.out.println("Enter the maximum number of staff: ");
   int tempMaxStaff = Menu.userIntIn();
   System.out.println("Enter the maximum number of animals: ");
   int tempMaxAnimals = Menu.userIntIn();
   System.out.println("Enter the cost of the region: ");
   double tempCost = Menu.userDoubleIn();
 
   Staff[] tempStaffList = new Staff[tempMaxStaff];
   Animal[] tempAnimalList = new Animal[tempMaxAnimals];
   RegionSpec tempSpec = new RegionSpec(tempMaxStaff, tempMaxAnimals, tempCost);
 
   regionList[numRegions] = new AmazonRainforest(tempStaffList, tempAnimalList, tempRegionName, tempSpec);
   
   System.out.println("Nice! A new Amazon rainforest region was added to the list. Remember, staff and animals must be added to the region." + numRegions);
   numRegions++;
  }
  
  
  
}//Zoo class
