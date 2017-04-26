public abstract class Section {
    protected int duration;

    public Section(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Implement default getter
    // Since this class is abstract there will be no problem with LSP
    public Program getProg() {
        return null;
    }

    // Discriminators
    // Implement default version.
    // Since this class is abstract there will be no problem with LSP
    public boolean isWorkSection() {
        return false;
    }

    public boolean isWaitSection() {
        return false;
    }

    public boolean isLaunchSection() {
        return false;
    }

}
