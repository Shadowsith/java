import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UTestFranzFolien01 {
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
        pA.addSection(new Work(20));
        pA.addSection(new Launch(pB));
        pA.addSection(new Work(10));
        pA.addSection(new Launch(pC));
        pA.addSection(new Work(10));

        pA.addSection(new Wait(50));
        pA.addSection(new Work(30));

        // Program B
        pB.addSection(new Work(90));

        // Program C
        pC.addSection(new Work(30));

    }

    @Test
    public void test_FifoScheduler_01() throws Exception {
        Scheduler sch = new FifoScheduler();
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);

        sch.initializeStartProcess(pcbA);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRScheduler_01() throws Exception {
        Scheduler sch = new RRScheduler(10, 4);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);

        sch.initializeStartProcess(pcbA);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRScheduler_02() throws Exception {
        Scheduler sch = new RRScheduler(20, 2);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);

        sch.initializeStartProcess(pcbA);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRScheduler_03() throws Exception {
        Scheduler sch = new RRScheduler(40, 1);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);

        sch.initializeStartProcess(pcbA);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLsndFifoScheduler_01() throws Exception {
        Scheduler sch = new MLsndFifoScheduler();
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        pcbA.setStaticPriority(9);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(2);

        sch.initializeStartProcess(pcbA);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLsndRRScheduler_01() throws Exception {
        Scheduler sch = new MLsndRRScheduler(10, 4);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        pcbA.setStaticPriority(9);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(2);

        sch.initializeStartProcess(pcbA);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLFsndRRScheduler_01() throws Exception {
        MLFsndRRScheduler sch = new MLFsndRRScheduler(40, 1);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);

        sch.initializeStartProcess(pcbA);
        sch.initializeDynamicPriorities();

        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLFsndRRScheduler_02() throws Exception {
        MLFsndRRScheduler sch = new MLFsndRRScheduler(20, 1);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);

        sch.initializeStartProcess(pcbA);
        sch.initializeDynamicPriorities();

        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }
}
