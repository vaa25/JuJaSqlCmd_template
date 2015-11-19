package ua.com.juja.sqlcmd.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCDatabaseManager implements DatabaseManager {
    private Connection connection;
    private ResultSet rs;
    private Statement stmt;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DataSet> getTableData(String tableName) {
        List<DataSet> result = new ArrayList<>();
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM " + tableName);
            int columnSize = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                DataSet dataSet = new DataSetImpl();
                for (int i = 0; i < columnSize; i++) {
                    dataSet.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-2);
        }
        return result;
    }

    @Override
    public int getSize(String tableName) {
        int result = 0;
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                result++;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-2);
        }
        return result;
    }

    @Override
    public Set<String> getTableNames() {
        Set<String> result = new HashSet<>();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'");
            while (rs.next()) {
                result.add(rs.getString("table_name"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-2);
        }
        return result;
    }

    @Override
    public void connect(String database, String userName, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }

    @Override
    public void clear(String tableName) {
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM " + tableName);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }

    @Override
    public void create(String tableName, DataSet input) {
        try {
            stmt = connection.createStatement();
            StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder(" VALUES (");
            for (String name : input.getNames()) {
                if (names.length() > 0) {
                    names.append(", ");
                    values.append(", ");
                }
                names.append(name);
                values.append(input.get(name));
            }
            names.append(")");
            values.append(")");
            sql.append(names).append(values);
            stmt.executeUpdate(sql.toString());
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        try {
            stmt = connection.createStatement();
            StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
            StringBuilder data = new StringBuilder();
            for (String name : newValue.getNames()) {
                if (data.length() > 0) {
                    data.append(", ");
                }
                data.append(name).append(" = ").append(newValue.get(name));
            }
            sql.append(data);
            sql.append(" WHERE id = ").append(id);
            stmt.executeUpdate(sql.toString());
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        Set<String> result = new HashSet<>();
        try {
            stmt = connection.createStatement();
            ResultSetMetaData rs = connection.getMetaData().getTables(null, null, tableName, null).getMetaData();
            int size = rs.getColumnCount();
            for (int i = 0; i < size; i++) {
                result.add(rs.getColumnName(i));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-2);
        }
        return result;
    }

    @Override
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
