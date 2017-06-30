package jjms.core.job.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jjms.core.job.IJobOutput;

/**
 * Describes the context in which the outputs for a Job will be routed.
 * @author jared
 */
public class JobOutputContext
{
	private final IJobOutput[] mOutputs;
	private final IJobOutput[] mLogs;
	private final IJobOutput[] mErrors;
	
	/**
	 * Initialises a new instance of the {@code JobOutputContext} class.
	 * @throws IOException thrown when an I/O Exception occurs during initialisation.
	 */
	public JobOutputContext() throws IOException
	{
		// TODO: Add loading of job outputs externally (db, file etc.)
		mOutputs = new IJobOutput[] { new DefaultFileOutput("Testing.out") };
		mLogs = new IJobOutput[] { new DefaultFileOutput("Testing.log") };
		mErrors = new IJobOutput[] { new DefaultFileOutput("Testing.err") };
	}

	/**
	 * Retrieve the standard outputs for this {@code JobOutputContext}.
	 * @return the array of {@code IJobOutput} representing the standard output channels.
	 */
	public IJobOutput[] getOutputs()
	{
		return mOutputs;
	}

	/**
	 * Retrieve the standard logs for this {@code JobOutputContext}.
	 * @return the array of {@code IJobOutput} representing the standard log channels.
	 */
	public IJobOutput[] getLogs()
	{
		return mLogs;
	}

	/**
	 * Retrieve the standard errors for this {@code JobOutputContext}.
	 * @return the array of {@code IJobOutput} representing the standard error channels.
	 */
	public IJobOutput[] getErrors()
	{
		return mErrors;
	}
}

/**
 * Internal file output placeholder class for testing.
 * @author jared
 */
class DefaultFileOutput implements IJobOutput
{
	private final String mFilePath;
	private final File mFile;
	private final FileWriter mFileWriter;
	
	private boolean mIsClosed = false;
	
	public DefaultFileOutput(String filePath) throws IOException
	{
		mFilePath = filePath;
		mFile = new File(mFilePath);
		
		mFileWriter = new FileWriter(mFile);
	}
	
	@Override
	public void write(String output)
	{
		try
		{
			mFileWriter.write(output);
		}
		catch (IOException e)
		{
			// TODO: Logging?
		}
	}
	
	@Override
	public void writeLine(String output)
	{
		try
		{
			mFileWriter.write(output + System.lineSeparator());
		}
		catch (IOException e)
		{
			// TODO: Logging?
		}
	}

	@Override
	public void close() throws IOException
	{
		if (mIsClosed)
		{
			return;
		}
		
		mFileWriter.flush();
		mFileWriter.close();
		
		mIsClosed = true;
	}

	@Override
	public boolean isClosed()
	{
		return mIsClosed;
	}

}