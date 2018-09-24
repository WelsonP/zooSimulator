
//ver. 1
/* ~~~~~~~~~~~~~~~~~~~~ CLASS HEADER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class: Labourer
 * Author: Welson Pan
 * Date: June 7th to June 15th
 * School: A.Y. Jackson
 * Purpose: The Labourer class extends Staff, and the objects represent the labourers of the zoo. Each labourer has 
            their own wage and number of hours, and their pay is calculated based on those fields. The class has the functionalities 
            of printing the Labourer's information and converting their information into file format. In the Menu class, Staff are 
            distinguished using instanceof, and Labourers have less privileges than the Managers when accessing the program.
 */
public class Labourer extends Staff {
  private double wage;
  private int hours;
  // tasks
  
  public double getWage() {
    return wage;
  }
  
  public void setWage(double newWage) {
    wage = newWage;
  }
  
  public int getHours() {
    return hours;
  }
  
  public void setHours(int newHours) {
    hours = newHours;
  }
  
  // Labourer constructor - takes in name, id, password, wage, number of hours and assigns values to fields
  public Labourer(String name, int id, String password, double newWage, int numHours) {
    super(name, id, password);
    wage = newWage;
    hours = numHours;
  }
  
  // calculatePay methed - returns how much the employee has earned to date
  public double calculatePay() {
    return hours * wage;
  }
  
  // returns the labourer information in file format
  public String toFile() {
    return "Labourer\r\n" + staffName + "\r\n" + staffNum + "\r\n" + staffPassword + "\r\n" + wage + "\r\n" + hours + "\r\n";
  }
  
  // toString method - returns a String containing the Staff's information
  public String toString() {
    return super.toString() + "\nPosition: Labourer\nWage: $" + wage + " per hour\nNumber of Hours: " + hours + "\n";
  }
}