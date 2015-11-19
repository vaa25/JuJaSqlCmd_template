package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.controller.command.Command;
import ua.com.juja.sqlcmd.controller.command.ExitException;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class MainController {

    private View view;
    private DatabaseManager manager;
    private Command[] commands;

    public MainController(View view, DatabaseManager manager, Command[] commands) {

        this.view = view;
        this.manager = manager;
        this.commands = commands;
    }

    public void run() {
        view.write("Привет юзер!\r\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password");
        String readed;
        while ((readed = view.read()) != null) {
            for (Command command : commands) {
                if (command.canProcess(readed)) {
                    try {
                        command.process(readed);
                        view.write("Введи команду (или help для помощи):");
                    } catch (ExitException e) {
                        return;
                    }
                }
            }
        }
    }
}
