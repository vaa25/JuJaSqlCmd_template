package ua.com.juja.sqlcmd.controller.command;

/**
 * Created by oleksandr.baglai on 28.08.2000.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }
}
