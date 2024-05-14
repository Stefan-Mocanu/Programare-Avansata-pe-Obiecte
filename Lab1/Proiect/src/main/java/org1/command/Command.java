package org1.command;

public interface Command {
    default void UseCommand(String[] arg) {
    }
}
