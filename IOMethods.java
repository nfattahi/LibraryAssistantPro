/* Librarian Assistant Pro - Version 1.7
 * Class: IB IO Methods
 * Copyright © IB Organization
 * Duties:
 * 		Validating the input data
 *		The class is acquired from IB Booklet
 * Creation Date: December 2008
 */

class IOMethods
{

	public static void output(String info)
	{ 
		System.out.println(info);
	}
	
	public static void output(char info)
	{ 
		System.out.println(info);
	}
	
	public static void output(byte info)
	{ 
		System.out.println(info);
	}
	
	public static void output(int info)
	{ 
		System.out.println(info);
	}
	
	public static void output(long info)
	{
		System.out.println(info);
	}
	
	public static void output(double info)
	{
		System.out.println(info);
	}
	
	public static void output(boolean info)
	{ 
		System.out.println(info);
	}
	
	//APPENDIX 2
	//© International Baccalaureate Organization 2004 101
	
	public static String input(String prompt)
	{ 
		String inputLine = "";
		System.out.print(prompt);
		try
		{
			inputLine = (new java.io.BufferedReader(
			new java.io.InputStreamReader(System.in))).readLine();
		}
		catch (Exception e)
		{ 
			String err = e.toString();
			System.out.println(err);
			inputLine = "";
		}
		
		return inputLine;
	}
	
	public static String inputString(String prompt)
	{ 
		return input(prompt);
	}
	
	public static String input()
	{ 
		return input("");
	}
	
	public static int inputInt()
	{ 
		return inputInt(""); 
	}
	
	public static double inputDouble()
	{ 
		return inputDouble(""); 
	}
	
	public static char inputChar(String prompt)
	{ 
		char result=(char)0;
		try
		{
			result=input(prompt).charAt(0);
		}
		catch (Exception e)
		{
			result = (char)0;
		}
		
		return result;
	}
	
	public static byte inputByte(String prompt)
	{ 
		byte result=0;
		try
		{
			result=Byte.valueOf(input(prompt).trim()).byteValue();
		}
		catch (Exception e)
		{
			result = 0;
		}
		
		return result;
	}
	
	public static int inputInt(String prompt)
	{ 
		int result=0;
		try
		{
			result=Integer.valueOf(input(prompt).trim()).intValue();
		}
		
		catch (Exception e)
		{
			result = 0;
		}
		
		return result;
	}
	
	public static long inputLong(String prompt)
	{ 
		long result=0;
		try
		{
			result=Long.valueOf(input(prompt).trim()).longValue();
		}
		catch (Exception e)
		{
			result = 0;
		}
		
		return result;
	}
	
	public static double inputDouble(String prompt)
	{ 
		double result=0;
		try
		{
			result=Double.valueOf(input(prompt).trim()).doubleValue();
		}
		catch (Exception e)
		{
			result = 0;
		}
		
		return result;
	}
	
	//APPENDIX 2
	//102 © International Baccalaureate Organization 2004
	
	public static boolean inputBoolean(String prompt)
	{ 
		boolean result=false;
		try
		{
			result=Boolean.valueOf(input(prompt).trim()).booleanValue();
		}
		catch (Exception e)
		{
			result = false;
		}
		
		return result;
	}
	//=========== end IBIO =======================================

}
