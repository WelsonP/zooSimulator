//Ver.2 June 7 2016
  /*
   * June 6
   henry W - Began on runMenuManager and ZooManager
   * June 7
   * 
   Henry w and sean - loginScreen
   Sean - SearchEmp rewrite (moved to Zoo)
   Henry W - financialManagement() and staffManagement()
   
   June 8
   Henry W - personalMenu
   */
  
  
  
  //Region requires a list of staff but the staff list doesnt work
  
  //

 /* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Menu
 * Author: Henry Li, Henry Wu, Welson Pan, Sean He
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: The Menu class contains the methods that make up the program interface to the users, and
            have different functionalities based on the type of Staff accessing the program at the 
            time. Labourers have access to 
 */
  
import java.util.Scanner;

public class Menu {
   
   static Scanner sc = new Scanner(System.in); //Used to gather user input
   public Staff user;                          //Used to determine type of user
   Zoo zoo = new Zoo("Staff.txt", "Regions.txt");                         //Zoo object to be used
   //Calls the login screen to begin the program
   
   
   public Menu() {
      loginScreen();
   }
   
   //Takes in username & password to login, options after login varies
   //depending on labourer or manager account
   public void loginScreen() { 
      boolean successfulLogin = false;
      do {
         try {
         
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " +
                             "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ZOO MANAGEMENT PROGRAM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " +
                             "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + 
                             "\n\nWelcome to the zoo management program. This program is designed for use by managers " +
                             "\nof the zoo and labourers of the zoo. Please enter your username and password to begin.\n"); 
            System.out.print("Username: ");
            int login = userIntIn();
            System.out.print("Password: ");
            String pass = sc.next();
            sc.nextLine();
            
            user = zoo.findStaff(login, 0 ,zoo.getNumStaff()-1); //looks for user
            
            if (user.checkPass(pass)) { //checks if password is correct or will throw NPE if didnt find staff
               successfulLogin = true;
            } 
            else {
               System.out.println("Invalid Password");
            }
         } 
         catch (NullPointerException npe) {
            System.out.println("User does not exist");
         }
      } while (!successfulLogin);
      
