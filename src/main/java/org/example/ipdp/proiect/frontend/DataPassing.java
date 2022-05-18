package org.example.ipdp.proiect.frontend;

import java.util.HashMap;

public class DataPassing {
    protected HashMap<String, Object> passedData;
    static private DataPassing dataPassing = null;

    private DataPassing() {
        passedData = new HashMap<>();
    }

    public static DataPassing getContext() {
        if (dataPassing == null) {
            dataPassing = new DataPassing();
        }

        return dataPassing;
    }

    public void addData(String name, Object value) {
        passedData.put(name, value);
    }

    public Object getData(String name) {
        return passedData.get(name);
    }

    public void clear() {
        passedData.clear();
    }
}
