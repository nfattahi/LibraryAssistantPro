/* Librarian Assistant Pro - Version 1.7
 * Class: User
 * Duties:
 * 		The instance of this class represents a user.
 *		Keeps the information of a user.
 * Creation Date: September 2008
 */
 
class User
{
	private String username;
	private String password;
	private int userType;
	
	public User(String user, String pass, int type)
	{
			username = user;
			password = pass;
			userType = type;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public int getUserType()
	{
		return userType;
	}
	
	public void setUsername(String newUsername)
	{
		username = newUsername;
	}

	public void setPassword(String newPass1)
	{
			password = newPass1;
	}
	
	public void setUserType(int newUserType)
	{
		userType = newUserType;
	}
}