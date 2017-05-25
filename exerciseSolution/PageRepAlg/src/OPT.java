public class OPT extends TemplateAlgorithm {
 // Implements the optimal strategy (OPT) 
    public OPT(int numFrames, int[] refseq) {
        super(numFrames, refseq);
    }

    public Vote computeNewVote(int pos, int f, int loaded, Vote currentVote) {
        // Update the vote for the frame to be kicked.
        // Compute distance to next request with same page as loaded at pos-1 (OPT).

        // Initialize cdist to a value large than any distance for a loaded page
        // that is requested again. We treat this value as some kind of infinity.
        // If the page loaded is requested eventually at all its distance
        // will be less than infinity.
        // On the other hand, if a loaded page is never requested again the
        // cdist will remain at the value infinity.

        int cdist = refseq.length; // refseq.length is treated as infinity
        for (int k = pos; k < refseq.length; k++) {
            if (refseq[k] == loaded) {
                // Page is requested again.
                // Store a distance that is better (here larger) than the
                // currently stored one.
                cdist = k - pos;
                break;
            }
        }

        if (cdist > currentVote.getMaxrate()) {
            // Page in frame f is the best candidate so far.
            // The value of cdist might still be infinity, which means
            // that the loaded page is never requested again.
            currentVote.setMaxrate(cdist);
            currentVote.kickFrame(f);
        }
        return currentVote;
    }

    // Some example for demonstration purposes
    public static void main(String[] args) {
        OPT alg = new OPT(3, new int[] { 1, 3, 4, 2, 5, 4, 3, 4, 5, 2, 1, 5, 3,
                4, 1 });
        alg.doPaging();
        alg.printASCII();
    }
}
