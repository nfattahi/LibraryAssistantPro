/* Librarian Assistant Pro - Version 1.7
 * Class: MagazineShelf
 * Duties:
 * 		The instance of this class represents a shef of magazines
 *		magazines in this shelf are all checked in
 *		the class gives us access to magazines and helps us
 *		to manipulate and use them
 * Creation Date: December 2008
 */

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

class MagazineShelf
{
	private DoublyLinkedList magazineShelf = new DoublyLinkedList();
	
	public int size()
	{
		return magazineShelf.size();
	}
	
	public void addToShelf(Magazine newMagazine)
	{
		magazineShelf.add(newMagazine);
	}
	
	public Magazine getMagazine(int n)
		throws NullPointerException
	{
		try
		{
			return (Magazine)magazineShelf.get(n+1).getValue();
		}
		catch (NullPointerException ex)
		{
			System.out.println("Oops, the index is out of bound!");
		}
		return null;
	}
	
	public void removeMagazine(Magazine magazine)
	{
		magazineShelf.removeObj(magazine);
	}
	
	public DoublyLinkedList getAllMagazines()
	{
		return magazineShelf;
	}
	
	public void setMagazineShelf(DoublyLinkedList newMagazineShelf)
	{
		magazineShelf = newMagazineShelf;
	}
	
	public ArrayList<String> magazineShelfToString()
	{
		ArrayList<String> strArr = new ArrayList<String>();
		int counter = 0;
		while(counter < magazineShelf.size())
		{
			Magazine magazine = (Magazine)magazineShelf.get(counter+1).getValue();
			strArr.add(magazine.toString());
			counter++;
		}
			
		return strArr;
	}

	//Creates a Magazine type object using the given string
	public Magazine stringToMagazine(String strBlock)
	{		
		String titleStr = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);
		Title title = new Title(titleStr, null, 0, null, 0);
		
		String trackingCode = strBlock.substring(0,strBlock.indexOf("|"));
		strBlock = strBlock.substring(strBlock.indexOf("|") + 1);

		String arrivalDateStr = strBlock.substring(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
		Date arrivalDate = new Date();
		try
		{
			arrivalDate = dateFormat.parse(arrivalDateStr);          
		}
		catch (ParseException e)
		{
			System.out.println("Invalid Date Parser Exception.");
			e.printStackTrace();
		}	
		Magazine myMagazine = new Magazine(title, trackingCode, arrivalDate);
		return myMagazine;
	}
	
	public void addAllMagazines(ArrayList<String> strArr)
	{
		int i = 0;
		while(i < strArr.size())
		{
			Magazine myMagazine = stringToMagazine(strArr.get(i));
			magazineShelf.add(myMagazine);
			i++;
		}
	}
	
	public void printMagazineShelfReport()
	{
		int i = 0;
		
		System.out.println("---------------------------Report: Checke in Magazines---------------------------");
		System.out.println("Title" + "\t\t\t\t\t\t\t" + "Tracking Code" + "\t\t\t\t\t\t" + "Arrival Date");
		while(i<magazineShelf.size())
		{
			Magazine myMagazine = (Magazine)magazineShelf.get(i+1).getValue();
			String title = myMagazine.getTitle();
			String trackingCode = myMagazine.getTrackingCode();
			
			Date arrivalDate = myMagazine.getArrivalDate();
			DateFunctions formatter = new DateFunctions();
			String arrivalDateStr = formatter.dateToString(arrivalDate);
			
			System.out.println(title + "\t\t\t\t\t\t\t" + trackingCode + "\t\t\t\t\t\t" + arrivalDateStr);
				
			i++;
		}
		System.out.println("");
	}
	
	//returns true If magazine exists in magazineShelf
	public boolean magazineExist(Title title, Date arrivalDate)
	{
		Magazine myMagazine;
		for (int i=0; i < magazineShelf.size(); i++)
		{
			myMagazine = (Magazine)magazineShelf.get(i+1).getValue();
			if(myMagazine.getTitle().equals(title) && myMagazine.getArrivalDate().equals(arrivalDate))
			{
				return true;
			}
		}
		return false;
	}
	
	//Looks for a specific tracking code in magazineShelf
	//If tracking code exists returns true 
	public boolean trackingCodeExist(String trackingCode)
	{
		Magazine myMagazine;
		for (int i=0; i < magazineShelf.size(); i++)
		{
			myMagazine = (Magazine)magazineShelf.get(i+1).getValue();
			if(myMagazine.getTrackingCode().equals(trackingCode))
			{
				return true;
			}
		}
		return false;
	}
	
