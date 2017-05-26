package jjms.core.job;

import java.net.*;
import java.util.Properties;

import jjms.core.job.loader.JobLoadException;

/**
 * Provides the context in which a job can be loaded and run.
 * @author jared
 */
public class JobContext
{
	private final URL mURL;
	private final String mClassName;
	private final Properties mPropeties;
	private URLClassLoader mClassLoader;
	
	private JobState mState;
	private Class mJobClass;
	
	public JobContext(URLConnection jarUrl, String className, Properties properties) throws JobLoadException
	{
		mURL = jarUrl.getURL();
		mClassName = className;
		mPropeties = properties;
		
		try
		{
			mClassLoader = new URLClassLoader(new URL[] { mURL }, this.getClass().getClassLoader());
		}
		catch (NullPointerException ex)
		{
			throw new JobLoadException("Invalid JAR Url supplied.", ex);
		}
		catch (SecurityException ex)
		{
			throw new JobLoadException("Invalid permissions to create class loader.", ex);
		}
	}
	
	public URL getUrl()
	{
		return mURL;
	}
	
	public JobState getState()
	{
		return mState;
	}
	
	public void setState(JobState state) throws IllegalStateException
	{
		if (mState != null)
		{
			throw new IllegalStateException("JobState is already assigned.");
		}
		
		mState = state;
	}
	
	public ClassLoader getClassLoader()
	{
		return mClassLoader;
	}

	public Properties getProperties()
	{
		return mPropeties;
	}
	
	public String getClassName()
	{
		return mClassName;
	}
	
	public Class getJobClass()
	{
		return mJobClass;
	}
	
	public void setJobClass(Class jobClass)
	{
		mJobClass = jobClass;
	}
}
