package org.example.ipdp.proiect.backend;

import org.example.ipdp.proiect.actions.*;
import org.example.ipdp.proiect.misc.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ActionsTestOnBackend {
    @Test
    public void addNewEntityByAction()
    {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        String entityName = "entity1";
        IAction action = new AddEntityAction(new Entity(entityName));

        action.applyStorage(backend);

        assertNotNull(backendHelper.getEntityByName(entityName));
    }

    @Test
    public void addNewAttributeByAction()
    {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        String parentName = "entity1";
        Entity parent = new Entity(parentName);

        backend.addEntity(parent);

        String attrName = "attr1";
        Attribute attribute = new Attribute(attrName, "type1");
        IAction action = new AddAttributeAction(parentName, attribute);

        action.applyStorage(backend);

        List<org.example.ipdp.proiect.backend.orm.Attribute> attrs = backendHelper.getEntityAttributes(parentName);

        assertEquals(1, attrs.size());
    }

    @Test
    public void removeEntityByAction()
    {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        String entityName = "entity2";

        // add new entity
        IAction action = new AddEntityAction(new Entity(entityName));
        action.applyStorage(backend);

        // remove entity
        action = new RemoveEntityAction(new Entity(entityName));
        action.applyStorage(backend);

        assertNull(backendHelper.getEntityByName(entityName));
    }

    @Test
    public void removeAttributeByAction()
    {
        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        String name = "entity1";

        backend.addEntity(new org.example.ipdp.proiect.misc.Entity(name));
        backend.addAttribute(name, new org.example.ipdp.proiect.misc.Attribute("name1", "type1"));
        //backend.removeAttribute(name, new org.example.ipdp.proiect.misc.Attribute("name1", "type1"));

        IAction action = action = new RemoveAttributeAction(name, new org.example.ipdp.proiect.misc.Attribute("name1", "type1"));
        action.applyStorage(backend);

        List<org.example.ipdp.proiect.backend.orm.Attribute> attrs = backendHelper.getEntityAttributes(name);

        assertEquals(0, attrs.size());
    }

    @Test
    public void addRelationByAction()
    {
        Entity entity1 = new Entity("addRelationByAction1");
        Entity entity2 = new Entity("addRelationByAction2");
        Relation relation = new Relation(entity1, entity2, RelationTypes.one_to_many);

        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        backend.addEntity(entity1);
        backend.addEntity(entity2);

        IAction action = new AddRelationAction(relation);

        action.applyStorage(backend);

        assertEquals(1, backendHelper.getRelations().size());
    }

    @Test
    public void removeRelationByAction()
    {
        Entity entity1 = new Entity("removeRelationByAction1");
        Entity entity2 = new Entity("removeRelationByAction2");
        Relation relation = new Relation(entity1, entity2, RelationTypes.one_to_many);

        Backend backend = new Backend();
        BackendHelper backendHelper = new BackendHelper(backend);

        backend.addEntity(entity1);
        backend.addEntity(entity2);
        backend.addRelation(relation);


        IAction action = new RemoveRelationAction(relation);

        action.applyStorage(backend);

        assertEquals(0, backendHelper.getRelations().size());
    }
}
