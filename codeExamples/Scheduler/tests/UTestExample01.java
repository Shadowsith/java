import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UTestExample01 {
    private Program pA = new Program("A");
    private Program pB = new Program("B");
    private Program pC = new Program("C");
    private Program pD = new Program("D");

    private PCB pcbA = new PCB(pA);
    private PCB pcbB = new PCB(pB);
    private PCB pcbC = new PCB(pC);
    private PCB pcbD = new PCB(pD);

    @Before
    public void initialize() {
        // Define work plan
        // Program A
        pA.addSection(new Work(30));
        pA.addSection(new Wait(35));
        pA.addSection(new Work(10));

        // Program B
        pB.addSection(new Work(20));
        pB.addSection(new Wait(10));
        pB.addSection(new Work(20));

        // Program C
        pC.addSection(new Work(25));
        pC.addSection(new Launch(pA));
        pC.addSection(new Work(10));
        pC.addSection(new Launch(pB));
        pC.addSection(new Work(5));

        // Program D
        pD.addSection(new Work(15));
        pD.addSection(new Launch(pC));
        pD.addSection(new Work(15));
        pD.addSection(new Wait(45));
        pD.addSection(new Work(20));

    }

    @Test
    public void test_FifoScheduler_01() throws Exception {
        Scheduler sch = new FifoScheduler();
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRScheduler_01() throws Exception {
        Scheduler sch = new RRScheduler(5, 4);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRScheduler_02() throws Exception {
        Scheduler sch = new RRScheduler(10, 2);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRScheduler_03() throws Exception {
        Scheduler sch = new RRScheduler(20, 1);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRSchedulerNoRefill_01() throws Exception {
        RRScheduler sch = new RRScheduler(5, 4);
        // sch.setExplain(true);

        sch.setRefill(false);
        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRSchedulerNoRefill_02() throws Exception {
        RRScheduler sch = new RRScheduler(10, 2);
        // sch.setExplain(true);

        sch.setRefill(false);
        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_RRSchedulerNoRefill_03() throws Exception {
        RRScheduler sch = new RRScheduler(20, 1);
        // sch.setExplain(true);

        sch.setRefill(false);
        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
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
        pcbA.setStaticPriority(2);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(9);
        sch.addPCB(pcbD);
        pcbD.setStaticPriority(9);

        sch.initializeStartProcess(pcbD);
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
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(9);
        sch.addPCB(pcbD);
        pcbD.setStaticPriority(9);

        sch.initializeStartProcess(pcbD);
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
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(9);
        sch.addPCB(pcbD);
        pcbD.setStaticPriority(9);

        sch.initializeStartProcess(pcbD);
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
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(9);
        sch.addPCB(pcbD);
        pcbD.setStaticPriority(9);

        sch.initializeStartProcess(pcbD);
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

        sch.setRefill(false);
        sch.addPCB(pcbA);
        pcbA.setStaticPriority(2);
        sch.addPCB(pcbB);
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(9);
        sch.addPCB(pcbD);
        pcbD.setStaticPriority(9);

        sch.initializeStartProcess(pcbD);
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
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(9);
        sch.addPCB(pcbD);
        pcbD.setStaticPriority(9);

        sch.initializeStartProcess(pcbD);
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
        pcbB.setStaticPriority(5);
        sch.addPCB(pcbC);
        pcbC.setStaticPriority(9);
        sch.addPCB(pcbD);
        pcbD.setStaticPriority(9);

        sch.initializeStartProcess(pcbD);
        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }

    @Test
    public void test_MLFsndRRScheduler_01() throws Exception {
        MLFsndRRScheduler sch = new MLFsndRRScheduler(10, 1);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
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
        MLFsndRRScheduler sch = new MLFsndRRScheduler(40, 1);
        // sch.setExplain(true);

        sch.addPCB(pcbA);
        sch.addPCB(pcbB);
        sch.addPCB(pcbC);
        sch.addPCB(pcbD);

        sch.initializeStartProcess(pcbD);
        sch.initializeDynamicPriorities();

        sch.run();

        String id = this.getClass().getName() + sch.toString();
        PrettyPrinterASCII pp = new PrettyPrinterASCII(sch, 5, id);
        pp.printStats(60, 160);
        pp.printPCBs();
        pp.close();
    }
}
