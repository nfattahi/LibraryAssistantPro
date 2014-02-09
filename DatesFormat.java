/* Librarian Assistant Pro - Version 1.7
 * Class: DatesFunction
 * Duties:
 * 		Starting, running and ending the program. this class is in charge
 * 		of controlling and using all other classes in the program.
 * Date: December 2008 - February 2009
 */
 
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;


class DatesFormat
{
	Date date = new Date();
	
	public DatesFormat(Date myDate)
	{
		date = myDate;
	}
	
	public Date formatDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		StringBuffer toAppendTo = new StringBuffer();
		FieldPosition pos = new FieldPosition(0);
		StringBuffer sb = new StringBuffer();
		sb = sdf.format(date, toAppendTo, pos);
		String str = sb.toString();
		ParsePosition pp = new ParsePosition(0); 
		Date returnDate = sdf.parse(str, pp);
		return returnDate;
	}
	
/*			
 *
 *			if(title.length() <= 30  &&  title.length()> 26)
			{
				System.out.println("Title" + "\t\t\t" + "Tracking Code" + "\t" + "Expiry Date");
				System.out.println(title   + "\t\t\t" + trackingCode    + "\t" + expiryDate);
			}
 *			else if(title.length() <= 25 &&  title.length() > 21)
			{
				System.out.println("Title" + "\t\t\t\t" + "Tracking Code" + "\t" + "Expiry Date");
				System.out.println(title   + "\t\t\t\t" + trackingCode    + "\t" + expiryDate);
			}
				
			else if(title.length() <= 20 &&  title.length() > 16)
			{
				System.out.println("Title" + "\t\t\t\t\t" + "Tracking Code" + "\t" + "Expiry Date");
				System.out.println(title   + "\t\t\t\t\t" + trackingCode    + "\t" + expiryDate);
			}
			else if(title.length() <= 15 &&  title.length() > 11)
			{
				System.out.println("Title" + "\t\t\t\t\t\t" + "Tracking Code" + "\t" + "Expiry Date");
				System.out.println(title   + "\t\t\t\t\t\t" + trackingCode    + "\t" + expiryDate);
			}
			else if(title.length() <= 10 &&  title.length() > 6)
			{
				System.out.println("Title" + "\t\t\t\t\t\t\t" + "Tracking Code" + "\t" + "Expiry Date");
				System.out.println(title   + "\t\t\t\t\t\t\t" + trackingCode    + "\t" + expiryDate);
			}
			else if(title.length() <= 5 &&   title.length() > 1)
			{
				System.out.println("Title" + "\t\t\t\t\t\t\t\t" + "Tracking Code" + "\t" + "Expiry Date");
				System.out.println(title   + "\t\t\t\t\t\t\t\t" + trackingCode    + "\t" + expiryDate);
			}*/
}
