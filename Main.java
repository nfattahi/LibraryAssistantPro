/* IB Computer Science HL Project: Dossier
 * 
 * Librarian Assistant Pro - Version 1.7
 * Developer: Navid Fattahi
 * Supervisor: Mr. Michael Powell
 * International School of Kuala Lumpur
 * 
 * Developing Platform Specifications:
 * 	Hardware:
 * 		Dell inspiron 1525 : 3rd Confuguration - Summer 2008 Model
 * 		Processor : Intel Core 2 Duo 2.0 GHz T5750
 * 		Memory (RAM) : 2 GB
 * 		Hard Disk Drive Capacity : 250 GB
 *
 * 	Software:
 *		Operating System: Windows Vista Ultimate 32-bit - Service Pack 1
 *		IDE : JCreator LE - Version 4.50.010
 */

/* Librarian Assistant Pro - Version 1.7
 * Class: Main
 * Duties:
 * 		Starting, running and ending the program. this class is in charge
 * 		of controlling and using all other classes in the program.
 * Date: September 2008 - February 2009
 */
 
  
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Main
{
	public static void main(String[] args)
	{		
		//Useful Variables! ************************************
		Scanner kb = new Scanner(System.in);
		IOMethods ioTool = new IOMethods();
		String username = "";
		String password;
		int menuNumber = 0;
		int subMenuNum;
		boolean prompt = false;
		int userAccessLevel = 3;//Set to guest
		int counter = 0;
		//*******************************************************
		
		//Variables related to the users ************************
		Users usernameList = new Users();	
		String pathname = "users.txt";
		File userFile = new File(pathname);
		ReadWriteFile reader = new ReadWriteFile();
		ReadWriteFile writer = new ReadWriteFile();
		ArrayList<String> usernamesFile = new ArrayList<String>();
		//********************************************************
		
		//Variables realted to the Titles *********************
		Titles magazineTitles = new Titles();
		String titlePathname = "titles.txt";
		File titlesFile = new File(titlePathname);
		RAF rafReaderWriter = new RAF(titlesFile);
		ArrayList<String> titlesArrayList = new ArrayList<String>();
		ArrayList<Title> titlesArr = new ArrayList<Title>();
		//********************************************************
		
		//Variables realted to the Magazines *********************
		MagazineShelf magazineShelf = new MagazineShelf();
		String magazinesPathname = "magzineShelf.txt";
		File magazinesFile = new File(magazinesPathname);
		RAF magazineSehlfRafRW = new RAF(magazinesFile);
		ArrayList<String> magazinesArrayList = new ArrayList<String>();
		ArrayList<Magazine> magazinesArr = new ArrayList<Magazine>();
		//********************************************************		
		
		//Reading textfiles and initializing our variables (lists, etc.)
		//Checking if this is the first time the user uses the program:
		try
		{
			usernamesFile = reader.readFromFile(userFile);
			
			titlesArrayList = rafReaderWriter.readRAF();
			magazineTitles.addAllTitles(titlesArrayList);

			magazinesArrayList = magazineSehlfRafRW.readRAF();
			magazineShelf.addAllMagazines(magazinesArrayList);
		}
		catch (IOException ex)
		{
			try
			{
				//Runs only if this is the first execution of the program
				PrintWriter users = new PrintWriter(userFile);
				username = ioTool.inputString("Welcome to Librarian Assistant Pro!\n" +
								   "To proceed you should create a user first.\n" +
								   "Please choose a username: ");
				String password1 = ioTool.inputString("Choose a password: ");
				String password2 = ioTool.inputString("Verify your password: ");
				while(!password2.equals(password1))
				{
					password2 = ioTool.inputString("Passwords do not match. Please try again: ");
				}
				usernameList.addUser(username, password1, password2, 1);
				usernamesFile = usernameList.writeUserList();
				try
				{
					writer.writeToFile(usernamesFile, userFile);
				}
				catch(IOException exception)
				{
					System.out.println("*** Cannot write to file " + userFile + " ***");
				}
				
				userAccessLevel = 1;
				prompt = true;
				
			}
			catch (FileNotFoundException exception)
			{
				System.out.println("*** Cannot create " + pathname + " ***");
			}
		}
		
		//Copying textfile content into an arraylist of users
		usernameList.readUserList(usernamesFile);
		
		// Getting username and password from the user
		while(prompt == false)
		{
			while(prompt == false)
			{
				//If user fails to enter the correct username and password
				//the progeam is automatically closed.
				if(counter >= 3)
				{
					System.out.println(	"Sorry. You have failed to enter the right username and password for 3 times." +
										"\nPlease try again later.");
					System.exit(0);
				}
				else
				{
					if (counter != 0)
						System.out.println();
					
					username = ioTool.inputString("Please enter your username: ");
					password = ioTool.inputString("Please enter your password: ");
					
					//Checking the database and prompting the user
					prompt = usernameList.login(username, password);
					
					//Finding the user type (access level)
					User user = usernameList.returnUser(username);
					if (user != null)
						userAccessLevel = user.getUserType();
					
					counter++;
				}
			}
		}
		
		if(prompt == true)
		{
			while(menuNumber != 4)	//Start of the main menu loop
			{
			showMainMenu();
			menuNumber = ioTool.inputInt();
			while(menuNumber < 1 || menuNumber > 4)
			{
				menuNumber = ioTool.inputInt("Number you entered is invalid. Please enter a number between 1 to 4: ");
			}
			
			if(menuNumber==1)
			{
				subMenuNum = 0;
				while(subMenuNum != 5)
				{
				showMagazinesMenu();
				subMenuNum = ioTool.inputInt();
				
				while(menuNumber < 1 || menuNumber > 5)
				{
					subMenuNum = ioTool.inputInt("Number you entered is invalid. Please enter a number between 1 to 5: ");
				}
				
				if(subMenuNum == 1)											//Adding titles
				{
					if(userAccessLevel == 1 || userAccessLevel == 2)		//Adding titles: Users-Only
																			//Guests cannot add titles
					{
					//Asking the user about the magazine creation type (Immidiate or Complete)
					int creationType = ioTool.inputInt("Do you want to create a quick(1) or a complete(2) magazine record? ");
					while(creationType != 1 && creationType != 2)
					{
						creationType = ioTool.inputInt("Number you entered is invalid. Please enter 1 or 2: ");
					}
					
					//Creating a magazine (Immidiate type)
					if(creationType == 1)
					{

						String title = ioTool.inputString("Enter the following information:\n" +
													"Title: ");
						if(magazineTitles.findTitle(title) != null)		// Checking whether the magazine exists in database
						{
							System.out.println("Sorry, the title already exists in databse!");
						}
						else
						{
						//Getting date and reformatting it ****************
						String firstArrivalDateStr = ioTool.inputString("First date of arrvial(ddmmyyyy): ");
						int firstArrivalDateInt = 0;
						try
						{
							firstArrivalDateInt = Integer.parseInt(firstArrivalDateStr);
						}
						catch (NumberFormatException ex)
						{
							firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"First date of arrvial(ddmmyyyy): ");
						}
						while (firstArrivalDateInt == 0 || firstArrivalDateStr.length() != 8)
						{
							firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"First date of arrvial(ddmmyyyy): ");
							try
							{
								firstArrivalDateInt = Integer.parseInt(firstArrivalDateStr);
							}
							catch (NumberFormatException ex)
							{
								firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																	"First date of arrvial(ddmmyyyy): ");
							}
						}
						DateFunctions dateVal = new DateFunctions();
						Date firstArrivalDate = dateVal.dateValidator(firstArrivalDateStr);
						
						//*************************************************
												
						//Getting date and reformatting it ****************
						String expiryDateStr = ioTool.inputString("Expiry Date(ddmmyyyy): ");
						int expiryDateInt = 0;
						try
						{
							expiryDateInt = Integer.parseInt(expiryDateStr);
						}
						catch (NumberFormatException ex)
						{
							expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"Expiry Date(ddmmyyyy): ");
						}
						while (expiryDateInt == 0 || expiryDateStr.length() != 8)
						{
							expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"Expiry Date(ddmmyyyy): ");
							try
							{
								expiryDateInt = Integer.parseInt(expiryDateStr);
							}
							catch (NumberFormatException ex)
							{
								expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																	"Expiry Date(ddmmyyyy): ");
							}
						}
						Date expiryDate = dateVal.dateValidator(expiryDateStr);
						//*************************************************

						double sbcCost = ioTool.inputDouble("Subscription cost(RM): ");	
						while (sbcCost == 0)
						{
							sbcCost = ioTool.inputDouble("Invalid input. Please try again.\nSubscription cost(RM): ");	
						}							
						int period = ioTool.inputInt("Period (days): ");
						while (period == 0)
						{
							period = ioTool.inputInt("Invalid input. Please try again.\nPeriod: ");	
						}
						Title magazine = new Title(title, expiryDate, sbcCost, firstArrivalDate, period);
						magazineTitles.addTitle(magazine);
						System.out.println("Magazine '" + title + "' is successfully created.");
						}
					}
					
					//Creating a magazine (Complete type)
					else if(creationType == 2)
					{
						String title = ioTool.inputString("Enter the following information:\n" +
													"Title: ");
						if(magazineTitles.findTitle(title) != null)		// Checking whether the magazine exists in database
						{
							System.out.println("Sorry, the title already exists in databse!");
						}
						else
						{
						//Getting date and reformatting it ****************
						String firstArrivalDateStr = ioTool.inputString("First date of arrvial(ddmmyyyy): ");
						int firstArrivalDateInt = 0;
						try
						{
							firstArrivalDateInt = Integer.parseInt(firstArrivalDateStr);
						}
						catch (NumberFormatException ex)
						{
							firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"First date of arrvial(ddmmyyyy): ");
						}
						while (firstArrivalDateInt == 0 || firstArrivalDateStr.length() != 8)
						{
							firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"First date of arrvial(ddmmyyyy): ");
							try
							{
								firstArrivalDateInt = Integer.parseInt(firstArrivalDateStr);
							}
							catch (NumberFormatException ex)
							{
								firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																	"First date of arrvial(ddmmyyyy): ");
							}
						}
						DateFunctions dateVal = new DateFunctions();
						Date firstArrivalDate = dateVal.dateValidator(firstArrivalDateStr);
						//*************************************************
						
						//Getting date and reformatting it ****************
						String expiryDateStr = ioTool.inputString("Expiry Date(ddmmyyyy): ");
						int expiryDateInt = 0;
						try
						{
							expiryDateInt = Integer.parseInt(expiryDateStr);
						}
						catch (NumberFormatException ex)
						{
							expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"Expiry Date(ddmmyyyy): ");
						}
						while (expiryDateInt == 0 || expiryDateStr.length() != 8)
						{
							expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"Expiry Date(ddmmyyyy): ");
							try
							{
								expiryDateInt = Integer.parseInt(expiryDateStr);
							}
							catch (NumberFormatException ex)
							{
								expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																	"Expiry Date(ddmmyyyy): ");
							}
						}
						Date expiryDate = dateVal.dateValidator(expiryDateStr);
						//*************************************************
											
						double sbcCost = ioTool.inputDouble("Subscription cost(RM): ");							
						while (sbcCost == 0)
						{
							sbcCost = ioTool.inputDouble("Invalid input. Please try again.\nSubscription cost(RM): ");	
						}							
						int period = ioTool.inputInt("Period (days): ");
						while (period == 0)
						{
							period = ioTool.inputInt("Invalid input. Please try again.\nPeriod: ");	
						}
						String type = ioTool.inputString("Type: ");
						String publisher = ioTool.inputString("Publisher: ");
						String country = ioTool.inputString("Country: ");
						String sourceType = ioTool.inputString("Source Type (Online/Offline): ");
						String vendor = ioTool.inputString("Vendor: ");
						String vendorPhone = ioTool.inputString("Vendor's Phone Number: ");
						String vendorAddress = ioTool.inputString("Vendor's Address: ");
						String issuedTo = ioTool.inputString("Issued to: ");
						String costCenter = ioTool.inputString("Cost Center: ");
						String budgetCat = ioTool.inputString("Budget Category: ");						
						
						Title magazine = new Title(title, expiryDate, sbcCost, firstArrivalDate, period,
														 type, publisher, country, sourceType, vendor, vendorPhone,
														 vendorAddress, issuedTo, costCenter, budgetCat);
						magazineTitles.addTitle(magazine);
											 
						System.out.println("Magazine '" + title + "' is successfully created.");
						}
					}
					}
					else
						System.out.println("Sorry, only registered users can add new titles!");
				}
				
				else if(subMenuNum == 2)								//Editing titles
				{
					if(userAccessLevel == 1 || userAccessLevel == 2)	//Editing titles: Users-Only
																		//Only users can edit titles
					{
					//Choosing the magazine which is to be editted and finding it in database
					String inputStr = "";
					inputStr = ioTool.inputString("Enter the magazine title you which to edit: ");
					Title inputTitle = magazineTitles.findTitle(inputStr);
					if (inputTitle != null)
					{
						
						System.out.println("Choose the field you want to edit:");
						System.out.println(	"1 - Title\n2 - First Date of Arrival\n3 - Expiry Date\n" +
											"4 - Subscription Cost\n5 - Period\n6 - Type\n" +
											"7 - Publisher\n8 - Country\n9 - Source Type\n" +
											"10- Vendor\n11- Vendor's Phone\n12- Vendor's Address\n" +
											"13- Issued to\n14- Cost Center\n15-Budget Category");
						int fieldNum = ioTool.inputInt("Enter the number of field which you wish to edit: ");
						while(fieldNum < 1 || fieldNum > 15)
						{
							fieldNum = ioTool.inputInt("Your number must be between 1 to 15: ");
						}
						
						if (fieldNum == 1)		//Editing title
						{
							System.out.println("Current Title: " + inputTitle.getTitle());
							inputTitle.setTitle(ioTool.inputString("Enter the new title: "));
						}
						else if (fieldNum == 2) //Editing First Arrival Date
						{
							System.out.println("Current First Date of Arrival: " + inputTitle.getTitle());
							
							//Getting the input from the user	
							String firstArrivalDateStr = ioTool.inputString("First date of arrvial (ddmmyyyy): ");
							int firstArrivalDateInt = 0;
							try
							{
								firstArrivalDateInt = Integer.parseInt(firstArrivalDateStr);
							}
							catch (NumberFormatException ex)
							{
								firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																   	   	"First date of arrvial(ddmmyyyy): ");
							}
							while (firstArrivalDateInt == 0 || firstArrivalDateStr.length() != 8)
							{
								firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																	 	 "First date of arrvial(ddmmyyyy): ");
								try
								{
									firstArrivalDateInt = Integer.parseInt(firstArrivalDateStr);
								}
								catch (NumberFormatException ex)
								{
									firstArrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																			 "First date of arrvial(ddmmyyyy): ");
								}
							}
							DateFunctions dateVal = new DateFunctions();
							Date firstArrivalDate = dateVal.dateValidator(firstArrivalDateStr);
							// Updating the field
							inputTitle.setFirstArrivalDate(firstArrivalDate);

						}
						else if (fieldNum == 3) //Editing Expiry Date
						{
							System.out.println("Current Expiry Date: " + inputTitle.getTitle());
							
							//Getting the input from the user	
							String expiryDateStr = ioTool.inputString("Expiry Date (ddmmyyyy): ");
							int expiryDateInt = 0;
							try
							{
								expiryDateInt = Integer.parseInt(expiryDateStr);
							}
							catch (NumberFormatException ex)
							{
								expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																	"Expiry Date(ddmmyyyy): ");
							}
							while (expiryDateInt == 0 || expiryDateStr.length() != 8)
							{
								expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																	"Expiry Date(ddmmyyyy): ");
								try
								{
									expiryDateInt = Integer.parseInt(expiryDateStr);
								}
								catch (NumberFormatException ex)
								{
									expiryDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																		"Expiry Date(ddmmyyyy): ");
								}
							}
							DateFunctions dateVal = new DateFunctions();
							Date expiryDateDate = dateVal.dateValidator(expiryDateStr);
							// Updating the field
							inputTitle.setExpiryDate(expiryDateDate);
						}
						else if (fieldNum == 4)	//Editing Subsciption Cost
						{
							System.out.println("Current Subscription Cost: " + inputTitle.getSbcCost());
							// Gettng the new value from the user
							double sbcCost = ioTool.inputDouble("Enter the new Subscription cost(RM): ");							
							while (sbcCost == 0)
							{
								sbcCost = ioTool.inputDouble("Invalid input. Please try again.\n" +
															 "Enter the new Subscription cost(RM): ");	
							}
							inputTitle.setSbcCost(sbcCost);
						}
						else if (fieldNum == 5)	//Editing Period
						{
							System.out.println("Current Period(days): " + inputTitle.getPeriod());
							// Gettng the new value from the user
							int period = ioTool.inputInt("Enter the new Period(days): ");							
							while (period == 0)
							{
								period = ioTool.inputInt("Invalid input. Please try again.\n" + 
														 "Enter the new Period(days): ");	
							}
							inputTitle.setPeriod(period);
						}
						else if (fieldNum == 6)	//Editing type
						{
							System.out.println("Current Type: " + inputTitle.type);
							inputTitle.type = ioTool.inputString("Enter the new type: ");
						}
						else if (fieldNum == 7)	//Editing publisher
						{
							System.out.println("Current publisher: " + inputTitle.publisher);
							inputTitle.publisher = ioTool.inputString("Enter the new publisher: ");
						}
						else if (fieldNum == 8)	//Editing country
						{
							System.out.println("Current Country: " + inputTitle.country);
							inputTitle.country = ioTool.inputString("Enter the new country: ");
						}
						else if (fieldNum == 9)	//Editing Source Type
						{
							System.out.println("Current Source Type (Online/Offline): " + inputTitle.sourceType);
							inputTitle.sourceType = ioTool.inputString("Enter the new Source Type (Online/Offline): ");
						}
						else if (fieldNum == 10)//Editing Vendor
						{
							System.out.println("Current Vendor: " + inputTitle.vendor);
							inputTitle.vendor = ioTool.inputString("Enter the new vendor: ");
						}
						else if (fieldNum == 11)//Editing Vendor's Phone Number
						{
							System.out.println("Current Vendor's Phone Number: " + inputTitle.vendorPhone);
							inputTitle.vendorPhone = ioTool.inputString("Enter the new Vendor's Phone Number: ");
						}
						else if (fieldNum == 12)//Editing Vendor's Address
						{
							System.out.println("Current Vendor's Address: " + inputTitle.vendorAddress);
							inputTitle.vendorAddress = ioTool.inputString("Enter the new Vendor's Address: ");
						}
						else if (fieldNum == 13)//Editing Issued to
						{
							System.out.println("Magazine is currently issued to: " + inputTitle.issuedTo);
							inputTitle.issuedTo = ioTool.inputString("Enter the new place which magazine " +
																		"should be issued to: ");
						}
						else if (fieldNum == 14)//Editing Cost Center
						{
							System.out.println("Current Cost Center: " + inputTitle.costCenter);
							inputTitle.costCenter = ioTool.inputString("Enter the new Cost Center: ");
						}
						else if (fieldNum == 15)//Editing Budget Category
						{
							System.out.println("Current Budget Category: " + inputTitle.budgetCat);
							inputTitle.budgetCat = ioTool.inputString("Enter the new Budget Category: ");
						}
						
					}
					else
						System.out.println("Sorry, The magazine you're looking for does not exist.");
					}
					else
						System.out.println("Sorry, only registered users can edit titles!");
					
				}
				else if(subMenuNum == 3)								//Removing titles
				{
					if(userAccessLevel == 1)							//Removing titles: Admin-Only
																		//Only admin can remove titles
					{
						String inputTitle = ioTool.inputString("Enter the magazine title you which to remove: ");
						Title foundTitle = magazineTitles.findTitle(inputTitle);
						if (foundTitle == null)
							System.out.println("Sorry, The magazine you're looking for does not exist.");
						else
						{
							magazineTitles.removeTitle(foundTitle);
							System.out.println("'" + foundTitle.getTitle() + "' was remove successfully!");
						}
					}
					else
						System.out.println("Sorry, only users with admin priorities can remove magazines!");
						
				}
				else if(subMenuNum == 4)								//Checkin Magazines
				{
					if(userAccessLevel == 1 || userAccessLevel == 2)	//Checkin Magazines: Users-Only
																		//Guests cannot checkin magazines
					{
					//Getting title of the magazine from user
					//If title is not found in database, user is informed
					//and an existing title should be entered again
					System.out.println("Enter the following information for the magazine you wish to check in: ");
					String magazineTitle = ioTool.inputString("Title: ");
					Title title = magazineTitles.findTitle(magazineTitle);
					if (title != null)
					{					
					String trackingCode = ioTool.inputString("Tracking Code: ");
					//Getting arrival date of the magazine from user
					String arrivalDateStr = ioTool.inputString("Date of Arrival(ddmmyyyy): ");
					int arrivalDateInt = 0;
					try
					{
						arrivalDateInt = Integer.parseInt(arrivalDateStr);
					}
					catch (NumberFormatException ex)
					{
						arrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
															"Date of Arrival(ddmmyyyy): ");
					}
					while (arrivalDateInt == 0 || arrivalDateStr.length() != 8)
					{
						arrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
															"Date of Arrival(ddmmyyyy): ");
						try
						{
							arrivalDateInt = Integer.parseInt(arrivalDateStr);
						}
						catch (NumberFormatException ex)
						{
							arrivalDateStr = ioTool.inputString("Invalid input. Please try again.\n" +
																"Date of Arrival(ddmmyyyy): ");
						}
					}
					DateFunctions dateVal = new DateFunctions();
					Date arrivalDate = dateVal.dateValidator(arrivalDateStr);
					if(arrivalDate.before(title.getFirstArrivalDate()))
					{
						System.out.println("Check in date is ealier than first date of arrvial of '" +
										   title.getTitle() + "' magazine! Check in can not be performed.");
					}
					else
					{
						if(magazineShelf.magazineExist(title, arrivalDate)== true)
							System.out.println("This magazine is already checked in!");
						else if (magazineShelf.trackingCodeExist(trackingCode)== true)
							System.out.println("The tracking code you entered already exists in database!");
						else
						{
							Magazine newMagazine = new Magazine(title, trackingCode, arrivalDate);
							magazineShelf.addToShelf(newMagazine);
							System.out.println("'" + title.getTitle() + "' with tracking code of '" + 
											   trackingCode + "' is successfully checked in!");							
						}
					}
					}
					else
						System.out.println("The title you're looking for does not exist. ");
					}
					else
						System.out.println("Sorry, only registered users can edit titles!");
				}
				}
				//********** Updating the external text files (databases) **********
				//******************* Writing data into textfiles ******************
				try
				{
					ArrayList<String> tempArrayList = new ArrayList<String>();
					tempArrayList = magazineTitles.titlesToString();
					
					rafReaderWriter.writeRAF(tempArrayList);
					titlesArrayList = rafReaderWriter.readRAF();
					
					tempArrayList = magazineShelf.magazineShelfToString();
					magazineSehlfRafRW.writeRAF(tempArrayList);
				}
				catch(IOException ex)
				{
					System.out.println("*** Cannot write to file " + userFile + " ***");
				}
				//**********   End of Updating the external text files    **********
				//******************************************************************
				
			}
			
			// Reports Menu
			else if(menuNumber==2)						//Reports menu: All-Users have access
			{
				subMenuNum = 0;
				while(subMenuNum != 6)
				{
				showReportsMenu();
				subMenuNum = ioTool.inputInt();
				
				while(subMenuNum < 1 || subMenuNum > 6)
				{
					subMenuNum = ioTool.inputInt("Number you entered is invalid. Please enter a number between 1 to 6: ");
				}
				
				if(subMenuNum==1)		//Submenu: Titles
				{
					magazineTitles.printTitlesReport(titlesArrayList);
				}
				else if(subMenuNum==2)	//Submenu: Checked in magazines
				{
					magazineShelf.printMagazineShelfReport();
				}
				else if(subMenuNum==3)	//Submenu: Delayed magazines
				{
					System.out.println(magazineTitles.size());
					magazineShelf.printDelayedMagazines(magazineTitles);
				}
				else if(subMenuNum==4)	//Submenu: Expired magazines
				{
					DateFunctions dateChecker = new DateFunctions();
					String returnString = "";
					int count = 1;
					
					System.out.println("----------Expired Magazines----------");
					for (int i = 0; i < magazineTitles.size(); i++)
					{
						Title title = magazineTitles.getTitle(i);
						if (dateChecker.isDatePassed(title.getExpiryDate()))
						{
								returnString = returnString + count + " - " + title.getTitle() + "\t" +
											   "Expired at: " + dateChecker.dateToString(title.getExpiryDate())+ "\n";
								count++;
						}
					}
					System.out.println(returnString);
				}
				else if(subMenuNum==5)	//Submenu: Users
				{
					usernameList.printUsersReport();
				}
				else					//Submenu: Exit
				{
					//If the user enters "6" it will the loop would end - no need to any code in here!
				}
				}
			}
			
			//Users Menu
			else if(menuNumber==3)
			{
				subMenuNum = 0;
				while(subMenuNum != 5)
				{
				showUsersMenu();
				subMenuNum = ioTool.inputInt();
				
				while(subMenuNum < 1 || subMenuNum > 5)
				{
					subMenuNum = ioTool.inputInt("Number you entered is invalid. Please enter a number between 1 to 5.");
				}
				
				if(subMenuNum==1)										//Changing password for the current account
				{
					if(userAccessLevel == 1 || userAccessLevel == 2)	//Creating users: Users-Only
																		//Guests cannot change accounts password
					{
						String oldPassword = ioTool.inputString("Please enter your old password: ");
						String newPassword1 = ioTool.inputString("New password: ");
						String newPassword2 = ioTool.inputString("New password verification: ");
						// Username is already defined as a Global variable in the main method
						
						usernameList.changePassword(username, oldPassword, newPassword1, newPassword2);
					}
					else
						System.out.println("Sorry, only registered users can change their password!");
				}
				else if(subMenuNum==2)								//Creating users
				{
					if(userAccessLevel==1)							//Creating users: Admin-Only
																	//Reqular users and guests cannot creat new users
					{
						System.out.println("Creating a new user:");
						String newUser = ioTool.inputString("Enter username: ");
						String newUsersPass1 = ioTool.inputString("Enter password: ");
						String newUsersPass2 = ioTool.inputString("Verify password: ");
						while(!newUsersPass1.equals(newUsersPass2))
						{
							newUsersPass2 = ioTool.inputString("Passwords do not match. Verify password: ");
						}
						int newUsersType = ioTool.inputInt("User's Access level (1,2,3): ");
						usernameList.addUser(newUser, newUsersPass1, newUsersPass2, newUsersType);
					}
					else
						System.out.println("Sorry, only users with admin priorities can create new users!");
				}
				else if(subMenuNum==3)								//Editing users
				{
					if(userAccessLevel == 1)						//Editing users: Admin-Only
																	//Reqular users and guests cannot edit users
					{
						String user = ioTool.inputString("Enter the username that you wish to edit: ");
						if (usernameList.userExists(user) == true)
						{
							System.out.println( "Enter the number of the field you want to edit:\n" +
												"1- Username\n2- Password\n3- UserType");
							int fieldNum = ioTool.inputInt("Enter the number of the field: ");
							
							if(fieldNum == 1)
							{
								System.out.println("Updating the username for the user " + user + ".");
								String newUsername = ioTool.inputString("Enter the new username: ");
								usernameList.changeUsername(user, newUsername);
							}
							
							else if(fieldNum == 2)
							{
								System.out.println("Updating the password for the user " + user + ".");
								String oldPass = ioTool.inputString("Enter the old password: ");
								String newPass1 = ioTool.inputString("Enter the new password: ");
								String newPass2 = ioTool.inputString("Verify the new password: ");
								usernameList.changePassword(user, oldPass, newPass1, newPass2);
							}
							
							else if(fieldNum == 3)
							{
								System.out.println("Updating the access level for the user " + user + ".");
								int newType = ioTool.inputInt("Enter the new Access level (1,2,3):\n" + 
															  "(1 = Admin, 2 = Regular User, 3 = Guest");
								while ( newType < 1 || newType > 3)
								{
									newType = ioTool.inputInt("You can only choose 1, 2 or 3 as access level.\n" +
													 "Please try again: ");		
								}
								usernameList.returnUser(user).setUserType(newType);	
							}
							
						}
						else
						{
							System.out.println("Username '" + user + "' does not exist.");
						}
					}
					else
						System.out.println("Sorry, only users with admin priorities can edit users!");
				}
				else if(subMenuNum == 4)			//Removing Users
				{
					if (userAccessLevel == 1)		//Removing users: Admin-Only
													//Reqular users and guests cannot remove users
					{
						System.out.println("Removing user:");
						String user = ioTool.inputString("Enter the username of the user you wish to remove: ");
						usernameList.removeUser(user);
					}
					else
						System.out.println("Sorry, only users with admin priorities can remove users!");
				}
				
			}
			}
			usernamesFile = usernameList.writeUserList();
			//Writing users information on users.txt file
			try
			{
				writer.writeToFile(usernamesFile, userFile);
			}
			catch(IOException ex)
			{
				System.out.println("*** Cannot write to file " + userFile + " ***");
			}
			
		}		//End of main loop

		}		//End of the if(prompt == true)
	}
	
	//************************** Helper methods : Printing submenues **************************
	private static void showMainMenu()
	{
		System.out.println("----------Main Menu----------");
		System.out.println("1- Magazines\n2- Reports\n3- Users\n4- Exit");
		System.out.print("Enter the number you wish to reach: ");
	}
	
	private static void showMagazinesMenu()
	{
		System.out.println("----------Magazines----------");
		System.out.println("1- Add a title\n2- Edit a title\n3- Remove a title\n" +
						   "4- Check in a magazine\n5- Back to main menu");
		System.out.print("Enter the number you wish to reach: ");
	}

	private static void showReportsMenu()
	{
		System.out.println("----------Reports----------");
		System.out.println("1- Titles\n2- Checked in magazines\n3- Delayed\n4- Expired Magazines\n" +
						   "5- Users\n6- Back to main menu");
		System.out.print("Enter the number you wish to reach: ");
	}
	
	private static void showUsersMenu()
	{
		System.out.println("----------Users----------");
		System.out.println("1- Change your password\n2- Add a new user\n3- Edit users\n" + 
						   "4- Remove users\n5- Back to main menu");
		System.out.print("Enter the number you wish to reach: ");
	}
}