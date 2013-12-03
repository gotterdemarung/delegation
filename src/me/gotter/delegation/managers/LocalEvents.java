package me.gotter.delegation.managers;

import java.util.ArrayList;
import java.util.Hashtable;

import me.gotter.delegation.EventSession;
import me.gotter.delegation.IEventEmitter;
import me.gotter.delegation.IEventHandler;
import me.gotter.delegation.ISessionEvent;

public class LocalEvents implements IEventEmitter {

	private Hashtable<Class<Object>, ArrayList<IEventHandler>> registeredListeners;
	private Hashtable<EventSession, IEventHandler> registeredSessionListeners;
	
	public LocalEvents()
	{
		registeredListeners = new Hashtable<Class<Object>, ArrayList<IEventHandler>>();
		registeredSessionListeners = new Hashtable<EventSession, IEventHandler>();
	}
	
	@Override
	public void emit(Object event)
	{
		if (event == null) {
			// Event is empty
			throw new NullPointerException("Empty event");
		}
		
		// Looking info registeredSessionListeners
		if (event instanceof ISessionEvent) {
			if (registeredSessionListeners.containsKey((ISessionEvent) event)) {
				// Invoking listener
				registeredSessionListeners.get((ISessionEvent) event).handleEvent(event, this);
				// Removing listener
				registeredSessionListeners.remove((ISessionEvent) event);
				return;
			}
		}
		
		// Iterating over general listeners
		for (IEventHandler handler : getListeners(event)) {
			handler.handleEvent(handler, this);
		}
	}
	
	/**
	 * Delegates event
	 * 
	 * @param event
	 * @param source
	 */
	public void handleEvent(Object event, IEventEmitter source)
	{
		// Ignoring information about source and delegating event
		this.emit(event);
	}

	/**
	 * Binds provided event listener to particular event
	 * 
	 * @param event
	 * @param handler
	 */
	public void bind(Object event, IEventHandler handler) {
		this.getListeners(event).add(handler);
	}

	/**
	 * Bind one-time session event to provided handler
	 * 
	 * @param session
	 * @param handler
	 */
	public void bind(EventSession session, IEventHandler handler) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Unbinds listener from all event
	 * 
	 * @param handler
	 */
	public void unbind(IEventHandler handler) {
		if (registeredListeners.size() > 0) {
			for (Class<Object> cls : registeredListeners.keySet()) {
				this.getListeners(cls).remove(handler);
			}
		}
	}

	/**
	 * Unbinds listener from particular event
	 * 
	 * @param event
	 * @param handler
	 */
	public void unbind(Object event, IEventHandler handler) {
		this.getListeners(event).remove(handler);
	}

	/**
	 * Removes all listeners
	 */
	public void clear() {
		// Using garbage collector
		if (registeredListeners.size() > 0) {
			registeredListeners = new Hashtable<Class<Object>, ArrayList<IEventHandler>>();
		}
	}

	
	/**
	 * Returns array of listeners for particular event
	 * 
	 * @param event
	 * @return
	 */
	protected ArrayList<IEventHandler> getListeners(Object event)
	{
		if (event == null) {
			throw new NullPointerException("Empty event");
		}
		return getListeners(event.getClass());
	}
	
	/**
	 * Returns array of listeners for particular event class
	 * 
	 * @param cls
	 * @return
	 */
	protected ArrayList<IEventHandler> getListeners(Class<Object> cls)
	{
		if (!registeredListeners.containsKey(cls)) {
			// Creating listeners array
			registeredListeners.put(cls, new ArrayList<IEventHandler>());
		}
		
		return registeredListeners.get(cls);
	}
}
