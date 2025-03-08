package com.createBot.command;

public abstract class AbstractCommand implements Command{
    protected String commandIdentifier;

    public abstract String getCommandIdentifier();

}
