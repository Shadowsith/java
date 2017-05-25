package de.fhi.gdp2.queue;

import java.util.Arrays;

// A queue implementation with an array used as a ring
// The array is growing automatically
// Due to the ring structure we do not need to shift after dequeue
// This kind of structure is used in operating systems (so called ring buffers)
// However, these ring buffers are usually coded in C or C++
//
public class RingArrayQueue implements Queue {
    // The initial size of the queue
    private final int initialSize = 2;
    // We store the queue in an array
    private int[] theQ;
    // Index of the first free slot in the queue
    private int tail;
    // Index of the head element in the queue
    private int head;
    // Number Of Stored Elements (nose)
    private int nose;

    public RingArrayQueue() {
        theQ = new int[initialSize];
        // initialize the array
        Arrays.fill(theQ, 0);
        tail = 0;
        head = 0;
        nose = 0;
    }

    // Enlarge the internal array
    private void enlarge() {
        // Only enlarge if the queue is really full
        if (isFull()) {
            // Create a new array with double the size
            int[] newQ = new int[2 * theQ.length];
            // Copy the entire and full queue to the new array
            int i = 0;
            while (i < theQ.length) {
                newQ[i] = theQ[(i + head) % theQ.length];
                i++;
            }
            // Adjust indexes to new array
            head = 0;
            // The tail is supposed to point to the first free element
            tail = theQ.length;
            // Since the queue was full we copied theQ.length elements
            nose = theQ.length;
            // Switch the queue
            // The old one is subject to garbage collection
            theQ = newQ;

        } else {
            System.out.println("Superfluous call to enlarge()");
        }
    }

    // -----------------------------------------
    // Implement methods of the interface Queue
    public void enqueue(int i) {
        if (isFull()) {
            enlarge();
        }
        // Store element
        theQ[tail] = i;
        // Maintain index tail
        tail = (tail + 1) % theQ.length;
        // Maintain nose; one more element stored
        nose++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("dequeue called on empty queue");
        }
        // Get the element to be returned
        int elem = theQ[head];
        // Cleanup
        theQ[head] = 0;
        // Maintain the index head
        head = (head + 1) % theQ.length;
        // Maintain nose; one element less stored
        nose--;
        // return the stored head element
        return elem;
    }

    public boolean isEmpty() {
        return nose == 0;
    }

    public boolean isFull() {
        return nose == theQ.length;
    }

    public int getQueueSize() {
        return theQ.length;
    }

    public void showQueue(boolean mute) {
        if (!mute) {
            System.out.printf("Printing Queue\n");
            System.out.printf("\tSize of Queue:%d\n", theQ.length);
            System.out.printf("\tNumber of stored elements:%d\n", nose);
            System.out.printf("\tIndex head:%d\n", head);
            System.out.printf("\tIndex tail:%d\n", tail);
            if (isEmpty()) {
                System.out.println("\tThe queue is empty");
            }
            System.out.print("\t[");
            for (int i = 0; i < theQ.length; i++) {
                if (i % 10 == 0 && i > 0)
                    System.out.printf("\n\t");
                if (i > 0)
                    System.out.printf(",");
                System.out.printf("%d", theQ[i]);
            }
            System.out.printf("]\n");
        }
    }

}
