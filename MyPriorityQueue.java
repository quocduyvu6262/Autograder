/**
 * TODO: Add your file header
 * Name: Duy Vu
 * ID: A17190576
 * Email: dqvu@ucsd.edu
 * Sources used: None
 * 2-4 sentence file description here
 * This file contains a MyPriorityQueue class, which
 * is an implementation for the Priority Queue ADT based
 * on MyMinHeap. Element can be pushed/poped from the queue
 * based on its priority. 
 */

import java.util.Collection;

/**
 * TODO: Add Class Header
 * This class implements the Priority Queue ADT using 
 * instance variable called heap.
 */
public class MyPriorityQueue<E extends Comparable<E>>
{

    //TODO: Add a public instance variable called "heap"
    //"heap" is of a generic MyMinHeap type
    public MyMinHeap<E> heap;

    
    /**
     * Constructor that creates an empty priority queue
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    /**
     * Constructor that creates a priority queue from a collection
     * @param collection The collection used to intialize priority queue
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        heap = new MyMinHeap<>(collection);
    }

    /**
     * Adds an element to the priority queue
     * @param element the element to be added
     */
    public void push(E element){
        heap.insert(element);
    }

    /**
     * Removes the element with the highest priority from the priority queue 
     * @return the element with the highest priority
     */
    public E pop(){
        return heap.remove();
    }

    /**
     * Sees the element with the highest priority from the priority queue
     * @return the element with the highest priority
     */
    public E peek(){
        return heap.getMin();
    }

    /**
     * Finds the number of elements in the priority queue
     * @return the number of elements in the priority queue
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * Remove all the elements from the priority queue.
     */
    public void clear(){
        heap.clear();
    }
}