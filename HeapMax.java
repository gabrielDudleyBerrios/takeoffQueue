import java.util.*;

public class HeapMax{

  private ArrayList<Info> maxHeap;
  public int insertIndex;

// Constructor of HeapMax. Initializes ArrayList and insertIndex
  public HeapMax(){
    maxHeap = new ArrayList<Info>();
    insertIndex = 0;
  }
/*   insert: This method inserts a new element to the max heap
    and organizes max heap for proper order
*/
  public void insert(Info element){
    maxHeap.add(element);
    int i = insertIndex;
    Info child = maxHeap.get(i);
    Info parent = maxHeap.get((i-1)/2);
    while(child.invocation > parent.invocation && parent.invocation > 0){
      swap(maxHeap, i, ((i-1)/2) );
      i = (i-1)/2;
      child = maxHeap.get(i);
      parent = maxHeap.get((i-1)/2);
    }
    insertIndex ++;
  }
/*   extractMax: This method returns the root element of the max heap
    or -1 if the max heap is Empty.
*/
  public int extractMax(){
    Info max = maxHeap.get(0);
    if(insertIndex == 0){
      System.out.println("Heap is empty, cannot return a max-value");
      return -1;
    }
    else{ return maxHeap.get(0).invocation; }

  }
/*   removeMax: This method removes and returns the root element of the
   max heap, and reorganize the heap accordingly to restore its max heap
   characteristics. If max heap is empty, removeMax prints a statement to
   explain and returns a dummy element
*/
  public Info removeMax(){
    if(insertIndex == 0){
      System.out.println("Heap is empty, cannot remove a max-value");
      Info dummy = new Info();
      return dummy;
    }
    else{
      Info temp = maxHeap.remove(insertIndex-1);
      insertIndex --;
      if(insertIndex != 0){
        Info max = maxHeap.remove(0);
        maxHeap.add(0, temp);
        heapify(0);
        return max;
      }
      else{ return temp;}
    }
  }
/*  display: This method prints all specified elements in the max heap
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
        Info heapSpot = maxHeap.get(index);
        System.out.print("(" + heapSpot.invocation + "," + heapSpot.flight + ")" + " ");
        index ++;
        lvlVal ++;
      }
      System.out.print(", ");
      numLvlVals = numLvlVals * 2;
    }
    System.out.println();
  }

/* heapify: This method reorganizes the max heap accordingly when element is removed
*/
  public void heapify(int i){
    while((2*i + 1) < insertIndex){
      int maxChildIndex = maxChild(i);
      if(maxHeap.get(i).invocation < maxHeap.get(maxChildIndex).invocation){
        swap(maxHeap, i, maxChildIndex);
      }
      i = maxChildIndex;
    }
  }

/* maxChild: This method evaluates the values of a parent nodes right and
  left children and returns the index of the child with the max value
*/
  public int maxChild(int i){
    int cL = 2*i + 1;
    int cR = 2*i + 2;
    Info leftChild = maxHeap.get(cL);
    if(cR == insertIndex){
      return cL;
    }
    else{
      Info rightChild = maxHeap.get(cR);
      if( leftChild.invocation > rightChild.invocation ){
        return cL;
      }
      else{ return cR; }
    }
  }

  // swap: This method swaps to elements in an ArrayList
  public void swap(ArrayList<Info> arr, int i1, int i2){
    Info temp1 = arr.get(i1);
    Info temp2 = arr.remove(i2);
    arr.add(i2, temp1);
    arr.add(i1, temp2);
    arr.remove(i1+1);
  }

}
