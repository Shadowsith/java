package de.fhi.gdp2.queue.utests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import de.fhi.gdp2.queue.ArrayListQueue;
import de.fhi.gdp2.queue.Queue;

public class UTestArrayListQueue {
    @Test
    public void test_DemoTheQueue_00() throws Exception {
        Queue q = new ArrayListQueue();
        boolean mute = true;

        q.showQueue(mute);
        assertTrue("Queue is supposed to be empty", q.isEmpty());

        q.enqueue(9);
        q.showQueue(mute);
        assertFalse("Queue is supposed to be non-empty", q.isEmpty());

        q.enqueue(15);
        q.enqueue(6);
        q.showQueue(mute);
        assertEquals("Wrong head value from queue", 9, q.dequeue());
        assertEquals("Wrong head value from queue", 15, q.dequeue());
        assertEquals("Wrong head value from queue", 6, q.dequeue());

        q.showQueue(mute);
        assertTrue("Queue is supposed to be empty", q.isEmpty());
    }

    @Test
    public void test_enqueue_01() throws Exception {
        Queue q = new ArrayListQueue();
        boolean mute = true;

        assertTrue("Queue is supposed to be empty", q.isEmpty());

        // Fill the queue and make it grow
        // int startSize = q.getQueueSize();
        // ArrayList has no internal length.
        int startSize = 2;
        int count = 0;
        for (int i = 0; i < 3 * startSize; i++) {
            q.enqueue(count + 1);
            count++;
            q.showQueue(mute);
        }

        // Dequeue and check values returned
        for (int i = 0; i < count; i++) {
            int elem = q.dequeue();
            q.showQueue(mute);
            assertEquals("Wrong dequeued element", i + 1, elem);
        }
        assertTrue("Queue is supposed to be empty", q.isEmpty());
    }

    @Test
    public void test_enqueue_02() throws Exception {
        Queue q = new ArrayListQueue();

        assertTrue("Queue is supposed to be empty", q.isEmpty());
        try {
            q.dequeue();
            fail("Expecting exception for dequeue on empty queue");
        } catch (RuntimeException e) {
            // expected
        }
    }

}
