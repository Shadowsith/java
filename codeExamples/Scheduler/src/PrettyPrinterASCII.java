import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class PrettyPrinterASCII {
    private Scheduler sch;
    private int frameLength;
    private PrintWriter out;
    public static final String OUTDIR = "output";

    public PrettyPrinterASCII(Scheduler sch, int frameLength, String filename)
            throws FileNotFoundException {

        this.sch = sch;
        this.frameLength = frameLength;

        // Create directory for storing files
        new File(OUTDIR).mkdir();
        // Create output stream
        this.out = new PrintWriter(new FileOutputStream(OUTDIR
                + java.io.File.separatorChar + filename));
    }

    public void close() {
        this.out.close();
    }

    public void printPCBs() {
        List<PCB> pcbs = sch.getPcbs();
        Map<Integer, List<String>> histRunq = sch.getHistRunq();

        this.out.printf("\n");
        this.out.printf("Strategy: %s\n", sch);
        this.out.printf("Start process: %s\n", sch.getStartPCB()
                .getProgramName());

        this.out.printf("\n");
        // Print workplans; use pcb.toString()
        for (PCB pcb : pcbs) {
            this.out.println(pcb.toString());
        }

        if (!pcbs.isEmpty()) {
            // Print time line
            this.out.printf("\n");
            printTimeLine(pcbs.get(0));
        }

        this.out.printf("\n");
        // Print life lines
        for (PCB pcb : pcbs) {
            printLifeLine(pcb);
        }

        if (!pcbs.isEmpty()) {
            // Print runqs line
            this.out.printf("\n");
            printRunqLine(pcbs.get(0), histRunq);
        }

        if (!pcbs.isEmpty()) {
            // Print time line
            this.out.printf("\n");
            printTimeLine(pcbs.get(0));
        }

        this.out.printf("\n");
        // Print state lines
        for (PCB pcb : pcbs) {
            printStateLine(pcb);
        }

        // Check if schedule used dynamic priorities
        // Just check the first pcb; all others will be the same
        if (!pcbs.isEmpty() && pcbs.get(0).hasDynamicPriority()) {
            this.out.printf("\n");
            // Print state lines
            for (PCB pcb : pcbs) {
                if (pcb.hasDynamicPriority()) {
                    printDynPrioLine(pcb);
                }
            }

            // Print table for dynamic quantum and time slices
            // Since the schedule contains dynamic priorities
            // we assume that the current Scheduler object is a
            // MLFsndRRScheduler
            printDynamicQuantumTimesliceTable(PCB.MAX_PRIO,
                    ((MLFsndRRScheduler) sch).getInitialQuantum());
        }
    }

    public void printLifeLine(PCB pcb) {
        List<SchedPart> schedule = pcb.getSchedule();
        this.out.printf("%s: ", pcb.getProgramName());
        for (SchedPart part : schedule) {
            int duration = part.getDuration();
            while (duration > 0) {
                char c = ' ';
                switch (part.getState()) {
                case R:
                    c = ' ';
                    break;
                case B:
                    c = '-';
                    break;
                case W:
                    c = '.';
                    break;
                case L:
                    c = 'L';
                    break;
                }
                this.out.printf("%c", c);
                duration -= frameLength;
            }
            if (part.getDuration() == 0) {
                // Final part
                this.out.printf("|");
            }
        }
        this.out.println();
    }

    public void printRunqLine(PCB pcb, Map<Integer, List<String>> histRunq) {
        // Get realtime of last entry in pcb
        List<SchedPart> schedule = pcb.getSchedule();
        int maxRealtime = schedule.get(schedule.size() - 1).getElapsed();

        // Compute maximal size of queues in histRunq
        // We need the size for a proper layout
        int max = 0;
        for (Integer rt : histRunq.keySet()) {
            int size = histRunq.get(rt).size();
            if (size > max) {
                max = size;
            }
        }

        // Print all lines of queues
        for (int l = 0; l < max; l++) {
            this.out.printf("%s", l == 0 ? "Q: " : "   ");

            // For each time frame with respect to our granularity
            // check if we stored a runq for that frame.
            // If yes, print the l-th entry of the runq
            //
            for (int rt = 0; rt <= maxRealtime; rt += frameLength) {
                if (histRunq.containsKey(rt)) {
                    List<String> q = histRunq.get(rt);
                    if (q.size() > l) {
                        // We have got an entry for that frame and
                        // position in the queue
                        this.out.printf("%s", histRunq.get(rt).get(l));
                    } else {
                        // No entry for the frame
                        this.out.printf(" ");
                    }
                } else {
                    this.out.printf(" ");
                }
            }
            this.out.println();
        }
    }

    public void printTimeLine(PCB pcb) {
        List<SchedPart> schedule = pcb.getSchedule();
        int maxRealtime = schedule.get(schedule.size() - 1).getElapsed();

        for (int b = 1 + (int) Math.log10(maxRealtime); b > 0; b--) {
            this.out.printf("   ");
            int realtime = 0;
            for (SchedPart part : schedule) {
                int duration = part.getDuration();
                while (duration > 0) {
                    // Compute b-th digit of base 10 representation
                    // of realtime value
                    int digit = (realtime % (int) Math.pow(10, b))
                            / (int) Math.pow(10, b - 1);
                    // Print if realtime is a multiple of 10
                    if (realtime % 10 == 0) {
                        this.out.printf("%d", digit);
                    } else {
                        this.out.printf(" ");
                    }
                    duration -= frameLength;
                    realtime += frameLength;
                }
                if (part.getDuration() == 0) {
                    // Final part
                    int digit = (realtime % (int) Math.pow(10, b))
                            / (int) Math.pow(10, b - 1);
                    this.out.printf("%d", digit);
                }
            }
            this.out.println();
        }
    }

    public void printStateLine(PCB pcb) {
        List<SchedPart> schedule = pcb.getSchedule();
        this.out.printf("%s: ", pcb.getProgramName());
        for (SchedPart part : schedule) {
            int duration = part.getDuration();
            boolean first = true;
            while (duration > 0) {
                // String c = part.getState().name();
                // We do not use above line since we like
                // to guarantee that c is only one character.
                // The enum constant might yield a longer name
                char c = ' ';
                switch (part.getState()) {
                case R:
                    c = 'R';
                    break;
                case B:
                    c = 'B';
                    break;
                case W:
                    c = 'W';
                    break;
                case L:
                    c = 'L';
                    break;
                }
                if (first) {
                    this.out.printf("%c", c);
                    first = false;
                } else {
                    this.out.printf(" ");
                }
                duration -= frameLength;
            }
            if (part.getDuration() == 0) {
                // Final part
                this.out.printf("R");
            }
        }
        this.out.println();
    }

    public void printDynPrioLine(PCB pcb) {
        List<SchedPart> schedule = pcb.getSchedule();
        this.out.printf("%s: ", pcb.getProgramName());
        for (SchedPart part : schedule) {
            int duration = part.getDuration();
            boolean first = true;
            while (duration > 0) {
                if (first && part.hasDynamicPriority()) {
                    int dynprio = part.getDynamicPriority();
                    this.out.printf("%d", dynprio);
                    first = false;
                } else {
                    this.out.printf(" ");
                }
                duration -= frameLength;
            }
            if (part.getDuration() == 0) {
                // Final part
                this.out.printf(" ");
            }
        }
        this.out.println();
    }

    public void printDynamicQuantumTimesliceTable(int prio, int quantum) {
        this.out.printf("\n");
        this.out.printf("Table for quantum depending on dynamic priority\n");
        while (prio >= 0) {
            this.out.printf("Prio=%d --> quantum=%4d\n", prio, quantum);
            prio--;
            quantum *= 2;
        }
    }

    public void printStatsPerPcb(PCB pcb, int start, int end) {
        this.out.printf("%s: ", pcb.getProgramName());
        this.out.printf("WtRdy:%3d", pcb.getWaitPlusReadyTime());
        this.out.printf(" Svc:%3d", pcb.getServiceTime());
        this.out.printf(" TrnArd:%3d", pcb.getTurnaroundTime());
        this.out.printf(" RspSum:%3d", pcb.getResponseTimes().getSum());
        this.out.printf(" RspCnt:%2d", pcb.getResponseTimes().getCount());
        this.out.printf(" mRsp:%6.2f", pcb.getResponseTimes().getMean());
        this.out.printf(" Thput:%d", pcb.getThroughput(start, end));
        this.out.println();
    }

    public void printStats(int start, int end) {
        List<PCB> pcbs = sch.getPcbs();
        this.out.printf("Statistics:\n");
        for (PCB pcb : pcbs) {
            printStatsPerPcb(pcb, start, end);
        }

        // Compute mean response time of overall system
        int count = 0;
        int sumResponseTime = 0;
        for (PCB pcb : pcbs) {
            sumResponseTime += pcb.getResponseTimes().getSum();
            count += pcb.getResponseTimes().getCount();
        }
        this.out.printf("Overall mean response time: %6.2f\n",
                (double) sumResponseTime / count);

        // Compute throughput of overall system
        count = 0;
        int sumThroughput = 0;
        for (PCB pcb : pcbs) {
            sumThroughput += pcb.getThroughput(start, end);
            count++;
        }
        this.out.printf("Overall throughput in [%d,%d[:%5.0f%%\n", start, end,
                (double) sumThroughput / count * 100);

        // Compute CPU usage of overall system (single processor)
        int sumBusy = 0;
        for (PCB pcb : pcbs) {
            sumBusy += pcb.getUsageInterval(start, end);
            // this.out.printf("Usage: %s:%d\n",pcb.getProgramName(),pcb.getUsageInterval(start,end));
        }
        this.out.printf("Overall CPU usage in [%d,%d[:%5.0f%%\n", start, end,
                (double) sumBusy / (end - start) * 100);

    }

}
