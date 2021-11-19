import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class TakeOffQueue{

  private static ArrayList<String[]> flightTable = new ArrayList<String[]>();
  private static int selection;

  public static void main(String[] args){
    selection = Integer.parseInt(args[1]);
    tableToArray(flightTable, args[0]);
// display table for testing
/*    for(int i = 0; i < flightTable.size(); i++){
      String[] line = flightTable.get(i);
      String print = Arrays.toString(line);
      System.out.println(print);
    } */

    HeapMin flightHeap1 = new HeapMin();
    HeapMax flightHeap2 = new HeapMax();

    for(int i = 0; i < flightTable.size(); i ++){
      int timeValue = timeToInteger(flightTable.get(i));
      int passengers = numPassengers(flightTable.get(i));
      String[] line = flightTable.get(i);
      Info infoElement1 = new Info(line[0], line[1], line[2], timeValue, passengers, 1);
      flightHeap1.insert(infoElement1);

      Info infoElement2 = new Info(line[0], line[1], line[2], timeValue, passengers, 2);
      flightHeap2.insert(infoElement2);
    }

  // print display for invocation 1
    if(selection == 1){
      while(flightHeap1.insertIndex > 0){
        Info removed = flightHeap1.removeMin();
        System.out.println(removed.flight);
      }
    }
  //print display for invocation 2
    else if(selection == 2){
      while(flightHeap2.insertIndex > 0){
        Info removed = flightHeap2.removeMax();
        System.out.println(removed.flight + " " + removed.numPassengers);
      }
    }
  }

  /* numPassengers converts number of passengers of type String into values
  of type int
  */
  public static int numPassengers(String[] tableLine){
    int numP = Integer.parseInt(tableLine[4]);
    return numP;
  }

  /* timeToInteger converts take off request times of type String
  into values of type int
  */
  public static int timeToInteger(String[] tableLine){
    int timeVal;
    String[] time = tableLine[3].split(":");
    int hour = Integer.parseInt(time[0]);
    int minutes = Integer.parseInt(time[1]);
    if((hour % 12) == 0){
      timeVal = minutes;
    }
    else{
      timeVal = minutes + (hour*60);
    }
    return timeVal;
  }

  //tableToArray converts flight requests from .txt file into 2D array
  public static void tableToArray(List table, String file){
    try{
      //function to find file for conversion
      File numFile = new File(file);
      //function to read file
      FileReader readFile = new FileReader(numFile);
      //function to convert lines from file into strings
      BufferedReader bufferedReader = new BufferedReader(readFile);
      String line;

      int i = 0;
      while((line = bufferedReader.readLine()) != null){
        String[] row = line.split(" ");
        table.add(row);
        i++;
        }
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}
