public class RRScheduler extends RRbased {
    public RRScheduler(int timeSlice, int quantum) {
        super(timeSlice, quantum);
    }

    public void addToRunQ(PCB pcb) {
        // Set state of process to B
        pcb.setToReady();
        // RR strategy: add process at the end
        runq.add(pcb);
        if (explain) {
            System.out.printf("Process %s added to runq\n", pcb.getProgram()
                    .getName());
        }
        // Maintain counter for race conditions
        raceCount++;
    }

    protected PCB scheduleSpecial(PCB activePCB) {
        // Currently active process is in competition with those of the
        // runq
        // Apply strategy RR
        // Check timeBalance of currently active process
        if (activePCB.getTimeBalance() > 0) {
            // Re-schedule last active process
            // Do not refill its quantum since there are competitors
            if (explain) {
                System.out.printf(
                        "Scheduling decision: re-schedule of process %s\n\n",
                        activePCB.getProgramName());
            }
        } else {
            // Quantum exhausted; select process from runq
            if (explain) {
                System.out.printf("Quantum of active process %s exhausted\n\n",
                        activePCB.getProgramName());
            }
            activePCB = switchActiveWithQ(activePCB, "preemption");
            // Set quantum of activated process according to policy
            refillQuantumByPolicy(activePCB);
        }
        return activePCB;
    }
}
