
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author gmdnko003
 */
public class MainUI {
    
    static List<String> lineList;    
    static int filterSize = 21; //the variable filter value
    
    static int[] intArrayIndex; //the integer indexes
    static float[] intArrayValue; //the integer values array
    
    static boolean j = false; //boolean to find if arrayList is past the first index
    static NaiveSolution ns = new NaiveSolution();
    static ParallelUI pu = new ParallelUI(); //sa variable stands for sum all...
    static ParallelSolution ps; //ps variable stands for parallel solution...
    static long startTime = 0;
    static int seqCutoff; //(1/200) or //(1/100) or //(1/50) //(1/5)
    static int fileLength;
    static String dataFileName;
    static String outputFileName;
    
    
    private static void tick(){
	startTime = System.currentTimeMillis();
    }
    private static float tock(){
	return (System.currentTimeMillis() - startTime) / 1000.0f; 
    }
    
    public static void main (String[] args){
        //args[0].split(" "); //takes in command line parameters:...
        //<data file name> <filter size (must be an odd integer >= 3)> <output file name>
        dataFileName = args[0];
        filterSize = Integer.parseInt(args[1]);
        outputFileName = args[2];
        
        
        System.out.println("Please enter the name of the text file");
        //Scanner sc = new Scanner(System.in);
        //String file = sc.next();
        try {
            load(dataFileName);
                ns.naiveMethod(intArrayValue, filterSize);
                pu.parallelStart(intArrayValue, filterSize);
                //debugPrintAll(intArrayIndex, intArrayValue, ns.intArrayValues2, ps.getList());
                //filePrintAll(intArrayIndex, intArrayValue, ns.intArrayValues2, ps.getList());
            
        } catch (IOException ex) {
            Logger.getLogger(NaiveSolution.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void load(String filename) throws FileNotFoundException, IOException { 
               
        String path = System.getProperty("user.dir")+"/"+filename; //to get working file directory in all use case
        //Eg. "/home/g/gmdnko003/NetBeansProjects/HashingAssignment/src/lexicon.txt"
        System.out.println(path);
        int index = 0;
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String inputLine; //each line in the text file
            lineList = new ArrayList<String>(); //each line including the word, word type and defintion is an array element
            while ((inputLine = bufferedReader.readLine()) != null) {
                lineList.add(inputLine); //now we have an ArrayList of data inputs
                if (j==true){
                    String[] stringArray = inputLine.split(" ");
                    intArrayIndex[index] = Integer.parseInt(stringArray[0]);
                    intArrayValue[index] = Float.parseFloat(stringArray[1]);
                    index++;
                }
                else{
                    intArrayValue = new float[Integer.parseInt(inputLine)];
                    ns.intArrayValues2 = new float[Integer.parseInt(inputLine)];
                    ps.intArrayValues2 = new float[Integer.parseInt(inputLine)];
                    intArrayIndex = new int[Integer.parseInt(inputLine)];
                }
                j= true;
            }
            ns.noOfItems = Integer.parseInt(lineList.get(0));
            ps.noOfItems = Integer.parseInt(lineList.get(0));
            System.out.println(lineList.get(0));
            fileLength = Integer.parseInt(lineList.get(0));
            //j=false;
    }

    public static void debugPrintAll(int[] originalArrayIndexes,float[] originalArray, float[] naiveArray, float[] parallelArray){
        System.out.println("Debug printing...");
        for (int i=0; i<originalArray.length; i++){
            
            if (naiveArray[i] != parallelArray[i]){
                System.out.print("ERROR: ");
                System.out.println(originalArrayIndexes[i]+" : "+originalArray[i]+" "+naiveArray[i]+" "+parallelArray[i]+" ");
            }
            else{
                System.out.println(originalArrayIndexes[i]+" : "+originalArray[i]+" "+naiveArray[i]+" "+parallelArray[i]+" ");
            }
        }
    }
    
    public static void filePrintAll(int[] originalArrayIndexes,float[] originalArray, float[] naiveArray, float[] parallelArray) throws IOException{
//        System.out.println("Please enter the name of the output text file");
//        Scanner sc = new Scanner(System.in);
//        String file = sc.next();
        Runtime runtime = Runtime.getRuntime();
        
        
        PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
        writer.println("Output text file TestOutput.txt");
        writer.println("Number of available processors "+runtime.availableProcessors());
        writer.println("Output results for parallel and naive solutions...");
        writer.printf("%-22s%-22s%-22s%s\n","Index","OriginalArray","SerialArray","ParallelArray");
        writer.println("");
        for (int i=0; i<originalArray.length; i++){
            if (naiveArray[i] != parallelArray[i]){
                System.out.print("ERROR: ");
            }
            writer.printf("%-22s%-22s%-22s%s\n",originalArrayIndexes[i]+" : ",originalArray[i],naiveArray[i],parallelArray[i]);
            writer.println("");
        }
        writer.close();
    }
}
