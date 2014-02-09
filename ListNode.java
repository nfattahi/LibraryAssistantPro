/* Librarian Assistant Pro - Version 1.7
 * Class: DoublyLinkedList
 * Duties:
 * 		Instance of this class is a node. Node contains
 *		a value and two more nodes within itself
 *		this class is used to store Title and Magazine
 *		Objects
 * Date: September 2008
 */
 
// Represents a node of a doubly-linked list.
public class ListNode
{  
 	private Object value;
 	private ListNode previous;
 	private ListNode next;

 	// Constructor:
 	public ListNode(Object initValue, ListNode initPrevious,
                              ListNode initNext)
  	{
    	value = initValue;
    	previous = initPrevious;
    	next = initNext;
  	}

  	public Object getValue()
  	{
    	return value;
  	}

  	public ListNode getPrevious()
  	{
	    return previous;
  	}

  	public ListNode getNext()
  	{
    	return next;
  	}

  	public void setValue(Object theNewValue)
  	{
    	value = theNewValue;
  	}

  	public void setPrevious(ListNode theNewPrev)
  	{
	    previous = theNewPrev;
  	}

  	public void setNext(ListNode theNewNext)
  	{
    	next = theNewNext;
  	}
}