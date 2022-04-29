package org.example.ipdp.proiect.backend;

import org.apache.cayenne.query.ObjectSelect;
import org.example.ipdp.proiect.backend.orm.Attribute;
import org.example.ipdp.proiect.backend.orm.Entity;
import org.example.ipdp.proiect.backend.orm.Relationship;
import org.example.ipdp.proiect.misc.Relation;

import java.util.List;

public class BackendHelper {
    protected Backend backend;

    public BackendHelper(Backend backend){
        this.backend = backend;
    }

    public Entity getEntityByName(String entityName) {
        return ObjectSelect.query(Entity.class)
                .where(Entity.NAME.eq(entityName))
                .selectFirst(backend.context);
    }

    public List<Attribute> getEntityAttributes(String entityName) {
        return this.getEntityByName(entityName).getAttributes();
    }

    public List<Relationship> getRelations() {
        return ObjectSelect.query(Relationship.class).select(backend.context);
    }

    public Relationship getRelation(Relation relation) {
        Entity firstEntity = getEntityByName(relation.getFirstEntity().getEntityName());
        Entity secondEntity = getEntityByName(relation.getSecondEntity().getEntityName());

        return ObjectSelect.query(Relationship.class)
                .where(Relationship.FIRST_MEMBER.eq(firstEntity).andExp(Relationship.SECOND_MEMEBR.eq(secondEntity)))
                .selectFirst(backend.context);
    }
}
