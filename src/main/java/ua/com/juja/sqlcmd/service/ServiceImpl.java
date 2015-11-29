package ua.com.juja.sqlcmd.service;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.util.Util;

import java.util.Arrays;
import java.util.List;
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
        return Arrays.asList("help", "connect", "clear", "tables", "find", "menu");
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
        Util.checkConnection(manager, "clear " + tableName);
        manager.clear(tableName);
    }

    @Override
    public Set<String> tables() {
        Util.checkConnection(manager, "tables");
        return manager.getTableNames();
    }

    public String find(String tableName) {
        tableName = tableName.trim();
        Util.checkConnection(manager, "find " + tableName);
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
}
