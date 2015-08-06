
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    
    static int filterSize = 3; //the variable filter value
    static int[] intArrayIndex; //the integer indexes
    static float[] intArrayValue; //the integer values array
    
    static boolean j = false; //boolean to find if arrayList is past the first index
    static NaiveSolution ns = new NaiveSolution();
    static ParallelUI sa = new ParallelUI(); //sa variable stands for sum all...
    static ParallelSolution ps; //ps variable stands for parallel solution... 
    
    public static void main (String[] args){
        //args[0].split(" "); //takes in command line parameters:...
        System.out.println("Please enter the name of the text file");
        Scanner sc = new Scanner(System.in);
        String file = sc.next();
        //String file = sc.toString();
        try {
            load(file);
            //debugPrint(intArrayIndex, intArrayValue);
            //try{
                ns.naiveMethod(intArrayValue, filterSize);
                sa.parallelStart(intArrayValue, filterSize);
                //debugPrintAll(intArrayIndex, intArrayValue, ns.intArrayValues2, ps.getList());
            //}catch(Exception e){
            //    System.out.println("Failed to compute");
            ///}
            
        } catch (IOException ex) {
            Logger.getLogger(NaiveSolution.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void load(String filename) throws FileNotFoundException, IOException { 
        
        
        String path = System.getProperty("user.dir")+"/"+filename; //to get working file directory in all use case
        //Eg. "/home/g/gmdnko003/NetBeansProjects/HashingAssignment/src/lexicon.txt"
        System.out.println(path);
        int index = 0;
        
//        try (FileReader fileReader = new FileReader(path)) {
//            
//        }
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String inputLine; //each line in the text file
            lineList = new ArrayList<String>(); //each line including the word, word type and defintion is an array element
            while ((inputLine = bufferedReader.readLine()) != null) {
                //System.out.println(inputLine);
                lineList.add(inputLine); //now we have an ArrayList of data inputs
                if (j==true){
                    String[] stringArray = inputLine.split(" ");
                    intArrayIndex[index] = Integer.parseInt(stringArray[0]);
                    //System.out.println(stringArray[0]); //line number
                    intArrayValue[index] = Float.parseFloat(stringArray[1]);
                    //System.out.println(stringArray[1]); //floating point value
                    index++;
                }
                else{
                    intArrayValue = new float[Integer.parseInt(inputLine)];
                    ns.intArrayValues2 = new float[Integer.parseInt(inputLine)];
                    ps.intArrayValues2 = new float[Integer.parseInt(inputLine)];
                    intArrayIndex = new int[Integer.parseInt(inputLine)];
                }
                j= true;
                
                //intArrayIndex.add(Integer.parseInt(stringArray[0])); //add text file index
                //intArrayValue.add(Integer.parseInt(stringArray[1]));
            }
            ns.noOfItems = Integer.parseInt(lineList.get(0));
            ps.noOfItems = Integer.parseInt(lineList.get(0));
            System.out.println(lineList.get(0));
        
//        catch(Exception e){
//            System.out.println("File not found!");
//        }
    }
    
    /*
        this method will debug print the solution naive array and the solution parallel array
    */
    public static void debugPrintAll(int[] originalArrayIndexes,float[] originalArray, float[] naiveArray, float[] parallelArray){
        System.out.println("Debug printing...");
        for (int i=0; i<originalArray.length; i++){
            
            if (naiveArray[i] != parallelArray[i]){
                System.out.print("ERROR! ");
                System.out.println(originalArrayIndexes[i]+" : "+originalArray[i]+" "+naiveArray[i]+" "+parallelArray[i]+" ");
            }
            else{
                System.out.println(originalArrayIndexes[i]+" : "+originalArray[i]+" "+naiveArray[i]+" "+parallelArray[i]+" ");
            }
        }
    }
    
    public static void filePrintAll(int[] originalArrayIndexes,float[] originalArray, float[] naiveArray, float[] parallelArray){
        
    }
}
