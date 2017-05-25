package de.fhi.gdp2.queue;

// Interface for a queue (FIFO)

public interface Queue {
    // Append an element at the tail of the queue
    void enqueue(int i);

    // Get element from the front of the queue (FIFO)
    int dequeue();

    // Is queue empty
    boolean isEmpty();

    // -----------------------------------------
    // Auxiliary methods
    // - isFull() was added for convenience in the code
    // - the others were added for Unit-Testing

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
