package me.gotter.delegation;

/**
 * Interface for events, containing session ID,
 * which is used to identify it
 */
public interface ISessionEvent 
{
	/**
	 * Sets session id for event
	 * 
	 * @param id
	 */
	public void setEventSession(EventSession id);
	
	/**
	 * Returns session id
	 * 
	 * @return
	 */
	public EventSession getEventSession();
}
