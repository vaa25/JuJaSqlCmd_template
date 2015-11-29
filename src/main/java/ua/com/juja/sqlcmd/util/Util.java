package ua.com.juja.sqlcmd.util;

import ua.com.juja.sqlcmd.controller.command.ConnectionException;
import ua.com.juja.sqlcmd.model.DatabaseManager;

/**
 * Created by vaa25 on 29.11.2015.
 */
public class Util {

    public static void checkConnection(DatabaseManager manager, String command) {
        if (!manager.isConnected()) {
            throw new ConnectionException(command);
        }
    }
}
