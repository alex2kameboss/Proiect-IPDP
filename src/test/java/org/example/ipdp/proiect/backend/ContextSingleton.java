package org.example.ipdp.proiect.backend;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

public class ContextSingleton {
    private ServerRuntime cayenneRuntime;
    private ObjectContext context;
    private static ContextSingleton con = null;

    private ContextSingleton() {
        cayenneRuntime = ServerRuntime.builder()
                .addConfig("cayenne-test.xml")
                .build();
        context = cayenneRuntime.newContext();
    }

    public static ObjectContext getContext() {
        if (con == null) {
            con = new ContextSingleton();
        }

        return con.context;
    }
}
