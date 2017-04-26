import java.util.LinkedList;
import java.util.List;

public class PCB {
    private boolean explain; // Print debug info
    private Program prog; // Program to run
    private SchedState state; // Process state

    public static final int MAX_PRIO = 9; // Maximal process priority
    public static final int MIN_PRIO = 0; // Maximal process priority

    // The program counter
    private int curSection; // Index to current section in work plan of program
    private int timeInSection; // Time spent in current section

    private int quantum; // Number of time slices to spend
    private int timeSlice; // Size of a time slice in clock ticks
    private int timeBalance; // Time left in clock ticks

    private static final int UNUSED_PRIO = -1; // Mark prio as unused
    private int staticPriority;
    private int dynamicPriority;

    // The schedule already executed (past)
    private List<SchedPart> schedule;

    public PCB(Program prog) {
        this.explain = false;
        this.prog = prog;
        this.state = SchedState.R;
        this.curSection = 0;
        this.timeInSection = 0;
        this.schedule = new LinkedList<SchedPart>();

        this.quantum = 0;
        this.timeSlice = 0;
        this.timeBalance = 0;
        this.staticPriority = UNUSED_PRIO;
        this.dynamicPriority = UNUSED_PRIO;
    }

    public String toString() {
        if (staticPriority == UNUSED_PRIO) {
            return prog.getName() + ": " + prog.toString() + ";";
        } else {
            return prog.getName() + "(" + staticPriority + ")" + ": "
                    + prog.toString() + ";";
        }
    }

    // Setters
    public void setStaticPriority(int prio) {
        if (prio < MIN_PRIO || prio > MAX_PRIO) {
            throw new RuntimeException("Invalid process priority: choose "
                    + MIN_PRIO + "<= prio <= " + MAX_PRIO);
        }

        this.staticPriority = prio;
    }

    public void setExplain(boolean explain) {
        this.explain = explain;
    }

    public void setDynamicPriority(int prio) {
        if (prio < MIN_PRIO || prio > MAX_PRIO) {
            throw new RuntimeException("Invalid process priority: choose "
                    + MIN_PRIO + "<= prio <= " + MAX_PRIO);
        }
        this.dynamicPriority = prio;
    }

    // Methods for managing the time balance
    public void setTimeBalance(int realtime, int quantum, int timeSlice) {
        // Store quantum and timeSlice and compute timeBalance, which
        // is the time span missing to the end of the time slice
        // that is quantum time slices away
        this.quantum = quantum;
        this.timeSlice = timeSlice;
        timeBalance = (quantum + realtime / timeSlice) * timeSlice - realtime;
    }

    public void adjustTimeBalance(int time) {
        timeBalance -= time;
    }

    public void truncateTimeBalance() {
        // We adjust the time balance downwards to the next
        // multiple of timeSlice
        // Thus a partially used quantum is discarded
        timeBalance = (timeBalance / timeSlice) * timeSlice;
    }

    public void adjustPrioAndQuantum() {
        // For MLFsndRR: prio is decremeted and quantum is doubled
        if (dynamicPriority > 0) {
            dynamicPriority--;
            quantum *= 2;
        }
    }

    // Getters

    public int getQuantum() {
        return this.quantum;
    }

    public int getTimeBalance() {
        return timeBalance;
    }

    public int getTimeSlice() {
        return timeSlice;
    }

    public SchedState getState() {
        return this.state;
    }

    public int getStaticPriority() {
        return staticPriority;
    }

    public Program getProgram() {
        return prog;
    }

    public int getDynamicPriority() {
        return dynamicPriority;
    }

    public String getProgramName() {
        return prog.getName();
    }

    public List<SchedPart> getSchedule() {
        return schedule;
    }

    public Section getCurSection() {
        if (this.state.compareTo(SchedState.R) == 0) {
            throw new RuntimeException("Process is in state R");
        } else {
            return prog.getSection(curSection);
        }
    }

    public int getTimeInCurSection() {
        if (this.state.compareTo(SchedState.R) == 0) {
            throw new RuntimeException("Process is in state R");
        } else {
            return timeInSection;
        }
    }

    public int getTimeLeftInCurrentSection() {
        if (this.state.compareTo(SchedState.R) == 0) {
            throw new RuntimeException("Process is in state R");
        } else {
            return prog.getSectionDuration(curSection) - timeInSection;
        }
    }

    public boolean hasDynamicPriority() {
        return dynamicPriority != UNUSED_PRIO;
    }

    public boolean isInLastSection() {
        return prog.isLastSection(curSection);
    }

    // Methods for queries about the state of process
    public boolean isWaitingProcess() {
        return this.state.compareTo(SchedState.W) == 0;
    }

    public boolean isActiveProcess() {
        return this.state.compareTo(SchedState.L) == 0;
    }

    // Methods for changing the state of a process

    public void setToIdle() {
        // Switch to idle state
        if (explain)
            System.out.printf("Process %s set to state R\n", this.getProgram()
                    .getName());

        timeInSection = 0;
        this.state = SchedState.R;
    }

    public void setToWait() {
        if (explain)
            System.out.printf("Process %s set to state W\n", this.getProgram()
                    .getName());
        // Switch to wait state
        this.state = SchedState.W;
    }

    public void setToReady() {
        if (explain)
            System.out.printf("Process %s set to state B\n", this.getProgram()
                    .getName());
        // Switch to ready state
        this.state = SchedState.B;
    }

