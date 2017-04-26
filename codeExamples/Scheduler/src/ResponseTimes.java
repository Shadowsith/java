// Data class

public class ResponseTimes {
    private int count; // Count of contiguous sections in state B (Ready)
    private int sum; // Sum of times in state B
    private double mean; // quotient sum / count

    public ResponseTimes(int count, int sum, double mean) {
        super();
        this.count = count;
        this.sum = sum;
        this.mean = mean;
    }

    public int getCount() {
        return count;
    }

    public int getSum() {
        return sum;
    }

    public double getMean() {
        return mean;
    }

}
