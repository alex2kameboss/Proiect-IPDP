package org.example.ipdp.proiect.backend;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BackendTest {
    @Test
    public void testCayenneWork()
    {
        ServerRuntime cayenneRuntime = ServerRuntime.builder()
                .addConfig("cayenne-test.xml")
                .build();
        ObjectContext context = cayenneRuntime.newContext();

        assertNotNull(context);
    }
}
