public class FIFO extends TemplateAlgorithm {
    // Implements the strategy First In First Out (FIFO)
    public FIFO(int numFrames, int[] refseq) {
        super(numFrames, refseq);
    }

    public Vote computeNewVote(int pos, int f, int loaded, Vote currentVote) {
        // Update the vote for the frame to be kicked.
        // Compute the page with the maximal age (FIFO).

        // Initialize cage to a value that is less than the age of any loaded page.
        // The age of a loaded page is at least 1.
        // Find the frame that holds a page of maximal age.
 
        int cage = 0;
        for (int k = pos-1; k >= 0; k--) {
            if (frames[f][k] == loaded) {
                // Still the same page; increment cage
                cage++;
            } else {
                // Page at position k is different from loaded one
                break;
            }
         }

        if (cage > currentVote.getMaxrate()) {
            // Page in frame f is the best candidate so far.
            currentVote.setMaxrate(cage);
            currentVote.kickFrame(f);
        }
        return currentVote;
    }

    // Some example for demonstration purposes
    public static void main(String[] args) {
        FIFO alg = new FIFO(3,
                new int[] {1, 3, 4, 2, 5, 4, 3, 4, 5, 2, 1, 5, 3, 4, 1 });
        alg.doPaging();
        alg.printASCII();
    }
}
