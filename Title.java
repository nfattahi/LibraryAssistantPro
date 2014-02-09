/* Librarian Assistant Pro - Version 1.7
 * Class: Title
 * Duties:
 * 		The instance of this class represents a specefic title
 *		and Keeps the information of a title
 * Creation Date: September 2008
 */
 
import java.util.Date;

class Title
{
	//************************* Filds *************************
	private String title;
	private Date expiryDate;
	private double sbcCost;
	private Date firstArrivalDate;
	private int period;
	
	public String type;
	public String publisher;
	public String country;
	public String sourceType;
	public String vendor;
	public String vendorPhone;
	public String vendorAddress;
	public String issuedTo;
	public String costCenter;
	public String budgetCat;
	
	
	//********************* Constructors **********************
	//Immidiate creation type
	public Title(String myTitle, Date myExpiryDate, double mySbcCost, Date myFirstArrivalDate, int myPeriod)
	{
		title = myTitle;
 		expiryDate = myExpiryDate;
		sbcCost = mySbcCost;
		firstArrivalDate = myFirstArrivalDate;
		period = myPeriod;
	}
	
	//Non-Argument constructor
	public Title()
	{
		title = "";
 		expiryDate = null;
		sbcCost = 0.0;
		firstArrivalDate = null;
		period = 0;
	}
	
	//Complete creation type
	public Title(String myTitle, Date myExpiryDate, double mySbcCost, Date myFirstArrivalDate, int myPeriod,
					String myType, String myPublisher, String myCountry, String mySourceType, String myVendor,
					String myVendorPhone, String myVendorAddress, String myIssuedTo, String myCostCenter,
					String myBudgetCat)
	{
		title = myTitle;
 		expiryDate = myExpiryDate;
		sbcCost = mySbcCost;
		firstArrivalDate = myFirstArrivalDate;
		period = myPeriod;
		type = myType;
		publisher = myPublisher;
		country = myCountry;
		sourceType = mySourceType;
		vendor = myVendor;
		vendorPhone = myVendorPhone;
		vendorAddress = myVendorAddress;
		issuedTo = myIssuedTo;
		costCenter = myCostCenter;
		budgetCat = myBudgetCat;		
	}
	
	//************************ Methods ************************
	
	public String getTitle()
	{
		return title;
	}

	public Date getExpiryDate()
	{
		return expiryDate;
	}
	
	public double getSbcCost()
	{
		return sbcCost;
	}

	public Date getFirstArrivalDate()
	{
		return firstArrivalDate;
	}
	
	public int getPeriod()
	{
		return period;
	}
	
	public void setTitle(String newTitle)
	{
		title = newTitle;
	}

	public void setExpiryDate(Date newExpiryDate)
	{
		expiryDate = newExpiryDate;
	}
	
	public void setSbcCost(double newSbcCost)
	{
		sbcCost = newSbcCost;
	}
	
	public void setFirstArrivalDate(Date newFirstArrivalDate)
	{
		firstArrivalDate = newFirstArrivalDate;
	}
	
	public void setPeriod(int newPeriod)
	{
		period = newPeriod;
	}
	
	public boolean equals(Title other)
	{
		if(this.title.equals(other.title))
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		return 	title + "|" + expiryDate + "|" + sbcCost + "|" + firstArrivalDate + "|" +
				period + "|" + type + "|" + publisher + "|" + country + "|" + sourceType + "|" +
				vendor + "|" + vendorPhone + "|" + vendorAddress + "|" + issuedTo + "|" +
				costCenter + "|" + budgetCat;
	}
	
}
