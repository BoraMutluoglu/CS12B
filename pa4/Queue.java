//Bora Mutluoglu | bmutluog | 1564633 | cs12b | March 4, 2018 |
//an ADT for the Queue class implementing a Queue Interface.

public class Queue implements QueueInterface{
    // Define the Node class
    private class Node{
	Object item;
	Node next;

	// Node()                                                                                                       
	// constructor for new Node                                                                                     
	Node(Object newItem){
	    item = newItem;
	    next = null;
	}

    }
    
    private Node  top;
    private Node  bot;
    private int   numItems;
    
    // Queue()
    // constructor for Queue
    public Queue(){	
	// init head and numItems
	top = null;
	bot = null;
	numItems = 0;
    }


    // isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
    public boolean isEmpty(){
	if (numItems==0){
		return true;
	}else{
		return false;
	}
    }


    // length()
    // pre: none
    // post: returns the length of this Queue.
    public int length(){
	return( numItems );
    }

    
    // enqueue()
    // adds newItem to back of this Queue
    // pre: none
    // post: !isEmpty()
    public void enqueue(Object newItem){
	
// create a new node and set the .next to null

//	Node first = top;
// if the following queue is empty
    if(top == null){
    	Node N = new Node(newItem);
    	top = N;
    }
    else{
        Node C = top;
        while(C.next != null){
            if (C.next == null){
                break;
            }
            C = C.next;
        }
        C.next = new Node(newItem);
    }

	
//increase the number of items
	numItems++;
    }

    
    // dequeue()
    // deletes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    public Object dequeue() throws QueueEmptyException{
	
	// check if queue is empty
	if ( isEmpty() == true){
	    throw new QueueEmptyException(
					  "Queue: dequeue() called on empty queue");
	}
	
//if the queue is 
//not empty, store the first item,and set a new top, decrement numItems
	Object object = top.item;
// if there is only one node in queue perform this special case else change the directory
	if ( top == bot){
	    top = null;
	    bot = null;
	} else {
	    top = top.next;
	}
	// decrement number of items since one got dequeued

	numItems--;

	return object;

    }


    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    public Object peek() throws QueueEmptyException{
// if queue is empty throw the exception                                                                                      
	if ( isEmpty() == true){
            throw new QueueEmptyException("Queue: peek() called on empty queue");
        }else{

	return top.item;
        }

    }

    
    // dequeueAll()
    // sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    public void dequeueAll() throws QueueEmptyException{
//if the queue is empty throw the exception                                                                                   
	if ( isEmpty() == true){
            throw new QueueEmptyException("Queue: dequeueAll() called on empty queue");
        }

	top = null;
	bot = null;
	numItems = 0;
    }


    // toString()                                                                                                       
    // overrides Object's toString() method                                                                               
    public String toString(){
        String qString = "";
        Node N = top;                                                                                     
        for (; N != null ; N = N.next){
            qString += N.item.toString() + " ";
        }

        return ( qString );
    }

}