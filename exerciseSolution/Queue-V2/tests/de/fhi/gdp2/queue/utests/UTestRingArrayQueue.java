package de.fhi.gdp2.queue.utests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.fhi.gdp2.logger.ConsoleLogger;
import de.fhi.gdp2.logger.FileLogger;
import de.fhi.gdp2.logger.LogFilter;
import de.fhi.gdp2.logger.LogLevel;
import de.fhi.gdp2.logger.LogMgr;
import de.fhi.gdp2.logger.Logger;
import de.fhi.gdp2.queue.Queue;
import de.fhi.gdp2.queue.RingArrayQueue;

@SuppressWarnings("unused")
public class UTestRingArrayQueue {
    @Test
    public void test_DemoTheQueue_00() throws Exception {
        Queue<Integer> q = new RingArrayQueue<Integer>();
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

        assertEquals("Wrong head value from queue", 9, q.dequeue().intValue());
        q.showQueue(mute);

        assertEquals("Wrong head value from queue", 15, q.dequeue().intValue());
        q.showQueue(mute);

        assertEquals("Wrong head value from queue", 6, q.dequeue().intValue());
        q.showQueue(mute);

        assertTrue("Queue is supposed to be empty", q.isEmpty());
    }

    @Test
    public void test_enqueue_01() throws Exception {
        Queue<Integer> q = new RingArrayQueue<Integer>();
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
        Queue<Integer> q = new RingArrayQueue<Integer>();

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
        // Configure logging
        LogMgr logManager = new LogMgr();       
        Logger fileLoggerDebug   = new FileLogger(LogLevel.DEBUG,"debug.log");
        Logger fileLoggerNoDebug = new FileLogger(LogLevel.INFO,"nodebug.log");
        // All messages but DEBUG messages are logged to fileLoggerNoDebug
        logManager.attachLogger(fileLoggerNoDebug);      
        // Wrap the debug logger such that only messages of level DEBUG pass
        // All DEBUG messages are logged to fileLoggerDebug
        logManager.attachLogger(new LogFilter(fileLoggerDebug,LogLevel.DEBUG));
        // Attention: do not miss to close the logger!!!
        
        // Create a queue with a non-trivial logger
        Queue<Integer> q = new RingArrayQueue<Integer>(logManager);
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
        
        // Close the logger
        logManager.close();
    }

}
