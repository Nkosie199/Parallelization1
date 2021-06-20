
import java.util.concurrent.ForkJoinPool;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gmdnko003
 */
public class ParallelUI {
	static long startTime = 0;
        static float psTime;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float toc(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	static final ForkJoinPool fjPool = new ForkJoinPool();
        
	static float[] sum(float[] arr, int filterNo){
	  fjPool.invoke(new ParallelSolution(arr, 0, arr.length, filterNo));
          return ParallelSolution.getList();
	}

	
	public static void parallelStart(float[] list, int filterNo) {		
		tick();
		float[] resultArr = sum(list, filterNo);
		float time = toc();
                psTime =  time;
                System.out.println("");
                System.out.println("Start of parallel solution...");
		System.out.println("Run took "+ time +" seconds");
                System.out.println("End of parallel solution...");
                System.out.println("");
	}
        
    private static void debugPrint(int[] intArrayIndex, float[] arrayValue){
        System.out.println("Debug printing...");
        for (int i=0; i<arrayValue.length; i++){
            System.out.println(intArrayIndex[i]+" : "+arrayValue[i]);
        }
    }

}
