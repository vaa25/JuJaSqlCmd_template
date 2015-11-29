package ua.com.juja.sqlcmd.service;

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
        return Arrays.asList("help", "connect", "clear", "tables", "menu");
    }

    @Override
    public void connect(String dbname, String username, String password) {
        manager.connect(dbname, username, password);
    }

    @Override
    public void clear(String tableName) {
        Util.checkConnection(manager, "clear " + tableName);
        manager.clear(tableName);
    }

    @Override
    public Set<String> tables() {
        Util.checkConnection(manager, "tables");
        return manager.getTableNames();
    }
}
