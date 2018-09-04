import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Search {

	public static void main(String[] args) throws IOException {
		Scanner in, in2 = null;
	    PrintWriter out = null;
	    String line = null;
	    String[] token = null;
	    String word = null;
	    String[] words = null;
	    String[] FileWords = null;
	    String[] argsWords = null;
	    int lineNumber = 0;
	      
	      if(args.length < 2){
		    	System.out.println("Usage: Search file target1 [target2 ..]");
		    	System.exit(1);
		    }
	  
	      in = new Scanner(new File(args[0]));
	      
	      //iterates through the file counting the number of lines
	      while(in.hasNext()){
	    	  in.next();
	    	  lineNumber++;
	      }
	      words = new String[lineNumber];
	      
	      in.close();
	      
	      in = new Scanner(new File(args[0]));
	      
	      //puts each word from the file into a String array
	      for (int j = 0; j <lineNumber;j++){
	    	  words[j]=in.nextLine();
	    	  //System.out.println(words[j]);
	      }
	      int[] lineNumbers = new int[words.length];
		  //adds line numbers of each word to the new array lineNumbers for comparing later on
		  for(int ji = 0; ji<words.length;ji++){
			  lineNumbers[ji] = ji;
			  lineNumbers[ji]++;
			  
		  }
	      
	      argsWords = new String[args.length-1];
	      int counter = 0;
	      
	      //puts user input into a String array
	      for(int n = 1; n<args.length;n++){  
	    	  argsWords[counter] = args[n];
	    	  counter++;
	      }
	      
		  counter = 0;
		  
	      String[] sortedWords = new String[words.length];
	      
	      mergeSort(words, lineNumbers, 0, words.length-1);
	      
	      //the first String words except the words are Sorted
		      for(int i=0; i<words.length; i++) {
		         sortedWords[i] = words[i];
		      }
		      
		  
		      
		  for (int p = 0; p<argsWords.length;p++){    
		  System.out.println(binarySearch(sortedWords, lineNumbers, 0, sortedWords.length, argsWords[p]));
		  } 
	   }
	
	// binarySearch()
	// pre: Array A[p..r] is sorted
	   static String binarySearch(String[] A, int[] B, int p, int r, String target){
	      int q;
	      if(p == r) {
	         return target+ " not found";
	      }else{
	         q = (p+r)/2;
	         if(A[q].compareTo(target) == 0){
	        	 //q++;
	            return  target+ " found "+" on line "+ B[q];
	         }else if(target.compareTo(A[q]) < 0){
	            return binarySearch(A, B, p, q, target);
	         }else{ // target > A[q]
	            return binarySearch(A, B, q+1, r, target);
	         }
	      }
	   }
	   
	// mergeSort()
	   // sort subarray A[p...r]
	   public static void mergeSort(String[] A, int[] B, int p, int r){
	      int q;
	      if(p < r) {
	         q = (p+r)/2;
	         // System.out.println(p+" "+q+" "+r);
	         mergeSort(A, B, p, q);
	         mergeSort(A, B, q+1, r);
	         for (int i = 0; i<A.length;i++){
	         }
	         merge(A, B, p, q, r);
	      }
	   }

	   // merge()
	   // merges sorted subarrays A[p..q] and A[q+1..r]
	   public static void merge(String[] A, int[] B, int p, int q, int r){
	      int n1 = q-p+1;
	      int n2 = r-q;
	      String[] L = new String[n1];
	      String[] R = new String[n2];
	      int[] Lint = new int[n1];
	      int[] Rint = new int[n2];
	      int i, j, k;

	      for(i=0; i<n1; i++){
	         L[i] = A[p+i];
	         Lint[i] = B[p+i];
	      }
	      for(j=0; j<n2; j++){ 
	         R[j] = A[q+j+1];
	         Rint[j] = B[q+j+1];
	      }

	      i = 0; j = 0;
	      for(k=p; k<=r; k++){
	         if( i<n1 && j<n2 ){
	        	 String Lword = L[i];
	        	 String Rword = R[j];
	        	 //might have to change .charAt() function.
	            if( Lword.compareTo(Rword)<0 ){
	               A[k] = L[i];
	               B[k] = Lint[i];
	               i++;
	            }else{
	               A[k] = R[j];
	               B[k] = Rint[j];
	               j++;
	            }
	         }else if( i<n1 ){
	            A[k] = L[i];
	            B[k] = Lint[i];
	            i++;
	         }else{ // j<n2
	            A[k] = R[j];
	            B[k] = Rint[j];
	            j++;
	         }
	      }
	   }


	   
	
}
