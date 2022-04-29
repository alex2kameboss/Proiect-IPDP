package org.example.ipdp.proiect.backend;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;
import org.example.ipdp.proiect.backend.orm.*;

import javax.naming.Context;
import java.util.List;

public class Backend implements org.example.ipdp.proiect.misc.IStorage {
    protected ObjectContext context;

    public Backend(ObjectContext context) {
        this.context = context;
    }

    public Entity getEntityByName(String entityName) {
        return ObjectSelect.query(Entity.class)
                .where(Entity.NAME.eq(entityName))
                .selectFirst(context);
    }

    @Override
    public void addEntity(org.example.ipdp.proiect.misc.Entity entity) {
        Entity newEntity = context.newObject(Entity.class);
        newEntity.setName(entity.getEntityName());
        context.commitChanges();
    }

    @Override
    public org.example.ipdp.proiect.misc.Entity removeEntity(org.example.ipdp.proiect.misc.Entity entity) {
        Entity deleteEntity = this.getEntityByName(entity.getEntityName());

        if (deleteEntity != null) {
            context.deleteObject(deleteEntity);
            context.commitChanges();

            return entity;
        }

        return null;
    }

    @Override
    public org.example.ipdp.proiect.misc.Entity updateEntity(org.example.ipdp.proiect.misc.Entity oldEntity, org.example.ipdp.proiect.misc.Entity newEntity) {
        return null;
    }

    @Override
    public void addAttribute(String parent, org.example.ipdp.proiect.misc.Attribute attribute) {
        Entity entity = ObjectSelect.query(Entity.class)
                .where(Entity.NAME.eq(parent))
                .selectFirst(context);

        Attribute newAttribute = context.newObject(Attribute.class);
        newAttribute.setName(attribute.getAttributeName());
        newAttribute.setType(attribute.getAttributeType());

        entity.addToAttributes(newAttribute);

        context.commitChanges();
    }

    @Override
    public org.example.ipdp.proiect.misc.Attribute removeAttribute(String parentName, org.example.ipdp.proiect.misc.Attribute attribute) {
        Entity entity = getEntityByName(parentName);

        Attribute attributeDb = null;
        for(Attribute attr : entity.getAttributes()) {
            if (attr.getName().equals(attribute.getAttributeName()) &&
                attr.getType().equals(attribute.getAttributeType())) {
                attributeDb = attr;
                break;
            }
        }

        if (attributeDb != null) {
            context.deleteObject(attributeDb);
            context.commitChanges();
            return attribute;
        }

        return null;
    }

    @Override
    public org.example.ipdp.proiect.misc.Attribute updateAttribute(String parentName, org.example.ipdp.proiect.misc.Attribute oldAttribute, org.example.ipdp.proiect.misc.Attribute newAttribute) {
        return null;
    }

    @Override
    public void addRelation(org.example.ipdp.proiect.misc.Relation relation) {
        Entity firstEntity = getEntityByName(relation.getFirstEntity().getEntityName());
        Entity secondEntity = getEntityByName(relation.getSecondEntity().getEntityName());

        Relationship relationship = context.newObject(Relationship.class);
        relationship.setFirstMember(firstEntity);
        relationship.setSecondMemebr(secondEntity);
        relationship.setType(relation.getRelationTypes().name());
        context.commitChanges();
    }

    @Override
    public org.example.ipdp.proiect.misc.Relation removeRelation(org.example.ipdp.proiect.misc.Relation relation) {
        Entity firstEntity = getEntityByName(relation.getFirstEntity().getEntityName());
        Entity secondEntity = getEntityByName(relation.getSecondEntity().getEntityName());

        Relationship relationship = ObjectSelect.query(Relationship.class)
                .where(Relationship.FIRST_MEMBER.eq(firstEntity).andExp(Relationship.SECOND_MEMEBR.eq(secondEntity)))
                .selectFirst(context);

        if (relationship != null) {
            context.deleteObject(relationship);
            context.commitChanges();
            return  relation;
        }

        return null;
    }

    @Override
    public org.example.ipdp.proiect.misc.Relation updateRelation(org.example.ipdp.proiect.misc.Relation oldRelation, org.example.ipdp.proiect.misc.Relation newRelation) {
        return null;
    }

    @Override
    public org.example.ipdp.proiect.misc.DataModel getDataModel() {
        return null;
    }

    @Override
    public boolean applyActions(List<org.example.ipdp.proiect.misc.IAction> actions) {
        for (org.example.ipdp.proiect.misc.IAction action : actions) {
            action.applyStorage(this);
        }

        return true;
    }
}
