/**
 *
 * @author Rahnuma Islam Nishat
 * September 19, 2018
 * CSC 226 - Fall 2018
 */
import java.util.*;
import java.io.*;

public class QuickSelect {
    
    
    
    public static int QuickSelect(int[] A, int k) {
    	int low = 0;
    	int high = A.length - 1;
    	quickSort(A,low,high);
    	
    	return A[k-1];


    	
    }

    private static void quickSort(int[] B, int low, int high){
    	if (B == null || B.length == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = B[middle];
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (B[i] < pivot) {
				i++;
			}
 
			while (B[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = B[i];
				B[i] = B[j];
				B[j] = temp;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(B, low, j);
 
		if (high > i)
			quickSort(B, i, high);
	
    }
    
    public static void main(String[] args) {
        Scanner s;
        int[] array;
        int k;
        if(args.length > 0) {
	    	try{
				s = new Scanner(new File(args[0]));
				int n = s.nextInt();
				array = new int[n];
				for(int i = 0; i < n; i++){
				    array[i] = s.nextInt();
				}
	    	} catch(java.io.FileNotFoundException e) {
				System.out.printf("Unable to open %s\n",args[0]);
				return;
	    	}
	    	System.out.printf("Reading input values from %s.\n", args[0]);
        }

		else {

	    	s = new Scanner(System.in);
	    	System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
	    	int temp = s.nextInt();
	    	ArrayList<Integer> a = new ArrayList();
	    	while(temp >= 0) {
				a.add(temp);
				temp=s.nextInt();
	    	}
	    	array = new int[a.size()];
	    	for(int i = 0; i < a.size(); i++) {
				array[i]=a.get(i);
	    	}
	    
	    	System.out.println("Enter k");
        }
        k = s.nextInt();
        System.out.println("The " + k + "th smallest number is the list is " + QuickSelect(array,k));	
    }
}
