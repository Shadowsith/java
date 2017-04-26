import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Scheduler {
    // Safeguard against infinite loops
    public static final int MAX_STEPS = 100;
    private int steps = 0;

    protected boolean explain = false; // Print debug info

    // The list of PCBs (all processes; inactive ones have state 'R')
    protected List<PCB> pcbs = new LinkedList<PCB>();
    // The list of processes ready to run; state 'B'
    protected List<PCB> runq = new LinkedList<PCB>();
    // The history of runq
    protected Map<Integer, List<String>> histRunq = new TreeMap<Integer, List<String>>();

    // Elapsed time due to running the schedule
    protected int elapsed = 0;

    // Counter for race conditions
    protected int raceCount;

    // The starting PCB
    protected PCB startPCB;

    /* *************************************************
     * Abstract methods: must be implemented by derived classes
     */

    // This abstract method will contain the logic special to the strategy
    protected abstract PCB scheduleSpecial(PCB activePCB);

    // Depending on the strategy add process to the runq
    protected abstract void addToRunQ(PCB pcb);

    /* *************************************************
     * END abstract methods
     */

    // Setters und Getters

    // Set debug mode
    public void setExplain(boolean explain) {
        this.explain = explain;
        if (explain) {
            for (PCB pcb : pcbs) {
                pcb.setExplain(explain);
            }
        }
    }

    public PCB getStartPCB() {
        if (startPCB == null) {
            throw new RuntimeException("Start process not set");
        }
        return startPCB;
    }

    public List<PCB> getPcbs() {
        return pcbs;
    }

    public Map<Integer, List<String>> getHistRunq() {
        return histRunq;
    }

    protected PCB getActivePCB() {
        int found = 0;
        PCB activePCB = null;
        for (PCB pcb : pcbs) {
            if (pcb.isActiveProcess()) {
                activePCB = pcb;
                found++;
            }
        }
        if (found > 1) {
            throw new RuntimeException("More than one process in state L");
        }
        return activePCB;
    }

    // Add a PCB to be scheduled
    public void addPCB(PCB pcb) {
        // Check that there is no other PCB that shares the same Program object!
        // PCBs that contain a Program object of same name are allowed
        if (pcb != null) {
            for (PCB other : pcbs) {
                if (other.getProgram() == pcb.getProgram()) {
                    throw new RuntimeException(
                            "PCBs cannot share identical programs: "
                                    + pcb.getProgramName());
                }
            }
            pcbs.add(pcb);
        } else {
            throw new RuntimeException("Adding a null PCB is not allowed");
        }
    }

    // Set the initial PCB to start with
    // Strategies that depend on time slices will override and specialize this
    // method
    public void initializeStartProcess(PCB startPcb) {
        for (PCB pcb : pcbs) {
            // Do some checks
            if (pcb.getProgram().getWorkplan().isEmpty()) {
                throw new RuntimeException("Initial program is empty: "
                        + pcb.getProgramName());
            }
            if (!pcb.getProgram().getSection(0).isWorkSection()) {
                throw new RuntimeException("Program " + pcb.getProgramName()
                        + " does not start with work section");
            }

            if (pcb == startPcb) {
                this.startPCB = pcb;
                pcb.setToExecute();
                return;
            }
        }
        throw new RuntimeException("Unable to find start PCB "
                + startPcb.getProgramName());
    }

    // Main loop of scheduler
    public void run() {
        if (getActivePCB() == null) {
            throw new RuntimeException(
                    "Schedule not initialized; use method initializeStartProcess() of some PCB");
        }

        // Do the schedule in a loop as long as there are processes to run
        do {
            // Let the active process perform one step
            // A step is finished if one of the following situations occurs
            // Whatever may come first
            // - if the process completes a work launch*
            // - if the process exhausts its time balance (timebalance == 0)
            // - if some waiting process becomes ready (wait time finished)

            step();
            // use safeguard to avoid infinite loop
            steps++;

            // Now, call the scheduler
        } while (schedule() && steps <= MAX_STEPS);
    }

    // General decision procedure for the scheduler; who is next?
    protected boolean schedule() {
        // Generic part of schedule decision common to all strategies
        // We use a template pattern here
        //
        // Check if there are still unterminated processes.
        //
        // Return true, if there are still unterminated processes.
        // Return false otherwise
        //
        boolean result;

        // By the way, select the next process to run if there is any.
        // Either the most recently active or one from the runq.
        //
        // Note: it is possible that all processes are in state W.
        // Then, we cannot select an active process but the game is not
        // over yet; return true as well

        if (explain) {
            System.out.printf("\nStart of schedule()\n");
        }

        PCB activePCB = getActivePCB();

        if (activePCB == null && runq.isEmpty()) {
            // No process is in state L and none in state B (runq empty)

            // Are there any waiting processes?
            if (haveWaitingPCB()) {
                // At least one process is waiting
                // We have no active process but the game goes on
                if (explain) {
                    System.out
                            .printf("Scheduling decision: no ready processes -> IDLE\n\n");
                }
                result = true;
            } else {
                // Assertion: all processes are in state R
                if (explain) {
                    System.out
                            .printf("Scheduling decision: all processes in state R -> END\n\n");
                }
                reportFinalStatus();
                // Store final schedule
                for (PCB pcb : pcbs) {
                    pcb.storeSchedule(elapsed, 0);
                }
                result = false;
            }
        } else {
            // There will be a next running process
            result = true;

            // Select one according to strategy
            if (activePCB == null && !runq.isEmpty()) {
                // There is no active process
                // Take next one from runq
                activePCB = switchActiveWithQ(activePCB, "");
                // Set quantum of activated process according to refill policy
                //
                // Note: for strategies not based on time slices this might call
                // the
                // dummy implementation of abstract class Schedule
                refillQuantumByPolicy(activePCB);
            } else if (activePCB != null && runq.isEmpty()) {
                // We cannot take a process from the empty runq
                // Re-schedule last active process
                // Set quantum of re-scheduled process according to refill
                // policy
                //
                // Note: for strategies not based on time slices this might call
                // the
                // dummy implementation of abstract class Schedule
                refillQuantumByPolicy(activePCB);
                if (explain) {
                    System.out
                            .printf("Scheduling decision: re-schedule of process %s\n\n",
                                    activePCB.getProgramName());
                }
            } else if (activePCB != null && !runq.isEmpty()) {
                activePCB = scheduleSpecial(activePCB);
            } else {
                throw new RuntimeException("Error in case logic");
            }

        }
        return result;
    }

    // Compute one step in the schedule
    protected void step() {
        // Do one step in the schedule
        // Generic method common to all strategies
        // We use a template pattern

        // The active process is already selected via schedule() if there is any
        reportStatus();

        int nextIn = -1; // Minimal time ticks to next event
        // Init to impossible value -1

        // Compute minimal time ticks to next event
        PCB activePCB = getActivePCB(); // the one with the L
        // There may be none! e.g. none L but some W
        if (activePCB != null) {
            // Compute time to end of current section of active process
            nextIn = activePCB.getTimeLeftInCurrentSection();
            if (explain) {
                System.out
                        .printf("Time to end of current section in active process %s: %d\n",
                                activePCB.getProgramName(), nextIn);
            }

            // Note: Strategies that do not depend on time slices
            // will call the dummy method of class Schedule
            nextIn = nextDueToTimeBalanceSpecial(activePCB, nextIn);
        }

        // Compute next wakeup of some other process
        // There may be several minimal wakeups
        // We can cope with case where nextIn is still -1 (no active process)
        //
        for (PCB pcb : pcbs) {
            if (pcb.isWaitingProcess()) {
                if (nextIn < 0 || pcb.getTimeLeftInCurrentSection() < nextIn) {
                    nextIn = pcb.getTimeLeftInCurrentSection();
                    if (explain)
                        System.out.printf(
                                "Next event in waiting process %s: %d\n",
                                pcb.getProgramName(), nextIn);
                }
            }
        }

        // Here we know the time until the next event
        // At that time we need to write an intermediate state and re-schedule

        if (explain)
            System.out.printf("Computed time to next event: %d\n", nextIn);

        // Assertion: the minimal wakeup time cannot be 0
        // activePCB: we do not allow for work(0) phases
        // a waiting PCB: we do not allow for wait(0) phases and
        // a PCB that finished its waiting phase in the last step
        // would not yield isWaiting() == true in the current step.
        // It would be already in the next section with state 'B' (Ready)
        if (nextIn <= 0) {
            throw new RuntimeException("Minimal wakeup time <= 0");
        }

        if (explain)
            System.out.printf("\n------------- Realtime: %d -------------\n",
                    elapsed + nextIn);

        // Initialize counter for race conditions
        raceCount = 0;

        // Store planned schedule of this step for all PCBs
        for (PCB pcb : pcbs) {
            pcb.storeSchedule(elapsed, nextIn);
        }

        // Let the time go by for all PCBs
        for (PCB pcb : pcbs) {
            boolean sectionFinished;

            // Let the time go by for pcb
            sectionFinished = pcb.letTimeGoBy(elapsed, nextIn, pcb);

            maintainStateAfterProgress(pcb, sectionFinished);
        }

        // Note: strategies that do not depend on time values
        // and prios will call the dummy method of class Scheduer
        maintainTimeBalanceAndPrio(activePCB, nextIn);

        // Add time passed during step
        elapsed += nextIn;

        // Report race conditions
        if (raceCount > 1) {
            throw new RuntimeException("Potential race condition; raceCount="
                    + raceCount);
        }

        if (explain)
            System.out.printf("Step finished at realtime %d\n", elapsed);
    }

    /*********************
     * Some dummy methods Introduced for template pattern
     */

    protected void refillQuantumByPolicy(PCB activePCB) {
        // Part of template pattern
        // Empty dummy method for strategies that are not based on time slices
        // Since class Scheduler is abstract there is no problem with the LSP
    }

    protected int nextDueToTimeBalanceSpecial(PCB activePCB, int nextIn) {
        // Part of template pattern
        // Empty dummy method for strategies that are not based on time slices
        // Strategies that depend on time slices will override this method
        // Since class Scheduler is abstract there is no problem with the LSP
        return nextIn;
    }

    protected void maintainTimeBalanceAndPrio(PCB activePCB, int nextIn) {
        // Part of template pattern
        // Empty dummy method for strategies that are not based on time slices
        // or priorities
        // Strategies that depend on time slices and/or dynamic
        // prios will override this method
    }

    /*********************
     * END dummy methods
     */

    protected PCB switchActiveWithQ(PCB oldActivePCB, String msg) {
        // Suspend current process if there is any
        // Take new process from runq
        // Assertion: runq not empty

        PCB newActivePCB;
        newActivePCB = runq.remove(0);

        if (explain) {
            System.out.printf("Scheduling decision: %s\n", msg);
            System.out.printf("\ttaking process %s from the runq\n",
                    newActivePCB.getProgramName());
        }

        if (oldActivePCB != null) {
            // Suspend active process
            addToRunQ(oldActivePCB);
            if (explain) {
                System.out.printf("\tsuspending process %s\n\n",
                        oldActivePCB.getProgramName());
            }
        } else {
            if (explain) {
                System.out.printf("\n");
            }
        }
        // Set state of selected process
        newActivePCB.setToExecute();
        return newActivePCB;
    }

    protected void maintainStateAfterProgress(PCB pcb, boolean sectionFinished) {
        // Compute state of process pcb after progress in time
        // Process may be terminated, suspended or may get ready

        if (sectionFinished) {
            if (explain)
                System.out.printf("\tsection finished for program %s\n",
                        pcb.getProgramName());
            // Change state of process
            switch (pcb.getState()) {
            case R:
            case B:
                // nothing to do
                break;
            case L:
                // The running process finished its section
                // Must have been a Work section!
                if (pcb.isInLastSection()) {
                    pcb.setToIdle(); // Terminate process
                } else {
                    // Execute all launches that may follow and progress
                    // Progess is made inside pcb.getLaunchesAndProgress()
                    for (Program toStart : pcb.getLaunchesAndProgress()) {
                        // Launch process toStart
                        PCB pcbToStart = searchPCB(toStart);
                        // Check whether the pcb is already launched
                        if (pcbToStart.getState().compareTo(SchedState.R) != 0) {
                            throw new RuntimeException("PCB "
                                    + pcbToStart.getProgramName()
                                    + " is already launched");
                        }
                        addToRunQ(pcbToStart);
                    }
                    // Check if there is more to do past the Launch sections
                    // If not, process is already terminated
                    switch (pcb.getState()) {
                    case R:
                        // Nothing to do
                        break;
                    case B:
                    case W:
                        // Error in Logic
                        throw new RuntimeException(
                                "Unexpected state of process; cannot be in B or W after launches");
                    case L:
                        // Process is already positioned to next section
                        // Possible are sections Work and Wait
                        // Suspend if it is a Wait
                        if (pcb.getCurSection().isWaitSection()) {
                            pcb.setToWait();
                        } else if (pcb.getCurSection().isWorkSection()) {
                            // Progess already done inside
                            // pcb.getLaunchesAndProgress()
                        } else {
                            throw new RuntimeException(
                                    "Unexpected section in workplan");
                        }
                        break;
                    }
                }
                break;
            case W:
                // The running process finished its section
                // Must have been a Wait section!
                if (pcb.isInLastSection()) {
                    throw new RuntimeException(
                            "Illegal program: ends with a Wait section");
                }

                pcb.makeProgress();

                if (!pcb.getCurSection().isWorkSection()) {
                    throw new RuntimeException(
                            "Illegal program: Wait section must be followed by Work section");
                }
                addToRunQ(pcb); // add pcb to the runq
                break;
            }

        } else {
            // Section was not finished for this pcb
            // pcb is inside a Wait or Work
            // Nothing to do
        }

    }

    protected boolean haveWaitingPCB() {
        boolean found = false;
        for (PCB pcb : pcbs) {
            if (pcb.isWaitingProcess()) {
                found = true;
            }
        }
        return found;
    }

    protected void storeRunq() {
        List<String> rq = new LinkedList<String>();
        for (PCB pcb : runq) {
            rq.add(pcb.getProgramName());
        }
        histRunq.put(elapsed, rq);
    }

    protected void reportStatus() {
        storeRunq();
        if (explain)
            System.out.printf("Start of step() at realtime %d\n", elapsed);

        if (explain)
            System.out.printf("Process states are:\n");
        for (PCB pcb : pcbs) {
            if (explain)
                System.out.printf("%s:%s\n", pcb.getProgramName(),
                        pcb.getState());
        }

        if (explain)
            System.out.printf("Run Queue:");
        if (runq.isEmpty()) {
            if (explain)
                System.out.printf(" is empty\n");
        } else {
            for (int i = 0; i < runq.size(); i++) {
                if (explain)
                    System.out.printf(" %s", runq.get(i).getProgramName());
            }
            if (explain)
                System.out.println();
        }

    }

    protected void reportFinalStatus() {
        storeRunq();
        if (explain)
            System.out.printf("\nFinal state at realtime %d\n", elapsed);

        if (explain)
            System.out.printf("Process states are:\n");
        for (PCB pcb : pcbs) {
            if (explain)
                System.out.printf("%s:%s\n", pcb.getProgramName(),
                        pcb.getState());
        }

        if (explain)
            System.out.printf("Run Queue:");
        if (runq.isEmpty()) {
            if (explain)
                System.out.printf(" is empty\n");
        } else {
            for (int i = 0; i < runq.size(); i++) {
                if (explain)
                    System.out.printf(" %s", runq.get(i).getProgramName());
            }
            if (explain)
                System.out.println();
        }

    }

    private PCB searchPCB(Program p) {
        // Note:
        // The correctness of this search depends on the fact that
        // there are no two PCBs that share the same Object of class Program
        // If you use different Program Objects with a common program name
        // this is no problem!
        // We really like to compare the object references via == !
        for (PCB pcb : pcbs) {
            if (pcb.getProgram() == p) {
                return pcb;
            }
        }
        throw new RuntimeException("Unable to find PCB of program " + p);
    }

}
