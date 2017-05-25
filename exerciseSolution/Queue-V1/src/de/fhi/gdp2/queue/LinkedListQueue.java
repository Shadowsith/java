package de.fhi.gdp2.queue;

import java.util.LinkedList;

// A queue implementation by generic type LinkedList.
// We inherit from LinkedList in order to show
// combination of 'extends' and 'implements'
@SuppressWarnings("serial")
public class LinkedListQueue extends LinkedList<Integer> implements Queue {
    // -----------------------------------------
    // Implement methods of the interface Queue
    public void enqueue(int i) {
        this.add(i);
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("dequeue called on empty queue");
        }
        // Get the element to be returned
        int elem = this.get(0);
        // Shift contents of queue to 'the left'
        // This is simply done by removing the first element
        this.remove(0);
        // return the stored head element
        return elem;
    }

    // public boolean isEmpty()
    // is inherited

    public boolean isFull() {
        // An LinkedList is never full as long as there is memory
        return false;
    }

    public int getQueueSize() {
        return this.size();
    }

    public void showQueue(boolean mute) {
        if (!mute) {
            System.out.printf("Printing Queue\n");
            System.out.printf("\tSize of Queue:%d\n", this.size());
            System.out.printf("\tIndex tail: none\n");
            if (isEmpty()) {
                System.out.println("\tThe queue is empty");
            }
            System.out.print("\t[");
            for (int i = 0; i < this.size(); i++) {
                if (i % 10 == 0 && i > 0)
                    System.out.printf("\n\t");
                if (i > 0)
                    System.out.printf(",");
                System.out.printf("%d", this.get(i));
            }
            System.out.printf("]\n");

        }
    }

}
