package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.controller.command.Clear;
import ua.com.juja.sqlcmd.controller.command.Command;
import ua.com.juja.sqlcmd.controller.command.Exit;
import ua.com.juja.sqlcmd.controller.command.Help;
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

        MainController controller = new MainController(view, manager, new Command[]{
                new Help(view),
                new Exit(view),
                new Clear(manager, view)
        });
        controller.run();
    }
}
