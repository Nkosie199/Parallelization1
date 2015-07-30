
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gmdnko003
 */
public class NaiveSolution {
    
    static int noOfItems; //the number of items in the text file
    static int filterNo = 3; //the variable filter value
    static List<Integer> intArrayIndex; //the integer index
    static List<Integer> intArrayValue; //the integer value
    static boolean j = false; //boolean to find if arrayList is past the first index
    
    public static void main (String[] args){
        try {
            load("inp1.txt");
            //naiveMethod();
        } catch (IOException ex) {
            Logger.getLogger(NaiveSolution.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void load(String filename) throws FileNotFoundException, IOException { 
        List<String> lineList;
        
        String path = System.getProperty("user.dir")+"/"+filename; //to get working file directory in all use case
        //Eg. "/home/g/gmdnko003/NetBeansProjects/HashingAssignment/src/lexicon.txt"
        System.out.println(path);
        
        try (FileReader fileReader = new FileReader(path)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String inputLine; //each line in the text file
            lineList = new ArrayList<String>(); //each line including the word, word type and defintion is an array element
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
                lineList.add(inputLine); //now we have an ArrayList of data inputs
                if (j==true){
                    String[] stringArray = inputLine.split(" ");
                    System.out.println(stringArray[0]);
                    System.out.println(stringArray[1]);
                }         
                j= true;
                //intArrayIndex.add(Integer.parseInt(stringArray[0])); //add text file index
                //intArrayValue.add(Integer.parseInt(stringArray[1]));
            }
            noOfItems = Integer.parseInt(lineList.get(0));
            System.out.println(lineList.get(0));
        }
        catch(Exception e){
            System.out.println("File not found!");
        }
    }

    private static void naiveMethod() {
        for (int i=0; i<noOfItems; i++){
            
        }
    }
    
}
