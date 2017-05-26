package jjms.core.job.loader;

import jjms.core.job.JobBase;
import jjms.core.job.JobContext;

/**
 * Static class to load a job.
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
	 * Loads a new instance of a job given the context.
	 * @param ctx the context in which this job will be loaded.
	 * @return the new instance of the job.
	 * @throws JobLoadException thrown if an error occurs whilst loading the job.
	 */
	public static JobBase load(JobContext ctx) throws JobLoadException
	{
		if (ctx == null)
		{
			throw new JobLoadException("Job Context not initialized.");
		}
		
		try
		{
			Class jobClass = Class.forName(ctx.getClassName(), true, ctx.getClassLoader());
			if (jobClass == null || !JobBase.class.isAssignableFrom(jobClass))
			{
				throw new JobLoadException(String.format("Invalid job class %s.", ctx.getClassName()));
			}
			
			ctx.setJobClass(jobClass);
			return (JobBase) jobClass.newInstance();
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
