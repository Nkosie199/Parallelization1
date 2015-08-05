
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


//package parallelization1;

/**
 *
 * @author Nkosingiphile
 */
public class ParallelSolution extends RecursiveTask<Integer> {
    static long startTime = 0;
    int lo; // arguments
    int hi;
    static final int SEQUENTIAL_CUTOFF=500;
    static float[] arr;
	
    private static void tick(){
	startTime = System.currentTimeMillis();
    }
    
    private static float toc(){
	return (System.currentTimeMillis() - startTime) / 1000.0f; 
    }
    
    static final ForkJoinPool fjPool = new ForkJoinPool();
    
    static int sum(float[] arr){
        return fjPool.invoke(new ParallelSolution(arr,0,arr.length));
    }
    

    int ans = 0; // result 
	    
    ParallelSolution(float[] a, int l, int h) { 
	lo=l; hi=h; arr=a;  
    }
    
    public static void parallelMethod(float[] list, int filterNo) {
	tick();
        arr = list;
	int ParallelSolution = sum(list);
	float time = toc();
	System.out.println("Run took "+ time +" seconds");
		
	System.out.println("Sum is:");
	tick();
	sumArr = sum(arr);
	time = toc();
	System.out.println("Second run took "+ time +" seconds");
		
	System.out.println("Sum is:");
	System.out.println(sumArr);
		
    }


    protected Integer compute(){// return answer - instead of run
	if((hi-lo) < SEQUENTIAL_CUTOFF) {
            int ans = 0;
	    for(int i=lo; i < hi; i++)
		ans += arr[i];
		return ans;
        }
	else{
		    	
            ParallelSolution left = new ParallelSolution(arr,lo,(hi+lo)/2);
            ParallelSolution right= new ParallelSolution(arr,(hi+lo)/2,hi);
		    		// order of next 4 lines
		    		// essential Ð why?          
            left.fork();
            int rightAns = right.compute();
            int leftAns  = left.join(); 
            return leftAns + rightAns;	     
		     
	}
    }
}
