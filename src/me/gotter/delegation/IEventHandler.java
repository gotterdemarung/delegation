package me.gotter.delegation;

/**
 * Interface for all event handlers 
 */
public interface IEventHandler 
{
	/**
	 * Handles an event
	 * 
	 * @param event  Event to handle
	 * @param source Event manager, emitted an event
	 */
	public void handleEvent(Object event, IEventEmitter source);
}
