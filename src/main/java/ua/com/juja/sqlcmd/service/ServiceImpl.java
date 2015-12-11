package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DataSetImpl;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vaa25 on 29.11.2015.
 */
public class ServiceImpl implements Service {

    private DatabaseManager manager;

    public ServiceImpl(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public List<String> commandList() {
        return Arrays.asList("help", "connect", "clear", "tables", "create", "find", "menu");
    }

    @Override
    public void connect(String dbname, String username, String password) {
        dbname = dbname.trim();
        username = username.trim();
        password = password.trim();
        manager.connect(dbname, username, password);
    }

    @Override
    public void clear(String tableName) {
        tableName = tableName.trim();
        manager.clear(tableName);
    }

    @Override
    public Set<String> tables() {
        return manager.getTableNames();
    }

    public String find(String tableName) {
        tableName = tableName.trim();
        Set<String> columnNames = manager.getTableColumns(tableName);
        List<DataSet> columnDataSets = manager.getTableData(tableName);
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
        return builder.toString();
    }

    @Override
    public Set<String> columnNames(String tableName) {
        tableName = tableName.trim();
        return manager.getTableColumns(tableName);
    }

    @Override
    public void create(String tableName, Map<String, String[]> data) {
        tableName = tableName.trim();
        Set<String> columnNames = columnNames(tableName);
        DataSet dataSet = new DataSetImpl();
        for (Map.Entry<String, String[]> entry : data.entrySet()) {
            if (columnNames.contains(entry.getKey())) {
                dataSet.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        manager.create(tableName, dataSet);
    }
}
