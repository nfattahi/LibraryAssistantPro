import java.io.*;

class TextFile
{
	private String filePath;
	File txtFile;
	
	public TextFile(String myFilePath)
	{
		txtFile = new File(myFilePath);
		filePath = myFilePath;
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public void setFilePath(String newFilePath)
	{
		filePath = newFilePath;
	}
}
