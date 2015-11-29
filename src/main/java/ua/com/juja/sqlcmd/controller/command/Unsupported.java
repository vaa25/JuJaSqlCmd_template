package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.util.Util;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by oleksandr.baglai on 28.08.2015.
 */
public class Unsupported implements Command {

    private View view;
    private DatabaseManager manager;

    public Unsupported(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        Util.checkConnection(manager, command);
        view.write("Несуществующая команда: " + command);
    }

    @Override
    public String format() {
        return "";
    }

    @Override
    public String description() {
        return "для неподдерживаемых команд";
    }
}
