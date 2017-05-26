package jjms.core.job.output;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

import jjms.core.job.IJobOutput;

public class LinkedJobOutput implements IJobOutput
{
	private final IJobOutput[] mLinkedOutputs;
	private final Queue<String> mOutput;
	
	private boolean mIsClosed = false;
	
	public LinkedJobOutput(IJobOutput[] outputs)
	{
		mLinkedOutputs = outputs;
		mOutput = new PriorityQueue<String>();
	}
	
	public LinkedJobOutput(IJobOutput output)
	{
		this(new IJobOutput[] { output });
	}
	
	@Override
	public void add(String output)
	{
		mOutput.add(output);
		for (IJobOutput out : mLinkedOutputs)
		{
			out.add(output);
		}
	}

	@Override
	public void close()
	{
		for (IJobOutput out : mLinkedOutputs)
		{
			try
			{
				out.close();
			}
			catch (IOException e)
			{
				// TODO: Logging?
			}
		}
		
		mIsClosed = true;
	}

	@Override
	public boolean isClosed()
	{
		return mIsClosed;
	}

}
