/**
 * TODO: Add your file header
 * Name: Duy Vu
 * ID: A17190576
 * Email: dqvu@ucsd.edu
 * Sources used: None
 * 
 * 2-4 sentence file description here
 * This files contains the custom tests for MyMinHeap
 * class. These tests are different
 * from written tests in the PublicTester file.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 * 
 * The custom test follow the methods description
 * in the PA document. Tests are supposed to cover all
 * possible edge case and ensure the correct behavior
 * of each method in MyMinHeap.
 */
public class CustomTester {

    /**
     * Helper method to initialize all instance variables of MyDeque
     * 
     * @param meanHeap The heap to initialize
     * @param data     The data array
     */
    static void initMinHeap(MyMinHeap<Integer> heap, ArrayList<Integer> data) {
        heap.data = new ArrayList<>(data);
    }
    
    /**
     * Test the constructor when DataList or any element 
     * in DataList is null.
     */
    @Test
    public void testMyMinHeapConstructor() {
        try{
            MyMinHeap<Integer> minheap = new MyMinHeap<>(null);
            fail();
        }catch(NullPointerException e){}

        try{
            ArrayList<Integer> inputList = new ArrayList<Integer>(
                Arrays.asList(
                        new Integer[] { 5, 4, 1, null, 2, 9, null, 3 }
                )
            );
            MyMinHeap<Integer> minheap = new MyMinHeap<>(inputList);
            fail();
        }catch(NullPointerException e){}
    }

    /**
     * Test the getMinChildIdx method when the node is the leaf or it
     * has two equal children
     */
    @Test
    public void testGetMinChildIdx() {
        //Node is a leaf
        ArrayList<Integer> inputList = new ArrayList<Integer>(
            Arrays.asList(
                    new Integer[] {1,14,5,8,4,4}
            )
        );
        MyMinHeap<Integer> minHeap = new MyMinHeap<>(inputList);
        assertEquals("Method should return -1",-1, minHeap.getMinChildIdx(minHeap.size()));

        //Node has two equal children
        assertEquals("Method should return index of the left child",1, minHeap.getMinChildIdx(0));
    }

    /**
     * Test the percolateUp method on the valid 
     * index at the last of the heap
     */
    @Test
    public void testPercolateUp() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] {14,2,6,4,45,24,53,1}
            )
        );
        initMinHeap(heap, startingList);
        heap.percolateUp(heap.size()-1);
        Integer[] expected = {1,14,6,2,45,24,53,4};
        for (int i = 0; i <8; i++) {
            assertEquals(
                "Heap after perlocating up last index. ",
                expected[i],
                heap.data.get(i)
            );
        }
    }

    /**
     * Test the percolateDown method on the valid index
     * in the middle of the heap
     */
    @Test
    public void testPercolateDown() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> startingList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] {3,14,6,4,45,24,53,1}
            )
        );
        initMinHeap(heap, startingList);
        heap.percolateDown(1);
        Integer[] expected = {3,4,6,1,45,24,53,14};
        for (int i = 0; i <8; i++) {
            assertEquals(
                "Heap after perlocating down index 1. ",
                expected[i],
                heap.data.get(i)
            );
        }
    }

    /**
     * Test the deleteIndex method on the valid index
     * in the middle of the heap
     */
    @Test
    public void testDeleteIndex() {
        //test on valid method in the middle of the heap
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> inputList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] {3,14,6,4,45,24,53,1}
            )
        );
        heap = new MyMinHeap<>(inputList);
        heap.deleteIndex(1);
        Integer[] expected = {1,4,6,14,45,24,53};
        for (int i = 0; i <7; i++) {
            assertEquals(
                "Heap after deleting index 1. ",
                expected[i],
                heap.data.get(i)
            );
        }
        //Decrement size by 1
        assertEquals("Heap size is decremented by 1",7, heap.size());
    }

    /**
     * Test the deleteIndex method on the valid method at the last
     * of the list
     */
    @Test
    public void testDeleteIndex2() {
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> inputList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] {3,14,6,4,45,24,53,1}
            )
        );
        heap = new MyMinHeap<>(inputList);
        heap.deleteIndex(heap.size()-1);
        Integer[] expected = {1,3,6,4,45,24,53};
        for (int i = 0; i <7; i++) {
            assertEquals(
                "Heap after deleting last index. ",
                expected[i],
                heap.data.get(i)
            );
        }
        //Decrement size by 1
        assertEquals("Heap size is decremented by 1",7, heap.size());
    }

    /**
     * Test the insert method on the null element
     */
    @Test
    public void testInsert(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> inputList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] {5,2,7}
            )
        );
        heap = new MyMinHeap<>(inputList);
        try{
            heap.insert(null);
            fail();
        }catch(NullPointerException e){}
    }

    /**
     * Test the insert method on the element that is 
     * greater than the root
     */
    @Test
    public void testInsert2(){
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        ArrayList<Integer> inputList = new ArrayList<Integer>(
            Arrays.asList(
                new Integer[] {58,24,36,43,11,64,22}
            )
        );
        heap = new MyMinHeap<>(inputList);
        heap.insert(Integer.valueOf(8));
        Integer[] expected = { 8,11,22,24,58,64,36,43 };
        for (int i = 0; i < 6; i++) {
            assertEquals(
                "Heap after inserting 8. ", 
                expected[i], 
                heap.data.get(i)
            );
        }

        assertEquals("Size is decremented by 1",8, heap.size());
    }

   
    /**
     * Test remove when the heap is empty and 
     * when the heap has 1 element, which is 
     * a root
     */
    @Test
    public void testRemove(){
        //when heap is empty
        MyMinHeap<Integer> heap = new MyMinHeap<>(); 
        heap.data = new ArrayList<>();
        assertEquals("Return null when heap is empty",null, heap.remove());
        assertEquals("Heap is empty", 0, heap.data.size());
        //when heap has one element
        heap.insert(Integer.valueOf(2));
        heap.insert(Integer.valueOf(1));
        assertEquals("Heap has two elements", 2, heap.size());
        
        assertEquals("Return the root",Integer.valueOf(1), heap.remove());
        assertEquals("Heap has one element", 1, heap.data.size());
        assertEquals("Return the root",Integer.valueOf(2), heap.remove());
        assertEquals("Heap is empty", 0, heap.data.size());

    }

  
    /**
     * Test getMin when the heap is empty
     */
    @Test
    public void testGetMin(){
        MyMinHeap<Integer> heap = new MyMinHeap<>(); 
        heap.data = new ArrayList<>();
        assertEquals("Return null when heap is empty",null, heap.getMin());
        assertEquals("Heap is empty", 0, heap.data.size());
    }
}