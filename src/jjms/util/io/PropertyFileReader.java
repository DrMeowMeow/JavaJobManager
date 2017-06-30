package jjms.util.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import jjms.util.Properties;
import jjms.util.PropertyFileException;

/**
 * Provides the ability to read and parse a properties file into its containing properties.
 * @author jared
 */
public final class PropertyFileReader
{
	// Empty private constructor so we cannot instantiate an object
	private PropertyFileReader()
	{
	}
	
	/**
	 * Read the contents of a property file and parse them into their properties.
	 * @param props the properties to parse the contents of the file to
	 * @param filePath the file path of the property file to parse
	 * @throws PropertyFileException thrown if an error occurs whilst reading/parsing the property file
	 */
	public static synchronized void read(Properties props, String filePath) throws PropertyFileException
	{
		FileInputStream in = null;
		BufferedReader reader = null;
		try
		{
			in = new FileInputStream(filePath);
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			
			String line = null;
			do 
			{
				try
				{
					line = reader.readLine();
					
					// Split on colon property seperator
					String[] lineParts = line.split(":", 2);
					props.set(lineParts[0], lineParts[1]);
				}
				catch (Exception ex)
				{
					// We can ignore this as failing to parse one line should
					// not stop us parsing the rest of the properties
				}
			}
			while (line != null);
			
			if (props.isEmpty())
			{
				throw new PropertyFileException("No properties read from specified file. File: " + filePath);
			}
		}
		catch (FileNotFoundException ex)
		{
			throw new PropertyFileException("Specified config file could not be found. File: " + filePath, ex);
		}
		catch (UnsupportedEncodingException ex)
		{
			throw new PropertyFileException("Specified encoding is unsupported.", ex);
		}
		finally
		{
			// Make sure we close the reader at the end no matter what
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					// Ignore
				}
			}
			
			// Make sure we close the input file stream at the end no matter what
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException ex)
				{
					// Ignore
				}
			}
		}
	}
}
