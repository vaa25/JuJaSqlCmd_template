package ua.com.juja.sqlcmd.service;

import java.util.List;

/**
 * Created by vaa25 on 29.11.2015.
 */
public interface Service {

    List<String> commandList();

    void connect(String dbname, String username, String password);

}
