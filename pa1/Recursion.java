//Name:Bora Mutluoglu CruzID:bmutluog
public class Recursion {
	static int count = -1;
	static int max;
	static int min;
	static int index = 0;
	static int minindex = 0;
	public static void main(String[] args){
		int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7}; 
		int[] B = new int[A.length];
		int[] C = new int[A.length];
		max = A[0];
		min = A [0];
		int minIndex = minArrayIndex(A, 0, A.length-1); 
		int maxIndex = maxArrayIndex(A, 0, A.length-1);
		      for(int x: A) System.out.print(x+" ");
		      System.out.println();
		      System.out.println( "minIndex = " + minIndex );
		      System.out.println( "maxIndex = " + maxIndex );
		      
		      reverseArray1(A, A.length, B);
		      for(int x: B) System.out.print(x+" ");
		      System.out.println();
		      
		      count=-1;
		      
		      reverseArray2(A, A.length, C);
		      for(int x: C) System.out.print(x+" ");
		      System.out.println();
		      
		      reverseArray3(A, 0, A.length-1);
		      for(int x: A) System.out.print(x+" ");
		      System.out.println();
}
	
//copies the leftmost n elements in X[] into the rightmost n positions in Y[] in reverse order.
static void reverseArray1(int[] X, int n, int[] Y){
	count++;
    if(n > 0) { 
    Y[X.length-n]= X[n-1];	
       reverseArray1(X, n-1, Y);          // print leftmost n-1 elements, reversed
    }		
}
//copies the rightmost n elements in X[] into the leftmost n positions in Y[] in reverse order.
static void reverseArray2(int[] X, int n, int[] Y){
	// if n==0 do nothing
	count++;
    if( n>0 ){
    	Y[n-1]=X[X.length-n];
       reverseArray2(X, n-1, Y);       // print the rightmost n-1 elements, reversed
    }
}
//Swaps the position of two variables in an array at the same time and iterates
//inwards
static void reverseArray3(int[] X, int i, int j){
	int tempX = 0;
	int tempY = 0;
	if(j>i){
		tempX = X[i];
		tempY = X[j];
		X[i] = tempY;
		X[j] = tempX;
		reverseArray3(X, i+1, j-1);
	}
}
//iterates through the Array and stores the index of the biggest number in
//the Array, returns the index p=0 r=A.length-1
static int maxArrayIndex(int[] X, int p, int r){
	if(r > p){
		if (X[r] > max){
		max = X[r];
		index = r;
		}
		maxArrayIndex(X, p, r-1);
	}
	return index;	
}
//iterates through the Array and stores the index of the smallest number in the
//Array, returns the index p=0 r=A.length-1
static int minArrayIndex(int[] X, int p, int r){
	if(r > p){
		if (X[r] < min){
			min = X[r];
			minindex = r;
			}
		minArrayIndex(X, p, r-1);
		}
	return minindex;
	}
}
