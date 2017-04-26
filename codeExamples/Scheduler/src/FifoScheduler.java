public class FifoScheduler extends Scheduler {

    public String toString() {
        return this.getClass().getName();
    }

    protected void addToRunQ(PCB pcb) {
        // Set state of process to B
        pcb.setToReady();
        // According to FIFO add at the end
        runq.add(pcb);
        if (explain) {
            System.out.printf("Process %s added to runq\n", pcb.getProgram()
                    .getName());
        }
        // Maintain counter for race conditions
        raceCount++;
    }

    protected PCB scheduleSpecial(PCB activePCB) {
        // Re-schedule last active process
        if (explain) {
            System.out.printf(
                    "Scheduling decision: re-schedule of process %s\n\n",
                    activePCB.getProgramName());
        }
        return activePCB;
    }

}
