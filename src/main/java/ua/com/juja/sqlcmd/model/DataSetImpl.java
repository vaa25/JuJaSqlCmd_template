package ua.com.juja.sqlcmd.model;

import java.util.*;

public class DataSetImpl implements DataSet {
    private Map<String, Object> dataset = new LinkedHashMap<>();

    public void put(String name, Object value) {
        dataset.put(name, value);
    }

    public List<Object> getValues() {
        return new ArrayList<>(dataset.values());
    }

    public Set<String> getNames() {
        return dataset.keySet();
    }

    public Object get(String name) {
        return dataset.get(name);
    }

    public void updateFrom(DataSet newValue) {
        for (String name : newValue.getNames()) {
            dataset.put(name, newValue.get(name));
        }
    }
}
