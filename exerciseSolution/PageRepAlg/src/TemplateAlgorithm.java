// An abstract class for page replacement algorithms.
// We use the design pattern 'Template'

abstract public class TemplateAlgorithm {
    private int numFrames; // number of frames to use
    private boolean[] faults; // a list of page faults
    private int repls; // number of times a page is replaced
    
    // Attributes accessible to derived classes that implement a certain strategy
    protected int[][] frames; // a list of numFrames frames
    protected int[] refseq; // the reference sequence of pages that are accessed

    // Ctor
    public TemplateAlgorithm(int numFrames, int[] refseq) {
        this.numFrames = numFrames;
        this.refseq = refseq;

        // Initialize other attributes
        repls = 0;
        frames = new int[numFrames][this.refseq.length];
        faults = new boolean[this.refseq.length];
    }

    // Getters
    public int getNumFrames() {
        return numFrames;
    }

    public int[] getRefseq() {
        return refseq;
    }

    public boolean[] getFaults() {
        return faults;
    }

    public int getRepls() {
        return repls;
    }

    public int[][] getFrames() {
        return frames;
    }

    // Generic paging algorithm
    public void doPaging() {
        for (int r = 0; r < refseq.length; r++) { // for all requests
            // Rate all frames.
            // The resulting Vote object tells us whether a page in some
            // Frame will be replaced and in which frame the change takes place.
            Vote vote = rate(r);

            // Do we need to perform a page replacement
            faults[r] = vote.isReplaced();
            // Append one step in frame list
            for (int f = 0; f < numFrames; f++) {
                if (f == vote.getFrameToBeKicked()) {
                    if (r > 0 && frames[f][r - 1] != 0) {
                        // This is a real page replacement
                        // and not some trivial initial load.
                        repls++;
                    }
                    // Load the new page into frame
                    frames[f][r] = refseq[r];
                } else if (r == 0) {
                    // Initialize empty frame with 0 (mark as unused)
                    frames[f][r] = 0;
                } else {
                    // Np page replacement in frame f
                    // Replicate the already loaded page
                    frames[f][r] = frames[f][r - 1];
                }
            }
        }
    }

    public Vote rate(int r) {
        // Rate all pages loaded in frames according to strategy and decide
        // which one will be replaced.
        // Return a Vote object to the caller.
        
        // Initialization of vote is done in ctor Vote()
        Vote currentVote = new Vote(); 

        for (int f = 0; f < numFrames; f++) {
            if (r == 0) {
                // First page request in sequence; kick the current frame
                currentVote.kickFrame(f);
                break;
            } else if (frames[f][r - 1] == 0) {
                // Frame is still unused
                currentVote.kickFrame(f);
                break;
            } else {
                // Assertion: all frames are defined up to position r-1
                // What page is loaded currently at r-1 ?
                int loaded = frames[f][r - 1];

                if (refseq[r] == loaded) {
                    // Requested page is in frame f
                    // No need to load the page; no frame is kicked
                    currentVote.noKick();
                    break;
                }

                // Since the requested page is not loaded, some frame needs to
                // be kicked.
                // Call the template method updateVote() for the current frame
                // f. The method will update the vote according to the specific
                // strategy.
                currentVote = computeNewVote(r, f, loaded, currentVote);
            }
        }
        return currentVote;
    }

    // The abstract method that predicts which frame needs
    // to be loaded with the currently requested page.
    // The methods needs to be implemented in derived classe
    // Here, we make use of the design pattern 'Template'
    public abstract Vote computeNewVote(int pos, int frame, int loaded,
            Vote currentVote);

    // A method to dump the frame list along with additional information about
    // page faults and replacements.
    public void printASCII() {
        // Print an ASCII representation of the frame list
        System.out.printf("Name: %s\n", this.getClass().getName());
        System.out.printf("Requests:");
        for (int r = 0; r < refseq.length; r++) {
            System.out.printf("%2d ", refseq[r]);
        }
        System.out.printf("\n");

        for (int f = 0; f < frames.length; f++) {
            System.out.printf("Frame %2d:", f + 1);
            for (int i = 0; i < frames[f].length; i++) {
                System.out.printf("%2d ", frames[f][i]);
            }
            System.out.printf("\n");
        }

        System.out.printf("Faults  :");
        int count = 0;
        for (int i = 0; i < faults.length; i++) {
            if (!faults[i]) {
                System.out.printf("   ");
            } else {
                count++;
                System.out.printf(" P ");
            }
        }
        System.out.printf("\n");
        System.out.printf("Faults #: %3d     Replacements: %d\n", count, repls);
    }
}
