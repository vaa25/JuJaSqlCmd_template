package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

/**
 * Created by oleksandr.baglai on 28.08.2015.
 */
public class Help implements Command {

    private View view;
    private Command[] commands;
    public Help(View view) {
        this.view = view;
    }

    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("help");
    }

    @Override
    public void process(String string) {
        view.write("Существующие команды:");
        for (Command command : commands) {
            view.write("\t" + command.format());
            view.write("\t\t" + command.description());
        }
    }

    @Override
    public String format() {
        return "help";
    }

    @Override
    public String description() {
        return "для вывода этого списка на экран";
    }
}
