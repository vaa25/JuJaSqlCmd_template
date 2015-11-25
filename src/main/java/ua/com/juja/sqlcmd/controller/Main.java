package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.controller.command.*;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.sqlcmd.view.Console;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by oleksandr.baglai on 25.08.2015.
 */
public class Main {

    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new JDBCDatabaseManager();

        Help help = new Help(view);
        Command[] commands = new Command[]{
                new Connect(manager, view),
                new Tables(manager, view),
                new Clear(manager, view),
                new Create(manager, view),
                new Find(manager, view),
                help,
                new Exit(view),
                new Unsupported(manager, view)

        };
        help.setCommands(commands);
        MainController controller = new MainController(view, manager, commands);
        controller.run();
    }
}
