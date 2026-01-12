package minimumheap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;

/**
 * It represents a minimum heap. In a Min-Heap the key present at the root node must be less than or 
 * equal among the keys present at all of its children. The same property must be recursively true 
 * for all sub-trees in that tree.
 * @author Anita Scanu
 * @param <T>: type of the heap elements
 */
public class MinimumHeap<T> {
  ArrayList<T> minHeap = null;
  HashMap<T,Integer> map = null;
  Comparator<? super T> comparator = null;
  private int index;

  /**
   * It creates an empty Minimum Heap.
   * It accepts as input a comparator implementing the precedence relation between the heap elements.
   * @param comparator: a comparator implementing the precedence relation between the heap elements
   * @throws MinimumHeapException if the parameter is null
   */
  public MinimumHeap(Comparator<? super T> comparator) throws MinimumHeapException{
    if (comparator == null) throw new MinimumHeapException("MinimumHeap constructor: comparator parameter cannot be null");
    this.minHeap = new ArrayList<T>();
    this.map = new HashMap<T,Integer>();
    this.comparator = comparator;
    this.index = 0;
  }

  /**
   * Check if the heap is empty or not.
   * @return true if this heap is empty (it does not contain any elements)
   */
  public boolean isEmpty(){
    return (this.minHeap).isEmpty();
  }

  // return the index of the parent of the element with index i
  private int parent(int i) {
    return (i - 1) / 2;
  }

  // return the index of the left child of the element with index i
  private int left(int i) {
    return (i * 2) + 1;
  }

  // return the index of the right child of the element with index i
  private int right(int i) {
    return (i * 2) + 2;
  }

  /**
   * Return of the parent of an item.
   * @param element: the element whose parent is returned
   * @return the parent of element in the heap
   */
  public T getParent(T element){
    int elemIndex = map.get(element);
    if(elemIndex > 0)
      return (this.minHeap).get(parent(elemIndex));
    else
      return null;
  }

  /**
   * Return of the left child of an element.
   * @param element: the element whose left child is returned
   * @return left child of element in the heap
   */
  public T getLeft(T element){
    int elemIndex = map.get(element);
    if(left(elemIndex) < (this.minHeap).size())
      return (this.minHeap).get(left(elemIndex));
    else
      return null;
  }

  /**
   * Return of the right child of an element.
   * @param element: the element whose right child is returned
   * @return right child of element in the heap
   */
  public T getRight(T element){
    int elemIndex = map.get(element);
    if(right(elemIndex) < (this.minHeap).size())
      return (this.minHeap).get(right(elemIndex));
    else
      return null;
  }

  /**
   * Return of the dimension of the heap.
   * @return the number of elements in the heap
   */
  public int size() {
    return this.index;
  }

  /**
   * Insert a new element in the heap.
   * @param element: the new element to insert in the heap
   * @throws MinimumHeapException if the parameter is null or if the heap already contains the element
   * @return the minimum element from the heap (the root)
   */
  public void insert(T element) throws MinimumHeapException {
    if (element == null) throw new MinimumHeapException("MinimumHeap insert: element parameter cannot be null");
    if (this.map.containsKey(element)) throw new MinimumHeapException("MinimumHeap insert: minHeap already contains this element");
    (this.minHeap).add(index, element);
    (this.map).put(element, index);
    int current = index;
    while (current > 0 && (this.comparator).compare((this.minHeap).get(current), (this.minHeap).get(parent(current))) < 0) {
      swap(current, parent(current));
      current = parent(current);
    }
    index++;
  }

  // swap two elements in the heap
  private void swap(int p1, int p2){
    this.map.replace(this.minHeap.get(p1), p2);
    this.map.replace(this.minHeap.get(p2), p1);
    Collections.swap(this.minHeap, p1, p2);
  }

  /**
   * Removes and returns the minimum element from the heap.
   * @return the minimum element from the heap (the root)
   */
  public T extractMin() {
    if(index == 0) return null;
    // since its a min heap, so root = minimum element
    T popped = this.minHeap.get(0);
    if(index == 1){
      this.map.remove(popped);
      this.minHeap.remove(this.minHeap.size() - 1);
      index--;
      // no need to call minHeapify because the heap is empty now
      return popped;
    }
    else{
      T lastElem = this.minHeap.get(this.minHeap.size() - 1);
      this.map.remove(popped);
      this.map.remove(lastElem);
      this.minHeap.remove(this.minHeap.size() - 1);
      this.map.put(lastElem, 0);
      this.minHeap.set(0, lastElem);
      index--;
      // minHeapify to mantain the propriety of the min heap
      minHeapify(0);
      return popped;
    }
  }

  // return true if the element in i is leaf
  private boolean isLeaf(int i) {
    if (right(i) >= this.index || left(i) >= this.index)
      return true;
    return false;
  }

  /**
   * Removes and returns the minimum element from the heap.
   * @param oldValue: the element to decrease
   * @param newValue: the new value of the element to decrease 
   * @throws MinimumHeapException if oldValue is null or if newValue is null or if the heap does not contains oldValue of if
   *         newValue is already in the heap of if oldValue is less or equal to newValue
   * @return the minimum element from the heap (the root)
   */
  public void decrease(T oldValue, T newValue) throws MinimumHeapException {
    if (oldValue == null) throw new MinimumHeapException("MinimumHeap decrease: oldValue parameter cannot be null");
    if (newValue == null) throw new MinimumHeapException("MinimumHeap decrease: newValue parameter cannot be null");
    if (!this.map.containsKey(oldValue)) throw new MinimumHeapException("MinimumHeap decrease: minHeap does not contain this element");
    if (this.map.containsKey(newValue)) throw new MinimumHeapException("MinimumHeap decrease: minHeap already contains this element");
    if (this.comparator.compare(oldValue, newValue) <= 0) throw new MinimumHeapException("MinimumHeap decrease: newValue parameter must be lower than oldValue parameter");
    // index of the oldValue in the heap
    int i = this.map.get(oldValue);
    this.minHeap.set(i, newValue);
    this.map.remove(oldValue);
    this.map.put(newValue, i);
    while ((this.comparator).compare((this.minHeap).get(i), (this.minHeap).get(parent(i)))< 0) {
        swap(i, parent(i));
        i = parent(i);
    }
  }

  // restore the proprieties of the heap
  private void minHeapify(int i) {
    if (!isLeaf(i)) {
      if ((this.comparator).compare(this.minHeap.get(i),this.minHeap.get(left(i))) > 0 || (this.comparator).compare(this.minHeap.get(i),this.minHeap.get(right(i))) > 0) {
        if ((this.comparator).compare(this.minHeap.get(left(i)),this.minHeap.get(right(i))) < 0) {
          swap(i, left(i));
          minHeapify(left(i));
        } 
        else {
          swap(i, right(i));
          minHeapify(right(i));
        }
      }
    }
  }

  // function to print the contents of the heap
  public void printHeap() {
    for (int i = 0; i < (index / 2); i++) {
      System.out.print("Parent : " + this.minHeap.get(i));
      if (left(i) < index)
        System.out.print(" Left : " + this.minHeap.get(left(i)));
      if (right(i) < index)
        System.out.print(" Right :" + this.minHeap.get(right(i)));
      System.out.println();
    }
  }
}
