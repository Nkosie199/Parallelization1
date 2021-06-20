import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author gmdnko003
 */
public class ParallelSolution extends RecursiveAction  {
    int lo; // arguments
    int hi;
    float[] arr;
    static int SEQUENTIAL_CUTOFF = 9999;
    MainUI mi = new MainUI();
          
    static float[] intArrayValues2; //the new array of integer values with median filtering applied
    static int noOfItems; //the number of items in the text file
    int filterSize; //ie. 3
    int median;
    int immutables;
    
    
    int ans = 0; // result 
	    
    ParallelSolution(float[] a, int l, int h, int filterNo) {      
        lo=l; hi=h; arr=a; filterSize = filterNo;
    }


    protected void compute(){// return answer - instead of run
        //SEQUENTIAL_CUTOFF = arr.length*(1/200);
        if((hi-lo) < SEQUENTIAL_CUTOFF) { //the size of the array
        //list is the full list of integer values
            immutables = (filterSize/2); //the number of variables that will no change given their filters
            median = (filterSize/2); //the median value
            //System.out.println("Computing from array index: "+lo+" to "+hi); 
            //System.out.println("Filter size: "+filterSize+" Median: "+median+" Immutables: "+immutables);
//            for (int i = 0; i < arr.length; i++){
//                System.out.println(arr[i]);
//            }
            for (int i=lo; i<hi; i++){ //for each iteration in the given array & condition to apply median filtering
                if (i < immutables){
                    intArrayValues2[i] = arr[i];
                }
                else if (i >= arr.length-immutables){
                    intArrayValues2[i] = arr[i];
                }
                else{
                    float[] filter = new float[filterSize]; //new interger array of filterable elements 
                    int j = -immutables;
                    for (int k=0; k<filterSize; k++){
                        filter[k] = arr[i+j];
                        j++;
                        //System.out.println(filter[j]); //prints out each element in the unsorted filter array
                    }
                    Arrays.sort(filter);
                    intArrayValues2[i] = (filter[median]); //-1 to get the array index 
                }
            }           
        }
        else {	    	
            ParallelSolution left = new ParallelSolution(arr,lo,(hi+lo)/2,filterSize);
            ParallelSolution right= new ParallelSolution(arr,(hi+lo)/2,hi,filterSize);
            
            left.fork(); //
            right.compute();
            left.join(); 
            //return ans; 
        }
        
    }
    public static float[] getList(){
            return intArrayValues2;
    }
     
}