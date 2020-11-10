/* 
   (originally from R.I. Nishat - 08/02/2014)
   (revised by N. Mehta - 11/7/2018)
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class KMP{
    private String pat_search;
    private int N;
    private int M;
    
    
    public KMP(String pattern){  
		M = pattern.length();
		
		pat_search = pattern;

		
    }
    
    public int search(String txt){  
    	N = txt.length();
    
    	int lps[] = new int[M]; 
		// Preprocess the pattern 
    	computeLPSArr(pat_search, M, lps); 

        int j = 0;
  

        int i = 0; // index for txt[] 
        while (i < N) { 
            if (pat_search.charAt(j) == txt.charAt(i)) { 
                j++; 
                i++; 
            } 
            if (j == M) { //if the index for mathcing our pattern is equal to the length of our pattern
            	int ret_value = i - j;
                return ret_value;

            } 
  
           
            else if (i < N && pat_search.charAt(j) != txt.charAt(i)) { 
                // Do not match lps[0..lps[j-1]] characters, 
                // they will match anyway 
                if (j != 0) 
                    j = lps[j - 1];  
                else
                    i = i + 1; 
            } 
        } 


	return 0;
    }

    private void computeLPSArr(String pat, int M, int lps[]){

    	int len = 0; 
        int i = 1; 
        lps[0] = 0;

        while (i < M) { //iterating through the length of the string pattern
            if (pat.charAt(i) == pat.charAt(len)) { 
                len++; 
                lps[i] = len; 
                i++; 
            } 
            else 
            { 
                if (len != 0) { 
                    len = lps[len - 1]; 
                    
                } 
                else // if (len == 0) 
                { 
                    lps[i] = len; 
                    i++; 
                } 
            } 
        } 

    }
    
    
    public static void main(String[] args) throws FileNotFoundException{
	Scanner s;
	if (args.length > 0){
	    try{
		s = new Scanner(new File(args[0]));
	    }
	    catch(java.io.FileNotFoundException e){
		System.out.println("Unable to open "+args[0]+ ".");
		return;
	    }
	    System.out.println("Opened file "+args[0] + ".");
	    String text = "";
	    while(s.hasNext()){
		text += s.next() + " ";
	    }
	    
	    for(int i = 1; i < args.length; i++){
		KMP k = new KMP(args[i]);
		int index = k.search(text);
		if(index >= text.length()){
		    System.out.println(args[i] + " was not found.");
		}
		else System.out.println("The string \"" + args[i] + "\" was found at index " + index + ".");
	    }
	}
	else{
	    System.out.println("usage: java SubstringSearch <filename> <pattern_1> <pattern_2> ... <pattern_n>.");
	}
    }
}