/* Librarian Assistant Pro - Version 1.7
 * Class: DateFunctions
 * Duties:
 * 		This class helps us to deal with dates.
 *		Since dealing with dates are usually not
 *		very straightforward the methods in this
 *		class can be very helpful.
 *		Date Validation, Date Manipulation and
 *		Date to String conversions are functionalities
 *		of this class
 * Creation Date: November 2008
 */
 
import java.util.Date;
import java.text.SimpleDateFormat;

class DateFunctions
{
	//Validating the dates
	//Extra days and months are authomatically reduced
	//to the maximum possible values
	public Date dateValidator(String dateStr)
	{
		int day = Integer.parseInt(dateStr.substring(0,2));
		int month = Integer.parseInt(dateStr.substring(2,4));
		int year = Integer.parseInt(dateStr.substring(4));
		
		if (month > 12)
			month = 12;
		else if(month < 1)
			month = 1;
		
		//Months with 31 days
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
		{
			if (day > 31)
				day = 31;
		}
		//Months with 30 days
		else if(month == 4 || month == 6 || month == 9 || month == 11)
		{
			if (day > 30)
				day = 30;
		}
		//months with 28 days
		else
		{
			if (day > 28)
				day = 28;
		}
		
		if (day < 1)
		{
			day = 1;
		}
		
		if (year <= 1900)
			year = 100;				// Will set the year to 2000 --> 1900 + 100
		else
			year = year - 1900;		// Sets the year to user's input; otherwise year
									// would be equal to user input + 1900

		Date date = new Date(year, month-1, day);
		System.out.println(dateToString(date));
		return date;
	}
	
	//Checks whether date is already passed or not
	public boolean isDatePassed(Date date)
	{
		Date currentDate = new Date();
		if(currentDate.after(date))			//Checking if the date has been passed
			return true;
		else
			return false;
	}
	
	//Converts date to a string in the form dd/mm/yyyy
	public String dateToString(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder string = new StringBuilder(df.format(date));
		
		String returnString = "" + string;
		return returnString;
	
	}
	
	//Adds 'num' days to 'date' and returns the result
	//as another date
	public Date addDaysToDate(Date date, int num)
	{
		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
		StringBuilder string = new StringBuilder(df.format(date));
		String dateStr = "" + string;
		int dateInt = Integer.parseInt(dateStr);
		int resultDateInt = dateInt + num * 1000000;
		String resultDateStr = "" + resultDateInt;
		
		int day = Integer.parseInt(resultDateStr.substring(0,2));
		int month = Integer.parseInt(resultDateStr.substring(2,4));
		int year = Integer.parseInt(resultDateStr.substring(4));
		
		Date resultDate = new Date(year-1900, month-1, day);
		
		return resultDate;
	}
}
