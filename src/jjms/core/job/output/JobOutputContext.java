package jjms.core.job.output;

import java.io.IOException;

import jjms.core.job.IJobOutput;

public class JobOutputContext
{
	private final IJobOutput[] mOutputs;
	private final IJobOutput[] mLogs;
	private final IJobOutput[] mErrors;
	
	public JobOutputContext() throws IOException
	{
		// TODO: Add loading of job outputs externally (db, file etc.)
		mOutputs = new IJobOutput[] { new DefaultFileOutput("Testing.out") };
		mLogs = new IJobOutput[] { new DefaultFileOutput("Testing.log") };
		mErrors = new IJobOutput[] { new DefaultFileOutput("Testing.err") };
	}

	public IJobOutput[] getOutputs()
	{
		return mOutputs;
	}

	public IJobOutput[] getLogs()
	{
		return mLogs;
	}

	public IJobOutput[] getErrors()
	{
		return mErrors;
	}
}
