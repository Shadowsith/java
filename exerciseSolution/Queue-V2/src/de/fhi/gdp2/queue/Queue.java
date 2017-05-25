package de.fhi.gdp2.queue;

// Interface for a generic queue (FIFO)
// Element type is T

public interface Queue<T> {
    // Append an element at the tail of the queue
    void enqueue(T elem);

    // Get element from the front of the queue (FIFO)
    T dequeue();

    // Is queue empty
    boolean isEmpty();

    // -----------------------------------------
    // Auxiliary methods
    //  - isFull() was added for convenience in the code
    //  - the others were added for Unit-Testing

    // Is queue full
    boolean isFull();

    // Return size of the internal structure
    // This method is really only for demo purposes
    int getQueueSize();

    // For debugging
    // Print contents of queue
    // Parameter mute is supposed to mute the output
    // Output depends on implementation
    void showQueue(boolean mute);
}
