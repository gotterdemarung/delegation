package me.gotter.delegation;

/**
 * Interface for all event handlers 
 */
public interface EventHandlerInterface
{
	/**
	 * Handles an event
	 * 
	 * @param event  Event to handle
	 * @param source Event manager, emitted an event
	 */
	public void handleEvent(Object event, EventEmitterInterface source);
}
