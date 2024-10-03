public class QueueOverflowException extends Exception {
    private static final long serialVersionUID = 1L;

	public QueueOverflowException() {
        super("Queue overflow");
    }
}