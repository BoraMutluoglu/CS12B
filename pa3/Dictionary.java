//Bora Mutluoglu | bmutluog | 1564633 | cs12b | January 9, 2018 |
//Implementing a dictionary interface using nodes
//using simpler and recommended option of two string fields

public class Dictionary implements DictionaryInterface {
	private static final int INITIAL_SIZE = 1;
	private int physicalSize;  // current length of underlying array
	private int[] item;        // array of IntegerQueue items
	private int front;         // index of front element
	private int back;          // index of back element
	// private inner Node class
	   private class Node {
	      String key;
	      String value;
	      Node next;

	      Node(String x, String y){
	         key = x;
	         value = y;
	         next = null;
	      }
	   }
	   
	   private Node head;     // reference to first Node in List
	   private int numItems;  // number of items in this DictionaryList

	// constructor for the Dictionary class
	   public Dictionary(){
	      head = null;
	      numItems = 0;
	   }
	   
	public boolean isEmpty() {
	//if there are no items in the Dictionary, return true otherwise return false
		if (numItems == 0){
			return true;
		}else{
		return false;
		}
	}


	public int size() {
	//returns the number of Items in the dictionary(size);	
		return numItems;
	}


	public String lookup(String key) {
		String found = null;
		Node N = head; 
		int counter = 0;
		while(N != null && counter < 1){
		
			if(N.key.equals(key)){	
				found = N.value;
				counter++;
			}else{
	//iterate through the Node positions too look for the key
				N = N.next;
			}
			
		}
		return found;
	}

	//function to insert a key
	public void insert(String key, String value){
		int counter = 0;
		boolean nothing = false;
		if (lookup(key) != null){
			
			throw new DuplicateKeyException("cannot insert duplicate keys");
			
		} else {
			if (head==null) {
	//intialize a new Node object if current node is empty
				Node N = new Node(key, value);
				head = N;
				nothing = true;
			} else if (isEmpty()==false){
	//If the Node isn't empty iterate through to the next node until it is null
	//then insert the key and value into that node.
				Node N = head;
				
				while(N != null && counter == 0){
	//sets N.next to the last Node pointer of null

					if (N.next == null){
						counter++;
						break;
					}
	//sets the current Node N's direction to null
					N = N.next;
				}
	//inserts the given Node into next position of N
				N.next = new Node(key, value);
			}
	//increments the number of items in the dictionary
			numItems++;
			nothing = false;
		}
		
	}


	public void delete(String key) {
		int counter = 0;
		boolean nothing  = false;
		if (lookup(key) == null){
			throw new KeyNotFoundException("cannot delete non-existent key");
		}
		
		if(numItems==1 && counter  == 0){
			counter ++;
	         Node N = head;
	         head = head.next;
	         N.next = null;
	      }else if (nothing == false){
	    	  Node N = head;
				if (N.key.equals(key)) {
					head = N.next;
				} else {
					while(!N.next.key.equals(key))
						N = N.next;
						N.next = N.next;
						N.next = N.next.next;
				 
			}
			
	}
		numItems--;
		
}


	public void makeEmpty() {
	//sets the pointer of the head to null and sets the number
	//of elements in the list to zero.
		head = null;
		numItems = 0;
	}
	

	// toString()
	// pre: none
	// post:  prints current state to stdout
	// Overrides Object's toString() method
	   public String toString(){
		      StringBuffer sb = new StringBuffer();
		      Node N = head;

		      for( ; N!=null; N=N.next){
		         sb.append(N.key).append(" ").append(N.value).append("\n");
		      }
		      return new String(sb);
	   }

}
