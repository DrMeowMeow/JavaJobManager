package jjms.util;

import java.util.HashMap;

/**
 * Represents a set of key-value pair properties
 * @author jared
 */
public class Properties
{
	private final HashMap<String, String> mProperties;
	
	/**
	 * Initialises a new empty instance of the {@code Properties} class.
	 */
	public Properties()
	{
		mProperties = new HashMap<String, String>();
	}

	/**
	 * Retrieves the paired value for the given key.
	 * @param key the key associated with the value to retrieve.
	 * @return the value associated with the given key.
	 */
	public String get(String key)
	{
		return mProperties.get(key);
	}
	
	/**
	 * Sets the paired value for the given key.
	 * @param key the key associated with the value to set.
	 * @param value the new value to set.
	 */
	public void set(String key, String value)
	{		
		mProperties.put(key, value);
	}
	
	/**
	 * Returns {@code true} if this map contains no key-value mappings.
	 * @return {@code true} if this map contains no key-value mappings.
	 */
	public boolean isEmpty()
	{
		return mProperties.isEmpty();
	}
}
