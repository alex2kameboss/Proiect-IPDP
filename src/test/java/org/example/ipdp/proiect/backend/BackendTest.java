package org.example.ipdp.proiect.backend;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.example.ipdp.proiect.backend.orm.Attribute;
import org.example.ipdp.proiect.backend.orm.Entity;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testAddEntity() {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        backend.addEntity(new org.example.ipdp.proiect.misc.Entity("entity1"));

        Entity entity = backendHelper.getEntityByName("entity1");

        assertNotNull(entity);
    }

    @Test
    public void testAddAttribute() {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        String name = "entity1";

        backend.addEntity(new org.example.ipdp.proiect.misc.Entity(name));
        backend.addAttribute(name, new org.example.ipdp.proiect.misc.Attribute("name1", "type1"));

        List<Attribute> attrs = backendHelper.getEntityAttributes(name);

        assertEquals(1, attrs.size());
    }
}
