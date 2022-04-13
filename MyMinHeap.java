import java.util.ArrayList;
/**
 * TODO: Add your file header
 * Name: Duy Vu
 * ID: A17190576
 * Email: dqvu@ucsd.edu
 * Sources used: None
 * 
 * 2-4 sentence file description here
 * This file contains MyMinHeap class, which is an implementation
 * for the min heap data structure using MinHeapInterface. Elements
 * can be added to the end of the heap, and the root can be removed.
 */
import java.util.Collection;

/**
 * TODO: Add class header
 * This class implements MyMinHeap data structures using the ArrayList
 * instance variable called data. 
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{
    private static final int DOUBLE = 2;

    public ArrayList<E> data;
    /**
     * Constructor that initializes data to an empty ArrayList
     */
    public MyMinHeap(){
        data = new ArrayList<>();
    }

    /**
     * Constructor that initializes a min-heap using the elements
     * in collection
     */
    public MyMinHeap(Collection<? extends E> collection){
        if(collection == null){
            throw new NullPointerException();
        }
        if(collection.contains(null)){
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);
        //Iterate data backward, and precolate each element
        //down
        for(int i = this.data.size()-1;i>=0;i--){
            this.percolateDown(i);
        }

    }
    /**-------------Core Methods----------------------*/
    /**
     * Add element to the end of the heap  and percolate 
     * only the inserted element up until no heap properties 
     * are violated 
     * @param element - element to be added
     */
    public void insert(E element) {
        //throw exception if element is null
        if(element == null){
            throw new NullPointerException();
        }
        this.data.add(element);
        this.percolateUp(this.size()-1);
    }

    /**
     * Return the root(smallest) element of the heap
     * @return the root. Return null if heap is empty
     */
    public E getMin() {
        if(this.size()==0) return null;
        return this.data.get(0);
    }

    /**
     * Remove and return the root (this will be the 
     * smallest) element in the heap
     * @return the deleted root. If the heap is empty,
     * return null.
     */
    public E remove() {
        if(this.size()==0) return null;
        //delete root
        E root = this.deleteIndex(0);
        return root;
    }

    /**
     * Return the number of elements in this min-heap
     * @return the number of elements in the heap
     */
    public int size() {
        return this.data.size();
    }

    /**
     * Clear out the entire heap
     */
    public void clear() {
        this.data.clear();
    }

    /**----------Helper Methods---------------------*/
    /**
     * Swap elements at From and To indices in data
     * @param from - index of the element to be swapped
     * @param to - index of the element to be swapped
     */
    protected void swap(int from, int to){
        E fromVal = (E)this.data.get(from);
        this.data.set(from, data.get(to));
        this.data.set(to,fromVal);
    }
    
    /**
     * Calculate and return the parent index of the 
     * parameter index.
     * @param index - index of the child element
     * @return the parent index of the child index
     */
    protected int getParentIdx(int index){
        return (index-1)/DOUBLE;
    }

    /**
     * Calculate and return the left child index of 
     * the parameter index.
     * @param index - index of parent element
     * @return the left child index of the parent
     * index
     */
    protected int getLeftChildIdx(int index){
        return (index*DOUBLE) + 1;
    }

    /**
     * Calculate and return the right child index of 
     * the parameter index.
     * @param index - index of the parent element
     * @return the right child index of the parent
     * index
     */
    protected int getRightChildIdx(int index){
        return (index*DOUBLE) + DOUBLE;
    }

    /**
     * Calculate and return the index of the smaller 
     * child of the element at index.
     * @param index - index of the parent element
     * @return the index of the left child if two
     * children are equal. Return -1 if the node 
     * has no children
     */
    protected int getMinChildIdx(int index){
        int leftChildIndex = this.getLeftChildIdx(index);
        int rightChildIndex = this.getRightChildIdx(index);
        //Check if indices are out of bound
        if(leftChildIndex >= this.size()){
            return -1;
        }
        if(rightChildIndex >= this.size()){
            return leftChildIndex;
        }
        //Compare leftChild with rightChild
        E leftChild = this.data.get(leftChildIndex);
        E righChild = this.data.get(rightChildIndex);
        if(leftChild.compareTo(righChild)<0){
            return leftChildIndex;
        }
        else if(leftChild.compareTo(righChild)>0){
            return rightChildIndex;
        }
        return leftChildIndex;
    }

    /**
     * Percolate the element at index up until no heap 
     * properties are violated by this element 
     * @param index - index of the child element to be 
     * perlocated up
     */
    protected void percolateUp(int index){
        int parentIndex = this.getParentIdx(index);
        while(this.data.get(parentIndex).compareTo(
            this.data.get(index))>0){

            swap(parentIndex, index);
            index = parentIndex;
            parentIndex = this.getParentIdx(index);
        }
    }

    /**
     * Percolate the element at index down until no heap 
     * properties are violated by this element.If swapping 
     * is needed, always swap places with the smaller child. 
     * If both children are equal and swapping is needed, 
     * swap with the left child.
     * @param index - index of parent element to be perlocated
     * down
     */
    protected void percolateDown(int index){
        int childIndex = this.getMinChildIdx(index);
        while(childIndex != -1 && 
            this.data.get(index).compareTo(
            this.data.get(childIndex))>0){

            this.swap(index, childIndex);
            index = childIndex;
            childIndex = this.getMinChildIdx(index);
        }
    }

    /**
     * Remove the element at index from data and return it.
     * @param index - index of element to be deleted
     * @return the deleted element
     */
    protected E deleteIndex(int index){
        int lastElementIndex = this.size()-1;
        //swap lastElement with node to be deleted
        this.swap(index, lastElementIndex);
        E deletedElement = this.data.remove(lastElementIndex);
        //check if deleted element is the last element
        if(index == lastElementIndex){
            return deletedElement;
        }
        //either perlocate up or perlocate down
        int leftChildIndex = this.getLeftChildIdx(index);
        int rightChildIndex = this.getRightChildIdx(index);
        int parentIndex = this.getParentIdx(index);
        if(leftChildIndex >= this.size()){
            this.percolateUp(index);
        }
        else if(leftChildIndex<this.size() && rightChildIndex<this.size()){
            if(this.data.get(index).compareTo(
                this.data.get(leftChildIndex))>0
            || this.data.get(index).compareTo(
                this.data.get(rightChildIndex))>0){

                this.percolateDown(index);
            }
        }
        else if(leftChildIndex<this.size() && rightChildIndex>=this.size()){
            if(this.data.get(index).compareTo(
                this.data.get(leftChildIndex))>0){

                this.percolateDown(index);
            }
        }
        else if(parentIndex>=0){
            if(this.data.get(parentIndex).compareTo(
                this.data.get(index))>0){
                    
                this.percolateUp(index);
            }
        }
        return deletedElement;
    }


}