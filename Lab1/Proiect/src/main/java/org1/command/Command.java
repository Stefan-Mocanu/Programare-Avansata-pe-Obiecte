package org1.command;

/**
 * Interfata oricarei comenzi a aplicatiei
 */
public interface Command {
    /**
     * Utilizarea comenzii in sine
     * @param arg
     * @throws RuntimeException
     */
    default void UseCommand(String[] arg) {
    }
}