	// This method arranges all the issues of "title" based on their arrival date and returns an
	// arraylist of Magazines which contains all the issues of "title"
	public ArrayList<Magazine> sortIssues(Title title)
	{
		ArrayList<Magazine> magArr = new ArrayList<Magazine>();
		Magazine myMagazine;
		for (int i=0; i < magazineShelf.size(); i++)
		{
			myMagazine = (Magazine)magazineShelf.get(i+1).getValue();
			if(myMagazine.getTitle().equals(title.getTitle()))
			{
				magArr.add(myMagazine);
			}
		}
		if (!magArr.isEmpty())
		{
			quickSort(magArr, 0, magArr.size()-1);
			return magArr;
		}
		return null;
	}
	
	//Returns the latest issue of a specific title
	//available in magazine shelf
	public Magazine latestIssue(Title title)
	{
		if (sortIssues(title) != null)
		{
			ArrayList<Magazine> magArr = sortIssues(title);
			return 	magArr.get(magArr.size()-1);		//returning the last magaizne in the array list
														//which would be the latest issue
		}

		else
			return null;
												
	}
	
	//returns an arraylist of strings which contains
	//the string representation of all the magazines
	//which were to be arrived but are delayed
	public ArrayList<String> delayedMagazines(Titles titleList)
	{
		ArrayList<String> arrTitles = new ArrayList<String>();
		Date today = new Date();	//today's date
		
		for(int i = 0; i < titleList.size(); i++)
		{
			Title myTitle = titleList.getTitle(i);
			int period = myTitle.getPeriod();
			
			Magazine lastIssue = latestIssue(myTitle);
			if (lastIssue != null)
			{		
				Date lastArrivalDate = lastIssue.getArrivalDate();
				
				//adding period days to last arrival date
				DateFunctions dateTool = new DateFunctions();
				Date nextArrivalDate = dateTool.addDaysToDate(lastArrivalDate, period);
				
				//checking whether the magazine issue is delayed or not
				if(nextArrivalDate.before(today))
					arrTitles.add(myTitle.getTitle() + "|" + dateTool.dateToString(nextArrivalDate));
			}
		}
		return arrTitles;
	}
	
	public void printDelayedMagazines(Titles titleList)
	{
		int i = 0;
		ArrayList<String> arrTitles = delayedMagazines(titleList);
		System.out.println("---------------------------Report: Delayed Magazines---------------------------");
		System.out.println("Title" + "\t\t\t\t\t\t\t\t\t\t" + "Should have been arrived at");
		
		while(i<magazineShelf.size()-1)
		{
			String strBlock = arrTitles.get(i);
			String title = strBlock.substring(0, strBlock.indexOf("|"));
			strBlock = strBlock.substring(strBlock.indexOf("|")+1);
			
			String passedDate = strBlock.substring(0);
			
			System.out.println(title + "\t\t\t\t\t\t\t\t" + passedDate);
				
			i++;
		}
		System.out.println("");
	}
	
	// This sorting method is desigend to sort the magazines by their arrival dates
	public void quickSort (ArrayList<Magazine> magArr, int from, int to)
	{
		//  from is the lower index, to is the upper index
		//  of the region of the arraylist that is to be sorted
		if(to != 0)
		{
			int i = from;
			int j = to;
			Date temp;
			Date mid = magArr.get((from + to)/2).getArrivalDate();
			
		    //  partition
		    do
		    {    
		        while (magArr.get(i).getArrivalDate().before(mid))		//magArr.get(from).date < mid
		        	i++;
		        while (magArr.get(j).getArrivalDate().after(mid))		//magArr.get(to).date > mid
		        	j--;
		        
		        if (i <= j)
		        {
		            temp = magArr.get(i).getArrivalDate();
		            magArr.get(i).setArrivalDate(magArr.get(j).getArrivalDate());
		            magArr.get(j).setArrivalDate(temp);
		            i++;
		            j--;
		        }
		    }while (i <= j);
		
		    // Using recursion to sort all the arraylist:
		    if (from < j)					//Basecase
		    	quickSort(magArr, from, j);
		    if (i < to)						//Basecase
		    	quickSort(magArr, i, to);
		}
	}
}