      //After login, determines which menu to run depending on account type
      if (user instanceof Manager) {
         runMenuManagers();
      } 
      else {
         runMenuLabour();
      }
   }//loginScreen method
   
   //Menu for Labourer type account
   public void runMenuLabour() {
   
      int choose = 0;
     
      do { 
         System.out.println("\nMenu for " + user.getStaffName());
         System.out.println("1. Display Wage" + 
                             "\n2. Display Hours" + 
                             "\n3. Log-off");
         System.out.print("\nEnter choice: ");
         choose = userIntIn();
         
         switch(choose) {
            case 1:
               System.out.println("Your wage is: " + ((Labourer)user).getWage());
               break;
            case 2: 
               System.out.println("Your hours are: " + ((Labourer)user).getHours());
               break;
            case 3:
               break;
            default: 
               System.out.println("Invalid input. Please try again.");
               break;
         }
      } while (choose!= 3);
      
      loginScreen();
      
   }//runMenuLabour method 
   
   //Menu for Manager type account
   public void runMenuManagers() {
      int choose = 0;
      do {
         System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                             "\nMain Menu - For Managers: " +
                             "\n1.Zoo Management" +
                             "\n2.Staff Management" +
                             "\n3.Financial Management" +
                             "\n4.Personal information" +
                             "\n5.Print Region Status" + 
                             "\n6.Log-off");
         System.out.print("\nEnter choice: ");
         choose = userIntIn();
         
         switch (choose) {
            case 1:
               zooManage();
               break;
            case 2:
               staffManagement();
               break;
            case 3:
               financialManagement();
               break;
            case 4:
               personalMenu();
               break;
            case 5:
               zoo.printRegionStatus();
               break;
            case 6: 
               zoo.saveRegionList("Regions.txt");
               zoo.saveStaffList("Staff.txt");
               break; 
            default:                      
               System.out.println("Invalid input. Please try again.");
               break;
         }//switch case
         //log off for Managers exits the program, but log off for Labourers calls the loginScreen() method
         
      } while (choose != 6);
      
   }//runMenuManagers class
   
   
   //Zoo Management menu
   /*Creates new Animal
     Creates new region
    Edits animal information
     Edits Region information
     Returns to previous menu
   */
   public void zooManage() {
      
      int choose = 0;
      do {
         System.out.println("\n~~~~~~~~~~~~~~~~~" + 
                             "\nZoo Management: " +
                             "\n1.Add new Animal" +
                             "\n2.Add new Region" +
                             "\n3.Edit animal information" +
                             "\n4.Edit Region Information" +
                             "\n5.Display all Zoo information" +
                             "\n6.Back to main menu");
         System.out.print("\nEnter choice: ");
         choose = userIntIn();
         
         switch (choose) {
            case 1:
               createAnimal(); 
               break;
            case 2:
               if (zoo.getNumRegions() == zoo.getMaxRegions()) {
                  System.out.println("Max amount of regions. You cannot add new regions.");
                  break;
               } 
               else {
                  createRegionMenu();
               }
               break;
            case 3:
               editAnimal();
               break;
            case 4:
               editRegionMenu(); 
               break;
            case 5:
               System.out.println("\n" + zoo);
               break;
            case 6: 
               break;
            default:
               System.out.println("INVALID CHOICE");
               break;
         }//Switch case
         
      } while (choose != 6);
      
      //change choose to a global boolean instead and make choose local
      
   }//zooManage class
   
   //Creates a new Region in regionList
   public void createRegionMenu() {       
      int choose = 0; 
      do {
         System.out.print(   "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                     "\nChoose which type of region to create." + 
                             "\n1.Artic Region" + 
                             "\n2.Amazon Rainforest Region" + 
                             "\n3.Aquarium Region" +
                             "\n4.Back to main menu");
         System.out.print("\nEnter choice: ");
         choose = userIntIn();
         
      
         switch(choose) {
            case 1: 
               zoo.userCreateArctic();
               break;
            case 2: 
               zoo.userCreateAmazonRainforest();
               break;
            case 3: 
               zoo.userCreateAquarium();
               break;
            case 4: 
               break;
            default:
               System.out.println("INVALID CHOICE");
               break;
         }//switch
      }while(choose != 4);
   }//createRegionMenu method
   
   //Creates an animal and puts it into the designated Region animalList
   public void createAnimal() {
  int regionIndex = 0;
      Region regionSelect = null;
      do {          
         System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("Select region to make an animal in: ");
      
         for (int i = 0; i < zoo.getNumRegions(); i++ ) {
           System.out.println("" + (i+1) + ".Edit " + zoo.getRegionList()[i].getRegionName());
         }
         System.out.print("Enter String to select region: ");
        
         
     regionIndex = userIntIn();    
     regionSelect = zoo.regionChoice(regionIndex);
   
         if (regionSelect == null) 
            System.out.println("Invalid choice, please enter another region");
                              
      } while (regionSelect == null);
      
      
      
      System.out.print("Enter animal name: ");
      String name = sc.nextLine();
      System.out.print("Enter animal population: ");
      int pop = userIntIn();
      
      if (regionSelect.getRegionSpec().getMaxAnimals() <= regionSelect.getRegionSpec().getNumAnimals()) {
         System.out.println("Max amount of animals reached.");
      } 
      else {
   zoo.getRegionList()[regionIndex-1].createNewAnimal(name, pop);
   System.out.println("Nice! Your new animal has been added to the list.");
         
      }
      
   }
   
   //Edits animal information 
   /*Firstly finds species to edit
    Then determines whether to edit name or population*/
   public void editAnimal() {
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.print("\nEnter animal species to look for: ");
      Animal selectAnimal = zoo.findAnimalZoo(sc.nextLine());
      int choose = 0;
      
      System.out.println(selectAnimal); // T@@@@@@@@@@@@@@@
      
      do {
         
         System.out.print("\nEdit animal: " +
                             "\n1. Species name" + 
                             "\n2. Population amount" + 
                             "\n3. Back to main menu") ;
         
         System.out.print("\nEnter choice: ");
         choose = userIntIn();
         
         switch(choose) { 
            case 1: 
               System.out.print("\nEnter new species name: ");
               
               selectAnimal.setSpecies(sc.nextLine());
               break;
            case 2: 
               System.out.print("\nEnter new population: ");
               selectAnimal.setPopulation(Menu.userIntIn());
               break;
            case 3: 
               break;
            default: 
               System.out.println("Invalid input. Please try again.");
               break;
         }
         
      }while ( choose != 3) ;
      
   }//editAnimal method 
   
   
   //Edits a region's information
   public void editRegionMenu() {
      int backChoice = 0;    
      int choose = 0;  //# to 
      Region regionChoice = null;  //Temporary name to reduce code clutter
      boolean validChoice = false; //Is false if user enters an invalid number
      
      //Displays Regions that can be edited
      System.out.println("\n~~~~~~~~~~~~~~~~~");
      System.out.println(  "\nEdit Region Menu: ");
      for (int i = 0; i < zoo.getNumRegions(); i++ ) {
         System.out.println("" + (i+1) + ".Edit " + (zoo.getRegionList()[i]).getRegionName());
         backChoice = i+2;
      }
      System.out.println(backChoice + ".Back to main menu\n");
      
      //Selecting which region to edit
      do {
         System.out.print("Enter which region to edit: ");
         choose = userIntIn();
         
         if (choose > 0 && choose <= backChoice) {
            validChoice = true;
         } 
         else {
            System.out.println("Invalid input. Please try again.");
         }
         
         //User selects which field of Region they would like to edit
         if (validChoice) {
            regionChoice = zoo.getRegionList()[choose-1];
            
            String sharedRegionMenu = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                                 "\nChoose what you would like to edit: " + 
                                 "\n1. Change Region name" + 
                                 "\n2. Change number of visits to region" + 
                                 "\n3. Change region cost" + 
                                 "\n4. Update the days since accident" + 
                                 "\n5. Reset days since accident to zero";
            
            
              if (regionChoice instanceof AmazonRainforest) {
                do {
                System.out.print(sharedRegionMenu + 
                                 "\n6. Update if the trees are trimmed " +
                                 "\n7. Update the humidity " + 
                                 "\n8. Update the days since rain " + 
                                 "\n9. Update the days since grass trimmed " + 
                                 "\n10. Back to main menu ");
               System.out.println("\nEnter your choice: ");
               choose = userIntIn();
               
               switch (choose) {
                  case 1:
                     System.out.print("Enter new region name: ");
                     regionChoice.setRegionName(sc.nextLine());
                     
                     System.out.println("The name has been successfully updated.");
                     break;
                  case 2: 
                     System.out.print("Enter number of visits: ");
                     regionChoice.getRegionSpec().setNumVisits(userIntIn());  
                     
                     System.out.println("The number of visits have been updated.");
                     break;
                  case 3: 
                     System.out.print("Enter new cost: ");
                     regionChoice.getRegionSpec().setCost(userDoubleIn());
                     
                     System.out.println("The cost has been successfully updated.");
                     break;
                  case 4: 
                     System.out.println("Enter the days since an accident last occured.");
                     regionChoice.accidentUpdate(userIntIn());
                     System.out.println("The days since accident have been increased by one.");
                     
                     break;
                  case 5: 
                     regionChoice.accidentReset();
                     System.out.println("The days since accident have been reset to zero.");
                     break;
                  case 6:
                     System.out.println("Are the trees trimmed? (Enter true/false): ");
                     ((AmazonRainforest)regionChoice).setTreesTrimmed(userBooleanIn());
                     
                     System.out.println("The trees trimmed have been successfully updated.");
                     break;
                 case 7: 
                     System.out.println("Enter the new humidity value: ");
                     ((AmazonRainforest)regionChoice).setHumidity(userDoubleIn());
                     
                     System.out.println("The humidity has been successfully updated.");
                     break;
                 case 8: 
                     System.out.println("Enter the number of days since rain: ");
                     ((AmazonRainforest)regionChoice).setDaysSinceRain(userIntIn());
                     System.out.println("The days since rain have been updated.");
                     break;
                 case 9:
                     ((AmazonRainforest)regionChoice).setDaysSinceGrassTrimmed(userIntIn());
                     System.out.println("The days since grass trimmed has been updated.");
                     break;
                 case 10:
                     break;
                 default: 
                     System.out.println("Invalid input. Please try again.");
               }//switch case
              } while (choose != 10);
              } else if (regionChoice instanceof Arctic) {
                do {
                System.out.print(sharedRegionMenu + 
                                 "\n6. Update the water temperature " +
                                 "\n7. Update the snow volume " + 
                                 "\n8. Update if animals are fed " + 
                                 "\n9. Back to main menu ");
               System.out.println("\nEnter your choice: ");
               choose = userIntIn();
                switch (choose) {
                  case 1:
                    System.out.print("Enter new region name: ");
                    regionChoice.setRegionName(sc.nextLine());
                     
                     System.out.println("The name has been successfully updated.");
                     break;
                  case 2: 
                     System.out.print("Enter number of visits: ");
                     regionChoice.getRegionSpec().setNumVisits(userIntIn());  
                     
                     System.out.println("The number of visits have been updated.");
                     break;
                  case 3: 
                     System.out.print("Enter new cost: ");
                     regionChoice.getRegionSpec().setCost(userDoubleIn());
                     
                     System.out.println("The cost has been successfully updated.");
                     break;
                  case 4: 
                    regionChoice.accidentUpdate(userIntIn());
                     System.out.println("The days since accident have been increased by one.");
                     
                     break;
                  case 5: 
                    regionChoice.accidentReset();
                    System.out.println("The days since accident have been reset to zero.");
                    break;
                  case 6:
                    System.out.println("Enter the new water temperature: ");
                    ((Arctic)regionChoice).setWaterTemp(userDoubleIn());
                    System.out.println("The water temperature has been updated.");
                    break; 
                  case 7:
                    System.out.println("Enter the new snow volume: ");
                    ((Arctic)regionChoice).setSnowVolume(userDoubleIn());
                    System.out.println("The snow volume has been updated.");
                    break;
                  case 8:
                    System.out.println("Have the animals been fed? (true/false)");
                    ((Arctic)regionChoice).setAnimalsFed(userBooleanIn());
                    System.out.println("Whether the animals have been fed has been updated.");
                    break;
                  case 9:
                    break;
                  default: 
                    System.out.println("Invalid Input. Please try again.");
                }  
                } while (choose != 9);
              } else if (regionChoice instanceof Aquarium) {
                do {
                  System.out.print(sharedRegionMenu + 
                                 "\n6. Update whether the tank has been cleaned" +
                                 "\n7. Update whether the fishes have been fed" + 
                                 "\n8. Update whether the algae has been trimmed" + 
                                 "\n9. Update health of soil" + 
                                 "\n10. Update the days since the water has been changed" + 
                                 "\n11. Back to main menu");
                  
               System.out.println("\nEnter your choice: ");
               choose = userIntIn();
                switch (choose) {
                  case 1:
                    System.out.print("Enter new region name: ");
                    regionChoice.setRegionName(sc.nextLine());
                     
                     System.out.println("The name has been successfully updated.");
                     break;
                  case 2: 
                     System.out.print("Enter number of visits: ");
                     regionChoice.getRegionSpec().setNumVisits(userIntIn());  
                     
                     System.out.println("The number of visits have been updated.");
                     break;
                  case 3: 
                     System.out.print("Enter new cost: ");
                     regionChoice.getRegionSpec().setCost(userDoubleIn());
                     
                     System.out.println("The cost has been successfully updated.");
                     break;
                  case 4: 
                    regionChoice.accidentUpdate(userIntIn());
                     System.out.println("The days since accident have been increased by one.");
                     
                     break;
                  case 5: 
                    regionChoice.accidentReset();
                    System.out.println("The days since accident have been reset to zero.");
                    break;
                  case 6:
                    System.out.println("Is the tank clean? (true/false)");
                    ((Aquarium)regionChoice).setTankClean(userBooleanIn());
                    System.out.println("The cleanliness of the tank has been updated.");
                    break;
                  case 7:
                    System.out.println("Are the fishes fed? (true/false)");
                    ((Aquarium)regionChoice).setFishesFed(userBooleanIn());
                    System.out.println("Whether the fishes have been fed or not has been updated.");
                    break;
                  case 8:
                    System.out.println("Has the algae been trimmed? (true/false)");
                    ((Aquarium)regionChoice).setAlgaeTrimmed(userBooleanIn());
                    System.out.println("Whether the algae has been trimmed or not has been updated.");
                    break;
                  case 9:
                    System.out.println("Is soil healthy? (true/false)");
                    ((Aquarium)regionChoice).setHealthySoil(userBooleanIn());
                    System.out.println("The health of the soil has been updated.");
                    break;
                  case 10:
                    System.out.println("Enter the days since the water has been changed: ");
                    ((Aquarium)regionChoice).setDaysSinceWaterChange(userIntIn());
                    break;
                  case 11:
                    break;
                  default:
                    System.out.println("Invalid input. Please try again.");
                } 
              } while (choose != 11); 
         }
      } 
      } while(!validChoice);
   }//editRegionMenu
   
   
   
   /*-------------------------------------------------------------------------------------------------------------=====================================*/
   
   ////Staff Management menu//////////////////////////////////////
   public void staffManagement() {
      
      int choose = 0;
      do{
         System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                           "\nStaff Management: " +
                           "\n1. Display Employee info" +
                           "\n2. Fire Employee" +
                           "\n3. Edit Employee Info" +
                           "\n4. Hire new Employee" +
                           "\n5. Update an Employee's hours" + 
                           "\n6. Back to main menu");
         
         
         System.out.print("\nEnter choice: ");
         choose = userIntIn();
         
         switch (choose) {
            case 1:
               printEmployees();
               break;
            case 2:
               fireEmployee();
               break;
            case 3:
               editStaffInfo();
               break;
            case 4:
               hireStaff(); 
               break;
            case 5:
               changeEmployeeHours();
               break;
            case 6: 
               break;
            default:
               System.out.println("INVALID CHOICE");
               break;
         }//Switch case
         
      } while (choose != 6);
   }//staffManagement class          
   
   //Prints out on consoles all employees
   public void printEmployees() {
     for (int i = 0; i < zoo.getNumStaff(); i ++ ) {
        System.out.println(zoo.getStaffList()[i]);
     }
   }//printEmployee method
   
   //Fires an employee
   public void fireEmployee() {
    zoo.removeStaff();
    System.out.println("The staff member was successfully fired.");
   }//fireEmployee method
   
  
   //Edits an Employee's information
   public void editStaffInfo() {
      Staff select = zoo.searchStaff();
      System.out.println(select);
      int choice;
      
      if (select instanceof Labourer) {
         do {
            System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                              "\n1. Edit name" +
                              "\n2. Edit staff number" +
                              "\n3. Edit password" +
                              "\n4. Edit wage" +
                              "\n5. Edit hours worked" +
                              "\n6. Back to previous menu");
            System.out.print("\nEnter choice: ");
            choice = userIntIn();
            switch (choice) {
               case 1:
                  System.out.print("Enter new name: ");
                  select.setStaffName(sc.nextLine());
                  break;
               case 2:
                  System.out.print("Enter new staff number: ");
                  select.setStaffNum(userIntIn());
                  break;
               case 3:
                  System.out.print("Enter new password: ");
                  select.setStaffPassword(sc.nextLine());
                  break;
               case 4:
                  System.out.print("Enter new wage: ");
                  ((Labourer)select).setWage(userDoubleIn());
                  break;
               case 5:
                  System.out.print("Enter new hours: "); //as a mannual way to change hours in case of error
                  ((Labourer)select).setHours(userIntIn());
                  break;
               case 6:
                  break;
               default:
                  System.out.println("INVALID CHOICE");
                  break;
            }//switch
         } while (choice != 6);
      } 
      else {
         do {
            System.out.print( "\n~~~~~~~~~~~~~~~~~~~~~~~~" + 
                       "\n1. Edit name" +
                              "\n2. Edit staff number" +
                              "\n3. Edit password" +
                              "\n4. Edit salary" +
                              "\n5. Back to previous menu");
            System.out.print("\nEnter your choice: ");
            choice = userIntIn();
            switch (choice) {
               case 1:
                  System.out.print("Enter new name: ");
                  select.setStaffName(sc.nextLine());
                  break;
               case 2:
                  System.out.print("Enter new staff number: ");
                  select.setStaffNum(userIntIn());
                  break;
               case 3:
                  System.out.print("Enter new password: ");
                  select.setStaffPassword(sc.nextLine());
                  break;
               case 4:
                  System.out.print("Enter new salary: ");
                  ((Manager)select).setSalary(userDoubleIn());
                  break;
               case 5:
                  break;
               default:
                  System.out.println("INVALID CHOICE");
                  break;
            }
         } while (choice != 5);
      }
      
   }//editStaffInfo method
   
   //Creates a new employee to be added into staffList
   public void hireStaff() {
      if (zoo.getNumStaff() < zoo.getMaxStaff()) { //checks if can hold more staff
         int choice;
         Staff hire = null;
         do {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~" + 
                                "\n1. Hire a manager" + 
                                "\n2. Hire a labourer");
            choice = userIntIn();
            switch (choice) {
               case 1:
                  System.out.print("Enter name: ");
                  String name = sc.nextLine();
                  System.out.print("Enter staff number: ");
                  int staffNum = userIntIn();
                  System.out.print("Enter password: ");
                  String pass = sc.nextLine();
                  System.out.print("Enter salary: ");
                  double salary = userDoubleIn();
                  hire = new Manager(name, staffNum, pass, salary);
                  break;
               case 2:
                  System.out.print("Enter name: ");
                  name = sc.nextLine();
                  System.out.print("Enter staff number: ");
                  staffNum = userIntIn();
                  System.out.print("Enter password: ");
                  pass = sc.nextLine();
                  System.out.print("Enter wage: ");
                  double wage = userDoubleIn();
                  hire = new Labourer(name, staffNum, pass, wage, 0);
                  break;
               default:
                  System.out.println("INVALID INPUT");
                  break;
            }
         } while (choice != 1 && choice != 2);
         zoo.editStaffList(hire, zoo.getNumStaff()); 
         zoo.setNumStaff(zoo.getNumStaff() + 1);
      } 
      else {
         System.out.println("Cannot fit more staff."); 
      }
   }//hireStaff method
   
   public void changeEmployeeHours(){
      boolean changed = false;
      while (changed == false) {
         Staff select = zoo.searchStaff();
         System.out.println(select);
         if (select instanceof Labourer){
            System.out.print("Update new hours: ");
            ((Labourer)select).setHours(userIntIn());
            changed = true;
         } else if (select instanceof Manager) { 
            System.out.println("Please select a Labourer, not a Manager");
         }
      };
   }//changeEmployeeHOurs method 
      
   ////Financial Management Menu/////////////////
   public void financialManagement() {
      
      int choose = 0;
      do {      
         System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + 
                            "Financial Management : " + 
                           "\n1. Calculate Profits of each region" +
                           "\n2. Find the highest earning region" +
                           "\n3. Find the lowest earning region" +
                           "\n4. Calculate average earnings per region" +
                           "\n5. Output total earnings of the zoo" +
                           "\n6. Back to main menu");
         System.out.print("\nEnter choice: ");
         choose = userIntIn();
         
         switch (choose) {
            case 1:
               displayRegionEarning();
               break;
            case 2:
               highestEarningRegion();
               break;
            case 3:
               lowestEarningRegion();
               break;
            case 4:
               displayAvgEarning(); 
               break;
            case 5:
               totalEarnings();
               break;
            case 6:
               break;
            default:
               System.out.println("Invalid input. Please try again.");
               break;
         }
      } while (choose != 6);
   }//financialManagement method
   
   //Possibly syntax error?
   //Prints information on all regions
   public void displayRegionEarning() {
      System.out.println();
      zoo.printAllRegionsEarnings();
      System.out.println();
   }
   
   //Determines and displays highest earning region
   public void highestEarningRegion() {
      System.out.println("The highest earning region is " + (zoo.findHighestEarningRegion()).getRegionName()); 
   }
   
   //Determines and displays lowest earning region
   public void lowestEarningRegion() {
      System.out.println("The lowest earning region is " + (zoo.findLowestEarningRegion().getRegionName())); 
   }
   
   //Displays the total earnings of the entire Zoo
   public void totalEarnings() {
      System.out.println("The total earning of the zoo is: $" + zoo.calcTotalEarnings());
   }
   
   public void displayAvgEarning() {
      System.out.println("The average earnings of all regions is: $" + zoo.avgRegionEarnings());
   }
   
   
   
   ////Personal information Menu///////////////////////////////
   public void personalMenu() {
      
      Staff toUseOn; 
      int choose = 0;
      do {
         System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("\nDetermine employee to have information displayed: ");
         toUseOn = zoo.searchStaff();
      }while (toUseOn == null);
     
      do{
         System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("\nPersonal Information menu" +
                           "\n1. Check pay" +
                           "\n2. Display Info" + 
                           "\n3. Back to main menu");
         System.out.print("\nEnter choice: ");
         choose = userIntIn();
            
         switch (choose) {
            case 1:
               System.out.println("That employee's pay is: " + user.calculatePay());
               break;
            case 2:
               System.out.println("Employee info: " + user.toString());
               break;
            case 3: 
               break;
            default: 
               System.out.println("INVALID CHOICE");
               break;
         }
      } while (choose != 3);
   }//personalMenu method 
  
   //Utility methods/////////////////////////////
   /*public static createStaff() {
    String name;
    int staffNum;
    String pass;
    
    System.out.print("
    }
    Useless method so far - Henry L.'s psuedo code*/
   
   //takes input from user until they enter a int                         
   public static int userIntIn(){      
      boolean exit = false;
      int num = 0;
      do {
         try {
            num = Integer.parseInt(sc.nextLine());
            exit = true;
         } 
         catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Enter an integer.");
         }
      } while (!exit);
      return num;
      
   }
   
   //takes input from user until they enter a double
   public static double userDoubleIn(){      
      boolean exit = false;
      double num = 0;
      do {
         try {
            num = Double.parseDouble(sc.nextLine());
            exit = true;
         } 
         catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Enter a double.");
         }
      } while (!exit);
      return num;
      
   }
   
   // takes input from user until they enter a boolean
   public static boolean userBooleanIn(){      
      boolean exit = false;
      boolean choice = false;
      do {
         try {
            choice = Boolean.parseBoolean(sc.nextLine());
            exit = true;
         } 
         catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Enter true or false.");
         }
      } while (!exit);
      return choice;
      
   }
   
/////////////////////////////////////////////  
   
}//Menu class