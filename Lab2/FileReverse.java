import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReverse {
	static ArrayList<Character> List = new ArrayList<Character>();
	public static void main(String[] args) throws IOException{
		Scanner in = null;
	    PrintWriter out = null;
	    String line = null;
	    String[] token = null;
	    String word = null;
	 
	    int i, n, lineNumber = 0;
	    
	    if(args.length < 2){
	    	System.out.println("Usage: FileCopy <input file> <output file>");
	    	System.exit(1);
	    }
	    
	    in = new Scanner(new File(args[0]));
	    out = new PrintWriter(new FileWriter(args[1]));

	 // s.charAt(n-1) + 

	 // read lines from in, extract and print tokens from each line while( in.hasNextLine() ){
	    while( in.hasNextLine() ){      
	    	lineNumber++;
	 // trim leading and trailing spaces, then add one trailing space so // split works on blank lines
	    	line = in.nextLine().trim() + " ";
	 // split line around white space
	    	token = line.split("\\s+");
	 // print out tokens
	    	n = token.length;
	    	for(i=0; i<n; i++){
	    		word = token[i];
	 //System.out.println(word);
	 //write the output of stringReverse the the out file.
	    		out.print(stringReverse(word, word.length()));
	 //when the previous word has been written, create a new line.
	    		out.print("\n");
	    	}
	 	}
	    in.close();
 		out.close();
	}
	//Reverses the subarray from 0 to n in String s and returns it.
	public static String stringReverse(String s, int n){
		String answer = "";
	//if the length of String s is 1 return itself.
		if(s.length() == 1){
			return s;
		
		}else{
	//recursively calls the stringReverse function on the substring 0 to n-1, and passes in
	//n-1 to for int n as well. To ensure that the characters are added backwards correctly
	//s.charAt(n-1) is also utilized which adds characters backwards, recursively to the 
	//String answer.
			answer +=s.charAt(n-1) + stringReverse(s.substring(0,n-1),n-1);
			
		}
		return answer;
	}		
}
