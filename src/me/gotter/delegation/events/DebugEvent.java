package me.gotter.delegation.events;

/**
 * Base class for all debug events
 */
public final class DebugEvent {
    /**
     * Debug messages
     */
    private Object[] messages;

    /**
     * Nano time
     */
    private Long nanoTime;

    /**
     * Constructor
     *
     * @param messages
     */
    public DebugEvent(Object... messages) {
        this.messages = messages;
        this.nanoTime = System.nanoTime();
    }

    /**
     * Returns true if debug event does not contain any data
     *
     * @return
     */
    public boolean isEmpty() {
        return messages == null || messages.length == 0;
    }

    /**
     * Returns amount of messages in debug event
     *
     * @return
     */
    public int size() {
        if (messages == null) {
            return 0;
        }
        return messages.length;
    }

    /**
     * Returns array of messages as objects
     *
     * @return
     */
    public Object[] getMessages() {
        return messages;
    }

    /**
     * Returns array of messages as strings
     *
     * @param ignoreExceptions if true - instead of throwing exceptions returns empty strings
     * @return
     */
    public String[] getMessagesAsString(boolean ignoreExceptions) {
        String[] answer = new String[messages.length];
        for (int i = 0; i < messages.length; i++) {
            if (ignoreExceptions) {
                try {
                    answer[i] = messages[i].toString();
                } catch (Throwable e) {
                    answer[i] = "";
                }
            } else {
                answer[i] = messages[i].toString();
            }
        }
        return answer;
    }
}
