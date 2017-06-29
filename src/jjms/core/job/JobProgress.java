package jjms.core.job;

/**
 * Represents the current progress of a given Job.
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
	 * Retrieves the percentage progress of the Job.
	 * @return the Job's current progress.
	 */
	public double getProgress()
	{
		return mProgress;
	}
	
	/**
	 * Retrieves the action that the Job is currently performing.
	 * @return the current action of the Job.
	 */
	public String getCurrentAction()
	{
		return mCurrentAction;
	}
	
	/**
	 * Sets the percentage progress of the Job.
	 * @param value the new progress.
	 */
	public synchronized void setProgress(double value)
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
	 * Sets the action the Job is currently performing.
	 * @param newAction the new action.
	 */
	public synchronized void setCurrentAction(String newAction)
	{
		if (newAction == null)
		{
			return;
		}
		
		mCurrentAction = newAction;
	}
}
