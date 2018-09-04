//Bora Mutluoglu | bmutluog | 1564633 | cs12b | January 9, 2018 |
//A test class called ListTest to test various ADT function of my
//ListADT which uses Java Generics.
public class ListTest {
    
	public static void main(String[] args){
	List<String> A = new List<String>();
    List<String> B = new List<String>();
    List<List<String>> C = new List<List<String>>();
    
    A.add(1 , "Pizza");
    System.out.println(A.isEmpty());
    A.remove(1);
	System.out.println(A.isEmpty());
	A.add(1, "pizza");
	A.add(2, "hotdog");
	A.add(3, "bread");
	B.add(1, "apple");
	B.add(1, "banana");
	B.add(1, "kiwi");
	System.out.println("A size is: " + A.size());
	System.out.println("B size is: " + A.size());
	System.out.println("A equals B is: " + A.equals(B));
	C.add(1, A);
	C.add(2, B);
	System.out.println("contents of C are: " + C);
	System.out.println("Contents of C index 2 is: " + C.get(2));
	System.out.println("Contents of A index 1 is: " + A.get(1));
	System.out.println(A.isEmpty());
	System.out.println(A.size());
	A.removeAll();
	System.out.println(A.isEmpty());
	System.out.println(A.size());
	A.add(1, "pizza");
	A.add(2, "hotdog");
	A.add(3, "bread");
	System.out.println("A equals A is: " + A.equals(A));
	System.out.println("A contains: " + A.toString());
    
	try{
        System.out.println(A.get(4));
     }catch(ListIndexOutOfBoundsException e){
        System.out.println("Caught Exception: ");
        System.out.println(e);
        System.out.println("Continuing without interuption");
     }
    
	System.out.println("A contents are: " + A);
	System.out.println("A contents are: " + B);
	System.out.println("A contents are: " + C);

	}
}
