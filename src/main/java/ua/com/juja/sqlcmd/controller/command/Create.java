package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DataSetImpl;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by vaa25 on 19.11.2015.
 */
public class Create implements Command {

    private final DatabaseManager manager;
    private final View view;

    public Create(DatabaseManager manager, View view) {

        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("create|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Должно быть четное количество параметров в формате '" + format() + "', а ты прислал: '" + command + "'");
        }
        if (!manager.isConnected()) {
            throw new ConnectionException(command);
        }
        DataSet dataSet = extractDataSet(data);
        manager.create(data[1], dataSet);
        view.write("Запись {names:" + dataSet.getNames() + ", values:" + dataSet.getValues() + "} была успешно создана в таблице '" + data[1] + "'.");

    }

    private DataSet extractDataSet(String[] data) {
        DataSet result = new DataSetImpl();
        for (int i = 2; i < data.length; i += 2) {
            result.put(data[i], data[i + 1]);
        }
        return result;
    }

    @Override
    public String format() {
        return "create|tableName|column1|value1|column2|value2|...|columnN|valueN";
    }

    @Override
    public String description() {
        return "для создания записи в таблице";
    }
}
