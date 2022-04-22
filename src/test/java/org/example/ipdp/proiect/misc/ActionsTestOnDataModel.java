package org.example.ipdp.proiect.misc;

import org.example.ipdp.proiect.actions.AddAttributeAction;
import org.example.ipdp.proiect.actions.AddEntityAction;
import org.example.ipdp.proiect.actions.RemoveAttributeAction;
import org.example.ipdp.proiect.actions.RemoveEntityAction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ActionsTestOnDataModel {
    @Test
    public void addNewEntityByAction()
    {
        String entityName = "entity1";
        IAction action = new AddEntityAction(new Entity(entityName));
        DataModel model = new DataModel();

        action.applyDataModel(model);

        assertEquals(model.entities.get(entityName).getEntityName(), entityName);
    }

    @Test
    public void addNewAttributeByAction()
    {
        String parentName = "entity1";
        Entity parent = new Entity(parentName);
        String attrName = "attr1";
        Attribute attribute = new Attribute(attrName, "type1");
        IAction action = new AddAttributeAction(parentName, attribute);
        DataModel model = new DataModel();
        model.addEntity(parent);

        action.applyDataModel(model);

        assertEquals(model.entities.get(parentName).getAttribute(attrName), attribute);
    }

    @Test
    public void removeEntityByAction()
    {
        String entityName = "entity1";
        DataModel model = new DataModel();

        // add new entity
        IAction action = new AddEntityAction(new Entity(entityName));
        action.applyDataModel(model);

        // remove entity
        action = new RemoveEntityAction(model.getEntity(entityName));
        action.applyDataModel(model);

        assertEquals(model.entities.get(entityName), null);
    }

    @Test
    public void removeAttributeByAction()
    {
        String parentName = "entity1";
        Entity parent = new Entity(parentName);
        String attrName = "attr1";
        Attribute attribute = new Attribute(attrName, "type1");

        DataModel model = new DataModel();
        model.addEntity(parent);

        // add new attribute
        IAction action = new AddAttributeAction(parentName, attribute);
        action.applyDataModel(model);

        // remove attribute
        action = new RemoveAttributeAction(parentName, attribute);
        action.applyDataModel(model);

        assertEquals(model.entities.get(parentName).getAttribute(attrName), null);
    }
}
