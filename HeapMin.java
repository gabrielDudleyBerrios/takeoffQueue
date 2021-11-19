//HeapMin build off of HeapMin from lab6
import java.util.*;

public class HeapMin{

  private ArrayList<Info> minHeap;
  public int insertIndex;

//  Constructor of HeapMin. Initializes Arraylist and insertIndex
  public HeapMin(){
    minHeap = new ArrayList<Info>();
    insertIndex = 0;
  }
/*   insert: This method inserts a new element to the min heap
    and organizes min heap for proper order
*/
  public void insert(Info element){
    minHeap.add(element);
    int i = insertIndex;
    Info child = minHeap.get(i);
    Info parent = minHeap.get((i-1)/2);
    while(child.invocation < parent.invocation && parent.invocation > 0){
      swap(minHeap, i, ((i-1)/2) );
      i = (i-1)/2;
      child = minHeap.get(i);
      parent = minHeap.get((i-1)/2);
    }
    insertIndex ++;
  }
/*  extractMin: This method returns the root element of the min heap
    or -1 id the heap is empty
*/
  public int extractMin(){
    Info min = minHeap.get(0);
    if(insertIndex == 0){
      System.out.println("Heap is empty, cannot return a min-value");
      return -1;
    }
    else{ return minHeap.get(0).invocation; }

  }
/*  removemin: This method removes and returns the root element of the
   min heap, and reorganize the heap accordingly to restore its min heap
   characteristics. If the min heap is empty, removeMin prints a statement
   to explai and returns a dummy element.
*/
  public Info removeMin(){
    if(insertIndex == 0){
      System.out.println("Heap is empty, cannot remove a min-value");
      Info dummy = new Info();
      return dummy;
    }
    else{
      Info temp = minHeap.remove(insertIndex-1);
      insertIndex --;
      if(insertIndex != 0){
        Info min = minHeap.remove(0);
        minHeap.add(0, temp);
        heapify(0);
        return min;
      }
      else{ return temp;}
    }
  }
/*   display: This method prints all specified elements in the min heap
    in the order of levels for testing (e.g., root element first, root’s
    two children second, root’s grandchildren third, etc.).
*/
  public void display(){
    System.out.print("Current heap is: ");
    int index = 0;
    int numLvlVals = 1;
    while( index < insertIndex ){
      int lvlVal = 1;
      while( lvlVal <= numLvlVals && index < insertIndex){
        Info heapSpot = minHeap.get(index);
        System.out.print("(" + heapSpot.invocation + "," + heapSpot.flight + ")" + " ");
        index ++;
        lvlVal ++;
      }
      System.out.print(", ");
      numLvlVals = numLvlVals * 2;
    }
    System.out.println();
  }

  public void heapify(int i){
    while((2*i + 1) < insertIndex){
      int minChildIndex = minChild(i);
      if(minHeap.get(i).invocation > minHeap.get(minChildIndex).invocation){
        swap(minHeap, i, minChildIndex);
      }
      i = minChildIndex;
    }
  }

  public int minChild(int i){
    int cL = 2*i + 1;
    int cR = 2*i + 2;
    Info leftChild = minHeap.get(cL);
    if(cR == insertIndex){
      return cL;
    }
    else{
      Info rightChild = minHeap.get(cR);
      if( leftChild.invocation < rightChild.invocation ){
        return cL;
      }
      else{ return cR; }
    }
  }

  public void swap(ArrayList<Info> arr, int i1, int i2){
    Info temp1 = arr.get(i1);
    Info temp2 = arr.remove(i2);
    arr.add(i2, temp1);
    arr.add(i1, temp2);
    arr.remove(i1+1);
  }

}
