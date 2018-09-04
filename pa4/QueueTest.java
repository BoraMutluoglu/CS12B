//Bora Mutluoglu | bmutluog | 1564633 | cs12b | March 4, 2018 |
//an ADT tester for the Queue class implementing a Queue Interface.
public class QueueTest {
	public static void main(String[] args){
	      Queue A = new Queue();
	      Queue B = new Queue();
	      Queue C = new Queue();
	      Queue[] array = null;
	    
	    A.enqueue(1);
	    System.out.println(A.isEmpty());
	    A.dequeue();
		System.out.println(A.isEmpty());
		A.enqueue(1);
		A.enqueue(2);
		A.enqueue(3);
		B.enqueue(1);
		B.enqueue(1);
		B.enqueue(1);
		System.out.println("A size is: " + A.length());
		System.out.println("B size is: " + A.length());
		System.out.println("A equals B is: " + A.equals(B));
		C.enqueue(A);
		C.enqueue(B);
		System.out.println("contents of C are: " + C);
		System.out.println("Contents of C index 2 is: " + C.peek());
		System.out.println("Contents of A index 1 is: " + A.peek());
		System.out.println(A.isEmpty());
		System.out.println(A.length());
		A.dequeueAll();
		System.out.println(A.isEmpty());
		System.out.println(A.length());
		A.enqueue(1);
		A.enqueue(2);
		A.enqueue(3);
		System.out.println("A equals A is: " + A.equals(A));
		System.out.println("A contains: " + A.toString());
	    C.dequeueAll();
		try{
	        System.out.println(C.peek());
	     }catch(QueueEmptyException e){
	        System.out.println("Caught Exception: ");
	        System.out.println(e);
	        System.out.println("Continuing without interuption");
	     }
	    
		System.out.println("A contents are: " + A);
		System.out.println("B contents are: " + B);
		System.out.println("C contents are: " + C);

		}
}
