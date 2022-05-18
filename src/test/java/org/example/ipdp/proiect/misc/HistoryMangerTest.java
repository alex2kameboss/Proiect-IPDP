package org.example.ipdp.proiect.misc;

import org.example.ipdp.proiect.actions.AddEntityAction;
import org.example.ipdp.proiect.actions.RemoveEntityAction;
import org.example.ipdp.proiect.frontend.HistoryManager;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HistoryMangerTest {
    @Test
    public void testAddAction()
    {
        HistoryManager history = new HistoryManager(new IStorage() {
            @Override
            public void addEntity(Entity entity) {

            }

            @Override
            public Entity removeEntity(Entity entity) {
                return null;
            }

            @Override
            public Entity updateEntity(Entity oldEntity, Entity newEntity) {
                return null;
            }

            @Override
            public void addAttribute(String parent, Attribute attribute) {

            }

            @Override
            public Attribute removeAttribute(String parentName, Attribute attribute) {
                return null;
            }

            @Override
            public Attribute updateAttribute(String parentName, Attribute oldAttribute, Attribute newAttribute) {
                return null;
            }

            @Override
            public void addRelation(Relation relation) {

            }

            @Override
            public Relation removeRelation(Relation relation) {
                return null;
            }

            @Override
            public Relation updateRelation(Relation oldRelation, Relation newRelation) {
                return null;
            }

            @Override
            public DataModel getDataModel() {
                return new DataModel();
            }

            @Override
            public boolean applyActions(List<IAction> actions) {
                return false;
            }
        });

        String entityName = "Entity1";
        history.addAction(new AddEntityAction(new Entity(entityName)));
        history.addAction(new RemoveEntityAction(new Entity(entityName)));

        DataModel current = history.getData();

        assertEquals(0, current.entities.size());
    }

    @Test
    public void testUndoAction()
    {
        HistoryManager history = new HistoryManager(new IStorage() {
            @Override
            public void addEntity(Entity entity) {

            }

            @Override
            public Entity removeEntity(Entity entity) {
                return null;
            }

            @Override
            public Entity updateEntity(Entity oldEntity, Entity newEntity) {
                return null;
            }

            @Override
            public void addAttribute(String parent, Attribute attribute) {

            }

            @Override
            public Attribute removeAttribute(String parentName, Attribute attribute) {
                return null;
            }

            @Override
            public Attribute updateAttribute(String parentName, Attribute oldAttribute, Attribute newAttribute) {
                return null;
            }

            @Override
            public void addRelation(Relation relation) {

            }

            @Override
            public Relation removeRelation(Relation relation) {
                return null;
            }

            @Override
            public Relation updateRelation(Relation oldRelation, Relation newRelation) {
                return null;
            }

            @Override
            public DataModel getDataModel() {
                return new DataModel();
            }

            @Override
            public boolean applyActions(List<IAction> actions) {
                return false;
            }
        });

        String entityName = "Entity1";
        history.addAction(new AddEntityAction(new Entity(entityName)));
        history.addAction(new RemoveEntityAction(new Entity(entityName)));
        history.undoAction();

        DataModel current = history.getData();

        assertEquals(1, current.entities.size());
    }
}
