package jjms.core.job.runtime;

import java.io.IOException;

import jjms.core.job.JobBase;
import jjms.core.job.JobContext;
import jjms.core.job.JobState;
import jjms.core.job.loader.JobLoadException;
import jjms.core.job.loader.JobLoader;
import jjms.core.job.output.JobOutputContext;
import jjms.core.job.output.LinkedJobOutput;

public final class JobRunner
{
	/**
	 * Private constructor to prevent instantiation.
	 */
	private JobRunner()
	{
	}
	
	public static void run(JobOutputContext outputCtx, JobContext ctx) throws JobLoadException, JobRuntimeException
	{
		try
		{
			attachOutputContext(outputCtx, ctx);
			
			// Create the job instance
			JobBase jobInstance = JobLoader.load(ctx);
			
			// Run the job
			jobInstance.run(ctx.getState());
		}
		catch (JobLoadException ex)
		{
			throw ex;
		}
		catch (Exception ex)
		{
			throw new JobRuntimeException("An unexpected error occured during Job runtime.", ex);
		}
		finally
		{
			// Detach outputs
			detatchOutputContext(ctx.getState());
		}
	}
	
	private static void attachOutputContext(JobOutputContext outputCtx, JobContext ctx)
	{
		LinkedJobOutput out = new LinkedJobOutput(outputCtx.getOutputs());
		LinkedJobOutput log = new LinkedJobOutput(outputCtx.getLogs());
		LinkedJobOutput error = new LinkedJobOutput(outputCtx.getErrors());
		
		ctx.setState(new JobState(out, log, error, ctx.getProperties()));
	}
	
	private static void detatchOutputContext(JobState state)
	{
		try
		{
			state.getStdOut().close();
		}
		catch (IOException e)
		{
			// TODO: Logging?
		}
		
		try
		{
			state.getStdLog().close();
		}
		catch (IOException e)
		{
			// TODO: Logging?
		}
		
		try
		{
			state.getStdErr().close();
		}
		catch (IOException e)
		{
			// TODO: Logging?
		}
	}
}
