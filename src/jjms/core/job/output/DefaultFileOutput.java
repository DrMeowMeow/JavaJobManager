package jjms.core.job.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jjms.core.job.IJobOutput;

public class DefaultFileOutput implements IJobOutput
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
	public void add(String output)
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
