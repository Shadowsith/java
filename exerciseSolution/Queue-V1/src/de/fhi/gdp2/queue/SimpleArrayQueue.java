package de.fhi.gdp2.queue;

import java.util.Arrays;

// A simple queue implementation with an array
// that is growing automatically
// We always shift to 'the left' after dequeue
public class SimpleArrayQueue implements Queue {
    // The initial size of the queue
    private final int initialSize = 2;
    // We store the queue in an array
    private int[] theQ;
    // Index of the first free slot in the queue
    private int tail;

    public SimpleArrayQueue() {
        theQ = new int[initialSize];
        // initialize the array
        Arrays.fill(theQ, 0);
        tail = 0;
    }

    // Enlarge the internal array
    private void enlarge() {
        // Only enlarge if the queue is really full
        if (isFull()) {
            theQ = Arrays.copyOf(theQ, 2 * theQ.length);
            // Note: index tail is not altered
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
        tail++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("dequeue called on empty queue");
        }
        // Get the element to be returned
        int elem = theQ[0];
        // Shift contents of queue to 'the left'
        for (int i = 1; i < theQ.length && i < tail; i++) {
            theQ[i - 1] = theQ[i];
        }
        // Maintain index tail
        tail--;
        // Cleanup
        theQ[tail] = 0;
        // return the stored head element
        return elem;
    }

    public boolean isEmpty() {
        return tail == 0;
    }

    public boolean isFull() {
        return tail >= theQ.length;
    }

    public int getQueueSize() {
        return theQ.length;
    }

    public void showQueue(boolean mute) {
        if (!mute) {
            System.out.printf("Printing Queue\n");
            System.out.printf("\tSize of Queue:%d\n", theQ.length);
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
