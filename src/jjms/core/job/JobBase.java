package jjms.core.job;

/**
 * The abstract {@code JobBase} class that all Jobs must extend.
 * @author jared
 */
public abstract class JobBase
{
	/**
	 * The overridden run method for the Job.
	 * @param state the state this Job will use to run.
	 */
	public abstract void run(JobState state);
}