    public void setToExecute() {
        if (explain)
            System.out.printf("Process %s set to state L\n", this.getProgram()
                    .getName());
        // Switch to running state
        this.state = SchedState.L;
    }

    // Methods for making progress in the workplan

    // Advance in workplan of program
    public void makeProgress() {
        if (prog.isLastSection(curSection)) {
            throw new RuntimeException(
                    "Trying to execute program past to its end");
        }
        curSection++;
        timeInSection = 0;

        if (explain)
            System.out.printf("Process %s switched to section %d:%s\n",
                    this.getProgramName(), curSection,
                    prog.getSection(curSection));
    }

    public List<Program> getLaunchesAndProgress() {
        LinkedList<Program> ll = new LinkedList<Program>();

        // If it is the last one, return empty list
        if (prog.isLastSection(curSection)) {
            return ll;
        }
        // Otherwise: loop and collect all programs of Launch sections
        // Stop at first non-Launch section or last section of program
        // We are in section after last work
        Section s;
        do {
            makeProgress(); // Make progress in workplan
            s = prog.getSection(curSection);
            if (s.isLaunchSection()) {
                ll.add(s.getProg());
            }
        } while (s.isLaunchSection() && !prog.isLastSection(curSection));

        // If we are still positioned on a Launch section this is the end
        // of the workplan.
        if (s.isLaunchSection()) {
            setToIdle();
        }
        // Return all Launches
        return ll;
    }

    // Time management during execution
    public void storeSchedule(int elapsed, int time) {
        // Write schedule part of time passed for the records
        // depending in whether we use dynamic priorities or not
        if (dynamicPriority == UNUSED_PRIO) {
            this.schedule.add(new SchedPart(state, elapsed, time));
        } else if (state.equals(SchedState.R)) {
            this.schedule.add(new SchedPart(state, elapsed, time, UNUSED_PRIO));
        } else {
            this.schedule.add(new SchedPart(state, elapsed, time,
                    dynamicPriority));
        }
    }

    public boolean letTimeGoBy(int elapsed, int time, PCB pcb) {
        boolean sectionFinished = false;

        // Let the time go by in section
        switch (this.state) {
        case R:
        case B:
            // do nothing
            break;
        case W:
        case L:
            // Make progress in time
            timeInSection += time;
            if (getTimeLeftInCurrentSection() == 0) {
                sectionFinished = true;
            }
            break;
        }

        if (explain)
            System.out.printf("\tTime spent in process %s section %d:%d\n",
                    pcb.getProgramName(), curSection, timeInSection);

        return sectionFinished;
    }

    // Methods for computing the statistics
    public int getWaitPlusReadyTime() {
        int sum = 0;
        for (SchedPart part : schedule) {
            switch (part.getState()) {
            case R:
            case L:
                break;
            case W:
            case B:
                sum += part.getDuration();
                break;
            }
        }
        return sum;
    }

    public int getServiceTime() {
        int sum = 0;
        for (SchedPart part : schedule) {
            switch (part.getState()) {
            case R:
            case W:
            case B:
                break;
            case L:
                sum += part.getDuration();
                break;
            }
        }
        return sum;
    }

    public int getUsageInterval(int start, int end) {
        int sum = 0;
        for (SchedPart part : schedule) {
            switch (part.getState()) {
            case R:
            case W:
            case B:
                break;
            case L: // compute interval intersection!
                int el = part.getElapsed();
                int elpt = part.getElapsed() + part.getDuration();
                if (start <= el && elpt <= end) {
                    sum += part.getDuration();
                } else if (el <= start && end <= elpt) {
                    sum += end - start;
                } else if (start <= el && el <= end && end <= elpt) {
                    sum += end - el;
                } else if (el <= start && start <= elpt && elpt < end) {
                    sum += elpt - start;
                } else if (elpt < start || end < el) {
                    // no intersection
                } else
                    throw new RuntimeException("Error in intervall logic: "
                            + "start=" + start + " end=" + end + " elapsed="
                            + el + " elpt=" + elpt);
                break;
            }
        }
        return sum;
    }

    public int getTurnaroundTime() {
        int sum = 0;
        for (SchedPart part : schedule) {
            switch (part.getState()) {
            case R:
                break;
            case W:
            case B:
            case L:
                sum += part.getDuration();
                break;
            }
        }
        return sum;
    }

    public ResponseTimes getResponseTimes() {
        int count = 0;
        int sum = 0;
        double mean = 0.0;
        boolean inReadySection = false;
        for (SchedPart part : schedule) {
            switch (part.getState()) {
            case R:
            case W:
            case L:
                inReadySection = false;
                break;
            case B:
                if (!inReadySection) {
                    inReadySection = true;
                    count++;
                }
                sum += part.getDuration();
                break;
            }
        }
        if (count > 0) {
            mean = (double) sum / count;
        }
        return new ResponseTimes(count, sum, mean);
    }

    public int getThroughput(int start, int end) {
        // Compute whether process terminated within start/end interval
        // Return 0 for no, 1 for true
        // This is most convenient for computing the throughput of several
        // processes
        boolean wasActive = false;
        for (SchedPart part : schedule) {
            if (wasActive && start <= part.getElapsed()
                    && part.getElapsed() <= end
                    && part.getState().compareTo(SchedState.R) == 0) {
                return 1;
            }
            if (part.getState().compareTo(SchedState.L) == 0) {
                wasActive = true;
            }
        }
        return 0;
    }

}
