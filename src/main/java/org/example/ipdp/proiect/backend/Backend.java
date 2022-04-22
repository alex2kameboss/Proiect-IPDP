package org.example.ipdp.proiect.backend;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;
import org.example.ipdp.proiect.backend.orm.*;

import java.util.List;

public class Backend implements org.example.ipdp.proiect.misc.IStorage {
    protected ServerRuntime cayenneRuntime;
    protected ObjectContext context;

    public Backend() {
         cayenneRuntime = ServerRuntime.builder()
                .addConfig("cayenne-test.xml")
                .build();
         context = cayenneRuntime.newContext();
    }

    @Override
    public void addEntity(org.example.ipdp.proiect.misc.Entity entity) {
        Entity newEntity = context.newObject(Entity.class);
        newEntity.setName(entity.getEntityName());
        context.commitChanges();
    }

    @Override
    public org.example.ipdp.proiect.misc.Entity removeEntity(org.example.ipdp.proiect.misc.Entity entity) {
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
        return null;
    }

    @Override
    public org.example.ipdp.proiect.misc.Attribute updateAttribute(String parentName, org.example.ipdp.proiect.misc.Attribute oldAttribute, org.example.ipdp.proiect.misc.Attribute newAttribute) {
        return null;
    }

    @Override
    public void addRelation(org.example.ipdp.proiect.misc.Relation relation) {

    }

    @Override
    public org.example.ipdp.proiect.misc.Relation removeRelation(org.example.ipdp.proiect.misc.Relation relation) {
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
        return false;
    }
}
