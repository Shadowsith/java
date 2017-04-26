public abstract class RRbased extends Scheduler {
    protected int timeSlice;
    protected int quantum;
    protected boolean refill; // refill quantum if process is activated

    public RRbased(int timeSlice, int quantum) {
        super();
        this.quantum = quantum;
        this.timeSlice = timeSlice;
        this.refill = true;
    }

    public String toString() {
        return this.getClass().getName() + "-T" + timeSlice + "-Q" + quantum
                + (refill ? "-refill" : "-norefill");
    }

    public void setRefill(boolean refill) {
        this.refill = refill;
    }

    @Override
    public void initializeStartProcess(PCB startPcb) {
        super.initializeStartProcess(startPcb);
        // Assumption: elapsed == 0 for start process
        startPcb.setTimeBalance(0, quantum, timeSlice);
    }

    @Override
    protected int nextDueToTimeBalanceSpecial(PCB activePCB, int nextIn) {
        // Check time balance (not the quantum!)
        if (explain) {
            System.out.printf(
                    "Time left in active process %s due to time balance: %d\n",
                    activePCB.getProgramName(), activePCB.getTimeBalance());
        }
        if (activePCB.getTimeBalance() < nextIn) {
            nextIn = activePCB.getTimeBalance();
            if (explain) {
                System.out.printf("New minimum %s due to time balance: %d\n",
                        activePCB.getProgramName(), nextIn);
            }
        }
        return nextIn;
    }

    @Override
    protected void maintainTimeBalanceAndPrio(PCB activePCB, int nextIn) {
        if (activePCB != null) {
            // Maintain timeBalance of active pcb
            activePCB.adjustTimeBalance(nextIn);

            // Check if currently active process was suspended due entering
            // a wait section. If yes, truncate its time balance to next
            // multiple of current quantum
            if (activePCB.isWaitingProcess()) {
                activePCB.truncateTimeBalance();
            }
        }
    }

    @Override
    protected void refillQuantumByPolicy(PCB activePCB) {
        // Set quantum of activated process according to policy
        if (refill) {
            // Policy wants a refill
            activePCB.setTimeBalance(elapsed, quantum, timeSlice);
        } else if (activePCB.getTimeBalance() == 0) {
            // Process is scheduled but has no time left
            // Give it a new time balance
            activePCB.setTimeBalance(elapsed, quantum, timeSlice);
        } else {
            // Process has still time left and policy refill = false
            // Do not refill
        }
    }

    @Override
    protected PCB switchActiveWithQ(PCB oldActivePCB, String msg) {
        // Adjust quantum of switched out process
        if (oldActivePCB != null) {
            oldActivePCB.truncateTimeBalance();
        }
        return super.switchActiveWithQ(oldActivePCB, msg);

    }
}
