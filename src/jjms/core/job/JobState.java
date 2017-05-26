package jjms.core.job;

import java.util.Properties;

public class JobState
{
	private final Properties mJobProperties;
	private final IJobOutput mOut;
	private final IJobOutput mLog;
	private final IJobOutput mError;
	private final JobProgress mProgress;
	
	private boolean mStopped = false;
	
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
			mJobProperties = new Properties();
		}
		else
		{
			mJobProperties = properties;
		}
		
		mProgress = new JobProgress();
	}
	
	protected synchronized void stop()
	{
		mStopped = true;
	}
	
	public synchronized boolean isStopped()
	{
		return mStopped;
	}
	
	public synchronized JobProgress getProgress()
	{
		return mProgress;
	}
	
	public Properties getProperties()
	{
		return mJobProperties;
	}
	
	public IJobOutput getStdOut()
	{
		return mOut;
	}
	
	public IJobOutput getStdLog()
	{
		return mLog;
	}
	
	public IJobOutput getStdErr()
	{
		return mError;
	}
}
