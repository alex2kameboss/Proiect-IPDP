package org.example.ipdp.proiect.frontend;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.dbsync.CreateIfNoSchemaStrategy;
import org.apache.cayenne.access.dbsync.SchemaUpdateStrategy;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.di.Binder;
import org.apache.cayenne.di.Module;

public class FileContext {
    protected ServerRuntime cayenneRuntime;
    protected ObjectContext context;

    public FileContext(String path) {
        String finalPath = path.replace('\\', '/');
        finalPath = finalPath + "/database";

        cayenneRuntime = ServerRuntime.builder()
                .addConfig("cayenne-test.xml")
                .addModule(new Module() {
                    @Override
                    public void configure(Binder binder) {
                        binder.bind(SchemaUpdateStrategy.class)
                                .to(CreateIfNoSchemaStrategy.class);
                    }
                })
                .jdbcDriver("org.apache.derby.jdbc.EmbeddedDriver")
                .url("jdbc:derby:" + finalPath + ";create=true")
                .build();
        context = cayenneRuntime.newContext();
    }

    public ObjectContext getContext() {
        return context;
    }

    public void closeContext() {
        cayenneRuntime.shutdown();
    }
}
