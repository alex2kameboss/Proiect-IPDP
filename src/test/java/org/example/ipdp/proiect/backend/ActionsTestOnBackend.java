package org.example.ipdp.proiect.backend;

import org.example.ipdp.proiect.actions.*;
import org.example.ipdp.proiect.misc.Attribute;
import org.example.ipdp.proiect.misc.DataModel;
import org.example.ipdp.proiect.misc.Entity;
import org.example.ipdp.proiect.misc.IAction;
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

        String entityName = "entity1";

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
}
