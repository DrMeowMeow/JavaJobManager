package jjms.core.job;

import java.util.Properties;

/**
 * Represents the operating State of a given Job, by providing useful information and updates.
 * @author jared
 */
public class JobState
{
	private final Properties mJobProperties;
	private final IJobOutput mOut;
	private final IJobOutput mLog;
	private final IJobOutput mError;
	private final JobProgress mProgress;
	
	private boolean mStopped = false;
	
	/**
	 * Initialises a new instance of the {@code JobState} class.
	 * @param out the {@code IJobOutput} representing this states output channel.
	 * @param log the {@code IJobOutput} representing this states log channel.
	 * @param error the {@code IJobOutput} representing this states error channel.
	 * @param properties the {@code Properties} for the Job that this state represents.
	 */
	public JobState(IJobOutput out, IJobOutput log, IJobOutput error, Properties properties)
	{
		mOut = out;
		if (mOut == null)
		{
			//Default
		}
		
		mLog = log;
		if (mLog == null)
		{
			//Default
		}
		
		mError = error;
		if (mError == null)
		{
			//Default
		}
		
		if (properties == null)
		{
			// Initialise empty properties
			mJobProperties = new Properties();
		}
		else
		{
			mJobProperties = properties;
		}
		
		mProgress = new JobProgress();
	}
	
	/**
	 * Signals that the Job should be stopped.
	 */
	protected synchronized void stop()
	{
		mStopped = true;
	}
	
	/**
	 * Retrieves whether the given Job has been stopped or is stopping.
	 * @return the stopped state of the Job.
	 */
	public synchronized boolean isStopped()
	{
		return mStopped;
	}
	
	/**
	 * Retrieves the {@code JobProgress} for this Job.
	 * @return the current progress of the Job.
	 */
	public synchronized JobProgress getProgress()
	{
		return mProgress;
	}
	
	/**
	 * Retrieves the {@code Properties} for this Job.
	 * @return the properties assigned to this Job.
	 */
	public Properties getProperties()
	{
		return mJobProperties;
	}
	
	/**
	 * Retrieves the standard output for this {@code JobState}.
	 * @return the {@code IJobOutput} representing the standard output channel.
	 */
	public IJobOutput getStdOut()
	{
		return mOut;
	}

	/**
	 * Retrieves the standard log for this {@code JobState}.
	 * @return the {@code IJobOutput} representing the standard log channel.
	 */
	public IJobOutput getStdLog()
	{
		return mLog;
	}

	/**
	 * Retrieves the standard error for this {@code JobState}.
	 * @return the {@code IJobOutput} representing the standard error channel.
	 */
	public IJobOutput getStdErr()
	{
		return mError;
	}
}
