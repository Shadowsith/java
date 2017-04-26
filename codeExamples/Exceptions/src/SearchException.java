public class SearchException extends Exception {
    public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable t) {
        super(msg, t);
    }
}
