package de.fhi.gdp2.queue.utests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import de.fhi.gdp2.queue.Queue;
import de.fhi.gdp2.queue.RingArrayQueue;

@SuppressWarnings("unused")
public class UTestRingArrayQueue {
    @Test
    public void test_DemoTheQueue_00() throws Exception {
        Queue q = new RingArrayQueue();
        boolean mute = true;

        q.showQueue(mute);
        assertTrue("Queue is supposed to be empty", q.isEmpty());

        q.enqueue(9);
        q.showQueue(mute);
        assertFalse("Queue is supposed to be non-empty", q.isEmpty());

        q.enqueue(15);
        q.showQueue(mute);

        q.enqueue(6);
        q.showQueue(mute);

        assertEquals("Wrong head value from queue", 9, q.dequeue());
        q.showQueue(mute);

        assertEquals("Wrong head value from queue", 15, q.dequeue());
        q.showQueue(mute);

        assertEquals("Wrong head value from queue", 6, q.dequeue());
        q.showQueue(mute);

        assertTrue("Queue is supposed to be empty", q.isEmpty());
    }

    @Test
    public void test_enqueue_01() throws Exception {
        Queue q = new RingArrayQueue();
        boolean mute = true;

        assertTrue("Queue is supposed to be empty", q.isEmpty());

        // Fill the queue and make it grow
        int startSize = q.getQueueSize();
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
        Queue q = new RingArrayQueue();

        assertTrue("Queue is supposed to be empty", q.isEmpty());
        try {
            q.dequeue();
            fail("Expecting exception for dequeue on empty queue");
        } catch (RuntimeException e) {
            // expected
        }
    }

    @Test
    public void test_enqueue_03() throws Exception {
        Queue q = new RingArrayQueue();
        boolean mute = true;

        assertTrue("Queue is supposed to be empty", q.isEmpty());

        // Fill the queue and make it grow
        int startSize = q.getQueueSize();
        int count = 0;
        for (int i = 0; i < 3 * startSize; i++) {
            q.enqueue(count + 1);
            count++;
            q.showQueue(mute);
        }

        // Dequeue 2/3 of values and check values returned
        for (int i = 0; i < count / 3 * 2; i++) {
            int elem = q.dequeue();
            q.showQueue(mute);
            assertEquals("Wrong dequeued element", i + 1, elem);
        }

        // enqueue again
        int count2 = count;
        for (int i = 0; i < count / 3 * 2; i++) {
            q.enqueue(count2 + 1);
            count2++;
            q.showQueue(mute);
        }

        // Dequeue all of values and check values returned
        for (int i = 0; i < count; i++) {
            int elem = q.dequeue();
            q.showQueue(mute);
            assertEquals("Wrong dequeued element", count / 3 * 2 + i + 1, elem);
        }
        assertTrue("Queue is supposed to be empty", q.isEmpty());

    }

}
