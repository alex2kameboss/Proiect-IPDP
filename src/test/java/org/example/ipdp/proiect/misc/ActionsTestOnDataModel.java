package org.example.ipdp.proiect.misc;

import org.example.ipdp.proiect.actions.AddAttributeAction;
import org.example.ipdp.proiect.actions.AddEntityAction;
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
}
