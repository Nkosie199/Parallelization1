
import java.util.Arrays;

/**
 * @author gmdnko003
 */
public class NaiveSolution {
    
    static int noOfItems; //the number of items in the text file
    static float[] intArrayValues2; //the new array of integer values with median filtering applied
    static float nsTime;
    
    static long startTime = 0;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
    
    
    public static void naiveMethod(float[] list, int filterNo) { //naive method takes parameters ArrayList and filter size, returns array of values
        int median = (filterNo/2); //the median value
        int low= 0; //index of first element in filter
        int hi = filterNo; //index of last element in filter
        int immutables = (filterNo/2); //the number of variables that will no change given their filters
        tick();
        
        for (int i=0; i<noOfItems; i++){
            if (i<immutables){ //if array index is in the range of the first set of immutable items
                intArrayValues2[i] = list[i];
            }
            else if(i<(noOfItems-(immutables)) & i>=immutables){ //condition to apply median filtering
                float[] filter = new float[filterNo]; //new interger array of filterable elements 
                int j = 0; //iterator variable for filter array
                for (int k=low; k<hi; k++){
                    filter[j] = list[k];
                    j++;
                }
                Arrays.sort(filter);
                //System.out.println(intArrayValues2[i]);
                intArrayValues2[i] = (filter[median]); //-1 to get the array index
                low++;
                hi++;
            }
            else{
                intArrayValues2[i] = list[i];
            }
        }// now I have a mean filtered array of values called intArrayValues2!!!
        tock();
        float time = tock();
        nsTime = time;
        System.out.println("Start of naive solution...");
	System.out.println("Run took "+ time +" seconds");
        System.out.println("End of naive solution...");
        System.out.println("");
    }
    
    /*
        this method will debug print the original array from the text file with its relevant index
    */
    private static void debugPrint(int[] intArrayIndex, float[] arrayValue){
        System.out.println("Debug printing...");
        for (int i=0; i<arrayValue.length; i++){
            System.out.println(intArrayIndex[i]+" : "+arrayValue[i]);
        }
    }
}