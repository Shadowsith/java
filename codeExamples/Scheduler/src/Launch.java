public class Launch extends Section {
    private Program prog;

    public Launch(Program prog) {
        super(0);
        this.prog = prog;
    }

    @Override
    public String toString() {
        return "Launch(" + prog.getName() + ")";
    }

    // Override getter
    public Program getProg() {
        return prog;
    }

    // Override discriminator
    public boolean isLaunchSection() {
        return true;
    }

}
