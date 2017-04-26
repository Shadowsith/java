public class MLFsndRRScheduler extends RRbased {

    public MLFsndRRScheduler(int timeSlice, int initialQuantum) {
        super(timeSlice, initialQuantum);
    }

    public int getInitialQuantum() {
        return quantum;
    }

    // Called from user classes (here UNIT tests)
    public void initializeDynamicPriorities() {
        for (PCB pcb : pcbs) {
            pcb.setDynamicPriority(PCB.MAX_PRIO);
        }
    }

    public void addToRunQ(PCB pcb) {
        // Set state of process to B
        pcb.setToReady();

        // ML strategy: add process according to its dynamic priority
        int i = 0;
        int size = runq.size();
        int prio = pcb.getDynamicPriority();
        boolean done = false;

        for (i = 0; i < size; i++) {
            if (prio > runq.get(i).getDynamicPriority()) {
                runq.add(i, pcb);
                done = true;
                break;
            }
        }

        if (!done) {
            // We did not yet add
            runq.add(pcb);
        }

        if (explain) {
            System.out.printf("Process %s added to runq at position %d\n",
                    pcb.getProgramName(), i);
        }
        // Maintain counter for race conditions
        // There may be a race condition if we inserted with top priority
        if (prio == runq.get(0).getStaticPriority()) {
            if (i == 0) {
                // We are the first one with top priority in the queue
                raceCount = 1;
            } else {
                raceCount++;
            }
        }
    }

    protected PCB scheduleSpecial(PCB activePCB) {
        // Currently active process is in competition with those of the
        // runq
        if (runq.get(0).getDynamicPriority() > activePCB.getDynamicPriority()) {
            // There is a PCB in runq with higher prio; take that one
            activePCB = switchActiveWithQ(activePCB, "preemption");
            // Set quantum of activated process according to policy
            refillQuantumByPolicy(activePCB);
        } else if (runq.get(0).getDynamicPriority() < activePCB
                .getDynamicPriority()) {
            // All PCBs in runq have less prio
            // Keep the last active one running
            // publicSince it has the highest priority set its
            // quantum according to the policy.
            refillQuantumByPolicy(activePCB);
            if (explain) {
                System.out.printf(
                        "Scheduling decision: re-schedule of process %s\n\n",
                        activePCB.getProgramName());
            }
        } else {
            // There are PCBs in the runq with prio equal to the running
            // ones.
            // Apply secondary strategy RR
            // Check timeBalance of currently active process
            if (activePCB.getTimeBalance() > 0) {
                // Re-schedule last active process
                // Do not refill its quantum since there are competitors
                if (explain) {
                    System.out
                            .printf("Scheduling decision: re-schedule of process %s\n\n",
                                    activePCB.getProgramName());
                }
            } else {
                // Quantum exhausted; select process from runq
                if (explain) {
                    System.out.printf(
                            "Quantum of active process %s exhausted\n\n",
                            activePCB.getProgramName());
                }
                activePCB = switchActiveWithQ(activePCB, "preemption");
                // Set quantum of activated process according to policy
                refillQuantumByPolicy(activePCB);
            }
        }
        return activePCB;
    }

    @Override
    protected void maintainTimeBalanceAndPrio(PCB activePCB, int nextIn) {
        if (activePCB != null) {
            // Maintain timeBalance of active pcb
            activePCB.adjustTimeBalance(nextIn);

            // Check if currently active process was suspended due entering
            // a wait section. If yes, truncate its time balance to next
            // multiple of curent quantum and adjust dynamic priority and next
            // quantum
            if (activePCB.isWaitingProcess()) {
                activePCB.truncateTimeBalance();
                // Special for MLFsndRR strategy
                activePCB.adjustPrioAndQuantum();
            }
        }
    }

    @Override
    protected void refillQuantumByPolicy(PCB activePCB) {
        if (activePCB.getTimeSlice() == 0) {
            // Special for MLFsndRR strategy
            // PCB is active for the first time
            // Set its initial time and quantum
            activePCB.setTimeBalance(elapsed, quantum, timeSlice);
        } else {
            // Set quantum of activated process according to refill policy
            if (refill) {
                // Policy wants a refill
                // Special for MLFsndRR strategy
                // Use current quantum and timeslice of PCB for refilling the
                // time balance
                activePCB.setTimeBalance(elapsed, activePCB.getQuantum(),
                        timeSlice);
            } else if (activePCB.getTimeBalance() == 0) {
                // Process is scheduled but has no time left
                // Give it a new time balance
                activePCB.setTimeBalance(elapsed, activePCB.getQuantum(),
                        timeSlice);
            } else {
                // Process has still time left and policy refill = false
                // Do not refill
            }
        }
    }

    @Override
    protected PCB switchActiveWithQ(PCB oldActivePCB, String msg) {
        // Adjust time balance of switched out process
        // Adjust dynamic priority and next quantum
        if (oldActivePCB != null) {
            oldActivePCB.truncateTimeBalance();
            // Special for MLFsndRR strategy
            oldActivePCB.adjustPrioAndQuantum();
        }
        return super.switchActiveWithQ(oldActivePCB, msg);
    }
}
