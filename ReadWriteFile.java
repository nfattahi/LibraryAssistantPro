/* Librarian Assistant Pro - Version 1.7
 * Class: ReadWriteFile
 * Duties:
 * 		This class helps us to read from and write to text files
 * Date: January 2009
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class ReadWriteFile
{
	public ArrayList<String> readFromFile(File textFile)
		throws IOException	
		//Reads a file line by line and 
		//puts all the lines in an arraylist of strings
	{
		ArrayList<String> content = new ArrayList<String>();
		Scanner input = null;

		input = new Scanner(textFile);
		
		while(input.hasNextLine())
		{
			content.add(input.nextLine());
		}
		return content;
	}
	
	public void writeToFile(ArrayList<String> text, File textFile)
		throws IOException
		//Writes the content of a string arrayList to the file 
	{
		PrintWriter writer = null;
		writer = new PrintWriter(textFile);
		
		for(int i =0; i < text.size(); i++)
		{
			writer.println(text.get(i));
			writer.close();
		}
	}
}
