/* Librarian Assistant Pro - Version 1.7
 * Class: RAF (Random Access File)
 * Duties:
 * 		The instance of this class represents a RandomAccessFile
 * 		the class is used to read data from and write data to this
 *		RandomAccessFile
 * Creation Date: January 2009
 */

import java.io.RandomAccessFile;
import java.io.*;
import java.util.ArrayList;

class RAF
{
	private RandomAccessFile raf;
	
	public RAF(File file)
	{
        try
        {
        	raf = new RandomAccessFile(file, "rw");
        }
        catch (IOException ex)
        {
        	System.out.println("Error occured!");
        }
	}
	
	public ArrayList<String> readRAF()
		throws IOException, NullPointerException
	{
		ArrayList<String> stringArray = new ArrayList<String>();
		

		//Making sure the pointing is set to the beginning of the File
		raf.seek(0);
		//Writing the arrayList of strings into the RandomAccessFile
		while (raf.getFilePointer() < raf.length())
		{
			stringArray.add(raf.readUTF());
		}

		return stringArray;
	}
	
	public void writeRAF(ArrayList<String> stringArray)
		throws IOException
	{
		raf.seek(0);	//Making sure the pointing is set to the beginning of the File
						//So that the data would be overwritten
		try
		{
			int i = 0;
			//Reading RandomAccessFile and
			//Writing data into the arrayList of strings
			while(i < stringArray.size())
			{
				
				raf.writeUTF(stringArray.get(i));
				i++;
			}
		}
		catch (FileNotFoundException e)
		{
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
			
	}
}
