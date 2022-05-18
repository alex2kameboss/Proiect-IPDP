package org.example.ipdp.proiect.backend;

import org.example.ipdp.proiect.frontend.FileContext;
import org.example.ipdp.proiect.misc.Entity;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FileContextTest {
    @Test
    public void testCreateAnOpenAgain() {
        FileContext context = new FileContext("F:\\Facultate\\testdb");
        Backend backend = new Backend(context.getContext());
        backend.addEntity(new Entity("entity1"));
        context.closeContext();

        context = new FileContext("F:\\Facultate\\testdb");
        BackendHelper backendHelper = new BackendHelper(new Backend(context.getContext()));

        org.example.ipdp.proiect.backend.orm.Entity entity = backendHelper.getEntityByName("entity1");
        assertNotNull(entity);
    }
}
