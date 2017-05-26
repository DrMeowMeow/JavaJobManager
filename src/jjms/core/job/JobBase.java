package jjms.core.job;

/**
 * The abstract {@code JobBase} class that all jobs must extend.
 * @author jared
 */
public abstract class JobBase
{
	/**
	 * The overridden run method for the job.
	 * @param state the state this job will use to run.
	 */
	public abstract void run(JobState state);
}
