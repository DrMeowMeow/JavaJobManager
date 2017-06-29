package jjms.core.job;

import java.net.*;
import java.util.Properties;

import jjms.core.job.loader.JobLoadException;

/**
 * Provides the context in which a Job can be loaded and run.
 * @author jared
 */
public class JobContext
{
	private final URL mURL;
	private final String mClassName;
	private final Properties mPropeties;
	private final URLClassLoader mClassLoader;
	
	private JobState mState;
	private Class<? extends JobBase> mJobClass;
	
	/**
	 * Initialises a new instance of the {@code JobContext} class.
	 * @param jarUrl the URL to the jar that contains the Job.
	 * @param className the name of the Job class to run.
	 * @param properties Job specific properties that are available during runtime.
	 * @throws JobLoadException thrown if an exception occurs during Job loading.
	 */
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
	
	/**
	 * Retrieves the jar URL for this context.
	 * @return the Uniform Resource Locator for this contexts jar.
	 */
	public URL getUrl()
	{
		return mURL;
	}
	
	/**
	 * Retrieves the {@code JobState} for this context.
	 * @return the Job State for this context.
	 */
	public JobState getState()
	{
		return mState;
	}
	
	/**
	 * Sets the {@code JobState} for this context.
	 * @param state the Job State to be assigned to this context.
	 * @throws IllegalStateException thrown if the context already has a Job State assigned.
	 */
	public void setState(JobState state) throws IllegalStateException
	{
		if (mState != null)
		{
			throw new IllegalStateException("JobState is already assigned.");
		}
		
		mState = state;
	}
	
	/**
	 * Retrieves the {@code ClassLoader} for this context.
	 * @return the class loader for this context.
	 */
	public ClassLoader getClassLoader()
	{
		return mClassLoader;
	}

	/**
	 * Retrieves the {@code Properties} for this context.
	 * @return the properties for this context.
	 */
	public Properties getProperties()
	{
		return mPropeties;
	}
	
	/**
	 * Retrieves the Class Name for the Job that this context can run.
	 * @return the class name for this context.
	 */
	public String getClassName()
	{
		return mClassName;
	}
	
	/**
	 * Retrieves the Class for this context.
	 * @return the class for this context.
	 */
	public Class<? extends JobBase> getJobClass()
	{
		return mJobClass;
	}
	
	/**
	 * Sets the {@code Class} for this context.
	 * @param jobClass the class to be assigned to this context.
	 */
	public void setJobClass(Class<? extends JobBase> jobClass)
	{
		mJobClass = jobClass;
	}
}
