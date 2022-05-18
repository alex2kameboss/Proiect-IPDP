package org.example.ipdp.proiect;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    static private Log log = null;
    private Logger logger;

    private Log(String filePath) {
        logger = Logger.getLogger("Logging");
    }

    public static Log getContext() {
        if (log == null) {
            log = new Log(null);
        }

        return log;
    }

    public void setLogFile(String filePath) {
        if (filePath != null) {
            FileHandler fh = null;
            try {
                fh = new FileHandler(filePath);
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void error(String msg) {
        logger.severe(msg);
    }

    public void config(String msg) {
        logger.config(msg);
    }
}
