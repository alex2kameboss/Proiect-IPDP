package org.example.ipdp.proiect.backend;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.example.ipdp.proiect.backend.orm.Attribute;
import org.example.ipdp.proiect.backend.orm.Entity;
import org.example.ipdp.proiect.backend.orm.Relationship;
import org.example.ipdp.proiect.misc.Relation;
import org.example.ipdp.proiect.misc.RelationTypes;
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

        String name = "entity12";

        backend.addEntity(new org.example.ipdp.proiect.misc.Entity(name));
        backend.addAttribute(name, new org.example.ipdp.proiect.misc.Attribute("name12", "type1"));

        List<Attribute> attrs = backendHelper.getEntityAttributes(name);

        assertEquals(1, attrs.size());
    }

    @Test
    public void testRemoveEntity() {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        String entityName = "entityForRemove";

        backend.addEntity(new org.example.ipdp.proiect.misc.Entity(entityName));
        backend.removeEntity(new org.example.ipdp.proiect.misc.Entity(entityName));

        assertNull(backendHelper.getEntityByName(entityName));
    }

    @Test
    public void testRemoveAttribute() {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        String name = "entity11";

        backend.addEntity(new org.example.ipdp.proiect.misc.Entity(name));
        backend.addAttribute(name, new org.example.ipdp.proiect.misc.Attribute("name111", "type1"));
        backend.removeAttribute(name, new org.example.ipdp.proiect.misc.Attribute("name111", "type1"));

        List<Attribute> attrs = backendHelper.getEntityAttributes(name);

        assertEquals(0, attrs.size());
    }

    @Test
    public void testAddRelationship() {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        org.example.ipdp.proiect.misc.Entity entity1 = new org.example.ipdp.proiect.misc.Entity("Entity1");
        org.example.ipdp.proiect.misc.Entity entity2 = new org.example.ipdp.proiect.misc.Entity("Entity2");
        Relation relation = new Relation(entity1, entity2, RelationTypes.one_to_many);

        backend.addEntity(entity1);
        backend.addEntity(entity2);
        backend.addRelation(relation);

        assertEquals(1, backendHelper.getRelations().size());
    }

    @Test
    public void testRemoveRelationship() {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        org.example.ipdp.proiect.misc.Entity entity1 = new org.example.ipdp.proiect.misc.Entity("Entity1");
        org.example.ipdp.proiect.misc.Entity entity2 = new org.example.ipdp.proiect.misc.Entity("Entity2");
        Relation relation = new Relation(entity1, entity2, RelationTypes.one_to_many);

        backend.addEntity(entity1);
        backend.addEntity(entity2);
        backend.addRelation(relation);
        backend.removeRelation(relation);

        assertEquals(0, backendHelper.getRelations().size());
    }
}
