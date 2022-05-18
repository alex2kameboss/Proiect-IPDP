package org.example.ipdp.proiect.misc;

import org.example.ipdp.proiect.actions.*;
import org.junit.Test;

import javax.xml.crypto.Data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void updateEntityNameByAction()
    {
        String entityName = "entity1";
        String entityNameNew = "newEntity1";
        DataModel model = new DataModel();
        model.addEntity(new Entity(entityName));

        // add new entity
        IAction action = new UpdateEntityNameAction(entityName, entityNameNew);
        action.applyDataModel(model);

        assertTrue(model.entities.get(entityName) == null &&
                model.entities.get(entityNameNew).getEntityName().equals(entityNameNew));
    }

    @Test
    public void updateAttributeByAction()
    {
        String parentName = "entity1";
        Entity parent = new Entity(parentName);
        String attrName = "attr1";
        Attribute attribute = new Attribute(attrName, "type1");
        Attribute attribute1 = new Attribute(attrName, "type2");
        parent.addAttribute(attribute);
        DataModel model = new DataModel();
        model.addEntity(parent);

        IAction action = new UpdateAttributeAction(parentName, attribute, attribute1);
        action.applyDataModel(model);

        assertEquals(model.entities.get(parentName).getAttribute(attrName), attribute1);
    }

    @Test
    public void addRelationByAction()
    {
        Entity entity1 = new Entity("entity1");
        Entity entity2 = new Entity("entity2");
        Relation relation = new Relation(entity1, entity2, RelationTypes.one_to_many);

        IAction action = new AddRelationAction(relation);

        DataModel dataModel = new DataModel();
        dataModel.addEntity(entity1);
        dataModel.addEntity(entity2);

        action.applyDataModel(dataModel);

        assertEquals(1, dataModel.relations.size());
    }

    @Test
    public void removeRelationByAction()
    {
        Entity entity1 = new Entity("entity1");
        Entity entity2 = new Entity("entity2");
        Relation relation = new Relation(entity1, entity2, RelationTypes.one_to_many);

        IAction action = new RemoveRelationAction(relation);

        DataModel dataModel = new DataModel();
        dataModel.addEntity(entity1);
        dataModel.addEntity(entity2);
        dataModel.addRelation(relation);

        action.applyDataModel(dataModel);

        assertEquals(0, dataModel.relations.size());
    }
}
