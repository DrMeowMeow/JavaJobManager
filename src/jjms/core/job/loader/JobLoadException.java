package jjms.core.job.loader;

/**
 * Exception class to detail an error that occurred whilst loading a Job.
 * @author jared
 */
public class JobLoadException extends Exception
{
	private static final long serialVersionUID = -1125497098706175328L;

	/**
	 * Instantiates a new instance of the {@code JobLoadException} class.
	 * @param message the error message for this exception.
	 */
	public JobLoadException(String message)
	{
		super(message);
	}
	
	/**
	 * Instantiates a new instance of the {@code JobLoadException} class.
	 * @param message the error message for this exception.
	 * @param source the original source for this exception.
	 */
	public JobLoadException(String message, Exception source)
	{
		super(message, source);
	}
}
