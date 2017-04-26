public class SchedPart {
    private SchedState state;
    private int elapsed;
    private int duration;
    private int dynamicPriority;
    private static final int UNUSED_PRIO = -1;

    public SchedPart(SchedState state, int elapsed, int duration) {
        this.state = state;
        this.elapsed = elapsed;
        this.duration = duration;
        this.dynamicPriority = UNUSED_PRIO;
    }

    public SchedPart(SchedState state, int elapsed, int duration,
            int dynamicPriority) {
        this.state = state;
        this.elapsed = elapsed;
        this.duration = duration;
        this.dynamicPriority = dynamicPriority;
    }

    public int getDuration() {
        return duration;
    }

    public SchedState getState() {
        return state;
    }

    public int getElapsed() {
        return elapsed;
    }

    public int getDynamicPriority() {
        return dynamicPriority;
    }

    public boolean hasDynamicPriority() {
        return dynamicPriority != UNUSED_PRIO;
    }

    @Override
    public String toString() {
        return state.toString() + "(" + duration + ")";
    }
}
