public class Work extends Section {
    public Work(int duration) {
        super(duration);
    }

    @Override
    public String toString() {
        return "Work(" + duration + ")";
    }

    // Override discriminator
    public boolean isWorkSection() {
        return true;
    }

}
