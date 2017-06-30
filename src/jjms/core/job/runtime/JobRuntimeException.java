package jjms.core.job.runtime;

/**
 * Exception class to detail an error that occurred whilst running a Job.
 * @author jared
 */
public class JobRuntimeException extends Exception
{
	private static final long serialVersionUID = -4312431289525706318L;
	
	/**
	 * Instantiates a new instance of the {@code JobRuntimeException} class.
	 * @param message the error message for this exception.
	 */
	public JobRuntimeException(String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new instance of the {@code JobRuntimeException} class.
	 * @param message the error message for this exception.
	 * @param source the source exception for this runtime exception.
	 */
	public JobRuntimeException(String message, Exception source)
	{
		super(message, source);
	}
}
