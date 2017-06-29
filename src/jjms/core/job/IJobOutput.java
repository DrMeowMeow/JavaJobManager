package jjms.core.job;

import java.io.IOException;

/**
 * Represents the text base output for a Job.
 * @author jared
 */
public interface IJobOutput extends AutoCloseable
{
	/**
	 * Add a new string entry to the Job output.
	 * @param output the string entry to add.
	 */
	public void add(String output);
	
	/**
	 * {@inheritDoc}
	 */
	public void close() throws IOException;
	
	/**
	 * Determines if this Job output has been closed.
	 * @return the closed state of this {@code IJobOutput}.
	 */
	public boolean isClosed();
}
