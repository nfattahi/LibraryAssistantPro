/* Librarian Assistant Pro - Version 1.7
 * Class: DoublyLinkedList
 * Duties:
 * 		The instance of this class is a list of ListNodes
 * 		this class enables us to store information in a list
 * Date: September 2008
 */
 
 
import java.util.NoSuchElementException;

class DoublyLinkedList
{
	//Private fields: head and tail
	private ListNode head;
	private ListNode tail;
	int length;
	
	//No-arg constructor for making a DoublyLinkedList
	public DoublyLinkedList()
	{
		head = null;
		tail = null;
		length = 0;
	}
	
	//isEmpty method should return true if there is no object in the linked list
	public boolean isEmpty()
	{
		if(head == null)
			return true;
		else
			return false;
	}
	
	public int size()
	{
		return length;
	}
	
	//getFirst method returns the last object in the linked list
	public Object getFirst()
		throws NoSuchElementException
	{
		if(head != null)
			return head.getValue();  //Returns the last object on the list
		else
			throw new NoSuchElementException();
	}
	
	//getLast method returns the last object in the linked list
	public Object getLast()
		throws NoSuchElementException
	{
		if(tail != null)
			return tail.getValue();  //Returns the last object on the list
		else
			throw new NoSuchElementException();
	}
	
	public ListNode get(int elementNum)
	{
		ListNode temp = head;
		
		for(int i = 0; i < elementNum; i++)
		{
			if(i == elementNum - 1)
				return temp;
			else
				temp = temp.getNext();
		}
		return null;
	}
	
	//add method adds a new object at the end of the linked list and return true if successful
	public void add(Object obj)
	{
		ListNode node = new ListNode(obj, null, null);
		if(head != null)
		{
//			System.out.println("Here is DLLC. head is not null!");
			ListNode newNode = new ListNode (obj,null,head);
			head.setPrevious(newNode);
			newNode.setNext(head);
			head = newNode;
		}
		else
		{
//			System.out.println("Here is DLLC. head is freakin null!");
			head = node;
			tail = node;
		}
		length++;
	}
	
	//remove method gets the last elemnt of the linkedlist and returns it
	public Object removeLast()
		throws NoSuchElementException
	{
		Object returnObj;
		ListNode tempNode;
		if(head != null)
		{
			tempNode = head;
			returnObj = tail.getValue(); //Storing the object that has to be removed
			while(tempNode.getNext() != null)
			{
				if(!tempNode.getNext().getValue().equals(tail.getValue()))	//If the two are not equal
					tempNode = tempNode.getNext();
				else
				{
					tempNode.setNext(null);
					tail = tempNode;
					return returnObj;
				}
			}
			return null;
		}
		else
			throw new NoSuchElementException();
	}
	
	public Object removeObj(Object obj)
	{
		Object returnObj;
		ListNode tempNode;
		ListNode test = null;
		if(head != null)
		{
			tempNode = head;
			while(tempNode.getNext() != null)
			{
				if(tempNode.getNext().getValue().equals(obj))	//If the two are equal
				{
					returnObj = tempNode.getValue();
					tempNode.getPrevious().setNext(tempNode.getNext());
					tempNode.getNext().setPrevious(tempNode.getPrevious());
					return returnObj;
				}
				else
				{
					tempNode = tempNode.getNext();
				}
			}
			return null;
		}
		else
			throw new NoSuchElementException();	
	}
	
	//Appends otherList at the end of this list.
	public void append(DoublyLinkedList otherList)
	{
		if(this.tail != null && otherList.head != null)
		{
			this.tail.setNext(otherList.head);
		}
		length = length + otherList.length;
			
	}
	
	public String toString()
	{
		String str = "";
		ListNode temp = head;
		
		int flag = 0; 
			
		while(temp != null)
		{
			if(flag == 0)
				str += temp.getValue();
			else
				str += ", " + temp.getValue();
			temp = temp.getNext();
			flag++;
		}
		return str;
	}
}
