package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by vaa25 on 19.11.2015.
 */
public class Connect implements Command {
    private final DatabaseManager manager;
    private final View view;

    public Connect(DatabaseManager manager, View view) {

        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (data.length != 4) {
            throw new IllegalArgumentException("Неверно количество параметров разделенных знаком '|', ожидается 4, но есть: " + data.length);
        }
        manager.connect(data[1], data[2], data[3]);
        view.write("Успех!");
    }

    @Override
    public String format() {
        return "connect|database|userName|password";
    }

    @Override
    public String description() {
        return "для получения списка всех таблиц базы, к которой подключились";
    }
}
