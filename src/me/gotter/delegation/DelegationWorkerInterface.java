package me.gotter.delegation;

public interface DelegationWorkerInterface<T> {

    /**
     * Handles data, stored in argument
     *
     * @param argument
     */
    public void handle(T argument);

}
