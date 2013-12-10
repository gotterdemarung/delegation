package me.gotter.delegation.delegates;

import me.gotter.delegation.DelegationWorkerInterface;

import java.util.ArrayList;

/**
 * Generic delegate implementation
 *
 * @param <T>
 */
public class GenericDelegate<T> implements DelegationWorkerInterface<T> {

    protected ArrayList<DelegationWorkerInterface<T>> executors = null;

    /**
     * Registers new delegation worker
     *
     * @param worker
     */
    public void add(DelegationWorkerInterface<T> worker) {
        if (executors == null) {
            executors = new ArrayList<DelegationWorkerInterface<T>>();
        }
        executors.add(worker);
    }

    /**
     * Unregister delegation worker
     *
     * @param worker
     */
    public void remove(DelegationWorkerInterface<T> worker) {
        if (executors == null) {
            // No executors
            return;
        }
        executors.remove(worker);
        if (executors.size() == 0) {
            // Unsetting executors
            executors = null;
        }
    }

    /**
     * Returns count of assigned workers
     *
     * @return
     */
    public int size() {
        if (executors == null) {
            return 0;
        }
        return executors.size();
    }

    @Override
    public void handle(T argument) {
        if (executors == null) {
            // No executors
            return;
        }
        for (DelegationWorkerInterface<T> worker : executors) {
            worker.handle(argument);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericDelegate that = (GenericDelegate) o;

        if (executors != null ? !executors.equals(that.executors) : that.executors != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return executors != null ? executors.hashCode() : 0;
    }
}
