package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.util.Util;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by vaa25 on 19.11.2015.
 */
public class Tables implements Command {

    private final DatabaseManager manager;
    private final View view;

    public Tables(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("tables");
    }

    @Override
    public void process(String command) {
        if (!"tables".equals(command)) {
            throw new IllegalArgumentException("Формат команды '" + format() + "', а ты ввел: " + command);
        }
        Util.checkConnection(manager, command);
        view.write(manager.getTableNames().toString());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String format() {
        return "tables";
    }

    @Override
    public String description() {
        return "для получения списка всех таблиц базы, к которой подключились";
    }
}
