package models.command;

import models.context.Context;

public interface Command {
    void execute(Context context, String args);
}
