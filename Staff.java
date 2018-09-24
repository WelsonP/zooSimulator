//Ver. 2 June 07 2016
//created June 6
//added checkPass June 7

/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Staff
 * Author: Welson Pan & Sean He
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: Staff is an abstract class whose objects represent the staff working in the zoo. Each staff
        has their own name, unique number, and password. Staff use their password to log into the 
            program and are given privileges depending on what type of staff they are. Staff numbers 
            help identify and categorize Staff in an organized fashion. 
            
            Since different types of Staff are paid differently, calculatePay() is an abstract method 
            that calculates a Staff's earnings based on the type of Staff they are. Text file formats 
            for each type of Staff are different, and so there is an abstract method toFile() that 
            converts Staff information into text file format. The class also has the functionality 
            of checking whether an entered password matches the Staff's password, used when logging 
            in for security.
 */

abstract class Staff {  

 // fields
  
  protected String staffName;
  protected int staffNum;
  protected String staffPassword;
  
 // constructor to initiate respective values
  
  public Staff(String nameIn, int numIn, String pwIn) {
    staffName = nameIn;
    staffNum = numIn;
    staffPassword = pwIn;
  }
  
 // accessors and mutators
  
  public String getStaffName() {
    return staffName;
  }
  
  public void setStaffName(String nameIn) {
    staffName = nameIn;
  }
  
  public int getStaffNum() {
    return staffNum;
  }
  
  public void setStaffNum(int numIn) {
    staffNum = numIn;
  }
  
  public String getStaffPassword() {
    return staffPassword;
  }
  
  public void setStaffPassword(String pwIn) {
    staffPassword = pwIn;
  }
  
  // abstract method to calculate the pay
  
  abstract double calculatePay();
  
  // abstract method to convert Staff information to employee info
   
  abstract String toFile();
  
 // checks if the password entered matches with the staff password
  
  //compares the login Screen password the the saved password on file
  public boolean checkPass(String pwIn) {
    if (pwIn.equals(staffPassword)) {
      return true;
    } else {
      return false;
    }
  }
  
 // returns a String containing the staff information
  
  public String toString() {
    return "Name: " + staffName +
      "\nStaff Number: " + staffNum;
  }
}