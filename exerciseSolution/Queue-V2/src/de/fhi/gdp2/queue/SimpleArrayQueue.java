package de.fhi.gdp2.queue;

import java.util.Arrays;

import de.fhi.gdp2.logger.LogLevel;
import de.fhi.gdp2.logger.Logger;
import de.fhi.gdp2.logger.NullLogger;

// A simple queue implementation with an array
// that is growing automatically
// We always shift to 'the left' after dequeue
public class SimpleArrayQueue<T> implements Queue<T> {
    // The initial size of the queue
    private final int initialSize = 2;
    // We store the queue in an array
    private T[] theQ;
    // Index of the first free slot in the queue
    private int tail;
    //The logger to use
    private Logger logger;

    @SuppressWarnings("unchecked")
    public SimpleArrayQueue() {
        // See http://www.cogs.susx.ac.uk/courses/dats/notes/html/node181.html
        theQ = (T[]) new Object[initialSize];
        // initialize the array
        Arrays.fill(theQ, null);
        tail = 0;
        // Our default logger discards all messages
        this.logger = new NullLogger();
    }

    // Set a logger different from the default NullLogger
    public SimpleArrayQueue(Logger logger) {
        this();
        this.logger = logger;
    }
    
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    // Enlarge the internal array
    private void enlarge() {
        // Only enlarge if the queue is really full
        if (isFull()) {
            theQ = Arrays.copyOf(theQ, 2 * theQ.length);
            // Note: index tail is not altered
            logger.logMsg(LogLevel.INFO, "Enlarged queue to size " + theQ.length);
        } else {
            logger.logMsg(LogLevel.DEBUG, "Superfluous call to enlarge()");
        }
    }

    // -----------------------------------------
    // Implement methods of the interface Queue
    public void enqueue(T elem) {
        if (isFull()) {
            enlarge();
        }
        // Store element
        theQ[tail] = elem;
        // Log
        logger.logMsg(LogLevel.DEBUG, "enqueue:" + elem.toString());
        // Maintain index tail
        tail++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("dequeue called on empty queue");
        }
        // Get the element to be returned
        T elem = theQ[0];
        // Log
        logger.logMsg(LogLevel.DEBUG, "dequeue:" + elem.toString());
        // Shift contents of queue to 'the left'
        for (int i = 1; i < theQ.length && i < tail; i++) {
            theQ[i - 1] = theQ[i];
        }
        // Maintain index tail
        tail--;
        // Cleanup
        theQ[tail] = null;
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
