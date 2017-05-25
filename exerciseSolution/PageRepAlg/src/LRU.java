public class LRU extends TemplateAlgorithm {
    // Implements the strategy Least Recently Used (LRU)
    public LRU(int numFrames, int[] refseq) {
        super(numFrames, refseq);
    }

    public Vote computeNewVote(int pos, int f, int loaded, Vote currentVote) {
        // Update the vote for the frame from where we are going to kick the loaded page.
        // Compute distance to the last request with same page as loaded at pos-1 (LRU).

        // Initialize cdist to a value less than any distance to a last usage of a loaded page.
        // Since a page loaded has been requested at least once in the past the
        // distance to this request is at least 0 (last page requested at pos-1).
 
        int cdist = 0;
        for (int k = pos-1; k >= 0; k--) {
            if (refseq[k] == loaded) {
                // Page was requested at position k.
                // Store a distance that is better than the currently stored.
                cdist = pos - 1 -k;
                break;
            }
         }

        if (cdist > currentVote.getMaxrate()) {
            // Page in frame f is the best candidate so far.
            currentVote.setMaxrate(cdist);
            currentVote.kickFrame(f);
        }
        return currentVote;
    }

    // Some example for demonstration purposes
    public static void main(String[] args) {
        LRU alg = new LRU(3, new int[] { 1, 3, 4, 2, 5, 4, 3, 4, 5, 2, 1, 5, 3,
                4, 1 });
        alg.doPaging();
        alg.printASCII();
    }
}
