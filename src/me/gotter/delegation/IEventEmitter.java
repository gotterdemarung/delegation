package me.gotter.delegation;

public interface IEventEmitter 
{
	/**
	 * Emits an event
	 * 
	 * @param event
	 */
	public void emit(Object event);

}
