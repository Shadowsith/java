public class Wait extends Section {
    public Wait(int duration) {
        super(duration);
    }

    @Override
    public String toString() {
        return "Wait(" + duration + ")";
    }

    // Override discriminator
    public boolean isWaitSection() {
        return true;
    }

}
