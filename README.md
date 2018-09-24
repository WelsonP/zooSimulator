# Introduction: #

    This program is meant for managerial use in a zoo environment. Able to be used by both Managers and lower level staff working at the zoo, it tracks employee information such as name, employee number and their pay. Lower level staff are only able to access their own information but Managers can change the information contained within the Zoo, such as the Regions and Staff members that zoo contains. Regions contain different conditions that must be met in order for the particular region to be open. When the program is opened it will import Staff and Region information from designated text files. To begin the program the user must first log in using a pre-registered username and password. 

# 0.0 Log-In Screen: #

This is the first screen the user will see. Like any login screen, the user is to input their staff number and password. By logging in with a Labourer account the employee will be fourth to the Labourer main menu (2.0) and by logging in with a staff account the user will get into the Manager main menu (1.0).
 
# 1.0 Manager Main Menu: #

The main menu for Managers will be the central hub for the management for the whole of Zoo. It will contain the following functions:

# 1.1 Zoo Management #
Zoo Management will be for managing the Animals and Regions of the zoo. It contains the ability to create new Regions, new animals that are added to the zoo, as well as editing information of previously established regions and animals. 

# 1.1.1 Add new Animal #
           1.1.2 Add new Region
           1.1.3.Edit animal information
           1.1.4.Edit Region Information
           1.1.5 Display all Region information

# 1.1.1 Add new Animal #
Add new animal will first determine a region to deposit the animal in. If the Region has the max number of species of animals it will ask you to select another region. If not it will prompt for Species name and Population. 

# 1.1.1: Select Region to make an Animal in: #
    ……
    Enter name to select Region: 
    Enter Species name: 
    Enter population:

# 1.1.2.Add new Region #
Add new region will allow you to create an Arctic, Aquarium or Amazon Rainforest type region. After selection through entering the corresponding number, it will deposit the newly created region into the array that stores Regions. 

# 1.1.2:    Choose which type of region to create. #
                           1.Arctic Region
                           2.Amazon Rainforest Region
                           3.Aquarium Region

# 1.1.3.Edit animal information #
Asks the user to input a Species name. Lets the user choose fields of the Animal to edit:

Species name ~ will ask the user for a new name
Population amount ~ will ask the user for a new population

# 1.1.4.Edit Region Information #
Displays all regions to the user and make them select one to edit. Lets the user choose fields of Region to edit:

    Change Region name ~ Asks user for a new region name
Change number of visits to region ~ Asks user for number of visits
Change region cost ~ asks user for new price
Update days since accident ~ adds one day with no accident
Reset accident day counter ~ resets days since accident to 0

# 1.1.5.Display all Region information #
Display region information will check the condition of the Region and determine whether or not it is fit to be open to the public.

# 1.2 Staff Management #
Staff Management will be used by Managers to administer Employees, it will provide the following functions: 

# 1.2.1. Display Employee info #
Display Employee info will display the information on ALL members of staff.

# 1.2.2. Fire Employee #
Firing employees will delete an employee off of the Staff storage

# 1.2.3. Edit Employee Info #
Edit Employee Info will allow you to change the information of ANY staff member that is stored within the program, it will allow these changes for a Labourer: 
1.	Edit name
2.	Edit staff number
3.	Edit password
4.	Edit wage
5.	Edit hours worked 
For Managers there are no hours to change, and edit wage will be replaced by Edit Salary.

# 1.3 Financial Management #
Financial management will help the Manager view information about the zoo’s earnings. It will have the following functions: 

# 1.3.1 Calculate Profits of each region #
Individually displays the earnings of each region in the zoo

# 1.3.2 Find the highest earning region #
Displays the region with the highest earnings

# 1.3.3 Find the lowest earning region #
Displays the region with the lowest earnings

# 1.3.4 Calculate average earnings per region #
Displays the average earnings of all regions of the zoo

# 1.3.5 Output total earnings of the zoo #
Displays the total earnings of all the Regions in the zoo combined.

# 1.4 Personal Information #
Personal information will simply allow you to check the information of an employee, it does not allow you to change anything. It will first prompt you for the Staff Member’s name. Then it will allow these options: 

# 1.4.1 Check Pay #
Check pay will simply display the salary of a Manager or the wage of a Labourer

# 1.4.2. Display info #
Display info will show the staff member’s username, employee number, whether or not it’s a staff or manager. 

# 1.5 Print Region Status #
This will output whether each region is open or not.

# 2.0 Labourer Main Menu: #
The main menu for Labourers will only have access to information related to the Labourer’s account. It will have the following functions: 

# 2.1 Display Wage #
This will display the Labourer’s own wage

# 2.2 Display Hours #
This will display the Labourer's own hours worked
