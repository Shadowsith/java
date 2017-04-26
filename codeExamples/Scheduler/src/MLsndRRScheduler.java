public class MLsndRRScheduler extends RRbased {
    public MLsndRRScheduler(int timeSlice, int quantum) {
        super(timeSlice, quantum);
    }

    protected void addToRunQ(PCB pcb) {
        // Set state of process to B
        pcb.setToReady();

        // ML strategy: add process according to its static priority
        int i = 0;
        int size = runq.size();
        int prio = pcb.getStaticPriority();
        boolean done = false;

        for (i = 0; i < size; i++) {
            if (prio > runq.get(i).getStaticPriority()) {
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
        if (runq.get(0).getStaticPriority() > activePCB.getStaticPriority()) {
            // There is a PCB in runq with higher prio; take that one
            activePCB = switchActiveWithQ(activePCB, "preemption");
            // Set quantum of activated process according to policy
            refillQuantumByPolicy(activePCB);
        } else if (runq.get(0).getStaticPriority() < activePCB
                .getStaticPriority()) {
            // All PCBs in runq have less prio
            // Keep the last active one running
            // Since it has the highest priority set its
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

}
