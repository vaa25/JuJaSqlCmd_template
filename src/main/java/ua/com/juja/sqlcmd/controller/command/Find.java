package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.util.List;
import java.util.Set;

/**
 * Created by vaa25 on 19.11.2015.
 */
public class Find implements Command {
    private final DatabaseManager manager;
    private final View view;

    public Find(DatabaseManager manager, View view) {

        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("find|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (data.length != 2) {
            throw new IllegalArgumentException("Неверно количество параметров разделенных знаком '|', ожидается 2, но есть: " + data.length);
        }
        if (!manager.isConnected()) {
            throw new ConnectionException();
        }
        Set<String> columnNames = manager.getTableColumns(data[1]);
        List<DataSet> columnDataSets = manager.getTableData(data[1]);
        String delimeter = "--------------------";
        StringBuilder builder = new StringBuilder(delimeter);
        builder.append("\r\n|");
        for (String name : columnNames) {
            builder.append(name).append('|');
        }
        builder.append("\r\n").append(delimeter);
        if (columnDataSets.size() > 0) {
            for (DataSet dataSet : columnDataSets) {
                builder.append("\r\n|");
                for (String name : columnNames) {
                    builder.append(dataSet.get(name)).append('|');
                }
            }
        }
        builder.append("\r\n").append(delimeter);
        view.write(builder.toString());
    }


    @Override
    public String format() {
        return "find|tableName";
    }

    @Override
    public String description() {
        return "для получения содержимого таблицы 'tableName'";
    }
}
