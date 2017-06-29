package jjms.core.job.output;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

import jjms.core.job.IJobOutput;

/**
 * Queue based {@code IJobOutput} linker. Allows multiple {@code IJobOuput}s to be collected together and used as a single context.
 * @author jared
 */
public class LinkedJobOutput implements IJobOutput
{
	private final IJobOutput[] mLinkedOutputs;
	private final Queue<String> mOutput;
	
	private boolean mIsClosed = false;
	
	/**
	 * Initialises a new instance of the {@code LinkedJobOutput}.
	 * @param outputs array of {@code IJobOutput}s which will be linked into a single context.
	 */
	public LinkedJobOutput(IJobOutput[] outputs)
	{
		mLinkedOutputs = outputs;
		mOutput = new PriorityQueue<String>();
	}
	
	/**
	 * Initialises a new instance of the {@code LinkedJobOuput}.
	 * @param output {@code IJobOutput} which will be linked which will be linked into a single context.
	 */
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
