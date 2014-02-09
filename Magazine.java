/* Librarian Assistant Pro - Version 1.7
 * Class: Magazine
 * Duties:
 * 		The instance of this class represents a magazine
 *		magazine class extends title class; meaning that
 *		magazine IS-A title. Therefore it inherites all the
 *		properties of titles class.
 *		Magazine is an issue of a specific magazine and the
 *		instance of this class keeps the information of a
 *		magazine.
 * Creation Date: November 2008
 */
 
import java.util.Date;

class Magazine extends Title
{
	private Title magazineTitle;
	private String trackingCode;
	private Date arrivalDate;
	
	public Magazine(Title myTitle, String myTrackingCode, Date myArrivalDate)
	{
		magazineTitle = myTitle;
		trackingCode = myTrackingCode;
		arrivalDate = myArrivalDate;
	}
	
	public String getTitle()
	{
		return magazineTitle.getTitle();
	}
	
	public String getTrackingCode()
	{
		return trackingCode;
	}
	
	public Date getArrivalDate()
	{
		return arrivalDate;
	}
	
	public void setArrivalDate(Date newDate)
	{
		arrivalDate = newDate;
	}

	public String toString()
	{
		return 	magazineTitle.getTitle() + "|" + trackingCode + "|" + arrivalDate;
	}
}