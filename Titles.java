/* Librarian Assistant Pro - Version 1.7
 * Class: Titles
 * Duties:
 * 		The instance of this class represents a pack of titles
 *		all the titles are kept in a doublyLinkedList
 *		the class gives us access to titles and helps us
 *		to manipulate and use titles
 * Creation Date: September 2008
 */

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;


class Titles
{
	private DoublyLinkedList titleList = new DoublyLinkedList();
	
	public int size()
	{
		return titleList.size();
	}
	
	public Title getTitle(int n)
		throws NullPointerException
	{
		try
		{
			//Getting the nth element of the list
			//Casting is used to convert Object to Title
			return (Title)titleList.get(n+1).getValue();
		}
		catch (NullPointerException ex)
		{
			System.out.println("Oops, the index is out of bound!");
		}
		return null;
	}
	
	//Adds a title to titles list
	public void addTitle(Title newTitle)
	{
		titleList.add(newTitle);
	}
	
	//Removes a title from the titles list
	public void removeTitle(Title title)
	{
		titleList.removeObj(title);
	}
	
	public DoublyLinkedList getTitles()
	{
		return titleList;
	}
	
	public void setTitles(DoublyLinkedList newTitleList)
	{
		titleList = newTitleList;
	}
	
	//Gets all the titles in this list
	//Converts them into strings and returns
	//them as a one string arrayList
	public ArrayList<String> titlesToString()
	{
		ArrayList<String> strArr = new ArrayList<String>();
		int counter = 0;
		while(counter < titleList.size())
		{
			Title title = (Title)titleList.get(counter+1).getValue();
			strArr.add(title.toString());
			counter++;
		}
			
		return strArr;
	}
	
	//Creates a title object using the Given string
	public Title stringToTitle(String strBlock)
	{
			
		String title = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		
		String firstArrivalDateStr = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
		Date firstArrivalDate = new Date();
		try
		{
			firstArrivalDate = dateFormat.parse(firstArrivalDateStr);
		}
		catch (ParseException e)
		{
		e.printStackTrace();
		}

		String sbcCostStr = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		double sbcCost = Double.parseDouble(sbcCostStr);

		String expiryDateStr = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		Date expiryDate = new Date();
		try
		{
			expiryDate = dateFormat.parse(expiryDateStr);          
		}
		catch (ParseException e)
		{
			System.out.println("Invalid Date Parser Exception ");
			e.printStackTrace();
		}			
			
		String periodStr = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		int period = Integer.parseInt(periodStr);
		
		String type = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		
		String publisher = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		
		String country = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		
		String sourceType = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		
		String vendor = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		
		String vendorPhone = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		
		String vendorAddress = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|"));
		
		String issuedTo = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		
		String costCenter = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		String budgetCat = strBlock;

		Title myTitle = new Title(title, expiryDate, sbcCost, firstArrivalDate, period,
								type, publisher, country, sourceType, vendor, vendorPhone,
								vendorAddress, issuedTo, costCenter, budgetCat);
									
		return myTitle;
	}
	
	//Converts all the elements of an arraylist of strings
	//into Title objects and add them to titles list
	public void addAllTitles(ArrayList<String> strArr)
	{
		int i = 0;
		while(i < strArr.size())
		{
			Title myTitle = stringToTitle(strArr.get(i));
			titleList.add(myTitle);
			i++;
		}
	}
	
	public void printTitlesReport(ArrayList<String> strArr)
	{
		int i = 0;
		
		System.out.println("---------------------------Report: Titles---------------------------");
		while(i<strArr.size())
		{
			String strBlock = strArr.get(i);
			
			String title = strBlock.substring(0,strBlock.indexOf("|"));
			strBlock = strBlock.substring(strBlock.indexOf("|"));
			
			String expiryDateStr = strBlock.substring(0,strBlock.indexOf("|"));
			strBlock = strBlock.substring(strBlock.indexOf("|"));
			
			String firstArrivalDateStr = strBlock.substring(0,strBlock.indexOf("|"));
			strBlock = strBlock.substring(strBlock.indexOf("|"));
			
			String sbcCostStr = strBlock.substring(0,strBlock.indexOf("|"));
			strBlock = strBlock.substring(strBlock.indexOf("|"));
			
			String periodStr = strBlock.substring(0,strBlock.indexOf("|"));
			strBlock = strBlock.substring(strBlock.indexOf("|"));

			System.out.println(i+1 + "\t" + title);
			i++;
		}
	}
	
	//Looks for the title in titles list
	//if title was found returns it
	//otherwise returns false
	public Title findTitle(String title)
	{
		String tempStr;
		Title myTitle = new Title("", null, 0, null, 0);
		for (int i=0; i < titleList.size(); i++)
		{
			//Casting method is used to convert Object to Title
			myTitle = (Title)titleList.get(i+1).getValue();
			if(myTitle.getTitle().equals(title))
			{
				return myTitle;
			}
		}

		return null;
	}
}
