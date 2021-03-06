package ua.com.juja.sqlcmd.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vaa25 on 29.11.2015.
 */
public interface Service {

    List<String> commandList();

    void connect(String dbname, String username, String password);

    void clear(String tableName);

    Set<String> tables();

    String find(String tableName);

    Set<String> columnNames(String tableName);

    void create(String tableName, Map<String, String[]> map);
}
