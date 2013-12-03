package me.gotter.delegation;

/**
 * Unique identifier for sessions 
 */
public class EventSession 
{
	/**
	 * Container for ID
	 */
	private String id;
	

	/**
	 * Create a session with provided id
	 * @param id
	 */
	public EventSession(String id)
	{
		this.id = id;
	}
	
	/**
	 * Returns session ID as string
	 * 
	 * @return
	 */
	public String getId()
	{
		return id;
	}
	
	public String toString()
	{
		return getId();
	}
	
	public int hashCode()
	{
		return getId().hashCode();
	}
	
	public boolean equals(Object o)
	{
		if (o == null) {
			return false;
		}
		if (o instanceof EventSession) {
			return id.equals(((EventSession) o).id);
		}
		return o.toString().equals(id);
	}
}
