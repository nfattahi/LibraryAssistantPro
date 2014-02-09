/* Librarian Assistant Pro - Version 1.7
 * Class: Title
 * Duties:
 * 		The instance of this class represents group of users.
 *		Keeps all the users in an arrayList of users
 *		manages users and give them different functionalities
 * Creation Date: September 2008
 */
 
import java.util.ArrayList;

class Users
{
	private ArrayList<User> userList = new ArrayList<User>();
	
	//If user exists and username and password match, returns true
	public boolean login(String username, String pass)
	{
		if(userExists(username))
		{
			//Finding the user in the list
			User user = returnUser(username);
			
			if(user.getPassword().equals(pass))
			{
				System.out.println(user.getUsername() + " is successfully logged in!");
				return true;
			}
			else
			{
				System.out.println("Username and password do not match. Please try again.");
				return false;
			}
			
		}
		else
		{
			System.out.println("User does not exist.");
			return false;
		}
	}
	
	//Check whether user exists or not
	public boolean userExists(String username)
	{
		for(int i=0;i < userList.size(); i++)
		{
			//Finding the user in the list
			if(userList.get(i).getUsername().equals(username))
			{
				return true;
			}
		}
		return false;
	}
	
	//returns the user which corresponds with this username
	//if there is no such a user returns null
	public User returnUser(String username)
	{
		for(int i=0;i < userList.size(); i++)
		{
			if(userList.get(i).getUsername().equals(username))		//Finding the user in the list
			{
				return userList.get(i);
			}
		}
		return null;
	}
	
	public boolean changeUsername(String oldUsername, String newUsername)
	{
		for(int i=0;i < userList.size(); i++)
		{
			if(userList.get(i).getUsername().equals(oldUsername))
			{
				userList.get(i).setUsername(newUsername);
				System.out.println(oldUsername + "'s username is successfully updated to " + newUsername + ".");
				return true;
			}
		}
		System.out.println("Username '" + oldUsername +"' does not exist.");
		return false;
	}
	
	public boolean changePassword(String user, String oldPass, String newPass1, String newPass2)
	{
		for(int i=0;i < userList.size(); i++)
		{
			if(userList.get(i).getUsername().equals(user))
			{
				if(userList.get(i).getPassword().equals(oldPass))
				{
					if(newPass1.equals(newPass2))
					{
						userList.get(i).setPassword(newPass1);
						System.out.println("Your password is successfully changed.");
						return true;
					}
					else
					{
						System.out.println("The two passwords do not match. Please try again.");
						return false;	
					}
				}
				else
				{
					System.out.println("Old password is incorrect. please try again.");
					return false;
				}
			}
		}
		System.out.println("User does not exist.");
		return false;
	}
	
	public boolean addUser(String username, String password1, String password2, int type)
	{
		if(password1.equals(password2))
		{
			User user = new User(username, password1, type);
			userList.add(user);
			System.out.println("User '" + username + "' is successfully created.");
			return true;
		}
		else
		{
			System.out.println("Passwords do not match. Please try again.");
			return false;
		}
		
	}
	
	//Removes user from the arrayList
	public boolean removeUser(String user)
	{
		//if(this.userType == 1)
		for(int i=0;i < userList.size(); i++)
		{
			if(userList.get(i).getUsername().equals(user))
			{
				userList.remove(i);
				System.out.println(user + " is successfully removed.");
				return true;
			}
		}
		System.out.println("User '" + user +"' does not exist.");
		return false;
	}
	
	//Takes an array of strings as input and convert it to an array of Users
	public ArrayList<User> readUserList(ArrayList<String> userArray)
	{
		ArrayList<User> returnUserList = new ArrayList<User>();
		for(int i=0; i < userArray.size(); i++)
		{			
			String userBlock = userArray.get(i);
			String username = userBlock.substring(0,userBlock.indexOf("|"));
			String password = userBlock.substring(userBlock.indexOf("|")+1, userBlock.lastIndexOf("|"));
			int userType = Integer.parseInt(userBlock.substring(userBlock.lastIndexOf("|")+1));
			User user = new User(username, password, userType);
			returnUserList.add(user);
		}
		userList = returnUserList;
		
		return returnUserList;
	}
	
	//Takes an array of Users as input and convert it to an array of Strings
	public ArrayList<String> writeUserList()
	{
		ArrayList<String> returnStringArray = new ArrayList<String>();
		for(int i=0; i < userList.size(); i++)
		{			
			String username = userList.get(i).getUsername();
			String password = userList.get(i).getPassword();
			String userType = Integer.toString(userList.get(i).getUserType());
			
			String userStr = username + "|" + password + "|" + userType; 
			returnStringArray.add(userStr);
		}
		
		return returnStringArray;	
	}
	
	public void setUserList(ArrayList<User> usernamesList)
	{
		userList = usernamesList;
	}
	
	public String toString()
	{
		String str = "";
		for(int i=0;i < userList.size(); i++)
		{
			str = str + userList.get(i).getUsername();
		}
		return str;
	}
	
	//prints out a text stream which contains all the usernames
	public void printUsersReport()
	{
		String str = "";
		System.out.println("---------------------------Report: Users---------------------------");
		for(int i=0;i < userList.size(); i++)
		{
			str = userList.get(i).getUsername();
			System.out.println(i+1 + "\t" + str);
		}
	}
}

