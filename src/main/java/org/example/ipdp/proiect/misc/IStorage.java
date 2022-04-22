package org.example.ipdp.proiect.misc;

import org.example.ipdp.proiect.misc.Attribute;
import org.example.ipdp.proiect.misc.DataModel;
import org.example.ipdp.proiect.misc.Entity;
import org.example.ipdp.proiect.misc.Relation;

public interface IStorage {
    public void addEntity(Entity entity);

    public Entity removeEntity(Entity entity);

    public Entity updateEntity(Entity oldEntity, Entity newEntity);

    public void addAttribute(String parent, Attribute attribute);

    public Attribute removeAttribute(String parentName, Attribute attribute);

    public Attribute updateAttribute(String parentName, Attribute oldAttribute, Attribute newAttribute);

    public void addRelation(Relation relation);

    public Relation removeRelation(Relation relation);

    public Relation updateRelation(Relation oldRelation, Relation newRelation);

    public DataModel getDataModel();
}
