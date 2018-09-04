//Bora Mutluoglu | bmutluog | 1564633 | cs12b | January 22, 2018 |
//Exception thrown when a reference is made to an out of bounds index of the list
@SuppressWarnings("serial")
public class ListIndexOutOfBoundsException extends IndexOutOfBoundsException{
	public ListIndexOutOfBoundsException(String s){
		super(s);
	}
}
