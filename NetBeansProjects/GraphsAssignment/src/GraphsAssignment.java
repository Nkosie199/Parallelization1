
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gmdnko003
 */

public class GraphsAssignment {
    private static int tableSize;
    
    public static void main(String[] args) {
        List<String> lineList;
        int j = 0;
        
        String path = System.getProperty("user.dir")+"/GraphAssignmentExample.txt"; //to get working file directory in all use case
        System.out.println(path);
        System.out.println("Enter starting point:");
        Scanner s = new Scanner(System.in);
        String startPoint = s.nextLine();
        System.out.println("Enter the number of destinations:");
        Scanner sc = new Scanner(System.in);
        int noOfNodes = sc.nextInt();
        tableSize = noOfNodes-1;
        for (int i = 1; i < (noOfNodes-1); i++) {
            tableSize = tableSize * i;
        }
        tableSize = tableSize*2;
        System.out.println(tableSize);
        Vertex[] vertices =  new Vertex[tableSize];
        try {       
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String inputLine;
            lineList = new ArrayList<String>(); //each line including the word, word type and defintion is an array element
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.print(inputLine);
                String[] key1 = inputLine.split("\\s* \\s*"); //key1 is the current line's split according to wordType, word, defintion
                try{
                    String source  = key1[0]; System.out.print("source = "+key1[0]+" ");
                    String dest    = key1[1]; System.out.print("destination = "+key1[1]+" ");
                    double  cost;
                    cost = Double.parseDouble(key1[2]); System.out.print("cost = "+key1[2]+" ");
                    
                    vertices[j] = new Vertex(source, dest, cost);
                    //System.out.println(" ");
                    
                }
                catch( NumberFormatException e ){
                  System.err.println( "Skipping ill-formatted line " + inputLine);
                }
                System.out.println(" ");
                j++;
             }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
        
        System.out.println("");
        //Vertex.printPath();
        for (int i = 0; i < vertices.length; i++) {
            System.out.println(vertices[i].name+" "+vertices[i].dest+" "+vertices[i].cost);
        }
        System.out.println( "Complete..." );
    } 
}
