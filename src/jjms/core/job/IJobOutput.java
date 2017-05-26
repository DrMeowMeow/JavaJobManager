package jjms.core.job;

import java.io.IOException;

/**
 * Represents the text base output for a job.
 * @author jared
 */
public interface IJobOutput extends AutoCloseable
{
	/**
	 * Add a new string entry to the job output.
	 * @param output the string entry to add.
	 */
	public void add(String output);
	
	/**
	 * {@inheritDoc}
	 */
	public void close() throws IOException;
	
	/**
	 * Determines if this job output has been closed.
	 * @return
	 */
	public boolean isClosed();
}
