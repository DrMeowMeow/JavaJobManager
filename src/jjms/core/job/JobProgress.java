package jjms.core.job;

/**
 * Represents the current progress of a given job.
 * @author jared
 */
public class JobProgress
{
	private double mProgress;
	private String mCurrentAction;
	
	/**
	 * Instantiates a new instance of the {@code JobProgress} class.
	 */
	public JobProgress()
	{
		mProgress = 0;
		mCurrentAction = "Initialising.";
	}
	
	/**
	 * Retrieves the percentage progress of the job.
	 * @return
	 */
	public double getProgress()
	{
		return mProgress;
	}
	
	/**
	 * Retrieves the action that the job is currently performing.
	 * @return
	 */
	public String getCurrentAction()
	{
		return mCurrentAction;
	}
	
	/**
	 * Sets the percentage progress of the job.
	 * @param value the new progress.
	 */
	public void setProgress(double value)
	{
		if (value > 100.0)
		{
			mProgress = 100.0;
			return;
		}
		
		if (value < 0.0)
		{
			mProgress = 0.0;
			return;
		}
		
		mProgress = value;
	}
	
	/**
	 * Sets the action the job is currently performing.
	 * @param newAction the new action.
	 */
	public void setCurrentAction(String newAction)
	{
		if (newAction == null)
		{
			return;
		}
		
		mCurrentAction = newAction;
	}
}
