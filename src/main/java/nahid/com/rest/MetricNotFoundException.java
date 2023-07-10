package nahid.com.rest;

public class MetricNotFoundException extends RuntimeException {
	public MetricNotFoundException(String message) {
		super(message);
	}
}
