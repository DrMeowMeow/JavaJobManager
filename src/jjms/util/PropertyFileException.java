package jjms.util;

/**
 * Exception class to detail an error that occurred whilst handling a Property File.
 * @author jared
 */
public class PropertyFileException extends Exception
{
	private static final long serialVersionUID = -1167571460004097071L;

	/**
	 * Instantiates a new instance of the {@code PropertyFileException} class.
	 * @param message the error message for this exception.
	 */
	public PropertyFileException(String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new instance of the {@code PropertyFileException} class.
	 * @param message the error message for this exception.
	 * @param source the source exception for this file exception.
	 */
	public PropertyFileException(String message, Exception source)
	{
		super(message, source);
	}
}
