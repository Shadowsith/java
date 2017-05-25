// A data class to be used for information exchange between
// the generic page replacement algorithm (template)
// and the method computeNewVote() of the specific replacement strategy

public class Vote {
    private boolean replaced;
    private int frameToBeKicked;
    private int maxrate;
    
    public Vote(){
        replaced = false;
        frameToBeKicked = -1;
        maxrate = 0;
    }
    
    public void kickFrame(int frame){
        replaced = true;
        frameToBeKicked = frame;
    }
    
    public void noKick(){
        replaced = false;
        frameToBeKicked = -1;
    }

    public int getMaxrate() {
        return maxrate;
    }

    public void setMaxrate(int maxrate) {
        this.maxrate = maxrate;
    }

    public boolean isReplaced() {
        return replaced;
    }

    public int getFrameToBeKicked() {
        return frameToBeKicked;
    } 
}
