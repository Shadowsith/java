import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UTestExample02 {
    private Program pA = new Program("A");
    private Program pB = new Program("B");
    private Program pC = new Program("C");

    private PCB pcbA = new PCB(pA);
    private PCB pcbB = new PCB(pB);
    private PCB pcbC = new PCB(pC);

    @Before
    public void initialize() {
        // Define work plan
        // Program A
        pA.addSection(new Work(40));

        // Program B
        pB.addSection(new Work(10));
        pB.addSection(new Launch(pC));
        pB.addSection(new Work(5));
        pB.addSection(new Wait(40));
        pB.addSection(new Work(25));
        pB.addSection(new Wait(20));
        pB.addSection(new Work(20));

        // Program C
        pC.addSection(new Work(10));
        pC.addSection(new Launch(pA));
        pC.addSection(new Work(10));
        pC.addSection(new Wait(10));
        // pC.addSection(new Wait(20)); // race condition for Fifo
        pC.addSection(new Work(40));

    }

    @Test
    public void test_FifoScheduler_01() throws Exception {
        Scheduler sch = new FifoScheduler();
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);

        sch.initializeStartProcess(pcbB);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRScheduler_01() throws Exception {
        Scheduler sch = new RRScheduler(10, 2);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);

        sch.initializeStartProcess(pcbB);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLsndRRScheduler_01() throws Exception {
        Scheduler sch = new MLsndRRScheduler(5, 4);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        pcbA.setStaticPriority(2);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(9);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(4);

        sch.initializeStartProcess(pcbB);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLsndRRScheduler_02() throws Exception {
        Scheduler sch = new MLsndRRScheduler(10, 2);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        pcbA.setStaticPriority(2);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(9);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(4);

        sch.initializeStartProcess(pcbB);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLsndRRScheduler_03() throws Exception {
        Scheduler sch = new MLsndRRScheduler(20, 1);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        pcbA.setStaticPriority(2);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(9);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(4);

        sch.initializeStartProcess(pcbB);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLsndRRSchedulerNoRefill_01() throws Exception {
        MLsndRRScheduler sch = new MLsndRRScheduler(5, 4);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        pcbA.setStaticPriority(2);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(9);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(4);

        sch.initializeStartProcess(pcbB);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLsndRRSchedulerNoRefill_02() throws Exception {
        MLsndRRScheduler sch = new MLsndRRScheduler(10, 2);
        // sch.setExplain(true);
        sch.setRefill(false);

        sch.addPCB(pcbA);
        pcbA.setStaticPriority(2);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(9);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(4);

        sch.initializeStartProcess(pcbB);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLsndRRSchedulerNoRefill_03() throws Exception {
        MLsndRRScheduler sch = new MLsndRRScheduler(20, 1);
        // sch.setExplain(true);
        sch.setRefill(false);

        sch.addPCB(pcbA);
        pcbA.setStaticPriority(2);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(9);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(4);

        sch.initializeStartProcess(pcbB);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

}
