package com.dkit.oopca5.server;

import com.dkit.oopca5.core.TaskDatabase;

public interface ICommand
{
    public String generateResponse(String[] components, TaskDatabase taskList);
}
