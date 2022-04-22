package org.example.ipdp.proiect.misc;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class DataModel {
    protected HashMap<String, Entity> entities;
    protected LinkedList<Relation> relations;

    public DataModel() {
        this.entities = new HashMap<>();
        this.relations = new LinkedList<>();
    }

    public DataModel(@NotNull final DataModel dataModel) {
        this.entities = new HashMap<>();
        this.relations = new LinkedList<>();

        Collection<Entity> entries = dataModel.entities.values();
        for (Entity entity : entries) {
            this.addEntity(new Entity(entity));
        }

        for (Relation relation : relations) {
            this.addRelation(new Relation(
                    this.getEntity(relation.getFirstEntity().getEntityName()),
                    this.getEntity(relation.getSecondEntity().getEntityName()),
                    relation.getRelationTypes()
            ));
        }
    }

    public void addEntity(Entity entity) {
        entities.put(entity.getEntityName(), entity);
    }

    public Entity getEntity(String entityName){
        return entities.get(entityName);
    }

    public Entity removeEntity(String entityName){
        if (entities.containsKey(entityName)) {
            return entities.remove(entityName);
        } else {
            return null;
        }
    }

    public void addRelation(Relation relation) {
        relations.add(relation);
    }

    public Relation removeRelation(Entity firstEntity, Entity secondEntity){
        Relation goldRelation = new Relation(firstEntity, secondEntity, RelationTypes.one_to_one);

        ListIterator<Relation> iterator = relations.listIterator();
        boolean isInList = false;
        Relation relation = null;

        while (iterator.hasNext()) {
            relation = iterator.next();
            if (goldRelation.equals(relation)) {
                isInList = true;
                break;
            }
        }

        if (isInList) {
            relations.remove(relation);
            return relation;
        } else {
            return null;
        }
    }
}
