//Ver. 1 June 06 2016
/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Labourer
 * Author: Welson Pan & Sean He
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: The Manager class extends Staff, and the objects represent the manager of the zoo. Each Manager has 
            their salary, and their pay is calculated based on that field. The class has the functionalities 
            of printing the Manager's information and converting their information into file format. In the Menu class, Staff are 
            distinguished using instanceof, and Managers have more privileges than the Labourers when accessing the program.
 */

class Manager extends Staff {
 
 // fields
  private double salary;
  
 // constructor to initiate fields and superclass
  
  public Manager(String nameIn, int numIn, String pwIn, double salaryIn) {
    super(nameIn, numIn, pwIn);
    salary = salaryIn;
  }
  
 // accessors and mutators
  public double getSalary() {
    return salary;
  }
  
  public void setSalary(double salaryIn) {
    salary = salaryIn;
  }
  
 //calculates the pay of the manager
  public double calculatePay() {
    return salary;
  }
  
  //returns the manager information in file format
  public String toFile() {
    return "Manager\r\n" + staffName + "\r\n" + staffNum + "\r\n" + staffPassword + "\r\n" + salary + "\r\n";
  }
  
 //returns a String containing the manager's information
  public String toString() {
    return super.toString() +
      "\nPosition: Manager" +
      "\nSalary: $" + salary + "\n";
  }
}