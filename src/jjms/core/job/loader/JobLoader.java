package jjms.core.job.loader;

import jjms.core.job.JobBase;
import jjms.core.job.JobContext;

/**
 * Provides the ability to load a Job.
 * @author jared
 */
public final class JobLoader
{
	/**
	 * Private constructor to prevent instantiation.
	 */
	private JobLoader()
	{
	}
	
	/**
	 * Loads a new instance of a Job given the context.
	 * @param ctx the context in which this Job will be loaded.
	 * @return the new instance of the Job.
	 * @throws JobLoadException thrown if an error occurs whilst loading the Job.
	 */
	public static JobBase load(JobContext ctx) throws JobLoadException
	{
		if (ctx == null)
		{
			throw new JobLoadException("Job Context not initialized.");
		}
		
		try
		{
			// Get the job class from the class loader
			Class<? extends JobBase> jobClass = (Class<? extends JobBase>) Class.forName(ctx.getClassName(), true, ctx.getClassLoader());
			
			ctx.setJobClass(jobClass);
			return (JobBase) jobClass.newInstance();
		}
		catch (ClassCastException ex)
		{
			throw new JobLoadException(String.format("Invalid job class %s.", ctx.getClassName()));
		}
		catch (ClassNotFoundException ex)
		{
			throw new JobLoadException(String.format("Class %s cannot be found in JAR %s.", ctx.getClassName(), ctx.getUrl()), ex);
		}
		catch (InstantiationException ex)
		{
			throw new JobLoadException(String.format("Failed to instantiate class %s in JAR %s.", ctx.getClassName(), ctx.getUrl()), ex);
		}
		catch (IllegalAccessException ex)
		{
			throw new JobLoadException(String.format("Invalid permission required to instantiate class %s in JAR %s.", ctx.getClassName(), ctx.getUrl()), ex);
		}
	}
}
